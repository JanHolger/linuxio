package eu.bebendorf.linuxio.socket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.Inet6Address;
import java.net.UnknownHostException;

@Setter
@Getter
@NoArgsConstructor
public class SockAddrIn6 {

    int port;
    long flowinfo;
    byte[] address;
    long scopeId;

    public SockAddrIn6(String address, int port) {
        setAddress(address).setPort(port);
    }

    public SockAddrIn6 setAddress(byte[] address) {
        this.address = address;
        return this;
    }

    public SockAddrIn6 setAddress(String address) {
        try {
            return setAddress(Inet6Address.getByName(address).getAddress());
        } catch (UnknownHostException e) {
            return setAddress(new byte[AddressFamily.INET6.getAddressLength()]);
        }
    }

}
