package ${packageModel};
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author ${author}
*/
public class ${className} implements Serializable {

    private static final long serialVersionUID = ${timeStamp}L;

<#list attrs as attr>

    <#if attr.remarks!="" || attr.nullAble?? ||attr.columnDef??>
    /**
    <#if attr.isKey == 1>
    * 主键
    </#if>
    * ${attr.remarks}
    * isNullAble:${attr.nullAble}<#if attr.columnDef??>,defaultVal:${attr.columnDef}</#if>
    */
    </#if>
    private ${attr.javaTypeName} ${attr.propertiesName};
</#list>

<#list attrs as attr>

    public void set${attr.propertiesName?cap_first}(${attr.javaTypeName} ${attr.propertiesName}){this.${attr.propertiesName} = ${attr.propertiesName};}

    public ${attr.javaTypeName} get${attr.propertiesName?cap_first}(){return this.${attr.propertiesName};}
</#list>
    @Override
    public String toString() {
        return "${className}{" +
            <#list attrs as attr>
                "${attr.propertiesName}='" + ${attr.propertiesName} + '\'' +
            </#list>
            '}';
    }

    public static Builder Build(){return new Builder();}

    public static ConditionBuilder ConditionBuild(){return new ConditionBuilder();}

    public static UpdateBuilder UpdateBuild(){return new UpdateBuilder();}

    public static QueryBuilder QueryBuild(){return new QueryBuilder();}

    public static class UpdateBuilder {

        private ${className} set;

        private ConditionBuilder where;

        public UpdateBuilder set(${className} set){
            this.set = set;
            return this;
        }

        public ${className} getSet(){
            return this.set;
        }

        public UpdateBuilder where(ConditionBuilder where){
            this.where = where;
            return this;
        }

        public ConditionBuilder getWhere(){
            return this.where;
        }

        public UpdateBuilder build(){
            return this;
        }
    }

    public static class QueryBuilder extends ${className}{
        /**
        * 需要返回的列
        */
        private Map<String,Object> fetchFields;

        public Map<String,Object> getFetchFields(){return this.fetchFields;}

    <#list attrs as attr>
        private List<${attr.javaTypeName}> ${attr.propertiesName}List;

        public List<${attr.javaTypeName}> get${attr.propertiesName?cap_first}List(){return this.${attr.propertiesName}List;}

        <#if attr.isBetween = "yes">
        private ${attr.javaTypeName} ${attr.propertiesName}St;

        private ${attr.javaTypeName} ${attr.propertiesName}Ed;

        public ${attr.javaTypeName} get${attr.propertiesName?cap_first}St(){return this.${attr.propertiesName}St;}

        public ${attr.javaTypeName} get${attr.propertiesName?cap_first}Ed(){return this.${attr.propertiesName}Ed;}
        </#if>

        <#if attr.javaTypeName = "String">
        private List<${attr.javaTypeName}> fuzzy${attr.propertiesName?cap_first};

        public List<${attr.javaTypeName}> getFuzzy${attr.propertiesName?cap_first}(){return this.fuzzy${attr.propertiesName?cap_first};}

        private List<${attr.javaTypeName}> rightFuzzy${attr.propertiesName?cap_first};

        public List<${attr.javaTypeName}> getRightFuzzy${attr.propertiesName?cap_first}(){return this.rightFuzzy${attr.propertiesName?cap_first};}
        </#if>
    </#list>
        private QueryBuilder (){
            this.fetchFields = new HashMap<>();
        }
        <#list attrs as attr>
        <#if attr.isBetween = "yes">

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>BetWeen(${attr.javaTypeName} ${attr.propertiesName}St,${attr.javaTypeName} ${attr.propertiesName}Ed){
            this.${attr.propertiesName}St = ${attr.propertiesName}St;
            this.${attr.propertiesName}Ed = ${attr.propertiesName}Ed;
            return this;
        }

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>GreaterEqThan(${attr.javaTypeName} ${attr.propertiesName}St){
            this.${attr.propertiesName}St = ${attr.propertiesName}St;
            return this;
        }
        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>LessEqThan(${attr.javaTypeName} ${attr.propertiesName}Ed){
            this.${attr.propertiesName}Ed = ${attr.propertiesName}Ed;
            return this;
        }
        </#if>

        <#if attr.javaTypeName = "String">
        public QueryBuilder fuzzy${attr.propertiesName?cap_first} (List<${attr.javaTypeName}> fuzzy${attr.propertiesName?cap_first}){
            this.fuzzy${attr.propertiesName?cap_first} = fuzzy${attr.propertiesName?cap_first};
            return this;
        }

        public QueryBuilder fuzzy${attr.propertiesName?cap_first} (${attr.javaTypeName} ... fuzzy${attr.propertiesName?cap_first}){
            this.fuzzy${attr.propertiesName?cap_first} = solveNullList(fuzzy${attr.propertiesName?cap_first});
            return this;
        }

        public QueryBuilder rightFuzzy${attr.propertiesName?cap_first} (List<${attr.javaTypeName}> rightFuzzy${attr.propertiesName?cap_first}){
            this.rightFuzzy${attr.propertiesName?cap_first} = rightFuzzy${attr.propertiesName?cap_first};
            return this;
        }

        public QueryBuilder rightFuzzy${attr.propertiesName?cap_first} (${attr.javaTypeName} ... rightFuzzy${attr.propertiesName?cap_first}){
            this.rightFuzzy${attr.propertiesName?cap_first} = solveNullList(rightFuzzy${attr.propertiesName?cap_first});
            return this;
        }
        </#if>

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>(${attr.javaTypeName} ${attr.propertiesName}){
            set${attr.propertiesName?cap_first}(${attr.propertiesName});
            return this;
        }

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>List(${attr.javaTypeName} ... ${attr.propertiesName}){
            this.${attr.propertiesName}List = solveNullList(${attr.propertiesName});
            return this;
        }

        public QueryBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>List(List<${attr.javaTypeName}> ${attr.propertiesName}){
            this.${attr.propertiesName}List = ${attr.propertiesName};
            return this;
        }

        public QueryBuilder fetch${attr.propertiesName?cap_first}(){
            setFetchFields("fetchFields","${attr.propertiesName}");
            return this;
        }

        public QueryBuilder exclude${attr.propertiesName?cap_first}(){
            setFetchFields("excludeFields","${attr.propertiesName}");
            return this;
        }
        </#list>
        private <T>List<T> solveNullList(T ... objs){
            if (objs != null){
            List<T> list = new ArrayList<>();
                for (T item : objs){
                    if (item != null){
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public QueryBuilder fetchAll(){
            this.fetchFields.put("AllFields",true);
            return this;
        }

        public QueryBuilder addField(String ... fields){
            List<String> list = new ArrayList<>();
            if (fields != null){
                for (String field : fields){
                    list.add(field);
                }
            }
            this.fetchFields.put("otherFields",list);
            return this;
        }
        @SuppressWarnings("unchecked")
        private void setFetchFields(String key,String val){
            Map<String,Boolean> fields= (Map<String, Boolean>) this.fetchFields.get(key);
            if (fields == null){
                fields = new HashMap<>();
            }
            fields.put(val,true);
            this.fetchFields.put(key,fields);
        }

        public ${className} build(){return this;}
    }


    public static class ConditionBuilder{
        <#list attrs as attr>
        private List<${attr.javaTypeName}> ${attr.propertiesName}List;

        public List<${attr.javaTypeName}> get${attr.propertiesName?cap_first}List(){return this.${attr.propertiesName}List;}

            <#if attr.isBetween = "yes">
        private ${attr.javaTypeName} ${attr.propertiesName}St;

        private ${attr.javaTypeName} ${attr.propertiesName}Ed;

        public ${attr.javaTypeName} get${attr.propertiesName?cap_first}St(){return this.${attr.propertiesName}St;}

        public ${attr.javaTypeName} get${attr.propertiesName?cap_first}Ed(){return this.${attr.propertiesName}Ed;}
            </#if>

            <#if attr.javaTypeName = "String">
        private List<${attr.javaTypeName}> fuzzy${attr.propertiesName?cap_first};

        public List<${attr.javaTypeName}> getFuzzy${attr.propertiesName?cap_first}(){return this.fuzzy${attr.propertiesName?cap_first};}

        private List<${attr.javaTypeName}> rightFuzzy${attr.propertiesName?cap_first};

        public List<${attr.javaTypeName}> getRightFuzzy${attr.propertiesName?cap_first}(){return this.rightFuzzy${attr.propertiesName?cap_first};}
            </#if>
        </#list>
    <#list attrs as attr>
        <#if attr.isBetween = "yes">

        public ConditionBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>BetWeen(${attr.javaTypeName} ${attr.propertiesName}St,${attr.javaTypeName} ${attr.propertiesName}Ed){
            this.${attr.propertiesName}St = ${attr.propertiesName}St;
            this.${attr.propertiesName}Ed = ${attr.propertiesName}Ed;
            return this;
        }

        public ConditionBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>GreaterEqThan(${attr.javaTypeName} ${attr.propertiesName}St){
            this.${attr.propertiesName}St = ${attr.propertiesName}St;
            return this;
        }
        public ConditionBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>LessEqThan(${attr.javaTypeName} ${attr.propertiesName}Ed){
            this.${attr.propertiesName}Ed = ${attr.propertiesName}Ed;
            return this;
        }
        </#if>

        <#if attr.javaTypeName = "String">
        public ConditionBuilder fuzzy${attr.propertiesName?cap_first} (List<${attr.javaTypeName}> fuzzy${attr.propertiesName?cap_first}){
            this.fuzzy${attr.propertiesName?cap_first} = fuzzy${attr.propertiesName?cap_first};
            return this;
        }

        public ConditionBuilder fuzzy${attr.propertiesName?cap_first} (${attr.javaTypeName} ... fuzzy${attr.propertiesName?cap_first}){
            this.fuzzy${attr.propertiesName?cap_first} = solveNullList(fuzzy${attr.propertiesName?cap_first});
            return this;
        }

        public ConditionBuilder rightFuzzy${attr.propertiesName?cap_first} (List<${attr.javaTypeName}> rightFuzzy${attr.propertiesName?cap_first}){
            this.rightFuzzy${attr.propertiesName?cap_first} = rightFuzzy${attr.propertiesName?cap_first};
            return this;
        }

        public ConditionBuilder rightFuzzy${attr.propertiesName?cap_first} (${attr.javaTypeName} ... rightFuzzy${attr.propertiesName?cap_first}){
            this.rightFuzzy${attr.propertiesName?cap_first} = solveNullList(rightFuzzy${attr.propertiesName?cap_first});
            return this;
        }
        </#if>

        public ConditionBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>List(${attr.javaTypeName} ... ${attr.propertiesName}){
            this.${attr.propertiesName}List = solveNullList(${attr.propertiesName});
            return this;
        }

        public ConditionBuilder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>List(List<${attr.javaTypeName}> ${attr.propertiesName}){
            this.${attr.propertiesName}List = ${attr.propertiesName};
            return this;
        }
    </#list>

        private <T>List<T> solveNullList(T ... objs){
            if (objs != null){
            List<T> list = new ArrayList<>();
                for (T item : objs){
                    if (item != null){
                        list.add(item);
                    }
                }
                return list;
            }
            return null;
        }

        public ConditionBuilder build(){return this;}
    }

    public static class Builder {

        private ${className} obj;

        public Builder(){
            this.obj = new ${className}();
        }

<#list attrs as attr>
        public Builder <#if attr.propertiesName?starts_with("set") || attr.propertiesName?starts_with("get")>with${attr.propertiesName?cap_first}<#else>${attr.propertiesName}</#if>(${attr.javaTypeName} ${attr.propertiesName}){
            this.obj.set${attr.propertiesName?cap_first}(${attr.propertiesName});
            return this;
        }
</#list>
        public ${className} build(){return obj;}
    }

}
