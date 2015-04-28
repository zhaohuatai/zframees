package zht;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

import org.zht.framework.util.ZBeanUtil;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.reflectasm.MethodAccess;

public class FastJsonTest {
	 public static void main(String[] args)   {
		 test3();
		 
   }
		private static void test3() {
			Map  map=new HashMap<String, Object>();
			map.put("name", "zhangsan");
			map.put("age", 20);
		    UserInfo info=(UserInfo) ZBeanUtil.convertMapToBean(map, UserInfo.class);
			String str_json=JSON.toJSONString(info);
			System.out.println(str_json);
			System.out.println("-----------------------------");
			 Map map32=ZBeanUtil.convertBeanToMap(info);
			 
			 String str_info_json=JSON.toJSONString(map);
			System.out.println(str_info_json);
		}
	private static void test2(){
			  UserInfo info=new UserInfo();  
		      info.setName("zhangsan");  
//		      info.setAge(24);  
		      Map map=ZBeanUtil.convertBeanToMap(info);
			  String str_json=JSON.toJSONString(map);
			  System.out.println(str_json);
		}
	private static void test1(){
		  UserInfo info=new UserInfo();  
	      info.setName("zhangsan");  
//	      info.setAge(24);  
		  String str_json=JSON.toJSONString(info);
		  System.out.println(str_json);
	}
	
	private static void test4(){
		  UserInfo info=new UserInfo();  
	      info.setName("zhangsan");  
	      info.setAge(24);  
		 MethodAccess access = MethodAccess.get(UserInfo.class);  
		  String name = (String)access.invoke(info, "getName");  
		  int age = (int)access.invoke(info, "getAge");  
		  System.out.println(name+" "+age);
		 
	}
}
