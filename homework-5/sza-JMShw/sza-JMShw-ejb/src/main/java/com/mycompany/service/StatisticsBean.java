package com.mycompany.service;

import com.mycompany.dto.JobDTO;
import com.mycompany.dto.MessageDTO;
import com.mycompany.dto.StatInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
@Lock(LockType.READ)
public class StatisticsBean {

    ConcurrentMap<Integer, MessageDTO> jobs = new ConcurrentHashMap<>();
    List<StatInfo> statInfo = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsBean.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public void addJobs(JobDTO job, long startTime) {
        Date date = new Date(startTime);
        LOGGER.info("New job available at " + sdf.format(date) + " " + job.getEstimatedTime());
    }

    public void finishJobs(MessageDTO msg) {
        StatInfo info = new StatInfo();
        info.setNumber(msg.getNumber());
        if (msg.getJobTime() < 5) {
            msg.setDone(true);
        } else if (msg.getJobTime() >= 5) {
            msg.setDone(false);
        }
        if (msg.isDone()) {
            info.setSuccesful(true);
            LOGGER.info(msg.getNumber() + ". job was successful at " + sdf.format(msg.getTime()));
        } else {
            info.setSuccesful(false);
            LOGGER.info(msg.getNumber() + ". job was unsuccessful at " + sdf.format(msg.getTime()));
        }
        jobs.put(msg.getNumber(), msg);
        statInfo.add(info);
    }

    public List<StatInfo> getStat() {
        return statInfo;
    }
}
