package com.yichen.casetest.dao;

import com.yichen.casetest.model.db.DbTestDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbTestMapper {

    void save(DbTestDo dbTestDo);

}