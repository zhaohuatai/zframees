package zht;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.zht.framework.redis.spring.RedisService;


	public class TestRedis {
		
		  public void test4() {
			  ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			  StringRedisTemplate redisTemplate = (StringRedisTemplate) app.getBean("redisTemplate");
			    
			    String key = "spring";
			    ListOperations<String, String> lop = redisTemplate.opsForList();
			    RedisSerializer<String> serializer = new StringRedisSerializer();
			    redisTemplate.setKeySerializer(serializer);
			    redisTemplate.setValueSerializer(serializer);
			    
			  
			    // rt.setDefaultSerializer(serializer);

			    lop.leftPush(key, "aaa");
			    lop.leftPush(key, "bbb");
			    long size = lop.size(key); // rt.boundListOps(key).size();
			    Assert.assertEquals(2, size);
			  }
		  public void test5() {
			  ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			  StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) app.getBean("redisTemplate");
			    ValueOperations<String, String> vop = stringRedisTemplate.opsForValue();
			    String key = "string_redis_template";
			    String v = "use StringRedisTemplate set k v";
			    vop.set(key, v);
			    String value = vop.get(key);
			    Assert.assertEquals(v, value);
			  }
		  public void test61() {
			  ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			  StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) app.getBean("redisTemplate");
			    Long dbsize = (Long) stringRedisTemplate
			        .execute(new RedisCallback<Object>() {
			          @Override
			          public Long doInRedis(RedisConnection connection)
			              throws DataAccessException {
			            StringRedisConnection stringRedisConnection=(StringRedisConnection)connection;
			            return stringRedisConnection.dbSize();
			          }
			        });
			    System.out.println("dbsize:" + dbsize);
			  }
		  public void test62() {
			  ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			  StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) app.getBean("redisTemplate");
			    List<Object> txresult = stringRedisTemplate
			        .execute(new SessionCallback<List<Object>>() {

			          @Override
			          public List<Object> execute(RedisOperations operations)
			              throws DataAccessException {
			            operations.multi();
			            operations.opsForHash().put("hkey", "multikey4",
			                "multivalue4");
			            operations.opsForHash().get("hkey", "k1");

			            return operations.exec();
			          }

			        });
			    for (Object o : txresult) {
			      System.out.println(o);
			      /**
			       * 0. false/true 
			       * 1. v1
			       */
			    }
			  }
		  
		  
		 public static void main(String[] args) throws InterruptedException {
		ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	    //这里已经配置好,属于一个redis的服务接口
	    RedisService redisService = (RedisService) app.getBean("redisService");
	
	    String ping = redisService.ping();//测试是否连接成功,连接成功输出PONG
	    System.out.println(ping);
	
	    //首先,我们看下redis服务里是否有数据
	    long dbSizeStart = redisService.dbSize();
	    System.out.println(dbSizeStart);
	
	    redisService.set("username", "oyhk");//设值(查看了源代码,默认存活时间30分钟)
	    String username = redisService.get("username");//取值 
	    System.out.println(username);
	    redisService.set("username1", "oyhk1", 1);//设值,并且设置数据的存活时间(这里以秒为单位)
	    String username1 = redisService.get("username1");
	    System.out.println(username1);
	    Thread.sleep(2000);//我睡眠一会,再去取,这个时间超过了,他的存活时间
	    String liveUsername1 = redisService.get("username1");
	    System.out.println(liveUsername1);//输出null
	
	    //是否存在
	    boolean exist = redisService.exists("username");
	    System.out.println(exist);
	
	    //查看keys
	    Set<String> keys = redisService.keys("*");//这里查看所有的keys
	    System.out.println(keys);//只有username username1(已经清空了)
	
	    //删除
	    redisService.set("username2", "oyhk2");
	    String username2 = redisService.get("username2");
	    System.out.println(username2);
	    redisService.del("username2");
	    String username2_2 = redisService.get("username2");
	    System.out.println(username2_2);//如果为null,那么就是删除数据了
	
	    //dbsize
	    long dbSizeEnd = redisService.dbSize();
	    System.out.println(dbSizeEnd);
	
	    //清空reids所有数据
	    //redisService.flushDB();
}
}
