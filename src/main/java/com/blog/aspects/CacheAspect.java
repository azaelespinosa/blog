package com.blog.aspects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Aspect for handling cacheable methods.
 *
 */
@Slf4j
@Aspect
@Component
public class CacheAspect {

    @Autowired
    private Environment env;

    private Map<String, Object> cache;

    private Map<String, Long> timeMap = new HashMap<String, Long>();

    private final String EXPIRE_TIME = "expireTime";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:SSS");

    public CacheAspect() {
        cache = new HashMap<String, Object>();
    }

    /**
     * Pointcut for all methods annotated with <code>@Cacheable</code>
     */
    @Pointcut("execution(@Cacheable * *.*(..))")
    @SuppressWarnings("unused")
    private void cache() {
    }

    @Around("cache()")
    public Object aroundCachedMethods(ProceedingJoinPoint thisJoinPoint)  throws Throwable {
        log.debug("Execution of Cacheable method catched");

        // generate the key under which cached value is stored
        // will look like caching.aspectj.Calculator.sum(Integer=1;Integer=2;)
        StringBuilder keyBuff = new StringBuilder();

        // append name of the class
        keyBuff.append(thisJoinPoint.getTarget().getClass().getName());

        // append name of the method
        keyBuff.append(".").append(thisJoinPoint.getSignature().getName());

        keyBuff.append("(");
        // loop through cacheable method arguments
        for (final Object arg : thisJoinPoint.getArgs()) {
            // append argument type and value
            keyBuff.append(arg.getClass().getSimpleName() + "=" + arg + ";");
        }
        keyBuff.append(")");
        String key = keyBuff.toString();

        log.debug("Key = " + key);
        Object result = cache.get(key);
        if (result == null) {
            log.debug("Result not yet cached.");
            result = thisJoinPoint.proceed();
            log.info("Storing "+ thisJoinPoint.getSignature().getName() +" values '" + result + "' to cache");
            cache.put(key, result);
            cacheExpiration();
        } else {
            log.info("Object was found in cache -> " + result);
            cleanCache();
        }

        return result;
    }

    public void cacheExpiration() {
        Date date = new Date();
        timeMap.put(EXPIRE_TIME, date.getTime());
    }

    private void cleanCache() {
        int expiryInMillis	 = Integer.parseInt(env.getProperty("cache.expiration"));
        long currentTime = new Date().getTime();

        for (Map.Entry<String,Long> entry : timeMap.entrySet()){
            if (currentTime > (timeMap.get(entry.getKey()) + expiryInMillis)) {
                timeMap.remove(entry.getKey());
                cache.clear();
                log.info("Removing cache : currentTime : " + sdf.format(new Date()) + " : " + entry.getKey() + " -> " + sdf.format(new Date(entry.getValue())) );
            }
        }
    }


}