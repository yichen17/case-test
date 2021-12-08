package com.yichen.casetest.controller;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/8 18:03
 * @describe  将图片保存到 mysq 中
 *
 *     逻辑：保存图片有两种形式，一种是直接将图片保存到数据库中，另一种是保存图片到某一个目录，然后数据库中存储
 *     图片对应的url 地址，如果需要反显数据时，可以通过url去查询信息。
 *
 *     缺陷：保存图片到数据库，图片数据传输存在压力。 url形式存储则存在迁移的压力，因为图片都保存在某一个目录，
 *     且数据库存的是对应url，如果更改图片位置需要进行批量操作。
 */
@RestController
@RequestMapping("/image")
public class ImageSaveController {

    private static Logger logger= LoggerFactory.getLogger(ImageSaveController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 保存图片到 数据库
     * @return 影响行数
     */
    @RequestMapping("/save")
    public String save(){
        FileInputStream fileInputStream=null;
        try{
            String path="G:\\project\\case-test\\src\\main\\resources\\image\\dog.jpeg";
            fileInputStream=new FileInputStream(new File(path));
        }
        catch (IOException e){
            logger.error(e.getMessage());
            return "读取文件流失败";
        }
        FileInputStream finalFileInputStream = fileInputStream;
        FileInputStream finalFileInputStream1 = fileInputStream;
        PreparedStatementSetter statementSetter=new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                try{
                    ps.setBinaryStream(1, finalFileInputStream, finalFileInputStream1.available());
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        };
        int res=jdbcTemplate.update("insert into imagetest(image) values (?)",statementSetter);
        return res+"";
    }

    /**
     * 反显图片
     * @param id 图片 id
     */
    @RequestMapping("/show")
    public String show(@RequestParam("id") int id){
        return "";
    }

}
