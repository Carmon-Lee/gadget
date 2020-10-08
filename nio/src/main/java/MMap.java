import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMap {

    public static void main(String[] args) {
        int length = 1024;
        try (RandomAccessFile file = new RandomAccessFile("howtodoinjava.dat", "rw")) {
            MappedByteBuffer out = file.getChannel()
                    .map(FileChannel.MapMode.READ_WRITE, 0, length);

            for (int i = 0; i < length; i++) {
                Thread.sleep(1000);
                out.put((byte) 'x');
            }

            System.out.println("Finished writing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
