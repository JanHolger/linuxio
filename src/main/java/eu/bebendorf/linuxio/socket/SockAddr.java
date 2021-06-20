package eu.bebendorf.linuxio.socket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Getter
@Setter
@NoArgsConstructor
public class SockAddr {

    AddressFamily family;
    byte[] address;

    public SockAddr(AddressFamily family, String address) {
        setFamily(family).setAddress(address);
    }

    public SockAddr setAddress(byte[] address) {
        this.address = address;
        return this;
    }

    public SockAddr setAddress(String address) {
        try {
            return setAddress(InetAddress.getByName(address).getAddress());
        } catch (UnknownHostException e) {
            return setAddress(new byte[AddressFamily.INET.getAddressLength()]);
        }
    }

}
