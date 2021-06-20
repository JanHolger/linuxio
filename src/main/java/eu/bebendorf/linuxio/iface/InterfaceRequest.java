package eu.bebendorf.linuxio.iface;

import eu.bebendorf.linuxio.socket.AddressFamily;
import eu.bebendorf.linuxio.socket.SockAddr;
import eu.bebendorf.linuxio.socket.SockAddrIn;
import eu.bebendorf.linuxio.socket.SockAddrIn6;
import lombok.NoArgsConstructor;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class InterfaceRequest {

    final ByteBuffer bb = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);

    public InterfaceRequest(byte[] source) {
        bb.put(source);
    }

    public InterfaceRequest setName(String name) {
        if(name == null)
            name = "";
        byte[] b = name.getBytes(StandardCharsets.UTF_8);
        if(b.length > 15)
            throw new IllegalArgumentException("name may only be up to 15 bytes long");
        ((Buffer) bb).position(0);
        bb.put(b);
        bb.put((byte) 0);
        return this;
    }

    public InterfaceRequest setSockAddr(SockAddr addr) {
        ((Buffer) bb).position(16);
        bb.putShort(addr.getFamily().getValue());
        bb.put(addr.getAddress());
        return this;
    }

    public InterfaceRequest setSockAddrIn(SockAddrIn addrIn) {
        ((Buffer) bb).position(16);
        bb.putShort(AddressFamily.INET.getValue());
        writeUShort(addrIn.getPort());
        bb.put(addrIn.getAddress(), 0, AddressFamily.INET.getAddressLength());
        bb.position(36);
        bb.put((byte) 3);
        return this;
    }

    public InterfaceRequest setSockAddrIn6(SockAddrIn6 addrIn6) {
        ((Buffer) bb).position(16);
        bb.putShort(AddressFamily.INET6.getValue());
        writeUShort(addrIn6.getPort());
        writeUInt(addrIn6.getFlowinfo());
        bb.put(addrIn6.getAddress(), 0, AddressFamily.INET6.getAddressLength());
        writeUInt(addrIn6.getScopeId());
        return this;
    }

    public String getName() {
        byte[] buffer = new byte[16];
        ((Buffer) bb).position(0);
        bb.get(buffer);
        String s = new String(buffer, StandardCharsets.UTF_8);
        return s.split("\0")[0];
    }

    public InterfaceRequest setFlags(int flags) {
        ((Buffer) bb).position(16);
        writeUShort(flags);
        return this;
    }

    public InterfaceRequest addFlags(int flags) {
        return setFlags(getFlags() | flags);
    }

    public InterfaceRequest removeFlags(int flags) {
        return setFlags(getFlags() & ~flags);
    }

    public int getFlags() {
        ((Buffer) bb).position(16);
        return readUShort();
    }

    public InterfaceRequest setIndex(int index) {
        ((Buffer) bb).position(16);
        bb.putInt(index);
        return this;
    }

    public int getIndex() {
        ((Buffer) bb).position(16);
        return bb.getInt();
    }

    public InterfaceRequest setMTU(int index) {
        ((Buffer) bb).position(16);
        bb.putInt(index);
        return this;
    }

    public int getMTU() {
        ((Buffer) bb).position(16);
        return bb.getInt();
    }

    public InterfaceRequest setSlave(String slave) {
        if(slave == null)
            slave = "";
        byte[] b = slave.getBytes(StandardCharsets.UTF_8);
        if(b.length > 15)
            throw new IllegalArgumentException("slave may only be up to 15 bytes long");
        ((Buffer) bb).position(16);
        bb.put(b);
        bb.put((byte) 0);
        return this;
    }

    public String getSlave() {
        byte[] buffer = new byte[16];
        ((Buffer) bb).position(0);
        bb.get(buffer);
        String s = new String(buffer, StandardCharsets.UTF_8);
        return s.split("\0")[0];
    }

    public InterfaceRequest setNewName(String newName) {
        if(newName == null)
            newName = "";
        byte[] b = newName.getBytes(StandardCharsets.UTF_8);
        if(b.length > 15)
            throw new IllegalArgumentException("newName may only be up to 15 bytes long");
        ((Buffer) bb).position(16);
        bb.put(b);
        bb.put((byte) 0);
        return this;
    }

    public String getNewName() {
        byte[] buffer = new byte[16];
        ((Buffer) bb).position(0);
        bb.get(buffer);
        String s = new String(buffer, StandardCharsets.UTF_8);
        return s.split("\0")[0];
    }

    public InterfaceRequest set(byte[] data) {
        ((Buffer) bb).position(0);
        bb.put(data);
        return this;
    }

    public byte[] build() {
        ((Buffer) bb).position(0);
        byte[] buffer = new byte[bb.capacity()];
        bb.get(buffer);
        return buffer;
    }

    private void writeUShort(int value) {
        ByteBuffer ibb = ByteBuffer.allocate(4);
        ibb.putInt(value);
        ((Buffer) ibb).position(2);
        bb.putShort(ibb.getShort());
    }

    private int readUShort() {
        ByteBuffer ibb = ByteBuffer.allocate(4);
        ((Buffer) ibb).position(2);
        ibb.putShort(bb.getShort());
        ((Buffer) ibb).position(0);
        return ibb.getInt();
    }

    private void writeUInt(long value) {
        ByteBuffer ibb = ByteBuffer.allocate(8);
        ibb.putLong(value);
        ((Buffer) ibb).position(4);
        bb.putInt(ibb.getInt());
    }

    private long readUInt() {
        ByteBuffer ibb = ByteBuffer.allocate(8);
        ((Buffer) ibb).position(4);
        ibb.putInt(bb.getInt());
        ((Buffer) ibb).position(0);
        return ibb.getLong();
    }

}
