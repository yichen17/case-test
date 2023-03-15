package com.yichen.casetest.problem.circleReference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Qiuxinchao
 * @date 2023/3/15 8:52
 * @describe 关闭service
 */
@Service
@Slf4j
public class CloseServiceImpl implements CloseService {

    @Autowired
    private ToolService toolService;

    @Override
    public void batchClose(){
        log.info("批量关闭");
    }

    @Override
    @Async("async-2")
    public void close(){
        toolService.recoverQuota();
        log.info("已关闭");
    }

}
