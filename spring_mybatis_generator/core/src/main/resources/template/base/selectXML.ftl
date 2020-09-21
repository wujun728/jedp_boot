<#if databaseType = "mysql">
    <#include "mysql/selectXML.ftl">

</#if>
<#if databaseType = "postgres">
    <#include "postgre/selectXML.ftl">
</#if>
