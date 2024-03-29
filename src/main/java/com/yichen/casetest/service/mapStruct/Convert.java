package com.yichen.casetest.service.mapStruct;

import com.yichen.casetest.model.JsonTestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/2/15 13:36
 * @describe
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = Factory.class)
public interface Convert {


    @Mappings({
            @Mapping(target = "desc", source = "name")
    })
    JsonTestDto convert(JsonTestDTO dto);




//    @Mappings({
//        @Mapping(target = "desc", source = "name")
//    })
    List<JsonTestDto> convert(List<JsonTestDTO> datas);



//    @Mappings({
//
//    })
//    List<JsonTestDto> convert(List<JsonTestDTO> datas, String desc);

    default JsonTestDto convert(JsonTestDTO dto, String desc){
        JsonTestDto convert = this.convert(dto);
        convert.setDesc(desc);
        return convert;
    }



}
