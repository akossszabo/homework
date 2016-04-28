package com.mycompany.worker;

import com.mycompany.dto.JobDTO;
import com.mycompany.dto.MessageDTO;
import com.mycompany.service.StatisticsBean;
import java.util.logging.Level;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(mappedName = "dzsobKju")
public class FastWorker implements MessageListener{

    final static Logger LOGGER = LoggerFactory.getLogger(FastWorker.class);
    
    @Inject
    StatisticsBean statBean;
    
    @Override
    public void onMessage(Message message) {
        try {
            JobDTO job = message.getBody(JobDTO.class);
            Thread.sleep(job.getEstimatedTime()/2*1000);
            MessageDTO msg = new MessageDTO();
            msg.setJobTime(job.getEstimatedTime()/2);
            if(msg.getJobTime()<5){
                msg.setDone(true);
            }else if(msg.getJobTime()>=5){
                msg.setDone(false);
            }
            msg.setNumber(job.getNumber());
            msg.setTime(System.currentTimeMillis());
            statBean.finishJobs(msg);
        } catch (JMSException | InterruptedException ex) {
            java.util.logging.Logger.getLogger(FastWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
