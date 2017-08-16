package com.wusiq.config;

import com.wusiq.entity.BookEntity;
import com.wusiq.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 可以用非Web的组件来充实完善RootConfig。在RootConfig里边，我们可以去定义一些跟Spring Web模块关系不是太紧密的Bean，就比
 * 如DataSource或者是HibernateTemplate这种与数据库相关的Bean。
 */
@Configuration
@ComponentScan(basePackages = {"com.wusiq"},excludeFilters = {@Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
public class RootConfig {

    /*redisConnection连接工厂类*/
    @Bean
    public RedisConnectionFactory redisCF(){
        JedisConnectionFactory jcf = new JedisConnectionFactory();
        jcf.setClientName("springRedis_jcf");
        jcf.setHostName("127.0.0.1");
        jcf.setPort(6379);
        return jcf;
    }

    /*只存储String的Redis模板bean*/
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory rcf){
        return new StringRedisTemplate(rcf);
    }

    /*基于UserEntity实体的Redis模板bean*/
    @Bean
    public RedisTemplate<String,UserEntity> userEntityRedisTemplate(RedisConnectionFactory rcf){
        RedisTemplate<String,UserEntity> rt = new RedisTemplate();
        rt.setConnectionFactory(rcf);
        return rt;
    }

    /**
     *1、基于BookEntity实体的Redis模板bean
     *2、希望将BookEntity类型的value序列化为JSON，而key是String类型
     *3、GenericToStringSerializer：使用Spring转换服务进行序列化；
     * JacksonJsonRedisSerializer：使用Jackson 1，将对象序列化为JSON；
     * Jackson2JsonRedisSerializer：使用Jackson 2，将对象序列化为JSON；
     * JdkSerializationRedisSerializer：使用Java序列化；
     * OxmSerializer：使用Spring O/X映射的编排器和解排器（marshaler和unmarshaler）实现序列化，用于XML序列化；
     * StringRedisSerializer：序列化String类型的key和value。
     */
    @Bean
    public RedisTemplate<String,BookEntity> bookEntityRedisTemplate(RedisConnectionFactory rcf){
        RedisTemplate<String,BookEntity> rt = new RedisTemplate<>();
        rt.setConnectionFactory(rcf);
        rt.setKeySerializer(new StringRedisSerializer());//key
        rt.setValueSerializer(new Jackson2JsonRedisSerializer<>(BookEntity.class));//value
        return rt;
    }
}
