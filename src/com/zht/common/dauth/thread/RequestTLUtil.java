package com.zht.common.dauth.thread;

import java.util.HashMap;
import java.util.Map;

public class RequestTLUtil {
	
	public static void setCurrentReqestInfo(CurrentReqestInfo currentReqestInfo) {
		if(currentReqestInfo!=null){
			RequestThreadLocal.setCurrentReqestInfo(currentReqestInfo);
		}
	}

	public static CurrentReqestInfo getCurrentReqestInfo() {
		return  RequestThreadLocal.getCurrentReqestInfo();
	}

	public static void clearCurrentReqestInfo() {
		RequestThreadLocal.clearCurrentReqestInfo();
	}
	
	
	
	//须知道是在第几次调用
	//ex: List=>loadDataSetFromOneEntity@1,loadDataSetFromOneEntity@2,loadDataSetFromOneEntity@3
	public static void addDaoAcessMethed(String daoAcessMethed){
		CurrentReqestInfo currentRInfo=RequestThreadLocal.getCurrentReqestInfo();
		if(currentRInfo!=null){
			Map<String, Integer> map=currentRInfo.getDaoMethedAcessName();
			if(map==null){
				map=new HashMap<String,Integer>();
			}
			if(map.containsKey(daoAcessMethed)){
				Integer count=map.get(daoAcessMethed);
				if(count==null){
					map.put(daoAcessMethed, 1);
				}else{
					map.put(daoAcessMethed, 1+count);
				}
			}else{
				map.put(daoAcessMethed, 1);
			}
			currentRInfo.setDaoMethedAcessName(map);
			RequestThreadLocal.setCurrentReqestInfo(currentRInfo);
		}
	}
	
	public static int getDaoAcessMethedCurrentCallTime(String daoAcessMethed){
		CurrentReqestInfo currentRInfo=RequestThreadLocal.getCurrentReqestInfo();
		if(currentRInfo!=null){
			Map<String, Integer> map=currentRInfo.getDaoMethedAcessName();
			if(map==null||map.size()==0){
				return 1;
			}
			if(map.containsKey(daoAcessMethed)){
				Integer count=map.get(daoAcessMethed);
				if(count==null){
					return 1;
				}else{
					return count;
				}
			}else{
				return 1;
			}
		}
		return 1;
	}
}
