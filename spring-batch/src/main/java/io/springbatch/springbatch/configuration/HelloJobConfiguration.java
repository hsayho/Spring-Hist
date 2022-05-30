package io.springbatch.springbatch.configuration;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob") // Job 생성
                .start(helloStep1())
                .next(helloStep2())
                .build();

    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")// Step 생성
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("================");
                        System.out.println(" >> Step1 executed");
                        System.out.println("================");
                        return RepeatStatus.FINISHED; // 해당 tasklet을 반복 or 1번 실행 후 넘어갈지 설정
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep2")// Step 생성
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("================");
                        System.out.println(" >> Step2 executed");
                        System.out.println("================");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
