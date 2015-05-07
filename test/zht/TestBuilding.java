package zht;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/applicationContext.xml"})

@Configuration
@ImportResource("classpath:/applicationContext.xml")
public class TestBuilding {
	
	/**
	 * @param args
	 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	 
	@Autowired
    private RedisTemplate<String, String> template;
	@Test
	@Transactional
	public void testSave() {
		template.execute(new RedisCallback<Object>() {

	            @Override
	            public Object doInRedis(RedisConnection connection) throws DataAccessException {
	                connection.set(
	                		template.getStringSerializer().serialize("user.uid." + "1"),
	                		template.getStringSerializer().serialize("userName1")
	                		);
	                return null;
	            }
	        });

	}
	
	@Test
	@Transactional
	public void   testGet() {
		
		  User u= template.execute(new RedisCallback<User>() {
			  Long id=1L;
	            @Override
	            public User doInRedis(RedisConnection connection) throws DataAccessException {
	                byte[] key = template.getStringSerializer().serialize("user.uid." + id);
	                if (connection.exists(key)) {
	                    byte[] value = connection.get(key);
	                    String name = template.getStringSerializer().deserialize(value);
	                    User user = new User();
	                    user.setName(name);
	                    user.setId(id);
	                    return user;
	                }
	                return null;
	            }
	        });
	}
}
