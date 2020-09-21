package db.mysql.env;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import db.mysql.model.ProducerParam;
import db.mysql.process.MysqlCommon;

import java.io.*;

/**
 * db.mysql
 *
 * @author ASUS
 * @date 2017/10/20 16:21
 */
public class RuntimeEnv {
    public static ProducerParam pp;

    public static MysqlCommon mc;

    public static final String storagePath = System.getProperty("user.home").replaceAll("\\\\","/")+"/";

    public static final String ppEnv = "ppEnv.json";

    public static final String switchFile = "mb-switch.json";


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void storageSwitch(JSONObject jsonObject)  {
        try {
            if (RuntimeEnv.pp != null) {
                System.out.println(storagePath);
                File file = new File(storagePath + switchFile);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(file);
                writer.write(JSON.toJSONString(jsonObject, true));
                writer.flush();
                writer.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static JSONObject readerSwitch(JSONObject defaultJSON) {
        File file = new File(storagePath + switchFile);
        if (!file.exists()) {
            storageSwitch(defaultJSON);
            return defaultJSON;
        }
        try {
            BufferedReader reader = new BufferedReader (new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();

            while(true){
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                stringBuilder.append(line);
            }

            JSONObject switchJSON =JSON.parseObject(stringBuilder.toString());
            reader.close();
            return switchJSON;
        }catch (Exception e){
            throw new IllegalArgumentException("readerSwitch fail",e);
        }


    }


    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void storage()  {
        try {
            if (RuntimeEnv.pp != null) {
                System.out.println(storagePath);
                File file = new File(storagePath + ppEnv);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(file);
                writer.write(JSON.toJSONString(RuntimeEnv.pp, true));
                writer.flush();
                writer.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void reader() throws IOException {
        File file = new File(storagePath + ppEnv);
        if (!file.exists()) {
            RuntimeEnv.pp = new ProducerParam();
            storage();
            return;
        }
        BufferedReader reader = new BufferedReader (new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        while(true){
            String line = reader.readLine();
            if (line == null){
                break;
            }
            stringBuilder.append(line);
        }

        RuntimeEnv.pp = JSON.parseObject(stringBuilder.toString(), ProducerParam.class);
        reader.close();
    }
}
