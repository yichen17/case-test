package com.yichen.casetest.test.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/5 16:58
 * @describe
 */
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCT = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "personName", source = "name")
    @Mapping(target = "id", ignore = true) // 忽略id，不进行映射
    PersonDTO convert(Person person);

}
