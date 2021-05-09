package string;

/**
 * Created on 2021-05-03
 */
class Reverse {
    // 214703
    public int reverse(int x) {
        if (x==Integer.MIN_VALUE) {
            return 0;
        }
        String maxStr = String.valueOf(Integer.MAX_VALUE);
        int res = 0;
        int sign = x>0?1:-1;
        x = x*sign;
        String s = String.valueOf(x);
        if (s.length()<maxStr.length()) {
            // 直接返回
            while(x!=0) {
                res = res*10 + x%10;
                x = x/10;
            }
        } else {
            // 长度相等时,这样比较：1）前面部分全部相同，后面不同；2）当前小于
            for (int i=s.length()-1;i>=0;i--) {
                //
                if (s.charAt(i) < maxStr.charAt(s.length()-i-1)) {
                    // 直接计算
                    while(x>0) {
                        res = res*10 + x%10;
                        x = x/10;
                    }
                    return res*sign;
                } else if (s.charAt(i)==maxStr.charAt(s.length()-i-1)) {
                    continue;
                } else {
                    //
                    return 0;
                }
            }
        }
        return res*sign;
    }

    public static void main(String[] args) {
        System.out.println(new Reverse().reverse(-2147483412));;
        System.out.println(new Reverse().reverse(-0));;
        System.out.println(new Reverse().reverse(-123));;
        System.out.println(new Reverse().reverse(123));;
        System.out.println(new Reverse().reverse(-2147483412));;
        System.out.println(new Reverse().reverse(Integer.MIN_VALUE));;
        System.out.println(new Reverse().reverse(Integer.MAX_VALUE));;
        System.out.println(new Reverse().reverse(-2147483412));;
        System.out.println(Integer.MAX_VALUE);
    }
}
