
    <insert id="insert${className}" <#if tableAttrs.autoKey??> useGeneratedKeys="true" keyProperty="${tableAttrs.autoKey}"</#if>>
        INSERT INTO ${sense}${tableName}${sense}
        (
        <trim suffixOverrides=",">
                <#list attrs as attr>
                    <if test="${attr.propertiesName}!=null">
                        ${sense}${attr.columnName}${sense},
                    </if>
                </#list>
        </trim>
        )
        VALUES
        (
        <trim suffixOverrides=",">
            <#list attrs as attr>
                <if test="${attr.propertiesName}!=null">
                    ${"#\{"}${attr.propertiesName}},
                </if>
            </#list>
        </trim>
        )
    </insert>
