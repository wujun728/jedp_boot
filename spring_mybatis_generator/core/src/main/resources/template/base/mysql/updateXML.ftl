
    <update id="update${className}">
        UPDATE ${sense}${tableName}${sense}
        SET
        <trim suffixOverrides=",">
        <#list attrs as attr>
            <#if attr.isAuto == "NO" && attr.isKey == 0>
            <if test="${attr.propertiesName} != null<#if attr.javaTypeName=="String"> and ${attr.propertiesName}!=''</#if>">
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.propertiesName}},
            </if>
            </#if>
        </#list>
        </trim>
        WHERE
        <trim suffixOverrides="and">
        <#list attrs as attr>
            <#if attr.isKey == 1>
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.propertiesName}} and
            </#if>
        </#list>
        </trim>
    </update>

    <update id="update">
        UPDATE ${sense}${tableName}${sense}
        SET
        <trim suffixOverrides=",">
    <#list attrs as attr>
        <#if attr.isAuto == "NO" && attr.isKey == 0>
        <if test="set.${attr.propertiesName} != null<#if attr.javaTypeName=="String"> and set.${attr.propertiesName}!=''</#if>">
            ${sense}${attr.columnName}${sense} = ${"#\{set."}${attr.propertiesName}},
        </if>
        </#if>
    </#list>
        </trim>
        <trim prefix="where" suffixOverrides="and | or">
            <#list attrs as attr>
                <if test="where.${attr.propertiesName}List != null">
                    ${sense}${attr.columnName}${sense} in
                    <foreach collection="where.${attr.propertiesName}List" close=")" open="(" separator="," item="item">
                        ${"#\{"}item}
                    </foreach> and
                </if>
            <#if attr.isBetween = "yes">
            <if test="where.${attr.propertiesName}St !=null">
                ${sense}${attr.columnName}${sense} >= ${"#\{where."}${attr.propertiesName}St} and
            </if>
            <if test="where.${attr.propertiesName}Ed!=null">
                ${sense}${attr.columnName}${sense} &lt;= ${"#\{where."}${attr.propertiesName}Ed} and
            </if>
            </#if>
            <#if attr.javaTypeName = "String">
            <if test ="where.fuzzy${attr.propertiesName?cap_first}!=null and where.fuzzy${attr.propertiesName?cap_first}.size()>0">
                (
                <foreach collection="where.fuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                    ${sense}${attr.columnName?cap_first}${sense} like concat('%',${"#\{"}item},'%')
                </foreach>
                ) and
            </if>
            <if test ="where.rightFuzzy${attr.propertiesName?cap_first}!=null and where.rightFuzzy${attr.propertiesName?cap_first}.size()>0">
                (
                <foreach collection="where.rightFuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                    ${sense}${attr.columnName?cap_first}${sense} like concat(${"#\{"}item},'%')
                </foreach>
                ) and
            </if>
            </#if>
            </#list>
        </trim>
    </update>