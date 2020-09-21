<#if databaseType = "mysql">
    <#include "mysql/updateXML.ftl">

</#if>
<#if databaseType = "postgres">
    <#include "postgre/updateXML.ftl">
</#if>

