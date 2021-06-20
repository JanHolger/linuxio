package eu.bebendorf.linuxio.socket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Setter
@Getter
@NoArgsConstructor
public class SockAddrIn {

    int port;
    byte[] address;

    public SockAddrIn(String address, int port) {
        setAddress(address).setPort(port);
    }

    public SockAddrIn setAddress(byte[] address) {
        this.address = address;
        return this;
    }

    public SockAddrIn setAddress(String address) {
        try {
            return setAddress(Inet4Address.getByName(address).getAddress());
        } catch (UnknownHostException e) {
            return setAddress(new byte[AddressFamily.INET.getAddressLength()]);
        }
    }

}
