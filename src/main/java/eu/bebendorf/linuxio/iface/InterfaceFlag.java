package eu.bebendorf.linuxio.iface;

public class InterfaceFlag {

    public static final int UP = 1;
    public static final int BROADCAST = 2;
    public static final int DEBUG = 4;
    public static final int LOOPBACK = 8;
    public static final int POINTOPOINT = 16;
    public static final int NOTRAILERS = 32;
    public static final int RUNNING = 64;
    public static final int NOARP = 128;
    public static final int PROMISC = 256;
    public static final int ALLMULTI = 512;
    public static final int MASTER = 1024;
    public static final int SLAVE = 2048;
    public static final int MULTICAST = 4096;
    public static final int PORTSEL = 8192;
    public static final int AUTOMEDIA = 16384;
    public static final int DYNAMIC = 32768;
    public static final int LOWER_UP = 65536;
    public static final int DORMANT = 131072;
    public static final int ECHO = 262144;

    // TUNSETIFF only
    public static final int TUN = 1;
    public static final int TAP = 2;
    public static final int NAPI = 16;
    public static final int NAPI_FRAGS = 32;
    public static final int MULTI_QUEUE = 256;
    public static final int ATTACH_QUEUE = 512;
    public static final int DETACH_QUEUE = 1024;
    public static final int PERSIST = 2048;
    public static final int NO_PI = 4096;
    public static final int NOFILTER = 4096;
    public static final int ONE_QUEUE = 8192;
    public static final int VNET_HDR = 16384;
    public static final int TUN_EXCL = 32768;

    private InterfaceFlag() {}

}
