package com.yichen.casetest.service.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Qiuxinchao
 * @date 2023/4/17 15:44
 * @describe
 */
@Component
@Slf4j
public class FirstJobService {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job firstJob(){
        return jobBuilderFactory.get("firstJob").start(step()).build();
    }

    private Step step(){
        return stepBuilderFactory.get("step").tasklet(((stepContribution, chunkContext) -> {
            log.info("执行步骤");
            return RepeatStatus.FINISHED;
        })).build();
    }

    // 多步骤任务

    @Bean
    public Job multiStepJob() {
        return jobBuilderFactory.get("multiStepJob").start(step1())
                .on(ExitStatus.COMPLETED.getExitCode()).to(step2())
                .from(step2())
                .on(ExitStatus.COMPLETED.getExitCode()).to(step3())
                .from(step3())
                .end().build();
    }

    private Step step1(){
        return stepBuilderFactory.get("step1").tasklet(((stepContribution, chunkContext) -> {
            log.info("执行步骤一操作。。。");
            return RepeatStatus.FINISHED;
        })).build();
    }
    private Step step2(){
        return stepBuilderFactory.get("step2").tasklet(((stepContribution, chunkContext) -> {
            log.info("执行步骤二操作。。。");
            return RepeatStatus.FINISHED;
        })).build();
    }

    private Step step3(){
        return stepBuilderFactory.get("step3").tasklet(((stepContribution, chunkContext) -> {
            log.info("执行步骤三操作。。。");
            return RepeatStatus.FINISHED;
        })).build();
    }



}
