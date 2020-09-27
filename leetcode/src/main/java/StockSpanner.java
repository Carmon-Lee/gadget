import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author liguang
 * @version StockSpanner.java, v 0.1 2020年09月05日 7:32 上午
 */
class StockSpanner {

    private Deque<Item> stack = new ArrayDeque<>();
    int idx = 0;
    int span = 0;
    Item tmpItem;

    public StockSpanner() {
    }

    public int next(int price) {
        span = 1;
        idx++;
        while (!stack.isEmpty() && stack.peek().price <= price) {
            tmpItem = stack.pop();
            span = Math.max(tmpItem.span + (idx - tmpItem.idx), span);
        }
        stack.push(new Item(idx, price, span));
        return span;
    }

    static class Item {
        int idx;
        int price;
        int span;

        public Item(int idx, int price, int span) {
            this.idx = idx;
            this.price = price;
            this.span = span;
        }
    }


    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));;
        System.out.println(stockSpanner.next(80));;
        System.out.println(stockSpanner.next(60));;
        System.out.println(stockSpanner.next(70));;
        System.out.println(stockSpanner.next(60));;
        System.out.println(stockSpanner.next(75));;
        System.out.println(stockSpanner.next(85));;
    }
}
