<@pp.dropOutputFile />
<@pp.changeOutputFile name = "/main/resources/oracle/toOracle.sql" />
<#list dalgen.tables as table>
CREATE TABLE ${table.sqlName?upper_case}(
    <#list table.columnList as column>
    <#if column_index gt 0>,</#if>${column.sqlName?upper_case} ${column.sqlType?upper_case},<#if column.sqlName?upper_case=="ID">NOT NULL </#if>
    </#list>
    , CONSTRAINT TB_TEST_PK PRIMARY KEY
      (
        ID
      )
      ENABLE
);

COMMENT ON TABLE ${table.sqlName?upper_case} IS '${table.remark!}';
<#list table.columnList as column>
COMMENT ON COLUMN ${table.sqlName?upper_case}.${column.sqlName?upper_case} IS '${column.remarks!}';
</#list>
</#list>
