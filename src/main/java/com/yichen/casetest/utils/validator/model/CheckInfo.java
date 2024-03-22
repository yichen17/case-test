package com.yichen.casetest.utils.validator.model;

import com.yichen.casetest.utils.validator.annotation.FieldVaild;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/21 14:53
 * @describe 校验信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInfo {

    private FieldVaild fieldVaild;
    private Set<Object> set;
    private int len;
    private Pattern pattern;
    /**
     * 是否为嵌套
     */
    private boolean nest;

    private String regexPattern;

    public String getRegexPattern(){
        if (regexPattern != null){
            return regexPattern;
        }
        if (fieldVaild.type() == FieldVaild.Type.regex){
            return regexPattern = fieldVaild.data();
        }
        if (fieldVaild.type() == FieldVaild.Type.setEnum || fieldVaild.type() == FieldVaild.Type.setSpecify){
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            boolean first = true;
            for (Object o : set) {
                if (first){
                    first = false;
                }
                else {
                    sb.append("|");
                }
                sb.append(o);
            }
            sb.append(")");
            regexPattern = sb.toString();
            return regexPattern;
        }
        regexPattern = "";
        return regexPattern;
    }


}
