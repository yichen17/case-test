package com.yichen.casetest.service.mapStruct;

import com.yichen.casetest.dao.JsonTestMapper;
import com.yichen.casetest.model.JsonTestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/2/15 13:35
 * @describe
 */
@Service
public class MapStructService {

    @Resource
    private Convert convert;

    @Autowired
    private JsonTestMapper jsonTestMapper;

    /**
     *  自定义类型转换实现包装
     */
    public JsonTestDto getById(Long id){
        JsonTestDTO jsonTestDTO = jsonTestMapper.getById(id);
//        JsonTestDto convert = this.convert.convert(jsonTestDTO);
        JsonTestDto convert = this.convert.convert(jsonTestDTO, "66666");
        return convert;
    }

    public List<JsonTestDto> listConvert(Long[] ids){
        List<JsonTestDTO> results = jsonTestMapper.getByIds(ids);
        return this.convert.convert(results);
//        return this.convert.convert(results, "描述");
    }

}
