package com.youmeek.ssm.service;

import com.youmeek.ssm.pojo.Spcolumn;
import com.youmeek.ssm.util.ConfigConstant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author frank.fang
 *         date 2016/5/5.
 */
public interface EditorService {

    List<Spcolumn> getAllSpCol();

    String saveFile(MultipartFile uploadPic, String spCol, String childCol , ConfigConstant.FileType fileType);
}
