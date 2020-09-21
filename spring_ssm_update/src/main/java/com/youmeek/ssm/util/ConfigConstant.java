package com.youmeek.ssm.util;

/**
 * Created by fgt on 2016/5/4.
 */
public class ConfigConstant {

    public enum shiColumn {

        ARTTRAIN("艺术培训","artTrain"),MOUNTAINMEMORY("记忆石嘴山","mountainMemory"),
        ACHIEVE("工业重镇陆港新区成就展","achieve"),MAINSCHOOL("五七干校","mainSchool"),
        PASTMEMORY("曾经的记忆","pastMemory"),OLDPHOTO("山梁项老照片","oldPhoto");

        private String colCn;//中文名
        private String colEn;//英文名

        shiColumn(String colCn,String colEn) {
            this.colCn = colCn;
            this.colEn = colEn;
        }

        /**
         * 根据英文获取枚举类
         * @param colEn
         * @return
         */
        public shiColumn getByEn(String colEn) {
            for (shiColumn col : values()) {
                if (colEn.equals(col.getColEn())) {
                    return  col;
                }
            }
            throw new IllegalArgumentException();
        }

        /**
         * 根据英文获取中文
         * @param colEn
         * @return
         */
        public String getColCn(String colEn) {
            for (shiColumn col : values()) {
                if (colEn.equals(col.colEn)) {
                    return col.getColCn();
                }
            }
            throw new IllegalArgumentException();
        }

        public String getColEn() {
            return colEn;
        }

        public String getColCn() {
            return colCn;
        }
    }

    public enum FileType {

        IMAGE("image"),
        VIDEO("video");

        private String type;

        FileType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}
