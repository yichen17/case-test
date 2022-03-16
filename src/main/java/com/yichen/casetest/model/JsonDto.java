package com.yichen.casetest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/14 17:22
 * @describe
 */

public class JsonDto {
    private String name;
    private Integer age;
    private String address;
    private Date date;

    /**
     * 类型 0-date  1-时间戳 long
     */
    private Integer flag;

    List<String> foods;

    public JsonDto() {
    }


    public JsonDto(String name, Integer age, String address, Date date, Integer flag, List<String> foods) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.date = date;
        this.flag = flag;
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getDate() {
        if (1 == flag){
            return date.getTime();
        }
        else {
            return date;
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getFoods() {
        return foods;
    }

    public void setFoods(List<String> foods) {
        this.foods = foods;
    }

    @Data
    public static class Inner{
        private String time = "2022-02-23";
    }
}
