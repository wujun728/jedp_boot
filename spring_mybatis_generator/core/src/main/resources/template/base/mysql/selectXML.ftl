    <resultMap type="${packageModel}.${className}" id="${className}Map">
        <#list attrs as attr>
        <result property="${attr.propertiesName}" column="${attr.columnName}"/>
        </#list>
    </resultMap>


    <select id="query${className}" resultMap="${className}Map">
        select
        <include refid="baseResult"></include>
        from  ${sense}${tableName}${sense}
        <trim prefix="where" suffixOverrides="and | or">
                <#list attrs as attr>
            <if test="${attr.propertiesName} != null<#if attr.javaTypeName=="String"> and ${attr.propertiesName}!=''</#if>">
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.propertiesName}} and
            </if>
                </#list>
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">
                <#list attrs as attr>
                <if test="${attr.propertiesName}List != null">
                    ${sense}${attr.columnName}${sense} in
                    <foreach collection="${attr.propertiesName}List" close=")" open="(" separator="," item="item">
                        ${"#\{"}item}
                    </foreach> and
                </if>
                <#if attr.isBetween = "yes">
                <if test="${attr.propertiesName}St !=null">
                    ${sense}${attr.columnName}${sense} >= ${"#\{"}${attr.propertiesName}St} and
                </if>
                <if test="${attr.propertiesName}Ed!=null">
                    ${sense}${attr.columnName}${sense} &lt;= ${"#\{"}${attr.propertiesName}Ed} and
                </if>
                </#if>
                <#if attr.javaTypeName = "String">
                <if test ="fuzzy${attr.propertiesName?cap_first}!=null and fuzzy${attr.propertiesName?cap_first}.size()>0">
                    (
                    <foreach collection="fuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                        ${sense}${attr.columnName?cap_first}${sense} like concat('%',${"#\{"}item},'%')
                    </foreach>
                    ) and
                </if>
                <if test ="rightFuzzy${attr.propertiesName?cap_first}!=null and rightFuzzy${attr.propertiesName?cap_first}.size()>0">
                    (
                    <foreach collection="rightFuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                        ${sense}${attr.columnName?cap_first}${sense} like concat(${"#\{"}item},'%')
                    </foreach>
                    ) and
                </if>
                </#if>
                </#list>
            </if>
        </trim>
    </select>

    <select id="query${className}Limit1" resultMap="${className}Map">
        select
        <include refid="baseResult"></include>
        from  ${sense}${tableName}${sense}
        <trim prefix="where" suffixOverrides="and | or">
                <#list attrs as attr>
            <if test="${attr.propertiesName} != null<#if attr.javaTypeName=="String"> and ${attr.propertiesName}!=''</#if>">
                ${sense}${attr.columnName}${sense} = ${"#\{"}${attr.propertiesName}} and
            </if>
                </#list>
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">
                <#list attrs as attr>
                    <if test="${attr.propertiesName}List != null">
                        ${sense}${attr.columnName}${sense} in
                        <foreach collection="${attr.propertiesName}List" close=")" open="(" separator="," item="item">
                            ${"#\{"}item}
                        </foreach> and
                    </if>
                <#if attr.isBetween = "yes">
                <if test="${attr.propertiesName}St !=null">
                    ${sense}${attr.columnName}${sense} >= ${"#\{"}${attr.propertiesName}St} and
                </if>
                <if test="${attr.propertiesName}Ed!=null">
                    ${sense}${attr.columnName}${sense} &lt;= ${"#\{"}${attr.propertiesName}Ed} and
                </if>
                </#if>
                <#if attr.javaTypeName = "String">
                <if test ="fuzzy${attr.propertiesName?cap_first}!=null and fuzzy${attr.propertiesName?cap_first}.size()>0">
                    (
                    <foreach collection="fuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                        ${sense}${attr.columnName?cap_first}${sense} like concat('%',${"#\{"}item},'%')
                    </foreach>
                    ) and
                </if>
                <if test ="rightFuzzy${attr.propertiesName?cap_first}!=null and rightFuzzy${attr.propertiesName?cap_first}.size()>0">
                    (
                    <foreach collection="rightFuzzy${attr.propertiesName?cap_first}"  separator="or" item="item">
                        ${sense}${attr.columnName?cap_first}${sense} like concat(${"#\{"}item},'%')
                    </foreach>
                    ) and
                </if>
                </#if>
                </#list>
            </if>
        </trim>
        limit 1
    </select>

    <sql id="allResult">
        <trim suffixOverrides=",">
    <#list attrs as attr>
        ${sense}${attr.columnName}${sense}<#if attr.typeName == "BIT">+0 as ${sense}${attr.columnName}${sense}</#if>,
    </#list>
        </trim>
    </sql>



    <sql id="baseResult">
        <trim suffixOverrides=",">
            <if test = "(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == true">

                <if test="fetchFields==null">
                    <include refid="allResult"></include>
                </if>
                <if test="fetchFields!=null">
                    <if test="fetchFields.AllFields !=null">
                        <include refid="allResult"></include>
                    </if>
                    <if test="fetchFields.AllFields ==null and fetchFields.fetchFields==null and fetchFields.excludeFields==null and fetchFields.otherFields==null">
                        <include refid="allResult"></include>
                    </if>
                    <if test="fetchFields.AllFields==null and fetchFields.fetchFields!=null">
                <#list attrs as attr>
                    <if test="fetchFields.fetchFields.${attr.propertiesName}==true">
                        ${sense}${attr.columnName}${sense}<#if attr.typeName == "BIT">+0 as ${sense}${attr.columnName}${sense}</#if>,
                    </if>
                </#list>
                    </if>
                    <if test="fetchFields.AllFields==null and fetchFields.excludeFields!=null">
                <#list attrs as attr>
                    <if test="fetchFields.excludeFields.${attr.propertiesName}==null">
                        ${sense}${attr.columnName}${sense}<#if attr.typeName == "BIT">+0 as ${sense}${attr.columnName}${sense}</#if>,
                    </if>
                </#list>
                    </if>
                    <if test="fetchFields.otherFields!=null and fetchFields.otherFields.size>0">
                        <foreach collection="fetchFields.otherFields" index="index" item="item" separator=",">
                        ${sense}${r"#{item}"}${sense}
                        </foreach>
                    </if>
                </if>
            </if>
            <if test="(_parameter instanceof ${packageModel}.${className}${r'$'}QueryBuilder) == false" >
                <include refid="allResult"></include>
            </if>

        </trim>
    </sql>
