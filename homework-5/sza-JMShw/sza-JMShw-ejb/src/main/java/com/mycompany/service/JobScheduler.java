package com.mycompany.service;

import com.mycompany.dto.JobDTO;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Topic;

@ApplicationScoped
@Singleton
@Startup
public class JobScheduler {
    
    static  int number= 0;
    
    @Resource
    private TimerService timerService;

    @Inject
    private JMSContext jmsc;
    
    @Inject
    private StatisticsBean statBean;
    
    @Resource(lookup = "dzsobKju")
    private Queue queue;

    @Resource(lookup = "dzsobTopik")
    private Topic topic;

    @PostConstruct
    private void configTimer() {
        ScheduleExpression se = new ScheduleExpression();
        se.dayOfMonth("*");
        se.hour("*");
        se.minute("*");
        se.second(0);
        Timer t = timerService.createCalendarTimer(se);
    }
    @Timeout
    public void sendJobToQueue(Timer t) {
        JMSProducer jmsp = jmsc.createProducer();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            number++;
            JobDTO job = new JobDTO(r.nextInt(5) + 1,number);
            jmsp.send(queue, job);
            statBean.addJobs(job,System.currentTimeMillis());
        }
    }
}
