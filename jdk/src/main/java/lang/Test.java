package lang;

public class Test {


    public int strToInt(String str) {
        long res = 0;
        int sigh = 1;

        char[] chs = str.toCharArray();
        boolean start = false;

        for (int i=0;i<chs.length;i++) {
            if (!start && chs[i]!=' ') {
                if (i<chs.length-1 && (chs[i]=='+' || chs[i]=='-')) {
                    sigh = chs[i]=='-'?-1:1;
                    if (chs[i]=='+' || chs[i]=='-') {
                        i++;
                    }
                }
                start = true;
            }
            if (!start) {
                if (chs[i]!='+' && chs[i]!='-' && chs[i]!=' ' && !(chs[i]>='0' && chs[i]<='9')) {
                    return 0;
                }
                continue;
            }

            if (chs[i]<'0' || chs[i]>'9') {
                break;
            }
            res = res*10+(chs[i]-'0');
            if (Math.abs(res)>Integer.MAX_VALUE) {
                break;
            }
        }
        //
        res = sigh*res;
        res = res>Integer.MAX_VALUE?Integer.MAX_VALUE:res;
        res = res<Integer.MIN_VALUE?Integer.MIN_VALUE:res;
        return (int)res;

    }





    public static void main(String[] args) {
//        System.out.println(new Test().arrangeCoins(1804289383));
        System.out.println(new Test().strToInt("  -42"));
        System.out.println(new Test().strToInt("  -4a2"));
        System.out.println(new Test().strToInt("  +-aa2"));
    }
}
