package com.yichen.casetest.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/1/6 9:59
 * @describe 执行 excel 相关操作
 */
@RequestMapping("/excel")
@Controller
public class ExcelController {

    private static Logger logger= LoggerFactory.getLogger(ExcelController.class);

    @RequestMapping("/export")
    @ResponseBody
    public String exportFile(HttpServletResponse response, HttpServletRequest request){

        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());
        row2.put("金额", new BigDecimal("23.50"));
        row2.put("生日",new Date());

        ArrayList<Map<String, Object>> rows1 = CollUtil.newArrayList(row1);
        ArrayList<Map<String, Object>> rows2 = CollUtil.newArrayList(row2);



        try{
            //  多个 sheet
            ExcelWriter writer = new ExcelWriter("test.xlsx","第一个sheet");
            // 重命名 sheet
//            writer.renameSheet(0,"第一个sheet");
            StyleSet styleSet = writer.getStyleSet();
            // 设置日期格式
//            styleSet.getCellStyleForDate().setDataFormat((short)14);


            // 合并单元格后的标题行，  用于 title
//            writer.merge(4, "一班成绩单");

            // 设置头部样式
            CellStyle headCellStyle = styleSet.getHeadCellStyle();
            headCellStyle.setShrinkToFit(true);

            // 写数据
            writer.write(rows1, true);
            writer.setSheet("第二个sheet");
            writer.write(rows2,true);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            // filename 后面的是文件名称
            String filename="test-"+DateUtil.format(new Date(),"yyyyMMddhhmmss")+".xlsx";
            response.setHeader("Content-Disposition","attachment;filename="+filename);
            ServletOutputStream out=response.getOutputStream();
            writer.flush(out, true);
            writer.close();
            IoUtil.close(out);
        } catch (IOException e) {
            logger.error("生成 excel 出错 {}",e.getMessage());
        }
        return "ok";
    }

}
