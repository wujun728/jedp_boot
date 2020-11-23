package com.youmeek.ssm.service.impl;

import com.youmeek.ssm.mapper.SpcolumnMapper;
import com.youmeek.ssm.pojo.Spcolumn;
import com.youmeek.ssm.service.EditorService;
import com.youmeek.ssm.util.ConfigConstant;
import com.youmeek.ssm.util.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Wujun
 *         date 2016/5/5.
 */
@Service
@Transactional
public class EditorServiceImpl implements EditorService {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 上传根目录
     */
    @Value("${rootPath}")
    String rootPath;

    @Autowired
    SpcolumnMapper spcolumnMapper;

    @Override
    public List<Spcolumn> getAllSpCol() {
        return spcolumnMapper.getAllSpCol();
    }

    /**
     * 分隔符
     */
    private static final String separator = File.separator;

    /**
     * 存储文件并返回路径
     * @author Wujun
     * @Date 2016/5/5 14:46
     */
    @Override
    public String saveFile(MultipartFile uploadPic, String spCol, String childCol , ConfigConstant.FileType fileType) {
        childCol = StringUtils.trimToNull(childCol);
        String childPath = childCol != null ? childCol + separator : "";

        // D:/upload/artTrain/image/
        String frontPath = rootPath + separator + spCol + separator + childPath + fileType.getType() + separator;

        // 根据上传文件获取文件后缀
        String subFix = uploadPic.getOriginalFilename().substring(uploadPic.getOriginalFilename().lastIndexOf(".")).toLowerCase();

        // 生成文件保存名称为
        String fileName = frontPath + FileUtils.getFileNameByDateTime() + subFix;

        logger.info("开始上传并保存文件资料至[" + fileName + "]\r\n");

        // 开始上传并保存文件资料
        try {
            uploadPic.transferTo(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
