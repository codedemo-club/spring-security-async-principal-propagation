package club.codedemo.springsecurityasyncprincipalpropagation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

/**
 * 异步方法
 * 将启用新的线程来运行该方法
 */
@Async
public void asyncCall() {
    try {
        log.info("在@Async注解的异步方法中获取当前登录用户信息: "
                + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    } catch (RuntimeException e) {
        log.info("未能成功的获取到当前登录用户信息");
    }
}
}
