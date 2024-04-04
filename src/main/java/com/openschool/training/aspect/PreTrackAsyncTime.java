package com.openschool.training.aspect;

import com.openschool.training.annotation.TrackAsyncTime;
import com.openschool.training.annotation.TrackTime;
import com.openschool.training.store.GoodRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Async
public class PreTrackAsyncTime {
    GoodRepositoryImpl goodRepository;

    public PreTrackAsyncTime(GoodRepositoryImpl goodRepository) {
        this.goodRepository = goodRepository;
    }

    private static final ThreadLocal<Long> TIME = new ThreadLocal<>();

    @Pointcut("@annotation(trackAsyncTime)")
    public void checkRulePointcut(TrackAsyncTime trackAsyncTime) {
    }


    @Before(value = "checkRulePointcut(trackAsyncTime)", argNames = "trackAsyncTime")
    public void before(TrackAsyncTime trackAsyncTime) {
        TIME.set(System.currentTimeMillis());
        System.out.println("before Async");
    }

    @After(value = "checkRulePointcut(trackAsyncTime)", argNames = "trackAsyncTime")
    public void after(TrackAsyncTime trackAsyncTime) {

        System.out.println("after trackAsyncTime");
//        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String methodName = trackAsyncTime.className();
        long endTime = System.currentTimeMillis();

        goodRepository.add(methodName, endTime - TIME.get());
    }
}
