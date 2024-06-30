package ca.taxchopper.taxserver.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class RequestAspect {

    @Autowired
    private HttpServletRequest request;

    /**
     * Pointcut to capture all methods in classes annotated with @RestController
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }

    @Around("restController()")
    public Object checkToken(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        String uri = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            uri = request.getRequestURI();
        }
        Method mth = getJpMethod(joinPoint);
        // Proceed directly
        log.info("Interface [ " + uri + " ] time costs: " + (System.currentTimeMillis() - start) + " ms");
        return joinPoint.proceed(joinPoint.getArgs());
    }

    /**
     * get Method of aspect
     *
     * @param jp
     * @return
     * @throws SecurityException
     */
    private Method getJpMethod(JoinPoint jp) throws SecurityException {
        Class[] parameterTypes = new Class[jp.getArgs().length];
        Object[] args = jp.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null) {
                parameterTypes[i] = args[i].getClass();
            } else {
                parameterTypes[i] = null;
            }
        }
        String methodName = jp.getSignature().getName();
        Method[] mts = jp.getTarget().getClass().getDeclaredMethods();
        for (Method m : mts) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return null;
    }
}
