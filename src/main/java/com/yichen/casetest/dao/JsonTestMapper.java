package com.yichen.casetest.dao;

import com.yichen.casetest.bugtest.mybatis.TimeDimensionEnum;
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

    List<JsonTestDTO> getByEnum(@Param("timeDimensionEnum") TimeDimensionEnum timeDimensionEnum, @Param("id") Long id );

    List<JsonTestDTO> getByEnum1(@Param("timeDimensionEnum") TimeDimensionEnum timeDimensionEnum);

    List<JsonTestDTO> getByEnum2(TimeDimensionEnum timeDimensionEnum);

    List<JsonTestDTO> getByEnum3(TimeDimensionEnum timeDimensionEnum);

    List<JsonTestDTO> getByEnum4(TimeDimensionEnum timeDimensionEnum);

    JsonTestDTO testZero(@Param("id")Long id);


}
