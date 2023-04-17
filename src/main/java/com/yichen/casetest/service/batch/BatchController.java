package com.yichen.casetest.service.batch;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @author Qiuxinchao
 * @date 2023/4/17 16:07
 * @describe
 */
@RestController
@RequestMapping("/batch")
@Api(value = "batch测试", tags = "batch测试")
@Slf4j
public class BatchController {

    @Autowired
    private Map<String, Job> maps;
    @Autowired
    private JobLauncher jobLauncher;

    @GetMapping("jobExec/{message}")
    public String jobExec(@PathVariable String message, @RequestParam String jobName) throws Exception {
        log.info("job exec 入参 {} {}", message, jobName);
        JobParameters parameters = new JobParametersBuilder()
                .addString("message", message)
                .toJobParameters();
        Job job = maps.get(jobName);
        if (Objects.isNull(job)){
            return String.format("没有%s对应的任务", jobName);
        }
        // 将参数传递给任务
        jobLauncher.run(job, parameters);
        return "success";
    }
}
