package threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author guang.li
 * @version CancelTaskTest.java v 1.0 2020/10/8 11:20
 */
public class CancelTaskTest {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        // 1、执行过程中的异常
        Future<?> result1 = executorService.submit(() -> {
            throw new RuntimeException("Exception while executing");
        });

        // 调用future的get方法时，会将执行过程中的异常抛出
        try {
            System.out.println(result1.get());
        } catch (Exception e) {
            System.out.println("Exception occurred while getting task-1 result!!!");
            System.out.println(e);
        }


        // 2、取消执行过程中的任务
        Future<?> result2 = executorService.submit(() -> {
            try {
                System.out.println("ready to execute");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Task-2 has been cancelled while executing!!!");
                System.out.println(e);
            }
        });

        System.out.println("cancel task");
        result2.cancel(true);

        try {
            Thread.sleep(100);
            result2.get();
        } catch (Exception e) {
            System.out.println("Exception occurred while getting task-2 result!!!");
            System.out.println(e);
        }
    }
}
