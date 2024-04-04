package com.openschool.training.aspect;

import com.openschool.training.annotation.TrackTime;
import com.openschool.training.store.GoodRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PreTrackTime {
    GoodRepositoryImpl goodRepository;
    Long startTime;

    public PreTrackTime(GoodRepositoryImpl goodRepository) {
        this.goodRepository = goodRepository;
    }

    @Pointcut("@annotation(trackTime)")
    public void checkRulePointcut(TrackTime trackTime) {
    }


    @Before(value = "checkRulePointcut(trackTime)", argNames = "trackTime")
    public void before(TrackTime trackTime) {
        startTime = System.currentTimeMillis();
        System.out.println("before");


    }

    @After(value = "checkRulePointcut(trackTime)", argNames = "trackTime")
    public void after(TrackTime trackTime) {

        System.out.println("after");
//        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String methodName = trackTime.className();
        long endTime = System.currentTimeMillis();
        //todo получать имя метода отдельно
        goodRepository.add(methodName, endTime - startTime);
    }
}
