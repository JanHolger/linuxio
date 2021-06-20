package eu.bebendorf.linuxio.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AddressFamily {

    UNIX((short) 1, 108),
    INET((short) 2, 4),
    AX25((short) 3, 0),
    IPX((short) 4, 0),
    APPLETALK((short) 5, 0),
    NETROM((short) 6, 0),
    BRIDGE((short) 7, 0),
    AAL5((short) 8, 0),
    X25((short) 9, 0),
    INET6((short) 10, 16);

    final short value;
    final int addressLength;

    public static AddressFamily fromValue(short value) {
        for(AddressFamily af : values()) {
            if(af.value == value)
                return af;
        }
        return null;
    }

}
