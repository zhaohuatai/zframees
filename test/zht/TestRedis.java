package zht;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zht.framework.redis.RedisService;


	public class TestRedis {
		
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
