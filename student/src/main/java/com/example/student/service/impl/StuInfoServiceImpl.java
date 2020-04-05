package com.example.student.service.impl;

import com.example.student.commons.Page;
import com.example.student.domain.StuInfo;
import com.example.student.mapper.StuInfoMapper;
import com.example.student.service.StuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shanks
 * @version 1.0
 * @program com.example.student.service.impl
 * @description
 * @date 2020/4/5 15:12
 */
@Service
public class StuInfoServiceImpl implements StuInfoService {

    @Autowired
    private StuInfoMapper stuInfoMapper;

    @Value("${custom.page-size}")
    private Integer pageSize;

    @Override
    public List<StuInfo> getStudentsByPage(Integer pageNumber) {
        return stuInfoMapper.selectStuInfosByPage(new Page((pageNumber - 1) * pageSize, pageSize));
    }

    @Override
    public Boolean addStuInfo(StuInfo stuInfo) {
        return stuInfoMapper.insertStuInfo(stuInfo) > 0;
    }

    @Override
    public Boolean removeStuInfoById(String id) {
        return stuInfoMapper.delStuInfoById(id) > 0;
    }

    @Override
    public Boolean removeStuInfoByStuId(String stuId) {
        return stuInfoMapper.delStuInfoByStuId(stuId) > 0;
    }

    @Override
    public Boolean changeStuInfo(StuInfo stuInfo) {
        return stuInfoMapper.updateStuInfo(stuInfo) > 0;
    }
}
