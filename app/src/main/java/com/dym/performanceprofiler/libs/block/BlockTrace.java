package com.dym.performanceprofiler.libs.block;

/**
 * BlockTrace class
 *
 * @author hangwei
 * @date 2018/12/7
 */
public class BlockTrace {

//    private static IBlockDetector detector = new BlockDetectByChoreographer();
    private static IBlockDetector detector = new BlockDetectByPrinter();

    public static void start(){
        detector.start();
    }

    public static void stop(){
        detector.stop();
    }
}
