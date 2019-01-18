package utils;

import java.net.InetAddress;

/**
 * @author yu 2018/12/19.
 */
public class ServerUtils {

    private static String serverIp;

    static {
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();
            serverIp = ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取服务器的ip地址
     * @return
     */
    public static String getServerIp() {
        return serverIp;
    }
}
