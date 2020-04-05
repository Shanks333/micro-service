package com.example.student.mapper;

import com.example.student.commons.Page;
import com.example.student.domain.StuInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.mapper
 * @description
 * @date 2020/4/5 15:11
 */
@Repository
public interface StuInfoMapper {

    List<StuInfo> selectStuInfosByPage(Page page);

    Integer insertStuInfo(StuInfo stuInfo);

    Integer delStuInfoById(String id);

    Integer delStuInfoByStuId(String stuId);

    Integer updateStuInfo(StuInfo stuInfo);
}
