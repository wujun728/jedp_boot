package cn.kiwipeach.demo.mapper;

import cn.kiwipeach.demo.domain.SalGrade;

public interface SalGradeMapper {
    int insert(SalGrade record);

    int insertSelective(SalGrade record);
}