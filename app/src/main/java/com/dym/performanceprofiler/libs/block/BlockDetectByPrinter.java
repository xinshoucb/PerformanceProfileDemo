package com.dym.performanceprofiler.libs.block;

import android.os.Looper;
import android.util.Printer;

/**
 * BlockDetectByPrinter class
 *
 * 利用looper循环执行msg前后输出的日志来检查是否有耗时操作
 *
 * @author hangwei
 * @date 2018/12/7
 */
public class BlockDetectByPrinter implements IBlockDetector {

    @Override
    public void start() {
        Looper.getMainLooper().setMessageLogging(new Printer() {

            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";

            @Override
            public void println(String x) {
                if (x.startsWith(START)) {
                    BlockMonitor.getInstance().startMonitor();
                }
                if (x.startsWith(END)) {
                    BlockMonitor.getInstance().removeMonitor();
                }
            }
        });
    }

    @Override
    public void stop() {
        BlockMonitor.getInstance().release();
    }
}
