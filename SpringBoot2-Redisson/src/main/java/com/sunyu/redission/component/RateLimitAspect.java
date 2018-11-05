package com.sunyu.redission.component;


import com.power.common.util.IpUtil;
import com.sunyu.redission.annotation.RateLimit;
import com.sunyu.redission.exception.RateLimitException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 基于redisson的分布式限流
 * @author yu 2018/11/05.
 */

@Aspect
@Component
public class RateLimitAspect {

    private final Logger logger = LoggerFactory.getLogger(RateLimitAspect.class);

    @Autowired
    private RedissonClient redissonClient;

    @Before(value = "execution(* com.sunyu.redission..controller.*.*(..)) && @annotation(limit)")
    public void requestLimit(RateLimit limit) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String ip = IpUtil.getIpAddr(request);
        String url = request.getRequestURL().toString();
        StringBuilder key = new StringBuilder();
        key.append("req_limit_").append(url).append(ip);
        logger.error("req_limit key:{} ",key.toString());
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("myRateLimiter");
        rateLimiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.MINUTES);
        if(!rateLimiter.tryAcquire()){
            logger.error("too many requests!");
            logger.info("用户访问超过了限定的次数[{}]",limit.limits());
            throw new RateLimitException("用户访问超过了限定的次数");
        }
    }
}
