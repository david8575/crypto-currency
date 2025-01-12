package org.david.crytocurrency.batch;

import lombok.RequiredArgsConstructor;
import org.david.crytocurrency.service.UpbitSlackService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class TickerSlackJobConfig {
    private final UpbitSlackService service;

    @Bean
    public Job tickerSlackJob(JobRepository jobRepository, Step step){
        return new JobBuilder("tickerSlackJob", jobRepository)
                .start(step)
                .build();
    }


    @Bean
    public Step step(JobRepository jobRepository, Tasklet tasklet, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("step", jobRepository)
                .tasklet(tasklet, platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet tasklet(){
        return (((contribution, chunkContext) -> {
            Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
            String market = String.valueOf(jobParameters.get("market").toString());

            service.execute(market);

            return RepeatStatus.FINISHED;
        }));
    }
}
