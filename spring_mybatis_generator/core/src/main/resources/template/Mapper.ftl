package ${packageMapper}.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ${packageModel}.${className};
/**
*  @author ${author}
*/
public interface ${mapperName}BaseMapper {

    <#include "base/insertMapper.ftl">

    <#include "base/updateMapper.ftl">

    <#include "base/selectMapper.ftl">

}