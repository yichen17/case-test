//package com.yichen.casetest.service.batch;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
///**
// * @author Qiuxinchao
// * @date 2023/4/17 16:06
// * @describe
// */
//@Slf4j
//@Component
//public class ValidatingItemProcessorDemo {
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//    @Resource(name = "processorSimpleReader")
//    private ListItemReader<TestData> processorSimpleReader;
//
//    @Bean
//    public Job validatingItemProcessorJob() throws Exception {
//        return jobBuilderFactory.get("validatingItemProcessorJob3")
//                .start(step())
//                .build();
//    }
//
//    private Step step() throws Exception {
//        return stepBuilderFactory.get("step")
//                .<TestData, TestData>chunk(2)
//                .reader(processorSimpleReader)
//                .processor(beanValidatingItemProcessor())
//                .writer(list -> list.forEach(System.out::println))
//                .build();
//    }
//
////    private ValidatingItemProcessor<TestData> validatingItemProcessor() {
////        ValidatingItemProcessor<TestData> processor = new ValidatingItemProcessor<>();
////        processor.setValidator(value -> {
////            // 对每一条数据进行校验
////            if ("".equals(value.getField3())) {
////                // 如果field3的值为空串，则抛异常
////                throw new ValidationException("field3的值不合法");
////            }
////        });
////        return processor;
////    }
//
//    private BeanValidatingItemProcessor<TestData> beanValidatingItemProcessor() throws Exception {
//        BeanValidatingItemProcessor<TestData> beanValidatingItemProcessor = new BeanValidatingItemProcessor<>();
//        // 开启过滤，不符合规则的数据被过滤掉；
////        beanValidatingItemProcessor.setFilter(true);
//        beanValidatingItemProcessor.afterPropertiesSet();
//        return beanValidatingItemProcessor;
//    }
//
//}
