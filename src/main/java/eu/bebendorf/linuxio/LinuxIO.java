package eu.bebendorf.linuxio;

import eu.bebendorf.linuxio.iface.InterfaceRequest;
import eu.bebendorf.linuxio.socket.AddressFamily;
import eu.bebendorf.linuxio.socket.SocketType;

import java.io.*;
import java.lang.reflect.Field;

public class LinuxIO {

    private static Field fdField;

    static  {
        try {
            fdField = FileDescriptor.class.getDeclaredField("fd");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        File f = new File("linuxio.so");
        try {
            InputStream in = LinuxIO.class.getClassLoader().getResourceAsStream("linuxio.so");
            FileOutputStream fos = new FileOutputStream(f);
            int r;
            byte[] buffer = new byte[1024];
            while ((r = in.read(buffer)) != -1)
                fos.write(buffer, 0, r);
            in.close();
            fos.close();
            f.deleteOnExit();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.load(f.getAbsolutePath());
    }

    public static int getFileDescriptor(RandomAccessFile file) {
        try {
            fdField.setAccessible(true);
            return (int) fdField.get(file.getFD());
        } catch (IOException | IllegalAccessException e) {
            return 0;
        }
    }

    public static native int socket(int family, int type, int protocol);

    public static int socket(AddressFamily family, SocketType type, int protocol) {
        return socket(family.getValue(), type.getValue(), protocol);
    }

    public static native int ioctl(int fd, long request, byte[] data);

    public static int ioctl(int fd, IOCTLRequest request, byte[] data) {
        return ioctl(fd, request.getValue(), data);
    }

    public static int ioctl(int fd, IOCTLRequest request, InterfaceRequest data) {
        byte[] buffer = data.build();
        int err = ioctl(fd, request, buffer);
        data.set(buffer);
        return err;
    }

    public static native int open(String path, int flags);

    public static native int close(int fd);

    public static native int read(int fd, byte[] buffer, int length);

    public static int read(int fd, byte[] buffer) {
        return read(fd, buffer, buffer.length);
    }

    public static native int write(int fd, byte[] buffer, int length);

    public static int write(int fd, byte[] buffer) {
        return write(fd, buffer, buffer.length);
    }

}
