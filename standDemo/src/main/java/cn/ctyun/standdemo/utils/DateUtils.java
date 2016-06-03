package cn.ctyun.standdemo.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author  woniu
 * @version V1.0  CreateTime：2014-6-6 下午5:05:41 
 */
public class DateUtils {
	private static int curDay;
	private static int curMonth;
	private static int curYear;
	private static String date;
	private static int[] dayOfMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30,
			31, 30, 31 };
	private static int[] dayOfMonthLeapYear = { 0, 31, 29, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	private static SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat fmtDate2 = new SimpleDateFormat("yyyyMMdd");

	private static SimpleDateFormat fmtDT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat fmtDT2 = new SimpleDateFormat("yyyyMMddHHmmss");

	/** 格式化工具集合 多线程下会有并发问题 */
	private static final Map<String, DateFormat> fmtMap = new HashMap<String, DateFormat>();

	//线程安全的DateFormat 增加了对不同Pattern的ThreadLocal的缓存
	private static final Map<String, ThreadLocal<SimpleDateFormat>> pool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
	private static final Object lock = new Object();
	public static SimpleDateFormat getDFormat(String pattern) {
		ThreadLocal<SimpleDateFormat> tl = pool.get(pattern);
		if(tl == null) {
			synchronized(lock) {
				tl = pool.get(pattern);
				if(tl == null) {
					final String p = pattern;
					tl = new ThreadLocal<SimpleDateFormat>() {
						protected synchronized SimpleDateFormat initialValue() {
							return new SimpleDateFormat(p);
						}
					};
					pool.put(p, tl);
				}
			}
		}
		return tl.get();
	}
	public static Date toDate(String dateStr, String pattern) {
		try {
			return getDFormat(pattern).parse(dateStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/** 将秒格式化为友好的时间描述 */
	public static String friendTime(long s1) {
		String ret = "";
		long[] unit = {1,60,3600,86400,31536000};
		String[] unitStr={"秒","分","小时","天","年"};
		long s = s1;
		
		if(s1<0){
			return "小于0";
		}
		long v = 0;
		for(int i = unit.length-1;i>=0;i--){
			if(v==0&& ret.length()>0){
				ret +="零";
			}
			v = s/unit[i];
			if(v!=0){
				ret +=v+unitStr[i];
			}
			s = s%unit[i];
		}
		return ret;
	}

	/**
	 * @param ex 格式化表达式
	 * @return 格式化工具
	 */
	private static DateFormat getFormat(String ex) {
		DateFormat fmt = fmtMap.get(ex);
		if (fmt == null) {
			fmt = new SimpleDateFormat(ex);
			fmtMap.put(ex, fmt);
		}
		return fmt;
	}
	
	/**
	 * 格式化日期
	 * @param date 日期
	 * @param ex 表达式
	 * @return 日期字符串
	 */
	public static String format(Date date, String ex) {
		return getFormat(ex).format(date);
	}

	/**
	 * 解析日期
	 * @param source 日期字符串
	 * @param ex 表达式
	 * @return 日期
	 * @throws java.text.ParseException
	 */
	public static Date parse(String source, String ex) throws ParseException {
		return getFormat(ex).parse(source);
	}



//	public static final String FmtDateTime2 = "yyyy-MM-dd HH:mm";
//	private static DateFormat fmtDT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private static SimpleDateFormat fmtTime = new SimpleDateFormat("HH:mm:ss");

	/** 返回当前日期,日期格式自己传入 */
	public static String getCurTime(String fmt) {
		if("".equals(fmt)) {
			fmt = fmtDT.toPattern();
		}
		return new SimpleDateFormat(fmt).format(new Date());
	}

	/** 返回当前日期, 精确到秒yyyy-MM-dd HH:mm:ss */
	public static String getCurTime() {
		return fmtDT.format(new Date());
	}

	/** 返回当前日期, 精确到秒yyyyMMddHHmmss */
	public static String getCurTime2() {
		return fmtDT2.format(new Date());
	}

	public static String toString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String toString2(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/** 获取当前月份，格式为MM */
	public static String getCurMonth() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(1);
		curMonth = calendar.get(2) + 1;
		String strMonth = null;
		if (curMonth < 10)
			strMonth = "0" + curMonth;
		else {
			strMonth = "" + curMonth;
		}
		date = curYear + strMonth;
		return date;
	}

	/** 由字符串转成日期对象, 提供界面层到对象层转换 */
	public static Date strToDate(String dateStr, String dateFormat)
			throws ParseException {
		if (dateStr == null)
			return null;
		DateFormat dt = null;
		if (dateFormat == null)
			dt = getDateFormat(dateStr);
		else {
			dt = new SimpleDateFormat(dateFormat);
		}
		return dt.parse(dateStr);
	}

	/** 由字符串转成日期对象,提供界面层到对象层转换,根据传入的日期字符串,分析该字符串的格式 */
	public static Date strToDate(String dateStr) throws ParseException {
		return strToDate(dateStr, null);
	}

	/** 将字符串转换为Calendar */
	public static Calendar strToCalendar(String dateStr) throws ParseException {
		Date date = strToDate(dateStr);
		return dateToCalendar(date);
	}

	/** 将字符串转换为Calendar,根据传入的日期字符串，分析该字符串的格式 */
	public static Calendar strToCalendar(String dateStr, String dateFmt)
			throws ParseException {
		Date date = strToDate(dateStr, dateFmt);
		return dateToCalendar(date);
	}

	public static Timestamp strToTimestamp(String dateStr)
			throws ParseException {
		Calendar cal = strToCalendar(dateStr);
		return calToTimeStamp(cal);
	}

	/** 根据传入的日期格式化特征，格式化当前日期 */
	public static String getDateStr(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}

	/** 获取当前日期YYYY.MM.DD */
	public static String getCurDate3() {
		String curDate = getCurDate();
		return curDate.substring(0,4) + "." + curDate.substring(4, 6) + "." + curDate.substring(6, 8);
	}

	/** 获取当前日期YYYYMMDD */
	public static String getCurDate() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(1);
		curMonth = calendar.get(2) + 1;
		curDay = calendar.get(5);
		String strDay = null;
		String strMonth = null;
		if (curDay < 10)
			strDay = (new StringBuilder("0")).append(curDay).toString();
		else
			strDay = (new StringBuilder()).append(curDay).toString();
		if (curMonth < 10)
			strMonth = (new StringBuilder("0")).append(curMonth).toString();
		else
			strMonth = (new StringBuilder()).append(curMonth).toString();
		date = (new StringBuilder()).append(curYear).append(strMonth)
				.append(strDay).toString();
		return date;
	}

	/** 获取当前日期YYYY-MM-DD */
	public static String getCurDate2() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(1);
		curMonth = calendar.get(2) + 1;
		curDay = calendar.get(5);
		String strDay = null;
		String strMonth = null;
		if (curDay < 10)
			strDay = (new StringBuilder("0")).append(curDay).toString();
		else
			strDay = (new StringBuilder()).append(curDay).toString();
		if (curMonth < 10)
			strMonth = (new StringBuilder("0")).append(curMonth).toString();
		else
			strMonth = (new StringBuilder()).append(curMonth).toString();
		date = (new StringBuilder()).append(curYear).append("-")
				.append(strMonth).append("-").append(strDay).toString();
		return date;
	}

	/** 获取当前年份 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(1);
	}

	/** 获取当前月份 */
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return (cal.get(2) + 1);
	}

	/** 获取当年一月份 */
	public static String getFirstMonthOfThisYear() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(1);
		date = (new StringBuilder()).append(curYear).append("01").toString();
		return date;
	}

	/** 获取当前年月 */
	public static String getCurrentYearMonth() {
		int year = getCurrentYear();
		int month = getCurrentMonth();
		String yearMonth = "";
		if (month >= 10)
			yearMonth = (new StringBuilder(String.valueOf(String.valueOf(year))))
					.append(String.valueOf(month)).toString();
		else
			yearMonth = (new StringBuilder(String.valueOf(String.valueOf(year))))
					.append("0").append(String.valueOf(month)).toString();
		return yearMonth;
	}

	/** 获取上月 */
	public static String getPreYearMonth() {
		int year = getCurrentYear();
		int month = getCurrentMonth() - 1;
		String yearMonth = "";
		if (month == 1) {
			--year;
			month = 12;
		}
		if (month >= 10) {
			yearMonth = String.valueOf(year) + String.valueOf(month);
		} else {
			yearMonth = String.valueOf(year) + "0" + String.valueOf(month);
		}
		return yearMonth;
	}

	/** 获取当前周 */
	public static int getCurrentWeek() {
		return getWeekOfYear(Calendar.getInstance());
	}

	/** 获取当前周 */
	public static int getWeekOfYear(Calendar cal) {
		return cal.get(3);
	}

	/** 获取指定周的起始日期 */
	public static Calendar getWeekStartDate(int year, int week) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, week);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return cal;
	}

	/** 获取指定周的结束日期 */
	public static Calendar getWeekEndDate(int year, int week) {
		Calendar cal = getWeekStartDate(year, week);
		// 加上6天得到周末的日期
		cal.add(Calendar.DAY_OF_YEAR, 6);
		return cal;
	}

	/** 获取当天 */
	public static int getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(5);
	}

	/** 获取当月第一天 */
	public static String getFirstDayOfThisMonth() {
		String preDay = getPreday();
		return (new StringBuilder(String.valueOf(preDay.substring(0, 5))))
				.append("01").toString();
	}

	/** 判断是否是闰年 */
	public static boolean curYearIsLeapYear() {
		GregorianCalendar gCalendar = new GregorianCalendar();
		curYear = gCalendar.get(1);
		return gCalendar.isLeapYear(curYear);
	}

	/** 获取昨天 */
	public static String getPreday() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);
		Date yesterday = today.getTime();
		return fmtDate2.format(yesterday);
	}

	/** 获取昨天 */
	public static String getPreday2() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);
		Date yesterday = today.getTime();
		return fmtDate.format(yesterday);
	}

	/** 获取昨天 */
	public static String getPreday3() {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);
		return new SimpleDateFormat("M月d日").format(today.getTime());
	}

	/** 获取明天 */
	public static String getNextday(Date d) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(d);
		ca.add(Calendar.DATE, 1);
		return fmtDate.format(ca.getTime());
	}

	/** 获取下个月 */
	public static String getNextMonth() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(1);
		curMonth = calendar.get(2) + 1;
		if (curMonth == 12) {
			curYear++;
			curMonth = 1;
		} else {
			curMonth++;
		}
		String strMonth = null;
		if (curMonth < 10)
			strMonth = (new StringBuilder("0")).append(curMonth).toString();
		else
			strMonth = (new StringBuilder()).append(curMonth).toString();
		date = (new StringBuilder()).append(curYear).append(strMonth)
				.toString();
		return date;
	}

	/** 今年已过月份列表 */
	public static ArrayList<String> getListMonths() {
		ArrayList<String> months = new ArrayList<String>(12);
		Calendar datet = Calendar.getInstance();
		int tyear = datet.get(2) != 0 ? datet.get(1) : datet.get(1) - 1;
		int tmonth = datet.get(2) != 0 ? datet.get(2) : 12;
		for (int i = 0; i < tmonth; i++)
			months.add(
					i,
					(new StringBuilder(String.valueOf(String.valueOf(tyear))))
							.append(i + 1 <= 9 ? (new StringBuilder("0"))
									.append(String.valueOf(i + 1)).toString()
									: String.valueOf(i + 1)).toString());
		return months;
	}

	/** 当前月的前半年的月份 */
	public static List<String> getListHalfYear() {
		List<String> months = new ArrayList<String>();
		Calendar date = Calendar.getInstance();
		if (date.get(2) >= 6) {
			months = DateUtils.getYearMonths(date.get(1), date.get(2) - 5,
					date.get(1), date.get(2));
		} else {
			months = DateUtils.getYearMonths(date.get(1) - 1, date.get(2) - 5,
					date.get(1), date.get(2));
			if (months.size() > 6) {
				months = months.subList(months.size() - 6, months.size());
			}
		}
		return months;
	}

	/** 获得指定年月范围内的年月字符串列表 */
    public static List<String> getYearMonths(int startYear, int startMonth,
			int endYear, int endMonth) {
		List<String> lst = new ArrayList<String>();
		for (int i = startYear; i <= endYear; i++) {
			for (int j = 1; j <= 12; j++) {
				if (((i == startYear && j < startMonth) || (i == endYear && j > endMonth))) {
				} else {
					String monthStr = "";
					if (j < 10)
						monthStr = "0" + j;
					else
						monthStr = Integer.toString(j);
					String yearMonth = Integer.toString(i) + monthStr;
					lst.add(yearMonth);
					// System.out.print("\n" + yearMonth);
				}
			}
		}
		return lst;
	}

	/** 获得年月字符串列表,为正表示从该月算去连续多少个月 */
	public static List<String> getYearMonths(String yearMonth, int timeSpan) {
		List<String> lst = new ArrayList<String>();
		int end_year = paraseYear(yearMonth);
		int end_month = paraseMonth(yearMonth);
		int start_year = 0;
		int start_month = 0;
		// 说明没有跨年度
		if (end_month - timeSpan > 0) {
			start_year = end_year;
			start_month = end_month - timeSpan;
		} else {
			start_year = end_year - 1;
			start_month = 12 - (timeSpan - end_month);// mod 12
		}
		lst = getYearMonths(start_year, start_month, end_year, end_month);
		return lst;
	}

	/** 获得上一个月之前timeSpan个月的年月字符串列表 */
	public static List<String> getYearMonth(int timeSpan) {
		List<String> lst = new ArrayList<String>();
		String curDate = getCurLastMonth();
		lst = getYearMonths(curDate, timeSpan);
		return lst;

	}

	/** 返回当前月份前一月的日期字符串 格式:YYYYMM */
	public static String getCurLastMonth() {
		Calendar calendar = Calendar.getInstance();
		curYear = calendar.get(Calendar.YEAR);
		curMonth = calendar.get(Calendar.MONTH) + 1;
		if (curMonth == 1) {// 如果是1月,上一月为前一年12月
			curYear--;
			curMonth = 12;
		} else {// 其他月份,月份减1,取得此月份最后一天
			curMonth--;
		}
		String strMonth = null;
		if (curMonth < 10) {
			strMonth = "0" + curMonth;
		} else {
			strMonth = "" + curMonth;
		}
		date = "" + curYear + strMonth;
		// System.out.println(date);
		return date;
	}

	/** 解析年月字符串得到年 */
	public static int paraseYear(String yearMonth) {
		String year = yearMonth.substring(0, 4);
		return Integer.parseInt(year);
	}

	/** 解析年月字符串得到月 */
	public static int paraseMonth(String yearMonth) {
		String month = yearMonth.substring(4);
		return Integer.parseInt(month);
	}

	/** 根据传入的日期字符串，自动判断用哪种日期格式解析 */
	public static DateFormat getDateFormat(String dateStr) {
		int pos1 = dateStr.indexOf("-");
		int pos2 = dateStr.indexOf(":");
		DateFormat dt = null;
		if ((pos1 != -1) && (pos2 != -1))
			dt = fmtDT;
		else if ((pos1 != -1) && (pos2 == -1))
			dt = fmtDate;
		else if ((pos1 == -1) && (pos2 != -1))
			dt = fmtTime;
		else {
			dt = fmtDate;
		}
		return dt;
	}

	/** 清除2011-09-02中的-分隔符 */
	public static String cleanTokenizer(String date) {
		StringTokenizer tokenizer = new StringTokenizer(date, "-");
		String datelist;
		String tokenlist;
		for (datelist = ""; tokenizer.hasMoreElements(); datelist = (new StringBuilder(
				String.valueOf(datelist))).append(tokenlist).toString())
			tokenlist = (String) tokenizer.nextElement();

		return datelist;
	}

	/** 日期转化为大小写 */
	public static String dataToUpper(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return numToUpper(year) + "年" + monthToUppder(month) + "月"
				+ dayToUppder(day) + "日";
	}

	/** 将数字转化为大写 */
	public static String numToUpper(int num) {
		// String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
		String u[] = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(num).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	/** 月转化为大写 */
	public static String monthToUppder(int month) {
		if (month < 10) {
			return numToUpper(month);
		} else if (month == 10) {
			return "十";
		} else {
			return "十" + numToUpper(month - 10);
		}
	}

	/** 日转化为大写 */
	public static String dayToUppder(int day) {
		if (day < 20) {
			return monthToUppder(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十";
			} else {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十"
						+ numToUpper(Integer.parseInt(str[1] + ""));
			}
		}
	}

	/** 返回传入时间的字符串格式 */
    public static String datetoStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String curDate = sdf.format(date);
        return curDate;
    }

	/** 返回当前时间的字符串格式 */
    public static String datetoStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String curDate = sdf.format(new Date());
        return curDate;
    }

	/** 由字符串转成日期对象 */
	public static Timestamp dateToTimeStamp(Date date) {
		Calendar cal = dateToCalendar(date);
		return calToTimeStamp(cal);
	}

	/** 将timestamp转换为calendar */
	public static Calendar timeStampToCal(Timestamp tim) {
		if (tim == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tim);
		return calendar;
	}

	/** 时间戳转换为Date */
	public static Date timeStampToDate(Timestamp tim) {
		return timeStampToCal(tim).getTime();
	}

	/** 日期转化为Calendar */
	public static Calendar dateToCalendar(Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}
	}

	/** Calendar转化为Timestamp */
	public static Timestamp calToTimeStamp(Calendar cal) {
		Timestamp tm = null;
		if (cal == null)
			tm = null;
		else
			tm = new Timestamp(cal.getTime().getTime());
		return tm;
	}

	/** 根据传入的开始日期（Calendar）和结束日期（Calendar），计算他们之间间隔了多少天,如果开始日期在结束日期之后则返回负值 */
	public static int getBetweenDays(Calendar beginCal, Calendar endCal) {
		int betweenDays = 0;
		if (beginCal.before(endCal)) {// 如果开始日期在结束日期之前，则直接调用getBetweenDays1，否则将2个时间对调
			betweenDays = getBetweenDaysOrder(beginCal, endCal);
		} else {
			betweenDays = -getBetweenDaysOrder(endCal, beginCal);
		}
		return betweenDays;
	}

	/** 根据传入的开始日期（Calendar）和结束日期（Calendar），计算他们之间间隔了多少天，注意开始日期必须在结束日期之前或为同一天 */
	public static int getBetweenDaysOrder(Calendar beginCal, Calendar endCal) {
		int betweenDays = 0;
		int begin_year = beginCal.get(Calendar.YEAR);
		int end_year = endCal.get(Calendar.YEAR);
		int leapYearCount = 0;// 闰年计数器
		for (int i = begin_year; i < end_year; i++) {// 在开始时间和结束时间中寻找闰年数
			if (isLeapYear(i)) {// 如果是闰年则闰年计数器加1
				leapYearCount++;
			}
		}
		int betweenYear = end_year - begin_year;// 开始时间和结束时间的间隔年数
		int begin_totalDays = getTotalDays(beginCal);// 获得开始日期距离当年年初的天数
		int end_totalDays = getTotalDays(endCal);// 获得结束日期距离当年年初的天数
		if (betweenYear == 0) {// 如果是同一年，直接用结束日期距年初的天数减去开始日期距年初的天数，便得到间隔天数
			betweenDays = end_totalDays - begin_totalDays;
		} else {// 如果不是同一年，则需要考虑期间经过了多少个闰年，得出计算公式如下
			betweenDays = end_totalDays - begin_totalDays + 366 * leapYearCount
					+ (betweenYear - leapYearCount) * 365;
		}
		return betweenDays;
	}
	
	/** 时间相差秒数 */
	public static int getBetweenSecond(Date beginDate, Date endDate) {
		if(beginDate == null ){
			return 0;
		}
		if(endDate == null ){
			return 0;
		}
		long a = beginDate.getTime();
		long b = endDate.getTime();
		long c = b - a;
		if(c <= 0){
			return 0;
		}
		int d = (int) (c / 1000);
		return d;
	}

	/** 是否闰年 */
	public static boolean isLeapYear(int curYear) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		return gCalendar.isLeapYear(curYear);
	}

	/** 今年已经过去的天数 */
	public static int getTotalDays(Calendar cal) {
		int totalDays = 0;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		if (isLeapYear(year)) {// 如果是闰年
			for (int i = 0; i < month; i++) {// 循环取得前几个月的天数和
				totalDays += dayOfMonthLeapYear[i];
			}
		} else {
			for (int i = 0; i < month; i++) {// 循环取得前几个月的天数和
				totalDays += dayOfMonth[i];
			}
		}
		totalDays += day;
		return totalDays;
	}

	/** 获取访问年下拉列表的年份，长度为近十年，默认取2010年及往后的年份 */
	private static List<String[]> getRecentYears() {
		List<String[]> list = new ArrayList<String[]>();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		for (int i = 0; i < 10; i++) {
			int upYear = year - i;
			String yearValue = String.valueOf(upYear);
			String yearName = yearValue + "年";
			String[] recentYear = { yearValue, yearName };
			if (upYear >= 2010) {
				list.add(recentYear);
			}
		}
		return list;
	}
	
	/** int转字符串（在小于10的整形前加0） */
	public static String formatLessThanTen(int input){
		if(input<10)
			return "0" + input;
		else
			return String.valueOf(input);
	}


	public static void main(String[] args) {

		System.out.println(getTotalDays(Calendar.getInstance()));
		
		System.out.println(dataToUpper(new Date()));

		for (String[] s : (List<String[]>) getRecentYears()) {
			System.out.println(s[0] + "   " + s[1]);
		}
	}
}
