package com.wujunshen.config;

import com.wujunshen.filter.HTTPBearerAuthorizeFilter;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User:frankwoo(吴峻申) <br>
 * Date:2016-11-3 <br>
 * Time:16:59 <br>
 * Mail:frank_wjs@hotmail.com <br>
 */

@Configuration
@EnableZuulProxy
public class FilterConfig {

    @Bean
    public HTTPBearerAuthorizeFilter accessFilter() {
        return new HTTPBearerAuthorizeFilter();
    }
}