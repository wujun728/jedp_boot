<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageMapper}.base.${mapperName}BaseMapper">

    <#include "base/insertXML.ftl">

    <#include "base/updateXML.ftl">

    <#include "base/selectXML.ftl">

</mapper>