package com.yichen.casetest.dao;

import com.yichen.casetest.model.DataTest;
import com.yichen.casetest.model.DataTestExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataTestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int countByExample(DataTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int deleteByExample(DataTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int insert(DataTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int insertSelective(DataTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    List<DataTest> selectByExample(DataTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    DataTest selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int updateByExampleSelective(@Param("record") DataTest record, @Param("example") DataTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int updateByExample(@Param("record") DataTest record, @Param("example") DataTestExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int updateByPrimaryKeySelective(DataTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_test
     *
     * @mbggenerated Mon Feb 07 16:03:46 CST 2022
     */
    int updateByPrimaryKey(DataTest record);
}