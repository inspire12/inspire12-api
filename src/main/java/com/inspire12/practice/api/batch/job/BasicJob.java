package com.inspire12.practice.api.batch.job;

import com.inspire12.practice.api.batch.dto.Basic;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class BasicJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job basicJob() {
//        return jobBuilderFactory.get("basicJob")
//                .preventRestart()
//                .start(basicStep())
//                .next()
//                .end()
//                .build();
//    }
//
//    @JobScope
//    @Bean
//    public Step basicStep() {
//        return stepBuilderFactory.get("basicStep")
//                .<Basic, Basic> chunk(10)
//                .reader()
//                .processor()
//                .writer()
//                .build();
//    }
//
//    @JobScope
//    @Bean
//    public Step basic2Step() {
//        return stepBuilderFactory.get("basic2Step")
//                .tasklet(((stepContribution, chunkContext) -> {
//
//                }))
//                .build();
//    }

}
