/**
* Implement pow(x, n).
*/

/*
把n看成是以2为基的位构成的，因此每一位是对应x的一个幂数，然后迭代直到n到最高位
比如说第一位对应x，第二位对应x*x,第三位对应x^4,...,第k位对应x^(2^(k-1)),
可以看出后面一位对应的数等于前面一位对应数的平方，所以可以进行迭代 
因为迭代次数等于n的位数，所以算法的时间复杂度是O(logn)
e.g. n = 13: 00001101, so 13 = 2^3 + 2^2 + 2^0
m^13 = m^(2^3 + 2^2 + 2^0) = m^(2^3) * m^(2^2) * m(2^0)
*/
/*
1 1 0 1

res = x; 				x = x^2
		 				x = x^4
res = x * x^4; 			x = x^8
res = x * x^4 * x^8; 	x = x^16
*/
public class Pow {
    public double myPow(double x, int n) {
        if(n == 0) return 1.0;
        double res = 1.0;
        if(n < 0) {
            //防止x取倒数以后溢出
            if(x >= 1.0 / Double.MAX_VALUE || x <= - 1.0 / Double.MAX_VALUE)
                x = 1.0 / x;
            else
                return Double.MAX_VALUE;
            //防止n取abs以后溢出
            if(n == Integer.MIN_VALUE) {
                res *= x;
                n += 1;
            }
        }
        //将x取倒数后，将n取变为绝对值，正数
        n = Math.abs(n);
        
        //判断符号
        boolean isNeg = false;
        if(x < 0 && n % 2 == 1)
            isNeg = true;
        x = Math.abs(x);
        
        while(n > 0) {
            if((n & 1) == 1) {
                //防止overflow
                if(res > Double.MAX_VALUE / x)
                    return Double.MAX_VALUE;
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return isNeg ? -res : res;
    }
}


// recursive + binary
public class Solution {
    public double myPow(double x, int n) {
        if(n < 0) {
            // if(x >= 1.0 / Double.MAX_VALUE || x <= - 1.0 / Double.MAX_VALUE) {
            //     if(n != Integer.MIN_VALUE)
                    return 1 / pow(x, -n);
            //     else
            //         return 1 / (pow(x, -(n + 1)) * x);
            // }
            // else 
            //     return Double.MAX_VALUE;
        } else 
            return pow(x, n);
    }
    private double pow(double x, int n) {
        if(n == 0) return 1.0;
        if(n == 1) return x;
        double val = pow(x, n / 2);
        if(n % 2 == 0)
            return val * val;
        else
            return val * val * x;
    }
}