package db.mysql.env;

/**
 * db.mysql.env
 *
 * @author mymx.jlh
 * @date 2017/12/19 20:18
 */
public enum  TemplateEnum {
    Java("java.ftl"),
    Mapper("Mapper.ftl"),
    MapperEmpty("MapperEmpty.ftl"),
    MapperXml("MapperXml.ftl"),
    MapperXmlEmpty("MapperXmlEmpty.ftl"),
    ReadMapper("ReadMapper.ftl"),
    ReadMapperEmpty("ReadMapperEmpty.ftl"),
    ReadMapperXml("ReadMapperXml.ftl"),
    ReadMapperXmlEmpty("ReadMapperXmlEmpty.ftl"),
    WriteMapper("WriteMapper.ftl"),
    WriteMapperEmpty("WriteMapperEmpty.ftl"),
    WriteMapperXml("WriteMapperXml.ftl"),
    WriteMapperXmlEmpty("WriteMapperXmlEmpty.ftl")
    ;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    TemplateEnum(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TemplateEnum{" +
                "text='" + text + '\'' +
                '}';
    }
}
