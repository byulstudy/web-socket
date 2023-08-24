package com.byultudy.socketserver.schedule;

import com.byultudy.socketserver.business.lineup.service.LineupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class LineupJob extends QuartzJobBean {

    private final LineupService lineupService;

    @Override
    protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
        log.info("실행");
        lineupService.enter();
        log.info("실행완료");
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(LineupJob.class)
                .storeDurably()
                .withIdentity("job_detail")
                .withDescription("Invoke Tistory Job service...")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("lineupTrigger")
                .withSchedule(cronSchedule("*/10 * * * * ?")
                        .inTimeZone(TimeZone.getTimeZone("Asia/Seoul")))
                .build();
    }

}
