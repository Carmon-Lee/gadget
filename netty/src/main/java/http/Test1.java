package http;

/**
 * @author liguang
 * Created on 2021-09-01
 */
public class Test1 {


    static long NumberOfCreatedInstances = 0;
    static long NumberOfDeletedInstances = 0;

    public Test1() {
        NumberOfCreatedInstances++;
    }

    static public void main(String args[]) {
        System.out.println("starting....");
        for (int i = 0; ; i++) {
            Test1 obj = new Test1();
            obj = null;
            if (i % 10000000 == 0) {
                System.out.println(
                        NumberOfCreatedInstances - NumberOfDeletedInstances);
            }
        }
    }
}
