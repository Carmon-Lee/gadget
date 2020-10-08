package type;

import org.openjdk.jol.info.ClassLayout;

import java.util.Arrays;

public class ObjectData {

    public static void main(String[] args) {
        Integer a = new Integer(1);
        String s = "string";
        System.out.println(ClassLayout.parseInstance(new int[]{}).toPrintable());

        Arrays.sort(new int[][]{}, (o1,o2)->{
            return o1[0]-o2[0];
        });
    }
}
