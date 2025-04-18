import java.util.Scanner;

public class RecursiveMultiply{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
   
        scanner.close();
            if (num1 > num2){
               System.out.print(mult(num2,num1));
            }
             else{
                System.out.print(mult(num1,num2));
             }
        }
        
    public static long mult(int num1, int num2){
        if (num1 == 0 || num2 == 0){
            return 0;
            
        }
        else if(num1 == 1){
            return num2;
        }
        if (num2 % 2 == 0){
            long halfNum = mult(num1,num2/2);
            return halfNum + halfNum;
        }
        else{
            long halfNum = mult(num1,(num2-1)/2);
            return num1 + halfNum + halfNum;
        }
}
}