package com.mycompany;

import java.io.Serializable;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Asynchronous
@Stateless
public class AsyncTestService implements Serializable {

    @Resource
    private SessionContext context;

    public Future<Long> asyncMethod() {
        Long bigNum = 500000L;
        Long i;
        Long myNumber = 0L;
        for (i = 0L; i <= bigNum; i++) {
            if (i.equals(bigNum)) {
                myNumber = i;
            }
        }
        if (context.wasCancelCalled()) {
            return new AsyncResult<>(0L);
        } else {
            return new AsyncResult<>(myNumber);
        }
    }
}
