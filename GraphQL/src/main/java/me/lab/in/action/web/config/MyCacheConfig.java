package me.lab.in.action.web.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan
@EnableCaching
public class MyCacheConfig {

    @Bean
    public KeyGenerator simpleKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(o.getClass().getSimpleName());
            stringBuilder.append(".");
            stringBuilder.append(method.getName());
            stringBuilder.append("[");
            for (Object obj : objects) {
                stringBuilder.append(obj.toString());
            }
            stringBuilder.append("]");

            return stringBuilder.toString();
        };
    }

    @Bean(destroyMethod="shutdown")
    RedissonClient redisson(@Value("classpath:/redisson.json") Resource configFile) throws IOException {
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        Config config = Config.fromJSON(configFile.getInputStream());
        return Redisson.create(config);
    }

    @Bean
    CacheManager cacheManager(RedissonClient redissonClient){
        Map<String, CacheConfig> config = new HashMap<>();

        config.put("apiCache", new CacheConfig(60*1000, 30*1000));
        return new RedissonSpringCacheManager(redissonClient, config);
    }

}
