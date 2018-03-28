import java.util.Scanner;

public class Sorting_word {
    public static void main(String[] args) {
        //단어 정렬 문제

        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        int num = Integer.parseInt(n);

        String word;
        String[] str = new String[num];
        for(int i=0;i<num;i++){
            str[i] = sc.next();
        }
        String tmp;
        for(int i=0;i<num-1;i++){
            for(int j=0;j<num-1-i;j++){
                if(str[j].length()>str[j+1].length()){
                    tmp = str[j+1];
                    str[j+1] = str[j];
                    str[j] = tmp;
                }
                if(str[j].length()==str[j+1].length()){
                    if(str[j].compareTo(str[j+1])>0){
                        tmp = str[j+1];
                        str[j+1] = str[j];
                        str[j] = tmp;
                    }
                }
            }
        }
        String tmpStr="";
        for(String e : str){
            if(!e.equals(tmpStr)){
                System.out.println(e);
            }
            tmpStr = e;
        }
    }
}
