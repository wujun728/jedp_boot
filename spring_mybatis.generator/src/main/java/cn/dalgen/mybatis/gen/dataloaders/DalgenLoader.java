package cn.dalgen.mybatis.gen.dataloaders;

import java.io.File;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.dalgen.mybatis.gen.common.FileNameSelector;
import cn.dalgen.mybatis.gen.enums.PagingCntTypeEnum;
import cn.dalgen.mybatis.gen.enums.ParamTypeEnum;
import cn.dalgen.mybatis.gen.enums.TypeMapEnum;
import cn.dalgen.mybatis.gen.model.Gen;
import cn.dalgen.mybatis.gen.model.config.CfOperation;
import cn.dalgen.mybatis.gen.model.config.CfResultMap;
import cn.dalgen.mybatis.gen.model.config.CfTable;
import cn.dalgen.mybatis.gen.model.dbtable.Column;
import cn.dalgen.mybatis.gen.model.dbtable.Table;
import cn.dalgen.mybatis.gen.model.java.Base;
import cn.dalgen.mybatis.gen.model.java.DAO;
import cn.dalgen.mybatis.gen.model.java.DO;
import cn.dalgen.mybatis.gen.model.java.DOMapper;
import cn.dalgen.mybatis.gen.model.java.Filelds;
import cn.dalgen.mybatis.gen.model.java.Paging;
import cn.dalgen.mybatis.gen.model.java.ResultMap;
import cn.dalgen.mybatis.gen.model.java.XmlMapper;
import cn.dalgen.mybatis.gen.model.java.domapper.DOMapperMethod;
import cn.dalgen.mybatis.gen.model.repository.CfTableRepository;
import cn.dalgen.mybatis.gen.model.repository.TableRepository;
import cn.dalgen.mybatis.gen.utils.CamelCaseUtils;
import cn.dalgen.mybatis.gen.utils.StringDalUtil;
import cn.dalgen.mybatis.gen.enums.MultiplicityEnum;
import cn.dalgen.mybatis.gen.model.config.CfColumn;
import cn.dalgen.mybatis.gen.model.java.domapper.DOMapperMethodParam;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;

/**
 * Created by bangis.wangdf on 15/12/3. Desc
 */
public class DalgenLoader extends AbstractDalgenLoader {
    /**
     * The constant LOG.
     */
    private static final Log               LOG               = new SystemStreamLog();
    /**
     * The constant RESULT_MANY.
     */
    private static final String            RESULT_MANY       = "List<{0}>";
    /**
     * The Table repository.
     */
    private              TableRepository   tableRepository   = new TableRepository();
    /**
     * The Gen xml file repository.
     */
    private              CfTableRepository cfTableRepository = new CfTableRepository();

    /**
     * Load.
     *
     * @param gen        the gen
     * @param connection the connection
     * @param tablesFile the tables file
     * @throws Exception the exception
     */
    @Override
    public void load(Gen gen, Connection connection, File tablesFile) throws Exception {
        String cmd = ConfigUtil.getCmd();
        //解析所有table.xml(为生成sqlMap.xml做准备)
        Map<String, CfTable> cfTableMap = Maps.newHashMap();
        Map<String, DefaultElement> xmltableMap = Maps.newHashMap();
        Map<String, Element> cfSqlMap = Maps.newHashMap();

        SAXReader saxReader = new SAXReader();
        // ignore dtd
        saxReader.setEntityResolver(new IgnoreDTDEntityResolver());

        for (File tableFile : tablesFile.listFiles(new FileNameSelector("xml"))) {
            Document document = saxReader.read(tableFile);

            DefaultElement tableElement = (DefaultElement)document.getRootElement();

            String tableName = StringUtils.upperCase(tableElement.attributeValue("sqlname"));

            xmltableMap.put(tableName, tableElement);

            List<Element> sqlElements = tableElement.elements("sql");
            if (CollectionUtils.isNotEmpty(sqlElements)) {
                for (Element sqlElement : sqlElements) {
                    cfSqlMap.put(tableName + "." + sqlElement.attributeValue("id"), sqlElement);
                }
            }
        }
        for (Entry<String, DefaultElement> elementEntry : xmltableMap.entrySet()) {
            cfTableMap.put(elementEntry.getKey(), cfTableRepository.gainCfTable(elementEntry.getValue(), cfSqlMap));
        }

        //存在的模板文件内容
        gen.setTmpCfTables(Lists.newArrayList(cfTableMap.values()));

        //获取到所有表相关信息
        for (String tbName : cfTableMap.keySet()) {
            Table table = tableRepository.gainTable(connection, tbName, cfTableMap.get(tbName));
            gen.addTmpTable(table);
        }

        List<String> needGenTableNames = preNeedGenTableNames(cmd, cfTableMap);

        //获取需要重新生成的表(为重新生成Mapper.xml,DO,Mapper.java 准备)
        Map<String, Table> tableMap = Maps.newHashMap();

        for (String tbName : needGenTableNames) {
            tableMap.put(StringUtils.upperCase(tbName),
                tableRepository.gainTable(connection, tbName, cfTableMap.get(tbName)));
        }

        //根据需要重新生成的表 准备数据
        for (String tbName : needGenTableNames) {
            CfTable cfTable = cfTableMap.get(tbName);
            Table table = tableMap.get(tbName);
            gen.addTable(table);
            //准备DO
            DO doClass = preDo(table, cfTable.getColumns());
            gen.addDO(doClass);

            //准备Mapper.xml
            XmlMapper xmlMapper = new XmlMapper();
            //准备resultMap对应的对象
            Map<String, Column> tbColumMap = Maps.newHashMap();
            for (Column column : table.getColumnList()) {
                tbColumMap.put(column.getSqlName(), column);
            }
            Map<String, Filelds> fileldsMap = Maps.newHashMap();
            for (Filelds filelds : doClass.getFieldses()) {
                fileldsMap.put(filelds.getName(), filelds);
            }
            Map<String, ResultMap> resultMaps = Maps.newHashMap();

            //preResultMap
            preResultMap(gen, tbName, cfTable, table, xmlMapper, tbColumMap, fileldsMap,
                resultMaps);

            xmlMapper.setSqlMap(cfTable.getSqlMap());

            //准备Mapper接口
            DOMapper doMapper = preDOMapper(gen, cfTable, table, doClass, resultMaps);
            gen.addDOMapper(doMapper);

            //准备DAO类
            DAO dao = preDAO(gen, cfTable, table, doClass, resultMaps);
            getClassAndImport(dao, doMapper.getPackageName() + "." + doMapper.getClassName(),
                "load1");
            getClassAndImport(doClass, doClass.getPackageName() + "." + doClass.getClassName(),
                "load2");
            dao.setDoMapper(doMapper);
            dao.setaDo(doClass);
            gen.addDao(dao);
            //准备MapperService

            //替换refid 为正式 mapper.xml中refid
            replaceRefid(cfSqlMap, cfTable, doMapper);
            xmlMapper.setCfTable(cfTable);
            xmlMapper.setDoClass(doClass);
            xmlMapper.setDoMapper(doMapper);
            xmlMapper.setTable(table);

            gen.addXmlMapper(xmlMapper);


        }
    }

    /**
     * 替换refid 为正式 mapper.xml中refid
     *
     * @param cfSqlMap
     * @param cfTable
     * @param doMapper
     */
    private void replaceRefid(Map<String, Element> cfSqlMap, CfTable cfTable, DOMapper doMapper) {
        if (CollectionUtils.isNotEmpty(cfTable.getOperations())) {
            for (CfOperation cfOperation : cfTable.getOperations()) {
                String cdata = cfOperation.getCdata();

                if (MapUtils.isNotEmpty(cfSqlMap)) {
                    for (String otTableRefid : cfSqlMap.keySet()) {
                        String[] splits = StringUtils.split(otTableRefid, ".", 2);

                        String className = null;
                        String logicName = splits[0];

                        String _pre = "";
                        for (String pre : ConfigUtil.getCurrentDb().getTablePrefixs().keySet()) {
                            //取最长的
                            if (StringUtils.startsWith(logicName, StringUtils.upperCase(pre)) && pre.length() > _pre
                                .length()) {
                                _pre = pre;

                            }
                        }
                        if (StringUtils.isNotBlank(_pre)) {
                            //删除 or 替换
                            String toTableName = ConfigUtil.getCurrentDb().getTablePrefixs().get(_pre)
                                + StringUtils.substring(logicName, _pre.length());
                            className = CamelCaseUtils.toCapitalizeCamelCase(toTableName);
                        }
                        if (StringUtils.isBlank(className)) {
                            className = CamelCaseUtils.toCapitalizeCamelCase(logicName);
                        }

                        String relNameSpace = doMapper.getPackageName() + "." + className + "DOMapper";
                        cdata = cdata.replaceAll("refid=\"" + otTableRefid,
                            "refid=\"" + relNameSpace + "." + splits[1]);
                    }
                }
                cfOperation.setCdata(cdata);
            }
        }
    }

    /**
     * Pre need gen table names list.
     *
     * @param cmd        the cmd
     * @param cfTableMap the cf table map
     * @return the list
     */
    private List<String> preNeedGenTableNames(String cmd, Map<String, CfTable> cfTableMap) {
        List<String> needGenTableNames = Lists.newArrayList();
        if (StringUtils.equals(StringUtils.trim(cmd), "*")) {
            needGenTableNames = Lists.newArrayList(cfTableMap.keySet());
        } else {

            for (String tableName : Lists
                .newArrayList(StringUtils.split(StringUtils.upperCase(cmd), ";"))) {
                boolean flag = true;
                for (String splitTableSuffix : ConfigUtil.getConfig().getSplitTableSuffixs()) {
                    if (StringUtils.endsWithIgnoreCase(tableName, splitTableSuffix)) {
                        needGenTableNames.add(StringUtils.replace(tableName, splitTableSuffix, ""));
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    needGenTableNames.add(tableName);
                }
            }
        }
        return needGenTableNames;
    }

    /**
     * Pre result map.
     *
     * @param gen        the gen
     * @param tbName     the tb name
     * @param cfTable    the cf table
     * @param table      the table
     * @param xmlMapper  the xml mapper
     * @param tbColumMap the tb colum map
     * @param fileldsMap the filelds map
     * @param resultMaps the result maps
     */
    private void preResultMap(Gen gen, String tbName, CfTable cfTable, Table table,
                              XmlMapper xmlMapper, Map<String, Column> tbColumMap,
                              Map<String, Filelds> fileldsMap, Map<String, ResultMap> resultMaps) {
        for (CfResultMap cfResultMap : cfTable.getResultMaps()) {
            ResultMap resultMap = new ResultMap();
            resultMap.setTableName(table.getSqlName());
            resultMap.setName(cfResultMap.getName());
            resultMap.setType(cfResultMap.getType());
            resultMap.setExtend(cfResultMap.getExtend());
            resultMap.setClassName(cfResultMap.getType());
            resultMap.setPackageName(ConfigUtil.getCurrentDb().getGenPackage("resultmap"));
            resultMap.setDesc(cfResultMap.getRemark());
            if (CollectionUtils.isNotEmpty(cfResultMap.getImports())) {
                for (String classz : cfResultMap.getImports()) {
                    resultMap.addImport(classz);
                }
            }
            //字段设置
            for (CfColumn cfColumn : cfResultMap.getColumns()) {
                Validate.notEmpty(cfColumn.getName(),
                    tbName + "xml 配置有误 DalgenLoader.preResultMap Gen=" + gen);
                Column column = new Column();
                column.setJavaName(CamelCaseUtils.toCamelCase(cfColumn.getName()));
                column.setJavaType(cfColumn.getJavatype());
                column.setSqlName(cfColumn.getName());
                column.setSqlType(cfColumn.getSqlType());
                column.setTestVal(cfColumn.getTestVal());
                column.setRemarks(cfColumn.getRemark());
                resultMap.addColumn(column);
                resultMap.addImport(cfColumn.getJavatype());
                Filelds filelds = new Filelds();
                filelds.setJavaType(
                    getClassAndImport(resultMap, column.getJavaType(), column.getSqlName()));
                filelds.setName(column.getJavaName());
                filelds.setDesc(column.getRemarks());
                filelds.setTestVal(cfColumn.getTestVal());

                resultMap.addFields(filelds);
            }
            //一对一设置
            resultMap.setAssociations(cfResultMap.getAssociations());
            //一对多设置
            resultMap.setCollections(cfResultMap.getCollections());

            resultMaps.put(cfResultMap.getName(), resultMap);
            xmlMapper.addResultMap(resultMap);
            gen.addResultMap(resultMap);
        }
    }

    /**
     * Pre dao dao.
     *
     * @param gen        the gen
     * @param cfTable    the cf table
     * @param table      the table
     * @param doClass    the do class
     * @param resultMaps the result maps
     * @return the dao
     */
    private DAO preDAO(Gen gen, CfTable cfTable, Table table, DO doClass,
                       Map<String, ResultMap> resultMaps) {
        DAO dao = new DAO();
        String daoNaming = ConfigUtil.getConfig().getExtParam("DaoNaming");
        String daoClazzName = daoNaming.replace("[EntityName]", table.getJavaName());
        dao.setClassName(daoClazzName);

        dao.setPackageName(ConfigUtil.getCurrentDb().getGenPackage("dao"));

        dao.setDesc(cfTable.getRemark());
        dao.setTableName(cfTable.getSqlname());

        Map<String, String> columnTypeMap = Maps.newHashMap();
        Map<String, String> columnDescMap = Maps.newHashMap();
        for (Column column : table.getColumnList()) {
            columnTypeMap.put(column.getJavaName(), column.getJavaType());
            columnDescMap.put(column.getJavaName(), column.getRemarks());
        }

        for (CfOperation operation : cfTable.getOperations()) {
            preDAOMethod(doClass, resultMaps, dao, operation, columnTypeMap,
                cfTable.getOperations());
        }
        return dao;
    }

    /**
     * Pre dao method.
     *
     * @param doClass    the do class
     * @param resultMaps the result maps
     * @param dao        the dao
     * @param operation  the operation
     * @param columnMap  the column map
     */
    private void preDAOMethod(DO doClass, Map<String, ResultMap> resultMaps, DAO dao,
                              CfOperation operation, Map<String, String> columnMap,
                              List<CfOperation> cfOperations) {

        DOMapperMethod method = new DOMapperMethod();
        method.setName(operation.getName());
        method.setDesc(operation.getRemark());
        method.setSql(operation.getSqlDesc());

        String resultType;

        if (CollectionUtils.isNotEmpty(cfOperations)) {
            for (CfOperation cfOperation : cfOperations) {
                if (StringUtils.equals(operation.getName(), cfOperation.getPagingCntOperation())) {
                    return;
                }
            }

        }

        if (operation.getMultiplicity() == MultiplicityEnum.paging) {
            Paging paging = new Paging();
            paging.setClassName(StringDalUtil.upperFirst(operation.getPaging()) + "Page");

            paging.setPackageName(ConfigUtil.getCurrentDb().getGenPackage("paging"));
            resultType = getClassAndImport(dao,
                paging.getPackageName() + "." + paging.getClassName(), operation.getName());
            DOMapperMethodParam param = new DOMapperMethodParam(resultType,
                StringDalUtil.lowerFirst(operation.getPaging()), null);
            method.setPagingFlag("true");
            if (operation.getPagingCntType() == PagingCntTypeEnum.pagingCustom) {
                method.setPagingCustomFlag("true");
            }
            method.setPagingCntOperation(operation.getPagingCntOperation());
            method.addParam(param);
        } else {
            preMethodParam(doClass, dao, operation, method, columnMap, null);
            resultType = operationResultType(doClass, dao, operation, resultMaps);
        }
        method.setReturnClass(resultType);
        method.setListLimit(operation.getListLimit());
        method.setKvMap(operation.getKvMap());
        if (!StringUtils.equals(method.getKvMap(), "false")) {
            method.setMapV(operation.getMapV());
            method.setMapK(operation.getMapK());
            method.setMapKType(getClassAndImport(
                dao, trunColunType(operation.getMapK(),
                    resultMaps.get(operation.getResultmap()), columnMap),
                operation.getName()));
            method.setMapVType(getClassAndImport(
                dao, trunColunType(operation.getMapV(),
                    resultMaps.get(operation.getResultmap()), columnMap),
                operation.getName()));
            getClassAndImport(dao, "java.util.Map", operation.getName());
            getClassAndImport(dao, "java.util.Set", operation.getName());
            getClassAndImport(dao, "java.util.List", operation.getName());
            getClassAndImport(dao, "java.util.LinkedHashMap", operation.getName());
            getClassAndImport(dao, "java.util.ArrayList", operation.getName());
            getClassAndImport(dao, "java.util.HashSet", operation.getName());
        }
        method.setOperationParams(operation.getOperationParams());
        method.setDeprecated(operation.getDeprecated());
        dao.addMothed(method);
    }

    private String trunColunType(String columnName, ResultMap resultMap,
                                 Map<String, String> columnMap) {
        String result = null;
        if (StringUtils.isNotBlank(columnName)) {
            if (resultMap != null) {
                List<Column> columnList = resultMap.getColumnList();
                for (Column column : columnList) {
                    if (StringUtils.equalsIgnoreCase(column.getSqlName(), columnName)) {
                        result = column.getJavaType();
                        break;
                    }
                }
            }
            if (resultMap == null || StringUtils.isBlank(result)) {
                String columnType = columnMap.get(CamelCaseUtils.toCamelCase(columnName));
                String custJavaType = ConfigUtil.getConfig().getTypeMap().get(columnType);
                result = (StringUtils.isNotBlank(custJavaType) ? custJavaType : columnType);
            }
        }
        return result;
    }

    /**
     * Pre do mapper do mapper.
     *
     * @param gen        the gen
     * @param cfTable    the cf table
     * @param table      the table
     * @param doClass    the do class
     * @param resultMaps the result maps
     * @return the do mapper
     */
    private DOMapper preDOMapper(Gen gen, CfTable cfTable, Table table, DO doClass,
                                 Map<String, ResultMap> resultMaps) {
        DOMapper doMapper = new DOMapper();
        String mapperInterfaceNaming = ConfigUtil.getConfig().getExtParam("MapperInterfaceNaming");
        String doMapperClazzName = mapperInterfaceNaming.replace("[EntityName]", table.getJavaName());
        doMapper.setClassName(doMapperClazzName);

        String xmlMapperNaming = ConfigUtil.getConfig().getExtParam("XMLMapperNaming");
        String xmlFileName = xmlMapperNaming.replace("[EntityName]", table.getJavaName());
        doMapper.setXmlFileName(xmlFileName);

        doMapper.setPackageName(ConfigUtil.getCurrentDb().getGenPackage("mapper"));
        doMapper.setXmlPackageName(ConfigUtil.getCurrentDb().getGenPackage("mapper.xml"));
        doMapper.setDesc(cfTable.getRemark());
        doMapper.setTableName(cfTable.getSqlname());

        Map<String, String> columnTypeMap = Maps.newHashMap();
        Map<String, String> columnDescMap = Maps.newHashMap();
        for (Column column : table.getColumnList()) {
            columnTypeMap.put(column.getJavaName(), column.getJavaType());
            columnDescMap.put(column.getJavaName(), column.getRemarks());
        }

        for (CfOperation operation : cfTable.getOperations()) {
            if (operation.getMultiplicity() == MultiplicityEnum.paging) {//分页
                prePagingMethod(gen, cfTable, table, doClass, resultMaps, doMapper, columnTypeMap,
                    columnDescMap, operation);
            } else {
                preMethod(doClass, resultMaps, doMapper, operation, columnTypeMap,
                    cfTable.getOperations());
            }
        }
        return doMapper;
    }

    /**
     * Pre paging method.
     *
     * @param gen           the gen
     * @param cfTable       the cf table
     * @param table         the table
     * @param doClass       the do class
     * @param resultMaps    the result maps
     * @param doMapper      the do mapper
     * @param columnTypeMap the column type map
     * @param columnDescMap the column desc map
     * @param operation     the operation
     */
    private void prePagingMethod(Gen gen, CfTable cfTable, Table table, DO doClass,
                                 Map<String, ResultMap> resultMaps, DOMapper doMapper,
                                 Map<String, String> columnTypeMap,
                                 Map<String, String> columnDescMap, CfOperation operation) {
        DOMapperMethod pagingResultMethod = new DOMapperMethod();
        pagingResultMethod.setName(operation.getName() + "Result");
        pagingResultMethod.setPagingName(operation.getName());
        pagingResultMethod.setDesc(operation.getRemark());
        pagingResultMethod.setSql(operation.getSqlDesc());
        pagingResultMethod.setPagingFlag("true");
        pagingResultMethod.setPagingCntOperation(operation.getPagingCntOperation());

        Paging paging = new Paging();
        paging.setClassName(StringDalUtil.upperFirst(operation.getPaging()) + "Page");
        paging.setPackageName(ConfigUtil.getCurrentDb().getGenPackage("paging"));

        String basePackage = ConfigUtil.getCurrentDb().getGenDalCommonPackage() + "."
            + ConfigUtil.getCurrentDb().getSubPackage("paging");
        if (StringUtils.startsWith(ConfigUtil.getCurrentDb().getSubPackage("paging"), "/")) {
            basePackage = StringUtils.substring(ConfigUtil.getCurrentDb().getSubPackage("paging"),
                1);
        }

        paging.setBasePackageName(basePackage);

        getClassAndImport(paging, paging.getBasePackageName() + ".BasePage", paging.getClassName());
        paging.setDesc(StringDalUtil.join(table.getSqlName(), cfTable.getRemark()));
        paging.setTableName(cfTable.getSqlname());

        String pagingResultType = operationResultType(doClass, paging, operation, resultMaps);

        paging.setResultType(pagingResultType);
        List<DOMapperMethodParam> params = preMethodParams(paging, operation, columnTypeMap);
        for (DOMapperMethodParam param : params) {
            Filelds filelds = new Filelds();
            filelds.setName(param.getParam());
            filelds.setJavaType(param.getParamType());
            filelds.setDesc(columnDescMap.get(param.getParam()));
            paging.addFields(filelds);
        }
        gen.addPaging(paging);
        //paging import到doMapper
        getClassAndImport(doMapper, paging.getPackageName() + "." + paging.getClassName(),
            operation.getName());
        getClassAndImport(doMapper, "java.util.List", operation.getName());
        //方法返回结果

        DOMapperMethodParam pagingParam = new DOMapperMethodParam(paging.getClassName(),
            StringDalUtil.lowerFirst(operation.getPaging()), null);
        pagingResultMethod.addParam(pagingParam);

        String resultType = operationResultType(doClass, doMapper, operation, resultMaps);

        paging.setResultType(resultType);
        pagingResultMethod.setReturnClass("List<" + resultType + ">");
        try {
            if (operation.getPagingCntType() != PagingCntTypeEnum.pagingCustom) {
                DOMapperMethod pagingCountMethod = (DOMapperMethod)BeanUtils
                    .cloneBean(pagingResultMethod);
                pagingCountMethod.setName(operation.getName() + "Count");
                pagingCountMethod.setReturnClass("int");
                doMapper.addMothed(pagingCountMethod);
            }
        } catch (Exception e) {
            LOG.error("", e);
        }
        pagingResultMethod.setOperationParams(operation.getOperationParams());
        pagingResultMethod.setDeprecated(operation.getDeprecated());
        doMapper.addMothed(pagingResultMethod);
    }

    /**
     * Pre method.
     *
     * @param doClass    the do class
     * @param resultMaps the result maps
     * @param doMapper   the do mapper
     * @param operation  the operation
     * @param columnMap  the column map
     */
    private void preMethod(DO doClass, Map<String, ResultMap> resultMaps, DOMapper doMapper,
                           CfOperation operation, Map<String, String> columnMap,
                           List<CfOperation> cfOperations) {
        DOMapperMethod method = new DOMapperMethod();
        method.setName(operation.getName());
        method.setDesc(operation.getRemark());
        method.setSql(operation.getSqlDesc());
        preMethodParam(doClass, doMapper, operation, method, columnMap, cfOperations);
        String resultType = operationResultType(doClass, doMapper, operation, resultMaps);
        method.setReturnClass(resultType);
        method.setListLimit(operation.getListLimit());
        method.setKvMap(operation.getKvMap());
        if (!StringUtils.equals(method.getKvMap(), "false")) {
            method.setMapV(operation.getMapV());
            method.setMapK(operation.getMapK());
            method.setMapKType(getClassAndImport(
                doMapper, trunColunType(operation.getMapK(),
                    resultMaps.get(operation.getResultmap()), columnMap),
                operation.getName()));
            method.setMapVType(getClassAndImport(
                doMapper, trunColunType(operation.getMapV(),
                    resultMaps.get(operation.getResultmap()), columnMap),
                operation.getName()));
            getClassAndImport(doMapper, "java.util.Map", operation.getName());
            getClassAndImport(doMapper, "java.util.List", operation.getName());
        }
        method.setOperationParams(operation.getOperationParams());
        doMapper.addMothed(method);
    }

    /**
     * Pre method param.
     *
     * @param doClass   the do class
     * @param doMapper  the do mapper
     * @param operation the operation
     * @param method    the method
     * @param columnMap the column map
     */
    private void preMethodParam(DO doClass, Base doMapper, CfOperation operation,
                                DOMapperMethod method, Map<String, String> columnMap,
                                List<CfOperation> cfOperations) {
        boolean isPagingCount = false;
        //判断是不是别的表的pagingCountSql
        if (CollectionUtils.isNotEmpty(cfOperations)) {
            for (CfOperation cfOperation : cfOperations) {
                if (StringUtils.equals(cfOperation.getPagingCntOperation(), operation.getName())) {
                    isPagingCount = true;
                    method.addParam(new DOMapperMethodParam(
                        StringDalUtil.upperFirst(cfOperation.getPaging()) + "Page",
                        StringDalUtil.lowerFirst(cfOperation.getPaging()), null));
                    break;
                }
            }
        }
        if (!isPagingCount) {
            if (operation.getParamType() == ParamTypeEnum.object) {
                method.addParam(new DOMapperMethodParam(getClassAndImport(doMapper,
                    doClass.getPackageName() + "." + doClass.getClassName(), "_preMethodParam"),
                    "entity", null));
            } else if (operation.getParamType() == ParamTypeEnum.objectList) {
                getClassAndImport(doMapper, doClass.getPackageName() + "." + doClass.getClassName(),
                    operation.getName());
                getClassAndImport(doMapper, "java.util.List", operation.getName());
                method.addParam(new DOMapperMethodParam("List<" + doClass.getClassName() + ">",
                    "list", null));
            } else {
                method.setParams(preMethodParams(doMapper, operation, columnMap));
            }
        }
    }

    /**
     * Pre method params list.
     *
     * @param doMapper  the do mapper
     * @param operation the operation
     * @param columnMap the column map
     * @return the list
     */
    private List<DOMapperMethodParam> preMethodParams(Base doMapper, CfOperation operation,
                                                      Map<String, String> columnMap) {
        List<DOMapperMethodParam> params = Lists.newArrayList();
        List<String> foreachTmp = Lists.newArrayList();
        for (Map.Entry pm : operation.getPrimitiveParams().entrySet()) {
            String pmName = (String)pm.getKey();
            String pmType = (String)pm.getValue();
            //如果是DO中的属性 则不需要在处理
            DOMapperMethodParam methodParam = null;
            if (StringUtils.contains(pmName, ".")
                || operation.getPrimitiveForeachOtherParams().containsKey("list_" + pmName)) {
                final String[] splits = StringUtils.split(pmName, ".");
                if (foreachTmp.contains(splits[0])) {
                    continue;
                } else {
                    foreachTmp.add(splits[0]);
                }
                List<String> foreachName_javaType = operation.getPrimitiveForeachOtherParams()
                    .get("list_" + splits[0]);

                if (CollectionUtils.isNotEmpty(foreachName_javaType)) {
                    String foreachJavaType = getClassAndImport(doMapper, foreachName_javaType.get(1),
                        "_preMethodParams");
                    getClassAndImport(doMapper, "java.util.List", "_preMethodParams");
                    methodParam = new DOMapperMethodParam("List<" + foreachJavaType + ">",
                        foreachName_javaType.get(0),
                        operation.getPrimitiveParamTestVals().get(pmName));
                }
            } else {

                String columnType = columnMap.get(pmName);
                if (StringUtils.startsWith(pmName, "old")) {
                    String xpnName = pmName.substring(3);
                    columnType = columnMap
                        .get(xpnName.substring(0, 1).toLowerCase() + xpnName.substring(1));

                }

                TypeMapEnum typeMapEnum = TypeMapEnum.getByJdbcTypeWithOther(pmType);
                String paramValType;

                if (typeMapEnum == TypeMapEnum.OTHER) {
                    paramValType = pmType;
                } else if (StringUtils.isBlank(columnType)) {
                    paramValType = typeMapEnum.getJavaType();
                } else {
                    paramValType = columnType;
                }

                String custJavaType = ConfigUtil.getConfig().getTypeMap().get(paramValType);

                String paramType = getClassAndImport(doMapper,
                    custJavaType == null ? paramValType : custJavaType,
                    operation.getName() + "_" + pmName);

                String foreachName = operation.getPrimitiveForeachParams().get(pmName);

                if (StringUtils.isBlank(foreachName)) {
                    methodParam = new DOMapperMethodParam(paramType, pmName,
                        operation.getPrimitiveParamTestVals().get(pmName));
                } else {
                    if (!StringUtils.contains(foreachName, ".")) {
                        getClassAndImport(doMapper, "java.util.List", "_preMethodParams");
                        methodParam = new DOMapperMethodParam("List<" + paramType + ">", foreachName,
                            operation.getPrimitiveParamTestVals().get(pmName));
                    }
                }
            }
            if (methodParam != null) {
                params.add(methodParam);
            }
        }
        return params;
    }

    /**
     * Operation result type string.
     *
     * @param doClass    the do class
     * @param base       the do mapper
     * @param operation  the operation
     * @param resultMaps the result maps
     * @return the string
     */
    private String operationResultType(DO doClass, Base base, CfOperation operation,
                                       Map<String, ResultMap> resultMaps) {

        if (StringUtils.startsWithIgnoreCase(operation.getName(), "insert")
            || StringUtils.startsWithIgnoreCase(operation.getName(), "update")
            || StringUtils.startsWithIgnoreCase(operation.getName(), "delete")) {
            if (StringUtils.isNotBlank(ConfigUtil.getConfig().getExtParam("IUD"))) {
                return ConfigUtil.getConfig().getExtParam("IUD");
            }
            return "int";
        }
        //返回类不为null
        String resultType;
        if (!StringUtils.isBlank(operation.getResulttype())) {
            resultType = getClassAndImport(base, operation.getResulttype(), operation.getName());
        } else if (!StringUtils.isBlank(operation.getResultmap()) && !StringUtils.equals(operation.getResultmap(),
            "BaseResultMap")) {
            ResultMap resultMap = resultMaps.get(operation.getResultmap());
            Validate.notNull(resultMap, "DalgenLoader.operationResultType 自定义ResultMap出错 table = "
                + doClass.getTableName() + " DO=" + doClass);
            resultType = getClassAndImport(base,
                resultMap.getPackageName() + "." + resultMap.getClassName(),
                operation.getName());
        } else {
            resultType = getClassAndImport(base,
                doClass.getPackageName() + "." + doClass.getClassName(), operation.getName());
        }

        //返回一行
        if (MultiplicityEnum.many == operation.getMultiplicity()) {
            base.addImport("java.util.List");
            return MessageFormat.format(RESULT_MANY, resultType);
        }
        return resultType;
    }

    /**
     * Pre do do.
     *
     * @param table     the table
     * @param cfColumns the cf columns
     * @return the do
     */
    private DO preDo(Table table, List<CfColumn> cfColumns) {
        DO doClass = new DO();
        String entityNaming = ConfigUtil.getConfig().getExtParam("EntityNaming");
        String doClazzName = entityNaming.replace("[EntityName]", table.getJavaName());
        doClass.setClassName(doClazzName);

        doClass.setPackageName(ConfigUtil.getCurrentDb().getGenPackage("dataobject"));
        doClass.setDesc(table.getRemark());
        doClass.setTableName(table.getSqlName());

        //不在DO中输出地字段
        List<String> rldcList = Lists.newArrayList();
        for (CfColumn cfColumn : cfColumns) {
            if (!StringUtils.isBlank(cfColumn.getRelatedColumn())) {
                rldcList.add(cfColumn.getRelatedColumn());
            }
        }

        for (Column column : table.getColumnList()) {
            //提出不需要在DO中出现的字段
            if (!rldcList.contains(column.getSqlName())) {
                Filelds filelds = new Filelds();
                filelds.setName(column.getJavaName());
                filelds.setDesc(column.getRemarks());
                filelds.setSqlName(column.getSqlName());
                filelds.setTestVal(column.getTestVal());
                filelds.setJavaType(
                    getClassAndImport(doClass, column.getJavaType(), column.getSqlName()));
                doClass.addFields(filelds);
            }
        }
        return doClass;
    }

    /**
     * Gets class and import.
     *
     * @param base      the base
     * @param classType the class type
     * @return the class and import
     */
    private String getClassAndImport(Base base, String classType, String clumnOrOption) {
        if (StringUtils.isBlank(classType)) {
            return null;
        }
        int lastIdx = StringUtils.lastIndexOf(classType, ".");
        if (lastIdx > 0) {
            base.addImport(classType);
        }
        //返回方法
        if (classType.indexOf("<") > 0 && classType.indexOf(".") > 0) {
            String _importClass = classType.substring(0, classType.indexOf("<"));
            _importClass = StringUtils.substring(_importClass, StringUtils.lastIndexOf(_importClass, ".") + 1);
            //处理<>内部的类
            String __importClass = classType.substring(classType.indexOf("<") + 1, classType.indexOf(">"));
            if (StringUtils.isNotBlank(__importClass) && __importClass.contains(".")) {
                __importClass = StringUtils.substring(__importClass, StringUtils.lastIndexOf(__importClass, ".") + 1);
            }
            return _importClass + "<" + __importClass + ">";
        } else {
            return StringUtils.substring(classType, lastIdx + 1);
        }
    }

}
