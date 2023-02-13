package com.yichen.casetest.model;

import com.yichen.casetest.test.mapstruct.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/12 14:19
 * @describe
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonTestDTO {

//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    private String name;

    private Long id;

    private SexEnum sex;

    public String getSex() {
        if (Objects.isNull(sex)){
            return null;
        }
        return "sex," + sex.name();
    }
}
