package club.codedemo.springsecurityasyncprincipalpropagation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAsync
public class SpringSecurityAsyncPrincipalPropagationApplication {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AsyncService asyncService;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityAsyncPrincipalPropagationApplication.class, args);
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @GetMapping(value = "/async")
    @ResponseBody
    public Object standardProcessing() throws Exception {
        log.info("在调用使用@Async注解的方法前，在主前线程中获取用户认证信息: "
                + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        // 调用异步方法
        asyncService.asyncCall();

        log.info("在调用使用@Async注解的方法后，在主前线程中获取用户认证信息: "
                + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
