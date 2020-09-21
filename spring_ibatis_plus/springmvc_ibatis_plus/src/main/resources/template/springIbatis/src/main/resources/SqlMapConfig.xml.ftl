<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE sqlMapConfig PUBLIC "-//ibatis.apache.org//DTD SQL Map Config 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-config-2.dtd">
<sqlMapConfig>
	<!-- settings元素指定全局配置选项 -->
	<!-- useStatementNamespaces属性指定是否开启命名空间，默认值为false(关闭) -->
	<!-- cacheModelsEnabled属性指定是否开启数据高速缓存，默认值为true(开启) -->
	<!-- enhancementEnabled属性指定是否开启cglib中已优化的类来提高延迟加载的性能，默认值为true(开启) -->
	<!-- lazyLoadingEnabled属性指定是否开启延迟加载，默认值为true(开启) -->
	<settings useStatementNamespaces="true" cacheModelsEnabled="true" enhancementEnabled="true"	lazyLoadingEnabled="true" />
	<!-- 增加的sqlMap映射文件请写在这里 --><#list mappers as mapper>
	<sqlMap resource="${mapper}" /></#list>
</sqlMapConfig>
