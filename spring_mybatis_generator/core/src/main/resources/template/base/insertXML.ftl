<#if databaseType = "mysql">
    <#include "mysql/insertXML.ftl">

</#if>
<#if databaseType = "postgres">
    <#include "postgre/insertXML.ftl">
</#if>

