package com.inspire12.practice.api.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class BatchJobConfig {

    @Bean
    public Tasklet myTasklet() {
        return (contribution, chunkContext) -> RepeatStatus.FINISHED;
    }

    @Bean
    public Job ExampleJob(JobRepository jobRepository, Step step) {
        Job exampleJob = new JobBuilder("exampleJob", jobRepository)
                .start(step)
                .build();

        return exampleJob;
    }

    @Bean
    public Step Step(JobRepository jobRepository, Tasklet myTasklet, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .tasklet(myTasklet, transactionManager)
                .build();
    }

}
