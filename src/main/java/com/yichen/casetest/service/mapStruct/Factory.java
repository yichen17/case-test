package com.yichen.casetest.service.mapStruct;

import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/2/15 13:38
 * @describe
 */
@Component
public class Factory {

    public JsonTestId convert(Long id){
        return JsonTestId.builder().id(id).build();
    }

}
