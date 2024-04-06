package com.openschool.training.aspect;

import com.openschool.training.store.GoodRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class PreTrackAsyncTime {
    GoodRepositoryImpl goodRepository;

    public PreTrackAsyncTime(GoodRepositoryImpl goodRepository) {
        this.goodRepository = goodRepository;
    }

    //    @Pointcut("execution(@com.openschool.training.annotation.TrackAsyncTime public PokemonsModel get*(..)) ")
//    @Pointcut("execution(* (@com.openschool.training.annotation.TrackAsyncTime *).*(..))")
//    @Pointcut("execution(* com.openschool.training.annotation.TrackAsyncTime.*(..))")
//    @Pointcut("@annotation(trackAsyncTime)")
    @Pointcut("@annotation(com.openschool.training.annotation.TrackAsyncTime)")
    public void asyncRunnerPointcut() {

    }

    @Around(value = "asyncRunnerPointcut()")
    public Object asyncRunner(ProceedingJoinPoint joinPoint) {
        System.out.println("перехват !!");
        try {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    return trackTime(joinPoint);
                } catch (Throwable e) {
                    log.error("asyncRunner error:", e);
                    return null;
                }

            }).get();
        } catch (Throwable e) {
            log.error("Ошибка при запуске с анотацией TrackAsyncTime", e);
            return null;
        }
    }

    protected Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();

        log.info("Асинхронный запуск с анотацией TrackAsyncTime.");

        Object o = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        log.info("Метод {} выполнился за {} мс ", methodName, endTime - startTime);
        goodRepository.add(methodName, endTime - startTime);
        return o;
    }
}
