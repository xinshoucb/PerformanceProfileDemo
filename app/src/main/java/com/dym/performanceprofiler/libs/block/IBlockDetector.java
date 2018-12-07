package com.dym.performanceprofiler.libs.block;

/**
 * IBlockDetector class
 *
 * @author hangwei
 * @date 2018/12/7
 */
public interface IBlockDetector {
    /**
     * 开始检查主线程阻塞
     */
    void start();

    /**
     * 停止检查主线程阻塞
     */
    void stop();
}
