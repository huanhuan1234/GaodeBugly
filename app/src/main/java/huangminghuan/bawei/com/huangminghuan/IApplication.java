package huangminghuan.bawei.com.huangminghuan;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class IApplication extends Application {


    /**
     * 为了保证运营数据的准确性，建议不要在异步线程初始化Bugly。
     * <p>
     * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
     * <p>
     * 输出详细的Bugly SDK的Log；
     * 每一条Crash都会被立即上报；
     * 自定义日志将会在Logcat中输出。
     * <p>
     * 建议在测试阶段建议设置成true，发布时设置为false。
     */

    private static IApplication iApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        iApplication = this;

        CrashReport.initCrashReport(iApplication, "3da91bb99e", false);

        Log.e("hhh iApplication",iApplication+"");

    }

//    其中获取进程名的方法“getProcessName”有多种实现方法，推荐方法如下：
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
