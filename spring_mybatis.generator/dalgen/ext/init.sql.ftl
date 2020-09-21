<@pp.dropOutputFile />
<#list dalgen.tables as table>
    <@pp.changeOutputFile name = "/main/resources/oracle/${table.sqlName}.sql" />

CREATE TABLE ${table.sqlName?upper_case}(
    <#list table.columnList as column>
    ${column.sqlName?upper_case} ${column.sqlType?upper_case},
    </#list>

    "ID" NUMBER,
    "NAME" VARCHAR2(20 BYTE),
    "CODE" VARCHAR2(20 BYTE),
    "AGE" NUMBER,
    "IN_COME" NUMBER(20,2),
    PRIMARY KEY("ID","CODE")
) ;

COMMENT ON TABLE "TABLE6" is '标注释';
COMMENT ON COLUMN "TABLE6"."ID" IS '主键';
COMMENT ON COLUMN "TABLE6"."NAME" IS '姓名';
COMMENT ON COLUMN "TABLE6"."CODE" IS 'code';
COMMENT ON COLUMN "TABLE6"."AGE" IS '年龄';
COMMENT ON COLUMN "TABLE6"."IN_COME" IS '收入';

commit;



create table ${table.sqlName?upper_case}
    <#list table.columnList as column>
        <#if column_index gt 0>,</#if>${column.sqlName} ,${column.sqlType},${column.length!""} ,${column.precision!""},${column.scale!""},,${column.remarks!""}
    </#list>

</#list>
