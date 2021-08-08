package ip;

import java.nio.ByteBuffer;

public class IpFrame {

    // 20 bytes
    private byte[] header;
    private byte[] body;

    public IpFrame(byte[] header, byte[] body) {
        this.header = header;
        this.body = body;
    }

    static class IpFrameBuilder {
        // version(4)+IHL(4)+dscp(6)+ecn(2)+totalLength(16)
        private int version; // 4 for ipv4, 6 for ipv6
        private int ihl; // internet header length, represented in 32-byte word size
        private byte tos;
        private short totalLength; // header+data
        // id(16)+flags(3)+offset(13)
        private short identification;
        // bit 0:reserved; bit 1:dont fragment; bit 2:more fragments
        private byte flags;
        // when fragmentation
        private short offset;
        // ttl(8)+protocol(8)+checksum(16)
        private byte ttl;
        private ProtocolEnum protocol;
        private short headerChecksum;
        // sourceAddr(32) nat会修改
        private int sourceAddress;
        // destAddr(32) nat会修改
        private int destAddress;
        // if IHL>5
        private byte[] options;
        private byte[] data;

        public static IpFrameBuilder builder() {
            IpFrameBuilder builder = new IpFrameBuilder();
            builder.setVersion(4);
            builder.setIHL(5);
            builder.setProtocol(ProtocolEnum.TCP);
            return builder;
        }

        public IpFrameBuilder setVersion(int version) {
            this.version = version;
            return this;
        }

        public IpFrameBuilder setIHL(int ihl) {
            this.ihl = ihl;
            return this;
        }

        public IpFrameBuilder notFragment() {
            this.flags = 1 << 1;
            return this;
        }

        public IpFrameBuilder moreFragment() {
            this.flags = 1;
            return this;
        }

        public IpFrameBuilder setFragmentOffset(short offset) {
            if (offset == 0) {
                return this;
            }
            this.offset = offset;
            this.flags = 0;
            return this;
        }

        public IpFrameBuilder setProtocol(ProtocolEnum protocol) {
            this.protocol = protocol;
            return this;
        }

        public IpFrameBuilder setSrcAddress(int address) {
            this.sourceAddress = address;
            return this;
        }

        public IpFrameBuilder setDestAddress(int address) {
            this.destAddress = address;
            return this;
        }


        public IpFrameBuilder setData(byte[] data) {
            int dataLength = data == null ? 0 : data.length;
            this.totalLength = (short) (this.ihl * 4 + dataLength);
            this.data = data;
            return this;
        }

        public IpFrame build() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(20);
            byteBuffer.put((byte) ((version << 4) | ihl));
            byteBuffer.put(tos);
            byteBuffer.putShort(totalLength);
            byteBuffer.putShort(identification);
            byteBuffer.putShort((short) ((flags<<13) & offset));
            byteBuffer.put(ttl);
            byteBuffer.put(protocol.getValue());
            byteBuffer.putShort(headerChecksum);
            byteBuffer.putInt(sourceAddress);
            byteBuffer.putInt(destAddress);
            return new IpFrame(byteBuffer.array(), data);
        }

        public IpFrame decode(byte[] header, byte[] data) {
            return null;
        }

        private void putInt(ByteBuffer byteBuffer, int value) {
        }
    }

    public void send(byte[] content) {

    }


    public static void main(String[] args) {
        IpFrame frame = IpFrameBuilder.builder()
                .setVersion((byte) 4)
                .setSrcAddress((127 << 24) | 1)
                .setDestAddress((172 << 24) | (31 << 16) | (1 << 8) | 1)
                .setData("hello".getBytes())
                .build();


    }
}
