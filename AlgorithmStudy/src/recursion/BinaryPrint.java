package recursion;

public class BinaryPrint {
    public static void main(String[] args) {
        toBinary(20);
    }

    private static void toBinary(int i) {
        if(i==0){
            return;
        }
        toBinary(i/2);
        System.out.print(i%2);
    }
}
