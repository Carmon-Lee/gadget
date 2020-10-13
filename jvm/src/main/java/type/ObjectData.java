package type;

import java.util.concurrent.TimeUnit;

public class ObjectData {

    public static void main(String[] args) {

//        try {
//            TimeUnit.SECONDS.sleep(60);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int count = 0;
        for (int i = 0; i < 1000_000; i++) {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("wake up");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (ObjectData.class) {
                count++;
            }
        }




    }
}
