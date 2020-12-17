package kybmig.ssm;

import kybmig.ssm.model.TodoModel;
import kybmig.ssm.service.RedisSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {
    private RedisConnectionFactory connection;
    private RedisSubscriber redisSubscriber;


    public RedisConfig(RedisConnectionFactory connection, RedisSubscriber redisSubscriber) {
        this.connection = connection;
        this.redisSubscriber = redisSubscriber;
    }


    @Bean
    // 设置 redis GET SET 的序列化
    public RedisTemplate<Integer, TodoModel> todoRedisTemplate() {
        RedisTemplate<Integer, TodoModel> template = new RedisTemplate<>();
        template.setConnectionFactory(this.connection);
        //有许多中序列反序列策略,这里选择类似json格式
        template.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    //配置频道
    RedisMessageListenerContainer container() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connection);
        //配置要订阅的订阅频道:频道名字就是"messgeQueen"
        //RedisSubscriber这个类在service包下
        container.addMessageListener(redisSubscriber, new PatternTopic("messageQueue"));
        return container;
    }
    
}
