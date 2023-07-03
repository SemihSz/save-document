package com.application.document.aspect;

import com.application.document.service.IsProjectAliveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Objects;

/**
 * Created by Semih, 3.07.2023
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class IsApplicationAliveAspect {

    private final IsProjectAliveService isProjectAliveService;

    private static final String DOCUMENT_CONTROLLER = "document";

    @Pointcut(" execution(* com.application.document.controller.DocumentController.*(..))")
    public void allMethods() {
    }

    /**
     * Control DocumentController rest controller endpoint is alive
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */

    @Around(" allMethods()")
    public Object validateAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        if (Objects.nonNull(isProjectAliveService) && Boolean.TRUE.equals(isProjectAliveService.getProjectIsAlive(DOCUMENT_CONTROLLER).getIsProjectAlive())) {

            return proceedingJoinPoint.proceed();
        }
        else {
            throw new RuntimeException("Uygulama kapalı");
        }
    }
}
