package com.yichen.casetest.problem.circleReference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 9:04
 * @describe
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private ToolService toolService;

    @Override
    public void saveOrder(){
        toolService.deductQuota();
        log.info("保存订单");
    }


}
