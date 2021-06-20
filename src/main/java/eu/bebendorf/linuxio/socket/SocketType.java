package eu.bebendorf.linuxio.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SocketType {

    STREAM(1),
    DGRAM(2),
    RAW(3),
    RDM(4),
    SEQPACKET(5);

    final int value;

}
