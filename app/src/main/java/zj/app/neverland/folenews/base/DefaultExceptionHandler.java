package zj.app.neverland.folenews.base;

import android.content.Context;

/**
 * Created by zj on 2017/4/5.
 */

public class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {


    private Context act = null;


    public DefaultExceptionHandler(Context act) {

        this.act = act;

    }


    @Override

    public void uncaughtException(Thread thread, Throwable ex) {


        // 收集异常信息 并且发送到服务器

        sendCrashReport(ex);


        // 等待半秒

        try {

            Thread.sleep(500);

        } catch (InterruptedException e) {

            //

        }


        // 处理异常

        handleException();


    }


    private void sendCrashReport(Throwable ex) {


        StringBuffer exceptionStr = new StringBuffer();

        exceptionStr.append(ex.getMessage());


        StackTraceElement[] elements = ex.getStackTrace();

        for (int i = 0; i < elements.length; i++) {

            exceptionStr.append(elements[i].toString());

        }


        //TODO

        //发送收集到的Crash信息到服务器

    }


    private void handleException() {

        //TODO

        //这里可以对异常进行处理。

        //比如提示用户程序崩溃了。

        //比如记录重要的信息，尝试恢复现场。

        //或者干脆记录重要的信息后，直接杀死程序。

    }
}
