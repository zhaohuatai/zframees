package org.zht.framework.redis.spring;

import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
/**
 * 
 * @author zhaohuatai
 *
 * @param <T>
 */
public class ObjectRedisTemplate<T> extends RedisTemplate<String, T>{
    public ObjectRedisTemplate(RedisConnectionFactory connectionFactory,Class<T> clazz) {
 
         RedisSerializer<T> objectSerializer = new Jackson2JsonRedisSerializer<T>(clazz);
 
         RedisSerializer<String> objectKeySerializer = new Jackson2JsonRedisSerializer<String>(String.class);
 
         setKeySerializer(objectKeySerializer);
         setValueSerializer(objectSerializer);
         setHashKeySerializer(objectSerializer);
         setHashValueSerializer(objectSerializer);
 
         setConnectionFactory(connectionFactory);
         afterPropertiesSet();
     }
 
     protected RedisConnection preProcessConnection(RedisConnection connection,boolean existingConnection) {
         return new DefaultStringRedisConnection(connection);
     }
     
     
}
