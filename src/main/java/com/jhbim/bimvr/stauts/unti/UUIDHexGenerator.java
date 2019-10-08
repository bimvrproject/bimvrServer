package com.jhbim.bimvr.stauts.unti;

import java.net.InetAddress;

/**
 * 生成随机数工具类
 */
public class UUIDHexGenerator {
    private static String sep = "";
    private static final int IP;
    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }
    private static short counter = (short) 0;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    public static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    /**
     * Unique in a local network
     */
    protected static int getIP() {
        return IP;
    }

    /**
     * Unique down to millisecond
     */
    protected static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    protected static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    /**
     * Unique across JVMs on this machine (unless they load this class in the
     * same quater second - very unlikely)
     */
    protected  static int getJVM() {
        return JVM;
    }

    protected static String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    protected static String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    public static String generate() {
        return new StringBuilder(36).append(format(getIP())).append(sep)
                .append(format(getJVM())).append(sep)
                .append(format(getHiTime())).append(sep)
                .append(format(getLoTime())).append(sep)
                .append(format(getCount())).toString();
    }

    /**
     * Unique in a millisecond for this JVM instance (unless there are >
     * Short.MAX_VALUE instances created in a millisecond)
     */
    protected static short getCount() {
        synchronized (UUIDHexGenerator.class) {
            if (counter < 0)
                counter = 0;
            return counter++;
        }
    }

    public static void main(String[] args) {
        UUIDHexGenerator uuidHexGenerator = new UUIDHexGenerator();
        String generate = uuidHexGenerator.generate();
        System.out.println(generate);
        int ip2 = uuidHexGenerator.getIP();
        System.out.println(ip2);
    }
}

