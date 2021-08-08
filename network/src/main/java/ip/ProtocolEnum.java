package ip;

/**
 * @author liguang
 * Created on 2021-05-10
 */
public enum ProtocolEnum {
    ICMP((byte) 1),
    IGMP((byte) 2),
    TCP((byte) 6),
    UDP((byte) 17),
    SCTP((byte) 132),
    ;

    private byte value;

    ProtocolEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
