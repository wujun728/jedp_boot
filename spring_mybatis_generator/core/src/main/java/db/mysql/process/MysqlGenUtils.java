package db.mysql.process;

import db.mysql.env.Constants;
import db.mysql.env.RuntimeEnv;
import db.mysql.env.TemplateEnum;
import db.mysql.model.GenrateParamReq;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.swing.*;
import java.io.*;
import java.util.Map;

/**
 * db.mysql
 * Created by ASUS on 2017/7/16.
 * 11:01
 */
public class MysqlGenUtils {

    public static void genrate(Map<String, Object> root) throws IOException, TemplateException {
        gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                .withOutPath(RuntimeEnv.pp.getModelOutPath())
                .withFileName(RuntimeEnv.pp.getClassName()+ Constants.Java_Type_Suffix)
                .withTemplateName(TemplateEnum.Java.getText())
                .withOverwrite(true)
                .withTemplateParam(root)
                .build());
        //是否读写分离
        if (!RuntimeEnv.pp.isSperateRead()) {

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath()+ Constants.Base_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperName()+ Constants.Base_Suffix+ Constants.Mapper_Suffix+ Constants.Java_Type_Suffix)
                    .withTemplateName(TemplateEnum.Mapper.getText())
                    .withOverwrite(true)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath())
                    .withFileName(RuntimeEnv.pp.getMapperName()+ Constants.Mapper_Suffix+ Constants.Java_Type_Suffix)
                    .withTemplateName(TemplateEnum.MapperEmpty.getText())
                    .withTemplateParam(root)
                    .withOverwrite(false)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath()+ Constants.Base_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+ Constants.Base_Suffix+ Constants.Mapper_Suffix+ Constants.Xml_Type_Suffix)
                    .withTemplateName(TemplateEnum.MapperXml.getText())
                    .withOverwrite(true)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath())
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+ Constants.Mapper_Suffix+ Constants.Xml_Type_Suffix)
                    .withTemplateName(TemplateEnum.MapperXmlEmpty.getText())
                    .withOverwrite(false)
                    .withTemplateParam(root)
                    .build());

        }
        else {

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath()+ Constants.Read_Path_Prefix + Constants.Base_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperName()+ Constants.Base_Suffix+ Constants.Read_Suffix+ Constants.Java_Type_Suffix)
                    .withTemplateName(TemplateEnum.ReadMapper.getText())
                    .withOverwrite(true)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath()+ Constants.Read_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperName()+ Constants.Read_Suffix+ Constants.Java_Type_Suffix)
                    .withTemplateName(TemplateEnum.ReadMapperEmpty.getText())
                    .withOverwrite(false)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath()+ Constants.Write_Path_Prefix + Constants.Base_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperName()+ Constants.Base_Suffix+ Constants.Write_Suffix+ Constants.Java_Type_Suffix)
                    .withTemplateName(TemplateEnum.WriteMapper.getText())
                    .withOverwrite(true)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperOutPath()+ Constants.Write_Path_Prefix )
                    .withFileName(RuntimeEnv.pp.getMapperName()+ Constants.Write_Suffix+ Constants.Java_Type_Suffix)
                    .withTemplateName(TemplateEnum.WriteMapperEmpty.getText())
                    .withOverwrite(false)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath()+ Constants.Read_Path_Prefix + Constants.Base_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+ Constants.Base_Suffix+ Constants.Read_Suffix+ Constants.Xml_Type_Suffix)
                    .withTemplateName(TemplateEnum.ReadMapperXml.getText())
                    .withOverwrite(true)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath()+ Constants.Read_Path_Prefix )
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+ Constants.Read_Suffix+ Constants.Xml_Type_Suffix)
                    .withTemplateName(TemplateEnum.ReadMapperXmlEmpty.getText())
                    .withOverwrite(false)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath()+ Constants.Write_Path_Prefix + Constants.Base_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+ Constants.Base_Suffix+ Constants.Write_Suffix+ Constants.Xml_Type_Suffix)
                    .withTemplateName(TemplateEnum.WriteMapperXml.getText())
                    .withOverwrite(true)
                    .withTemplateParam(root)
                    .build());

            gen(GenrateParamReq.GenrateParamReqBuilder.aGenrateParamReq()
                    .withOutPath(RuntimeEnv.pp.getMapperXmlOutPath()+ Constants.Write_Path_Prefix)
                    .withFileName(RuntimeEnv.pp.getMapperXmlName()+ Constants.Write_Suffix+ Constants.Xml_Type_Suffix)
                    .withTemplateName(TemplateEnum.WriteMapperXmlEmpty.getText())
                    .withOverwrite(false)
                    .withTemplateParam(root)
                    .build());

        }
    }
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void gen(GenrateParamReq req) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(MysqlGenUtils.class,"/template");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate(req.getTemplateName());

        // Create the root hash

        File dir = new File(req.getOutPath());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, req.getFileName());
        int isProduce;
        if (file.exists()&&req.isOverwrite()){
            isProduce =JOptionPane.showConfirmDialog(null,req.getFileName() +"文件已存在，是否生成","提示",JOptionPane.YES_NO_OPTION);
        }
        else if (file.exists()&&!req.isOverwrite()){
            isProduce=1;
        }
        else {
            isProduce = 0;
        }
        if (isProduce == 0) {
            OutputStream fos = new FileOutputStream(new File(dir, req.getFileName())); //文件的生成目录
            Writer out = new OutputStreamWriter(fos,"UTF-8");
            temp.process(req.getTemplateParam(), out);
            fos.flush();
            fos.close();
            System.out.println(req.getFileName() + "gen code success!");
        }
    }
}
