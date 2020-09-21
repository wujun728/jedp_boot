package ${packageMapper}.read.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import ${packageModel}.${className};
/**
*  @author ${author}
*/
public interface ${mapperName}BaseReadMapper {


    <#include "base/selectMapper.ftl">

}