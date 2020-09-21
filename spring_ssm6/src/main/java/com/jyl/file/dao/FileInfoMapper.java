package com.jyl.file.dao;

import com.jyl.file.model.FileInfo;

public interface FileInfoMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
	
}