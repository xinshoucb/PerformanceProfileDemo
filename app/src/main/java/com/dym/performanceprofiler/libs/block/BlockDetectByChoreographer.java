package com.dym.performanceprofiler.libs.block;

import android.view.Choreographer;

/**
 * BlockDetectByChoreographer class
 *
 * 利用帧渲染回调消息来检查是否有耗时操作导致帧刷新不及时。
 * 此方案有个缺点是无法检查Application启动过程的耗时操作，因为在第一个Activity创建完成没有帧渲染，也就没有对应的回调消息。
 *
 * @author hangwei
 * @date 2018/12/7
 */
public class BlockDetectByChoreographer implements IBlockDetector {

    @Override
    public void start() {
        Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long l) {
                // 移除上一个monitor
                BlockMonitor.getInstance().removeMonitor();

                BlockMonitor.getInstance().startMonitor();
                Choreographer.getInstance().postFrameCallback(this);
            }
        };

        Choreographer.getInstance().postFrameCallback(frameCallback);
    }

    @Override
    public void stop() {
        BlockMonitor.getInstance().release();
    }
}
