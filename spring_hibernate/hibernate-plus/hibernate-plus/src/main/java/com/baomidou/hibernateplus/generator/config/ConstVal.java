/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.hibernateplus.generator.config;

import java.io.File;
import java.nio.charset.Charset;

/**
 * 定义常量
 *
 * @author YangHu, tangguo
 * @since 2016/8/31
 */
public class ConstVal {

    public static final String MODULENAME = "ModuleName";

    public static final String PO = "PO";
    public static final String VO = "VO";
    public static final String SERIVCE = "Service";
    public static final String SERVICEIMPL = "ServiceImpl";
    public static final String DAO = "Dao";
    public static final String DAOIMPL = "DaoImpl";
    public static final String CONTROLLER = "Controller";

    public static final String PO_PATH = "po_path";
    public static final String VO_PATH = "vo_path";
    public static final String SERIVCE_PATH = "serivce_path";
    public static final String SERVICEIMPL_PATH = "serviceimpl_path";
    public static final String DAO_PATH = "dao_path";
    public static final String DAOIMPL_PATH = "daoimpl_path";
    public static final String CONTROLLER_PATH = "controller_path";

    public static final String JAVA_TMPDIR = "java.io.tmpdir";
    public static final String UTF8 = Charset.forName("UTF-8").name();
    public static final String UNDERLINE = "_";

    public static final String JAVA_SUFFIX = ".java";

    public static final String TEMPLATE_PO = "/template/po.java.vm";
    public static final String TEMPLATE_VO = "/template/vo.java.vm";
    public static final String TEMPLATE_SERVICE = "/template/service.java.vm";
    public static final String TEMPLATE_SERVICEIMPL = "/template/serviceImpl.java.vm";
    public static final String TEMPLATE_DAO = "/template/dao.java.vm";
    public static final String TEMPLATE_DAOIMPL = "/template/daoimpl.java.vm";
    public static final String TEMPLATE_CONTROLLER = "/template/controller.java.vm";

    public static final String MODEL_NAME = File.separator + "%s" + JAVA_SUFFIX;

    // 配置使用classloader加载资源
    public static final String VM_LOADPATH_KEY = "file.resource.loader.class";
    public static final String VM_LOADPATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    public static final String SUPERD_DAO_CLASS = "com.baomidou.hibernateplus.dao.IDao";
    public static final String SUPERD_DAOIMPL_CLASS = "com.baomidou.hibernateplus.dao.impl.DaoImpl";
    public static final String SUPERD_SERVICE_CLASS = "com.baomidou.hibernateplus.service.IService";
    public static final String SUPERD_SERVICEIMPL_CLASS = "com.baomidou.hibernateplus.service.impl.ServiceImpl";

}
