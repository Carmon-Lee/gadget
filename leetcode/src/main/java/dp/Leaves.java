package dp;

public class Leaves {

    public int minimumOperations(String leaves) {
        int allRed = 0;
        int redYellow = 0;
        int redYRed = 0;
        for (int i=0;i<leaves.length();i++) {
            if (i==2) {
                redYRed = redYellow + (leaves.charAt(i)=='r'?0:1);
            } else if (i>2) {
                redYRed = Math.min(redYRed, redYellow) + (leaves.charAt(i)=='r'?0:1);
            }
            if (i==1) {
                redYellow = allRed + (leaves.charAt(i)=='y'?0:1);
            } else if (i>1) {
                redYellow = Math.min(allRed, redYellow) + (leaves.charAt(i)=='y'?0:1);
            }
            allRed += leaves.charAt(i)=='r' ? 0 : 1;
            System.out.println(String.format("%d %d %d", allRed, redYellow, redYRed));
        }

        return redYRed;
    }

    public static void main(String[] args) {
        new Leaves().minimumOperations("rrryyyrryyyrr");
    }
}
