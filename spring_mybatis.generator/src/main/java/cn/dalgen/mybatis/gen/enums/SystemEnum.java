package cn.dalgen.mybatis.gen.enums;

/**
 * 操作系统
 * <p/>
 *
 * @author bangis.wangdf
 * @date 16/4/16.09:44
 */
public enum  SystemEnum {

    Window("window"),
    Linux("linux"),
    Mac("mac");

    private String code;
    private SystemEnum(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
