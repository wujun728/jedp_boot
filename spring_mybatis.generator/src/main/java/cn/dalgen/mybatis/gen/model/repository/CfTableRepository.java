package cn.dalgen.mybatis.gen.model.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.dalgen.mybatis.gen.enums.MultiplicityEnum;
import cn.dalgen.mybatis.gen.enums.PagingCntTypeEnum;
import cn.dalgen.mybatis.gen.enums.ParamTypeEnum;
import cn.dalgen.mybatis.gen.exception.DalgenException;
import cn.dalgen.mybatis.gen.model.config.CfAssociation;
import cn.dalgen.mybatis.gen.model.config.CfCollection;
import cn.dalgen.mybatis.gen.model.config.CfColumn;
import cn.dalgen.mybatis.gen.model.config.CfOperation;
import cn.dalgen.mybatis.gen.model.config.CfResultMap;
import cn.dalgen.mybatis.gen.model.config.CfTable;
import cn.dalgen.mybatis.gen.utils.CamelCaseUtils;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;
import cn.dalgen.mybatis.gen.utils.SysUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import sun.security.provider.MD5;

/**
 * SQL模板 文件解析
 *
 * @author bangis.wangdf
 * @date 15/12/6
 */
public class CfTableRepository {
    /**
     * The constant PARAM_PATTERN.
     */
    private static final Pattern PARAM_PATTERN = Pattern.compile("#\\{(.*?)\\}");

    /**
     * The constant STAR_BRACKET. 将 select * from 替换的正则
     */
    private static final Pattern STAR_BRACKET = Pattern.compile("\\((\\s*\\*\\s*)\\)");

    /**
     * 匹配?参数
     */
    private static final Pattern QUESTION_MARK_PATTERN = Pattern.compile("\\w+\\s*=\\s*\\?");

    private static final String JAVA_TYPE = "javatype\\s*=\\s*\".*?\"";

    /**
     * 从?参数中获取 column参数
     */
    private static final Pattern QUESTION_COLUMN_PATTERN = Pattern.compile("\\w{1,}");

    /**
     * The constant FOR_DESC_SQL_P. 为mapper.java的方法准备注释
     */
    private static final String FOR_DESC_SQL_P  = "\\s*<.*>\\s*";
    /**
     * The constant FOR_DESC_SQL_PN.
     */
    private static final String FOR_DESC_SQL_PN = "\\s{2,}";

    private static final String COUNT_X = "count\\(\\s*\\*\\s*\\)";

    /**
     * The constant ORDER_BY_PATTERN.
     */
    private static final String  ORDER_BY_PATTERN    = "[o|O][r|R][d|D][e|E][r|R]\\s+[b|B][y|Y]\\s+";
    /**
     * The constant SELECT_FROM_PATTERN. 正则表达式,贪婪匹配,勉强匹配 .* 贪婪 .*? 勉强,之匹配最近一个
     */
    private static final Pattern SELECT_FROM_PATTERN = Pattern
        .compile("[s|S][e|E][l|L][e|E][c|C][t|T]\\s+[\\s\\S]*?\\s+[f|F][r|R][o|O][m|M]");

    private static final Pattern MYCAT_HINT_PATTERN = Pattern.compile("(\\/\\*[\\*#!]mycat:.*?\\/)");

    /**
     * Gain cf table cf table.
     *
     * @param table the table file
     * @return the cf table
     * @throws DocumentException the document exception
     */
    public CfTable gainCfTable(DefaultElement table, Map<String, Element> cfSqlMap) throws DocumentException {
        CfTable cfTable = new CfTable();

        cfTable.setSqlname(attr(table, "sqlname"));
        cfTable.setPhysicalName(attr(table, "physicalName"));
        cfTable.setRemark(attr(table, "remark"));
        cfTable.setSequence(attr(table, "sequence"));
        cfTable.setOrdinalEffectiveDay(attr(table, "ordinalEffectiveDay"));
        cfTable.setOrdinalMaxPosition(attrLong(table, "ordinalMaxPosition"));
        fillColumns(cfTable, table);

        fillResultMap(cfTable, table);

        fillOperation(cfTable, table, cfSqlMap);

        fillSql(cfTable, table);

        return cfTable;
    }

    private void fillSql(CfTable cfTable, DefaultElement table) {
        List<Element> elements = table.elements("sql");
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element element : elements) {
                cfTable.addSqlMap(element.attributeValue("id"), element.asXML());
            }
        }
    }

    /**
     * Fill result map.
     *
     * @param cfTable the cf table
     * @param table   the table
     */
    private void fillResultMap(CfTable cfTable, Element table) {
        List<Element> elements = table.elements("resultmap");
        if (CollectionUtils.isNotEmpty(elements)) {
            for (Element e : elements) {
                CfResultMap cfResultMap = new CfResultMap();
                cfResultMap.setName(attr(e, "name"));
                cfResultMap.setType(attr(e, "type"));
                cfResultMap.setRemark(attr(e, "remark"));
                final String extend = attr(e, "extend");
                if (StringUtils.isNotBlank(extend)) {
                    cfResultMap.setExtend(extend);
                }
                List<Element> ers = e.elements();
                for (Element er : ers) {
                    if (StringUtils.equals(er.getName(), "column")) {
                        CfColumn cfColumn = new CfColumn();
                        cfColumn.setName(attr(er, "name"));
                        cfColumn.setJavatype(attr(er, "javatype"));
                        cfColumn.setSqlType(attr(er, "jdbctype"));
                        cfColumn.setRemark(attr(er, "remark"));
                        cfColumn.setRelatedColumn(attr(er, "relatedColumn"));

                        cfResultMap.addColumn(cfColumn);
                    } else if (StringUtils.equals(er.getName(), "association")) {
                        CfAssociation cfAssociation = new CfAssociation();
                        cfAssociation.setColumn(attr(er, "column"));
                        cfAssociation.setProperty(attr(er, "property"));
                        cfAssociation.setSelect(attr(er, "select"));
                        cfAssociation.setRemark(attr(er, "remark"));
                        cfResultMap.addAssociation(cfAssociation);
                    } else if (StringUtils.equals(er.getName(), "collection")) {
                        CfCollection cfCollection = new CfCollection();
                        cfCollection.setColumn(attr(er, "column"));
                        cfCollection.setProperty(attr(er, "property"));
                        cfCollection.setSelect(attr(er, "select"));
                        cfCollection.setRemark(attr(er, "remark"));
                        cfResultMap.addCollection(cfCollection);
                    } else if (StringUtils.equals(er.getName(), "import")) {
                        cfResultMap.assImport(attr(er, "class"));
                    }
                }
                cfTable.addResultMap(cfResultMap);
            }
        }
    }

    /**
     * Fill operation.
     *
     * @param cfTable the cf table
     * @param table   the table
     */
    private void fillOperation(CfTable cfTable, DefaultElement table, Map<String, Element> cfSqlMap) {
        List<Element> operations = table.elements("operation");
        for (Element operation : operations) {
            //  System.out.println(SysUtil.EncoderByMd5(operation.asXML()));

            CfOperation cfOperation = new CfOperation();
            cfOperation.setRemark(attr(operation, "remark"));
            cfOperation.setName(attr(operation, "name"));
            cfOperation.setFlushCache(attr(operation, "flushCache"));
            cfOperation.setUseCache(attr(operation, "useCache"));
            cfOperation.setDeprecated(attr(operation, "deprecated"));
            cfOperation.setMultiplicity(MultiplicityEnum.getByCode(attr(operation, "multiplicity")));
            cfOperation.setPagingCntType(PagingCntTypeEnum.getByCode(attr(operation, "pagingCntType")));
            cfOperation.setPaging(attr(operation, "paging"));
            cfOperation.setPagingCntOperation(attr(operation, "pagingCntOperation"));

            cfOperation.setUseGeneratedKeys(attr(operation, "useGeneratedKeys"));
            cfOperation.setKeyProperty(attr(operation, "keyProperty"));
            cfOperation.setAutoGen(attrBoolean(operation, "autoGen"));
            if (cfOperation.getMultiplicity() == MultiplicityEnum.paging) {
                Validate.notEmpty(cfOperation.getPaging(), "需要设置paging,用来生成分页类");
            }
            if (cfOperation.getPagingCntType() == PagingCntTypeEnum.pagingCustom) {
                Validate.notEmpty(cfOperation.getPagingCntOperation(),
                    "需要设置pagingCntSql,获取分页Operation");
                Validate.notEmpty(cfOperation.getPaging(), "需要设置paging,用来生成分页类");
            }
            cfOperation.setParamType(ParamTypeEnum.getByCode(attr(operation, "paramtype")));
            cfOperation.setResultmap(attr(operation, "resultmap"));
            cfOperation.setResulttype(attr(operation, "resulttype"));
            cfOperation.setKvMap(attr(operation, "kvmap"));
            cfOperation.setListLimit(attr(operation, "listLimit"));
            cfOperation.setMapK(attr(operation, "mapK"));
            cfOperation.setMapV(attr(operation, "mapV"));
            cfOperation.setTimeout(attrLong(operation, "timeout"));
            if (!StringUtils.equals(cfOperation.getKvMap(), "false")) {
                Validate.isTrue(cfOperation.getMultiplicity() == MultiplicityEnum.many,
                    "转KvMap要求返回结果必须是many");
                Validate.isTrue(StringUtils.isNotBlank(cfOperation.getMapK()), "转KvMap要求必填mapK");
            }
            Iterator<Attribute> iterator = operation.attributeIterator();
            while (iterator.hasNext()) {
                Attribute attribute = iterator.next();
                cfOperation.addOperationParam(attribute.getName(), attribute.getValue());
            }

            //sql内容
            setCfOperationCdata(cfTable, operation, table, cfOperation, cfSqlMap);

            cfTable.addOperation(cfOperation);
        }
    }

    /**
     * Sets cf operation cdata.
     *
     * @param cfTable     the cf table
     * @param oElement    the e
     * @param cfOperation the cf operation
     */
    private void setCfOperationCdata(CfTable cfTable, Element oElement, DefaultElement table, CfOperation cfOperation,
                                     Map<String, Element> cfSqlMap) {
        final Element extraparams = oElement.element("extraparams");
        oElement.remove(extraparams);

        List<Element> optimizePagings = oElement.elements("optimizePaging");

        String cXml = oElement.asXML();
        String[] lines = StringUtils.split(cXml, "\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < lines.length - 1; i++) {
            if (i > 1) {
                sb.append("\n");
            }
            sb.append(lines[i]);
        }
        String cdata = sb.toString();
        //SQlDESC
        String sqlDesc = cdata.replaceAll(FOR_DESC_SQL_P, " ");
        sqlDesc = sqlDesc.replaceAll(FOR_DESC_SQL_PN, " ");
        cfOperation.setSqlDesc(sqlDesc);
        String text = oElement.getTextTrim();
        if (StringUtils.indexOf(text, "*") > 0) {

            cdata = StringUtils.replace(cdata, "sf.*", "<include refid=\"Base_SF_Column_List\" />");
            cdata = StringUtils.replace(cdata, "SF.*", "<include refid=\"Base_SF_Column_List\" />");
            cdata = StringUtils.replace(cdata, "sF.*", "<include refid=\"Base_SF_Column_List\" />");
            cdata = StringUtils.replace(cdata, "Sf.*", "<include refid=\"Base_SF_Column_List\" />");

            cdata = StringUtils.replace(cdata, "\\\\*", "—-dotXStar--");
            cdata = StringUtils.replace(cdata, ".*", "—-dotStar--");
            cdata = StringUtils.replace(cdata, "/*", "--/Star--");
            cdata = StringUtils.replace(cdata, "*/", "--Star/--");

            cdata = cdata.replaceAll(COUNT_X, "count(!!!COUNT!!!)");

            cdata = StringUtils.replace(cdata, " *", " <include refid=\"Base_Column_List\" />");
            cdata = StringUtils.replace(cdata, "    *",
                "   <include refid=\"Base_Column_List\" />");
            cdata = StringUtils.replace(cdata, "\n*", "\n<include refid=\"Base_Column_List\" />");

            cdata = StringUtils.replace(cdata, "—-dotStar--", ".*");
            cdata = StringUtils.replace(cdata, "--/Star--", "/*");
            cdata = StringUtils.replace(cdata, "--Star/--", "*/");
            cdata = StringUtils.replace(cdata, "—-dotXStar--", "*");

            cdata = cdata.replaceAll("count\\(!!!COUNT!!!\\)", " count(*) ");

        }

        cdata = cdata.replaceAll(JAVA_TYPE, " ");

        //pageCount添加
        setCfOperationPageCdata(cdata, cfOperation, optimizePagings);

        //分页配置
        if (cfOperation.getMultiplicity() == MultiplicityEnum.paging) {
            //其他扩展
            if (cfOperation.getPagingCntType() == PagingCntTypeEnum.pagingCustom) {
                int indexOf = cdata.indexOf("{pageLimit}");
                Validate.isTrue(indexOf > 0, "pagingCustom 需要使用 {pageLimit} 指定分页");
                cdata = cdata.replace("{pageLimit}", "limit #{startRow},#{limit}");
            }
            if (cfOperation.getPagingCntType() == PagingCntTypeEnum.optimize) {
                cdata = cdata.replaceAll("<optimizePaging>", "");
                cdata = cdata.replaceAll("</optimizePaging>", " limit #{startRow},#{limit} ");
            }
            if(StringUtils.equalsIgnoreCase(ConfigUtil.getCurrentDb().getType(),"oracle")){
                cdata =  patternReplaceLast(ORDER_BY_PATTERN,cdata,ORDER_BY);
            }
        }
        cdata = delQuestionMarkParam(cdata, cfOperation, cfTable);

        //添加sql注释,以便于DBA 分析top sql 定位
        cfOperation.setCdata(addSqlAnnotation(cdata, cfOperation.getName(), cfTable.getSqlname()));

        //处理扩展参数
        dalExtraparams(extraparams, cfOperation);

        //获取参数
        fillOperationParams(oElement, table, cfOperation, cfSqlMap, cfTable);
    }

    private static final String REPLACE_TMP = " _ ⊙ o ⊙ _ ";
    public static final String ORDER_BY =" ORDER BY ";

    /**
     * Sets cf operation page cdata.
     *
     * @param cdata       the cdata
     * @param cfOperation the cf operation
     */
    private void setCfOperationPageCdata(String cdata, CfOperation cfOperation, List<Element> optimizePagings) {
        String forCount = cdata;
        //分页配置
        if (cfOperation.getMultiplicity() == MultiplicityEnum.paging) {

            if (cfOperation.getPagingCntType() != PagingCntTypeEnum.pagingCustom) {
                String cdataCount = null;
                if (cfOperation.getPagingCntType() == PagingCntTypeEnum.paging) {
                    Matcher selectFromMather = SELECT_FROM_PATTERN.matcher(forCount);
                    if (selectFromMather.find()) {
                        forCount = selectFromMather
                            .replaceFirst("SELECT\n          COUNT(*) AS total \n        FROM\n");
                    }

                    cdataCount = forCount.replaceAll(ORDER_BY_PATTERN, REPLACE_TMP);
                    int indexof = cdataCount.lastIndexOf(REPLACE_TMP);
                    if (indexof > 0) {
                        cdataCount = cdataCount.substring(0, indexof).replaceAll(
                            "(?m)^\\s*$" + System.lineSeparator(), "");
                    }
                    Validate.notEmpty(cdataCount, "分页cdataCount处理异常");
                } else if (cfOperation.getPagingCntType() == PagingCntTypeEnum.optimize) {
                    //提速模式
                    Validate.notEmpty(optimizePagings, "optimize 模式必须配置 <optimizePagings>");
                    forCount = optimizePagings.get(0).asXML();
                    Matcher selectFromMather = SELECT_FROM_PATTERN.matcher(forCount);
                    if (selectFromMather.find()) {
                        forCount = selectFromMather
                            .replaceFirst("SELECT\n          COUNT(*) AS total \n        FROM\n");
                    }

                    cdataCount = forCount.replaceAll(ORDER_BY_PATTERN, REPLACE_TMP);
                    int indexof = cdataCount.lastIndexOf(REPLACE_TMP);
                    if (indexof > 0) {
                        cdataCount = cdataCount.substring(0, indexof).replaceAll(
                            "(?m)^\\s*$" + System.lineSeparator(), "");
                    }
                    cdataCount = cdataCount.replaceAll("<optimizePaging>", "");
                    cdataCount = cdataCount.replaceAll("</optimizePaging>", "");
                    Validate.notEmpty(cdataCount, "分页cdataCount处理异常");

                } else if (cfOperation.getPagingCntType() == PagingCntTypeEnum.pagingExtCnt) {
                    cdataCount = forCount.replaceAll(ORDER_BY_PATTERN, REPLACE_TMP);
                    int indexof = cdataCount.lastIndexOf(REPLACE_TMP);
                    if (indexof > 0) {
                        cdataCount = cdataCount.substring(0, indexof).replaceAll(
                            "(?m)^\\s*$" + System.lineSeparator(), "");
                    }
                    Validate.notEmpty(cdataCount, "分页cdataCount处理异常");
                    cdataCount = "        SELECT\n          COUNT(*) AS total \n        FROM(" +
                        cdataCount + ") as tmp";

                }
                cdataCount = cdataCount.replaceAll(REPLACE_TMP, " ORDER BY ");
                cfOperation.setCdataPageCount(cdataCount);
            }
        }
    }

    /**
     * 处理扩展参数
     *
     * @param extraparams
     * @param cfOperation
     */
    private void dalExtraparams(Element extraparams, CfOperation cfOperation) {
        if (extraparams != null) {
            final List<Element> params = extraparams.elements();

            if (CollectionUtils.isNotEmpty(params)) {
                for (Element param : params) {
                    final String name = attr(param, "name");
                    cfOperation.addPrimitiveParam(name, attr(param, "javatype"));
                    String testVal = attr(param, "testVal");
                    if (StringUtils.isNotBlank(testVal)) {
                        cfOperation.addPrimitiveParamTestVal(name, testVal);
                    }
                }
            }
        }
    }

    /**
     * ? 参数替换
     *
     * @param cdata
     * @param cfOperation
     * @param cfTable
     * @return
     */
    private String delQuestionMarkParam(String cdata, CfOperation cfOperation, CfTable cfTable) {
        //
        if (!StringUtils.contains(cdata, '?')) {
            return cdata;
        }
        cfTable.getColumns();
        if (StringUtils.startsWithIgnoreCase(cfOperation.getName(), "insert")) {
            String sql = cdata;
            //sql 特殊处理一下
            sql = sql.replaceAll("\\s{1,}", "");
            sql = sql.replaceAll("\\(\\)", "");
            sql = sql.replaceAll("\\(", "\n(\n");
            sql = sql.replaceAll("\\)", "\n)\n");

            String[] sqlLines = StringUtils.split(sql, "\n");

            int i = 0;
            for (String sqlLine : sqlLines) {
                if (StringUtils.startsWith(sqlLine, "(")) {
                    break;
                }
                i++;
            }
            String insertLine = sqlLines[i + 1];
            String valueLine = sqlLines[i + 5];
            valueLine = valueLine.replaceAll("\\w{1},\\w{1}", "");
            String[] columns = StringUtils.split(insertLine, ',');
            String[] params = StringUtils.split(valueLine, ',');

            for (int j = 0; j < params.length; j++) {
                if (StringUtils.equals(params[j], "?")) {
                    try {
                        String columnParam = CamelCaseUtils.toCamelCase(columns[j]);
                        cdata = StringUtils.replace(cdata, "?", "#{" + columnParam + "}", 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DalgenException("参数设置错误#{}中,未正确使用 table=" + cfTable.getSqlname());
                    }

                }
            }

        } else {
            Matcher questionMarkPatternResult = QUESTION_MARK_PATTERN.matcher(cdata);
            while (questionMarkPatternResult.find()) {
                Matcher columnMatcher = QUESTION_COLUMN_PATTERN
                    .matcher(questionMarkPatternResult.group());
                while (columnMatcher.find()) {
                    String columnParam = CamelCaseUtils.toCamelCase(columnMatcher.group());
                    cdata = StringUtils.replace(cdata, "?", "#{" + columnParam + "}", 1);
                    cfOperation.addPrimitiveParam(columnParam, "");
                }
            }
        }
        return cdata;
    }

    /**
     * Add sql annotation string.
     *
     * @param cdata  the cdata
     * @param oName  the o name
     * @param tbName the tb name
     * @return the string
     */
    private String addSqlAnnotation(String cdata, String oName, String tbName) {

        Matcher matcher = MYCAT_HINT_PATTERN.matcher(cdata);
        String myCatAnn = null;
        String myCatAnnRpl = "&&@@MYCATHINT@@&&";
        if (matcher.find()) {
            myCatAnn = matcher.group(1);
            cdata = matcher.replaceAll(myCatAnnRpl);
        }

        String sqlAnnotation = StringUtils.upperCase(CamelCaseUtils
            .toInlineName(CamelCaseUtils.toCamelCase("ms_" + tbName + "_" + oName)));
        if (StringUtils.startsWithIgnoreCase(oName, "insert ")
            || StringUtils.startsWithIgnoreCase(oName, "update")
            || StringUtils.startsWithIgnoreCase(oName, "delete")) {
            if (StringUtils.contains(cdata, "update ")) {
                cdata = StringUtils.replace(cdata, "update ", "update /*" + sqlAnnotation + "*/ ");
            } else if (StringUtils.contains(cdata, "UPDATE ")) {
                cdata = StringUtils.replace(cdata, "UPDATE ", "UPDATE /*" + sqlAnnotation + "*/ ");
            } else if (StringUtils.contains(cdata, "insert ")) {
                cdata = StringUtils.replace(cdata, "insert ", "insert /*" + sqlAnnotation + "*/ ");
            } else if (StringUtils.contains(cdata, "INSERT ")) {
                cdata = StringUtils.replace(cdata, "INSERT ", "INSERT /*" + sqlAnnotation + "*/ ");
            } else if (StringUtils.contains(cdata, "delete ")) {
                cdata = StringUtils.replace(cdata, "delete ", "delete /*" + sqlAnnotation + "*/ ");
            } else if (StringUtils.contains(cdata, "DELETE ")) {
                cdata = StringUtils.replace(cdata, "DELETE ", "DELETE /*" + sqlAnnotation + "*/ ");
            }
        } else {
            if (StringUtils.contains(cdata, "select ")) {
                cdata = StringUtils.replace(cdata, "select ", "select /*" + sqlAnnotation + "*/ ");
            } else if (StringUtils.contains(cdata, "SELECT ")) {
                cdata = StringUtils.replace(cdata, "SELECT", "SELECT /*" + sqlAnnotation + "*/ ");
            }
        }
        if (StringUtils.isNotBlank(myCatAnn)) {
            cdata = StringUtils.replace(cdata, myCatAnnRpl, myCatAnn);
        }
        return cdata;
    }

    /**
     * Fill operation params. 原生态参数获取 添加List参数支持
     *
     * @param e           the e
     * @param cfOperation the cf operation
     */
    private void fillOperationParams(Element e, DefaultElement table, CfOperation cfOperation,
                                     Map<String, Element> cfSqlMap, CfTable cfTable) {

        if (cfOperation.getParamType() != ParamTypeEnum.primitive) {
            return;
        }

        //取出foreach 用来判断是否有List参数
        List<DefaultElement> items = e.elements();

        if (CollectionUtils.isNotEmpty(items)) {
            for (DefaultElement item : items) {
                List<Element> ies = item.elements();
                if (StringUtils.endsWithIgnoreCase("include", item.getName())) {
                    Element includeElement = elementById(table, item.attributeValue("refid"));
                    if (includeElement == null) {
                        includeElement = cfSqlMap.get(item.attributeValue("refid"));
                    }
                    Validate.notNull(includeElement, "include refid=" + item.attributeValue("refid") + " 对应的SQL 未配置");
                    fillOperationParams(includeElement, table, cfOperation, cfSqlMap, cfTable);
                } else if (CollectionUtils.isNotEmpty(ies)) {
                    recursiveForeachElement(cfOperation, table, ies, cfSqlMap, cfTable);
                } //找到List参数
                else if (StringUtils.equalsIgnoreCase(item.getName(), "foreach")) {
                    String collName = item.attributeValue("collection");
                    String itemName = item.attributeValue("item");
                    Validate.notEmpty(collName, "foreach 元素设置错误 table=" + cfOperation.getName());
                    Validate.notEmpty(itemName, "foreach 元素设置错误 table=" + cfOperation.getName());
                    String javatype = item.attributeValue("javatype");
                    if (StringUtils.isNotBlank(javatype)) {
                        cfOperation.addPrimitiveForeachOtherParam("list_" + itemName, collName,
                            javatype);
                    } else {
                        cfOperation.addPrimitiveForeachParam(itemName, collName);
                    }
                }
            }
        }

        Matcher m = PARAM_PATTERN.matcher(e.asXML());
        List<String> params = Lists.newArrayList();
        while (m.find()) {
            params.add(m.group(1));
        }

        for (String _p : params) {
            String p = _p.replaceAll(" ", "");
            String attr = null;
            String type = null;
            for (String s : StringUtils.split(p, ",")) {
                if (s.contains("=")) {
                    if (StringUtils.startsWithIgnoreCase(s, "javaType")
                        || StringUtils.startsWithIgnoreCase(s, "jdbcType")) {
                        type = StringUtils.split(s, "=")[1].trim();
                    }
                } else {
                    attr = StringUtils.trim(s);
                }
            }

            cfOperation.addPrimitiveParam(attr, type);
        }
    }

    private void recursiveForeachElement(CfOperation cfOperation, DefaultElement table, List<Element> ies,
                                         Map<String, Element> cfSqlMap, CfTable cfTable) {
        for (Element ie : ies) {
            if (StringUtils.endsWithIgnoreCase("include", ie.getName())) {
                Element includeElement = elementById(table, ie.attributeValue("refid"));
                if (includeElement == null) {
                    includeElement = cfSqlMap.get(ie.attributeValue("refid"));
                }
                Validate.notNull(includeElement, "include refid=" + ie.attributeValue("refid") + " 对应的SQL 未配置");
                fillOperationParams(includeElement, table, cfOperation, cfSqlMap, cfTable);
            } else if (StringUtils.equalsIgnoreCase(ie.getName(), "foreach")) {
                String collName = ie.attributeValue("collection");
                String itemName = ie.attributeValue("item");
                Validate.notEmpty(collName, "foreach 元素设置错误 table=" + cfOperation.getName());
                Validate.notEmpty(itemName, "foreach 元素设置错误 table=" + cfOperation.getName());
                String javatype = ie.attributeValue("javatype");
                if (StringUtils.isNotBlank(javatype)) {
                    cfOperation.addPrimitiveForeachOtherParam("list_" + itemName, collName,
                        javatype);
                } else {
                    cfOperation.addPrimitiveForeachParam(itemName, collName);
                }
                List<Element> _ies = ie.elements();
                if (CollectionUtils.isNotEmpty(_ies)) {
                    recursiveForeachElement(cfOperation, table, _ies, cfSqlMap, cfTable);
                }
            } else {
                if ((CollectionUtils.isNotEmpty(ie.elements()))) {
                    recursiveForeachElement(cfOperation, table, ie.elements(), cfSqlMap, cfTable);
                }
            }
        }
    }

    /**
     * Fill columns.
     *
     * @param cfTable the cf table
     * @param table   the table
     */
    private void fillColumns(CfTable cfTable, Element table) {

        List<Element> elements = table.elements("column");
        for (Element e : elements) {
            CfColumn cfColumn = new CfColumn();
            cfColumn.setName(attr(e, "name"));
            cfColumn.setJavatype(attr(e, "javatype"));
            cfColumn.setTestVal(attr(e, "testVal"));
            cfColumn.setTypeHandler(attr(e, "typeHandler"));
            cfColumn.setRelatedColumn(attr(e, "relatedColumn"));
            cfTable.addColumn(cfColumn);
        }

    }

    /**
     * Attr string.
     *
     * @param e    the e
     * @param attr the attr
     * @return the string
     */
    private String attr(Element e, String attr) {
        if (e == null || attr == null) {
            return null;
        }
        Attribute attribute = e.attribute(attr);
        if (attribute == null) {
            return null;
        } else {
            return attribute.getText();
        }
    }

    /**
     * Attr string.
     *
     * @param e    the e
     * @param attr the attr
     * @return the string
     */
    private Long attrLong(Element e, String attr) {
        if (e == null || attr == null) {
            return null;
        }
        Attribute attribute = e.attribute(attr);
        if (attribute == null) {
            return null;
        } else {
            return Long.valueOf(attribute.getText());
        }
    }

    /**
     * Attr string.
     *
     * @param e    the e
     * @param attr the attr
     * @return the string
     */
    private boolean attrBoolean(Element e, String attr) {
        if (e == null || attr == null) {
            return false;
        }
        Attribute attribute = e.attribute(attr);
        if (attribute == null) {
            return false;
        } else {
            return Boolean.valueOf(attribute.getText());
        }
    }

    private DefaultElement elementById(Element element, String id) {
        List<DefaultElement> elements = element.elements();
        if (CollectionUtils.isNotEmpty(elements)) {
            for (DefaultElement element1 : elements) {
                if (StringUtils.equals(element1.attributeValue("id"), id)) {
                    return element1;
                } else if (CollectionUtils.isNotEmpty(element1.elements())) {
                    DefaultElement defaultElement = elementById(element1, id);
                    if (defaultElement != null) {
                        return defaultElement;
                    }
                }
            }

        }
        return null;
    }

    /**
     * 正则表达式提花
     * @param regex
     * @param replacement
     * @return
     */
    private String patternReplaceLast(String regex,String content, String replacement){
        Matcher matcher = Pattern.compile(regex).matcher(content);
        matcher.reset();
        int cnt = 0;
        while (matcher.find()){
            cnt=cnt+1;
        }
        if(cnt>0) {
            matcher.reset();
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                if (cnt == 1) {
                    // 将匹配之前的字符串复制到sb,再将匹配结果替换为："favour"，并追加到sb
                    matcher.appendReplacement(sb, replacement);
                }
                cnt = cnt - 1;
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return content;
    }
}