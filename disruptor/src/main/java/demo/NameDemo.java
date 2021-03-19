package demo;

/**
 * @author liguang
 * Created on 2021-02-23
 */
public interface NameDemo {

    default String name() {
        return this.getClass().getSimpleName();
    }

    public static void main(String[] args) {
        NameDemo demo = new NameDemoImpl();
        System.out.println(demo.name());

        NameDemo demo2 = new NameDemoImpI2();
        System.out.println(demo2.name());
    }
}
