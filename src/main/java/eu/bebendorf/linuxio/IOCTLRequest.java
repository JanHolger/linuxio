package eu.bebendorf.linuxio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IOCTLRequest {

    SIOCSIFADDR(35094),
    SIOCSIFFLAGS(35092),
    SIOCGIFFLAGS(35091),
    SIOCGIFBRDADDR(35097),
    SIOCSIFBRDADDR(35098),
    SIOCGIFNETMASK(35099),
    SIOCSIFNETMASK(35100),
    SIOCGIFMTU(35105),
    SIOCSIFMTU(35106),
    SIOCSIFNAME(35107),
    TUNSETIFF(1074025674);

    final long value;

}
