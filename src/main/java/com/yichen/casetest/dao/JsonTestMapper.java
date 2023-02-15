package com.yichen.casetest.dao;

import com.yichen.casetest.model.JsonTestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/12 14:26
 * @describe json 测试 mapper
 */
@Mapper
public interface JsonTestMapper {

    JsonTestDTO getById(@Param("id") Long id);

    void insert(JsonTestDTO jsonTestDTO);

    List<JsonTestDTO> getByIds(@Param("ids") Long[] ids);

}
