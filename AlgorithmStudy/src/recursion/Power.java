package recursion;

public class Power {
    public static void main(String[] args) {
        double result = power(-8);
        System.out.println("result: "+result);
    }

    private static double power(int i) {
        if(i==0){
            return 1;
        }

        if(i>0) {
            return power(i - 1) * 2;
        }else {
            return power(i+1)*0.5;
        }
    }
}
