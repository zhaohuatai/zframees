package org.zht.framework.util.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zht.framework.util.LogUtil;
/**
 * 
 * @author zhaohuatai<br/>
 *
 *Calendar ADD方法：以调整的单位为基点（本例中为月），较大的单位（年）会发生借位、进位。 较小的单位会往小调整--》2014-03-31，加上13个月，年份会进位为2015年。 4月31日是不存在的，所以往小调整为4月30日<br/>
 *
 *Calendar ADD方法：所有的单位都会往大调整。--》2014-03-31，加上13个月，年份会进位为2015年。 4月31日是不存在的，所以往大调整为5月1日。<br/>
 *
 */
public class ZDateUtil {
	public static void main(String[] args) {
    	
    	System.out.println("-----------------001--------------------------------");
    	String dd=getCurrentDateStr(ZDateStyle.YYYY_MM_DD);
    	System.out.println("getdefaultDateStart-start: "+ getdefaultDateStart(dd) );
    	System.out.println("getdefaultDateStart-end: "+ getdefaultDateEnd(dd));
    	System.out.println("getdefaultDateStart-start: "+ dateToString(getdefaultDateStart(dd), ZDateStyle.YYYY_MM_DD_HH_MM_SS) );
    	System.out.println("getdefaultDateStart-end: "+ dateToString(getdefaultDateEnd(dd), ZDateStyle.YYYY_MM_DD_HH_MM_SS));
    	
    	System.out.println("getCurrentDate: "+getCurrentDateStr(ZDateStyle.YYYY_MM_DD_HH_MM_SS));
    	System.out.println("-------------------------------------------------");
	} 
	/**
	 * 获取当天开始时间 
	 * @param dateStr 格式:YYYY-MM-DD
	 * @return
	 */
	 public static Date getdefaultDateStart(String dateStr){
		  String str=dateStr+" 00:00:00";
	      return stringToDate(str, ZDateStyle.YYYY_MM_DD_HH_MM_SS);
	 }
	 /**
	  * 获取当当天结束时间 
	  * @param dateStr 格式:YYYY-MM-DD
	  * @return
	  */
	 public static Date getdefaultDateEnd(String dateStr){
		  String str=dateStr+" 23:59:59";
		  return stringToDate(str, ZDateStyle.YYYY_MM_DD_HH_MM_SS);
	 } 
	    /** 
	     * 获取当前日期 
	     * @return 
	     */  
	    public static String getCurrentDateStr(ZDateStyle dateFormat){  
	        return caseFormatter(dateFormat.getValue()).format(new Date());  
	    }  
	    

		/**
		 * 获取日期。默认yyyy-MM-dd格式。失败返回null。
		 * @param date 日期
		 * @return 日期
		 */
		public static String getdefaultFormatDate(Date date) {
			return dateToString(date, ZDateStyle.YYYY_MM_DD);
		}


		/**
		 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
		 * @param date 日期
		 * @return 时间
		 */
		public static String getdefaultFormatTime(Date date) {
			return dateToString(date, ZDateStyle.HH_MM_SS);
		}
		/**
		 * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
		 * @param date 日期
		 * @return 时间
		 */
		public static String getdefaultFormatDateTime(Date date) {
			return dateToString(date, ZDateStyle.YYYY_MM_DD_HH_MM);
		}
		
//-------------------------------String---Date--------S--------------------------------------------    
		/**
		 * 字符串转化为时间格式
		 * @param dateStr ：目标字符串
		 * @param dateFormat： 格式化
		 * @return
		 */
	    public static java.util.Date stringToDate(String dateStr, ZDateStyle dateFormat) {
	        SimpleDateFormat df = caseFormatter(dateFormat.getValue());
	        try {
	            return df.parse(dateStr);
	        } catch (ParseException e) {
	           LogUtil.genErrorLog(ZDateUtil.class, "stringToDate", "日期转化错误", e);
	           return null;
	        }
	    }
	    public static java.util.Date stringToDate(String dateStr, String pattern) {
	    	 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        try {
	            return sdf.parse(dateStr);
	        } catch (ParseException e) {
	           LogUtil.genErrorLog(ZDateUtil.class, "stringToDate", "日期转化错误", e);
	           return null;
	        }
	    }

	public static String dateToString(java.util.Date date, ZDateStyle dateFormat) {
		String result = null;
		try {
			if (date != null) {
				SimpleDateFormat sdf = caseFormatter(dateFormat.getValue());
				result = sdf.format(date);
			}
		} catch (Exception e) {
			LogUtil.genErrorLog(ZDateUtil.class, "dateToString", "日期格式化错误", e);
			return null;
		}
		return result;
	}

	public static String dateToString(java.util.Date date, String format) {
		String result = null;
		try {
			if (date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			}
		} catch (Exception e) {
			LogUtil.genErrorLog(ZDateUtil.class, "dateToString", "日期格式化错误", e);
			return null;
		}
		return result;
	}
//-------------------------------SQL---util----convert-----------------------------------  	    
	public static Date sqlDateToUtilDate(java.sql.Date date) {
		if(date==null){
			return null;
		}
		java.util.Date utilDate=new java.util.Date (date.getTime());
		return utilDate;
	}
	
	public static Date utilDateToSqlDate(java.util.Date date) {
		if(date==null){
			return null;
		}
		java.sql.Date sqlDate=new java.sql.Date( date.getTime());
		return sqlDate;
	}  
//------------------------------------------------------------------------------------- 
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	public static long getMillis(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}
	
//------------------------------------------------------------------------------------- 	
	/**
	 * 
	 * @param date：日期
	 * @param dateType:Calendar 常量
	 * @return
	 */
	public static int getInteger(Date date, int dateType) {
		int num = 0;
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			num = calendar.get(dateType);
		}
		return num;
	}
	/**
	 * 
	 * @param date
	 * @param dateType:Calendar 常量
	 * @param amount:加的值
	 * @return
	 */
	public static Date addInteger(Date date, int dateType, int amount) {
		Date myDate = null;
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(dateType, amount);
			myDate = calendar.getTime();
		}
		return myDate;
	}
	public static Date addYear(Date date, int yearAmount) {
		return addInteger(date, Calendar.YEAR, yearAmount);
	}
	public static Date addMonth(Date date, int monthAmount) {
		return addInteger(date, Calendar.MONTH, monthAmount);
	}
	public static Date addDay(Date date, int dayAmount) {
		return addInteger(date, Calendar.DATE, dayAmount);
	}
	public static Date addHour(Date date, int hourAmount) {
		return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
	}
	public static Date addMinute(Date date, int minuteAmount) {
		return addInteger(date, Calendar.MINUTE, minuteAmount);
	}
	public static Date addSecond(Date date, int secondAmount) {
		return addInteger(date, Calendar.SECOND, secondAmount);
	}
	
	//------------------------------------------------------------------
	/**
	 * 获取日期的星期。失败返回null。
	 * @param date 日期
	 * @return 星期
	 */
	public static ZDateWeek getWeek(Date date) {
		ZDateWeek week = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		switch (weekNumber) {
		case 0:
			week = ZDateWeek.SUNDAY;
			break;
		case 1:
			week = ZDateWeek.MONDAY;
			break;
		case 2:
			week = ZDateWeek.TUESDAY;
			break;
		case 3:
			week = ZDateWeek.WEDNESDAY;
			break;
		case 4:
			week = ZDateWeek.THURSDAY;
			break;
		case 5:
			week = ZDateWeek.FRIDAY;
			break;
		case 6:
			week = ZDateWeek.SATURDAY;
			break;
		}
		return week;
	}
	/** 
     * 判断是平年还是闰年 
     */   
    public static boolean isLeapyear(int year) {  
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0) {  
            return true;  
        } else {  
            return false;  
        }  
    } 
    @SuppressWarnings("deprecation")  
    public static String getMonthLastDay(int month) {  
        Date date = new Date();  
        int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },  
                        { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };  
        int year = date.getYear() + 1900;  
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {  
            return day[1][month] + "";  
        } else {  
            return day[0][month] + "";  
        }  
    }  
    
    public static int getIntervalDays(Date date, Date otherDate) {
		int num = -1;
		Date dateTmp = ZDateUtil.stringToDate(ZDateUtil.getdefaultFormatDate(date), ZDateStyle.YYYY_MM_DD);
		Date otherDateTmp = ZDateUtil.stringToDate(ZDateUtil.getdefaultFormatDate(otherDate), ZDateStyle.YYYY_MM_DD);
		if (dateTmp != null && otherDateTmp != null) {
			long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
			num = (int) (time / (24 * 60 * 60 * 1000));
		}
		return num;
	}
//-------------------------------------------------------------------------------------
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
	private static final Object object = new Object();
    /**
	 * 获取SimpleDateFormat,<br、>线程中单个对象存在，每次调用通过 dateFormat.applyPattern(pattern)创建新的pattern
	 * @param pattern 日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException 异常：非法日期格式
	 */
	private static SimpleDateFormat caseFormatter(String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = threadLocal.get();
		if (dateFormat == null) {
			synchronized (object) {
				if (dateFormat == null) {
					dateFormat = new SimpleDateFormat(pattern);
					dateFormat.setLenient(false);
					threadLocal.set(dateFormat);
				}
			}
		}
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}
//	  public static SimpleDateFormat  caseFormatter(String pattern){
//	    	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
//	    	return dateFormat;
//	    }
	  
//	    public static SimpleDateFormat  caseFormatter(ZDateFormat dateFormat){
//	    	SimpleDateFormat format = null;
//	    	switch (dateFormat) {
//		    	case FORMAT__yyyy_MM_dd:
//		    		format=new SimpleDateFormat("yyyy-MM-dd");
//		    	break;
//		    	case FORMAT_yyyy_MM_dd_HH_mm_ss:
//		    		format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		    	break;
//		    	case FORMAT_yyyyMM:
//		    		format=new SimpleDateFormat("yyyyMM");
//		    	break;
//		    	case FORMAT_yyyyMMdd:
//		    		format=new SimpleDateFormat("yyyyMMdd");
//		    	break;
//		    	case FORMAT_yyyyMMddHHmmss:
//		    		format=new SimpleDateFormat("yyyyMMddHHmmssS");
//		    	break;
//		    	default:
//		    		format=new SimpleDateFormat("yyyy-MM-dd");
//		    	break;
//	    	}
//	    	return format;
//	    }
}
