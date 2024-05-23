import java.text.Format
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date








fun Long.getTodayHHmm(): String {
    val date = Date(this)
    val sdf: Format = SimpleDateFormat("HH:mm")
    return sdf.format(date)
}

val todayStart = Date().apply {
    hours = 0
    minutes = 0
    seconds = 0
}.time // 获取今天开始的时间戳

val todayEnd = Date().apply {
    hours = 23
    minutes = 59
    seconds = 59
}.time // 获取今天结束的时间戳


//获取date对象
var data: Date = Date()
//获取日历对象
var calendar: Calendar = Calendar.getInstance()

//获取今天是星期几
fun Date.getToday_Englishname(): String {
    val list = arrayOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")
    calendar.time = data
    var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
    if (index < 0) {
        index = 0
    }
    return list[index]
}

//获取今天是星期几
fun Date.getToday_Chinaname(): String {
    val list = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    val calendar: Calendar = Calendar.getInstance()
    calendar.time = data
    var index: Int = calendar.get(Calendar.DAY_OF_WEEK) - 1
    if (index < 0) {
        index = 0
    }
    return list[index]
}

//获取当前日期
fun Date.getToday(): String {
    //需要得到的格式
    val sdf: Format = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(data)
}

//获取当前日期
fun Date.getTodayHHmm(): String {
    //需要得到的格式
    val sdf: Format = SimpleDateFormat("HH:mm")
    return sdf.format(data)
}

//获取上周的今天的日期
fun Date.getlastweekToday(): String {
    val sdf: Format = SimpleDateFormat("yyyy-MM-dd")
    val t: Long = calendar.getTimeInMillis()
    val l: Long = t - 24 * 3600 * 1000 * 7
    return sdf.format(l)
}

//获取上个月今天的日期
fun Date.getlastmonthToday(): String {
    val sdf: Format = SimpleDateFormat("yyyy-MM-dd")
    calendar.add(Calendar.MONTH, -1)
    return sdf.format(calendar.time)
}

//获取昨天的日期
fun Date.getDateofYesterday(): String? {
    val sdf: Format = SimpleDateFormat("yyyy-MM-dd")
    val t: Long = calendar.getTimeInMillis()
    val l: Long = t - 24 * 3600 * 1000
    val d: Date = Date(l)
    return sdf.format(d)
}

//获取上个月的第一天
fun Date.getFirstDayOfLastMonth(): String {
    val sdf: Format = SimpleDateFormat("yyyy-MM-dd")
    calendar.set(Calendar.DATE, 1)
    calendar.add(Calendar.MONTH, -1)
    return sdf.format(calendar.time)
}

//获取上个月的最后一天
fun Date.getLastDayOfLastMonth(): String {
    val sdf: Format = SimpleDateFormat("yyyy-MM-dd")
    calendar.set(Calendar.DATE, 1)
    calendar.add(Calendar.MONTH, -1)
    calendar.roll(Calendar.DATE, -1)
    return sdf.format(calendar.time);
}

//判断是否是闰年
fun Date.isLeapYear(year: Int): Boolean {
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        return true
    }
    return false
}


// 字符串日期 获取想要格式的日期格式，栗子："2017—10-10 10:10:10"
fun Date.getTime4String(time: String): String {
    //代转日期的字符串格式(输入的字符串格式)
    val inputsdf: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    //获取想要的日期格式(输出的日期格式)
    val outputsdf: SimpleDateFormat = SimpleDateFormat("HH:mm")
    val date: Date = inputsdf.parse(time)
    return outputsdf.format(date)
}


// 判断两个日期大小  如，第一个日期大于第二个日期，返回true  反之false
fun Date.isDateOneBigger(str1: String, str2: String): Boolean {
    var isBigger: Boolean = false
    //输入的格式，选择性更改
    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    isBigger = sdf.parse(str1).time >= sdf.parse(str2).time
    return isBigger
}

//获取某个日期的前一天
fun Date.getSpecifiedDayBefore(specifiedDay: String): String {
    //输出的日期格式
    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    //自定义过来的String格式的日期
    val date: Date = SimpleDateFormat("yyyy/MM/dd").parse(specifiedDay)
    calendar.time = date
    val day = calendar.get(Calendar.DATE)
    calendar.set(Calendar.DATE, day - 1)
    return sdf.format(calendar.time)
}


//获取某个日期的后一天
fun Date.getSpecifiedDayAfter(specifiedDay: String): String {
    //输出的日期格式
    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    //自定义过来的String格式的日期
    val date: Date = SimpleDateFormat("yyyy/MM/dd").parse(specifiedDay)
    calendar.time = date
    val day = calendar.get(Calendar.DATE)
    calendar.set(Calendar.DATE, day + 1)
    return sdf.format(calendar.time)
}
