import java.util.Scanner;
import java.util.*;

//Returns true or false if a list of integers is a sumofsubset.
public class SumOfSubsetPrevious{
    public static void main(String[] args){
        Scanner integers = new Scanner(System.in);
        int int1 = integers.nextInt();
        int[] numList = new int[int1];
        for (int i = 0; i < int1; i++){
            numList[i] = integers.nextInt();
        }
        integers.close();
        Arrays.sort(numList);

        System.out.println(sumOfSubset(numList[numList.length-1],numList, 0));


    }
    public static boolean sumOfSubset(int target,int [] numList, int counter){
        //System.out.println("Looking for: " + target);
        int total = numList[0];
        counter++;
        if (counter >= numList.length-1){
            return false;
        }
        for (int i = 1; i < numList.length-1; i++){
            //System.out.println(total + " + " + numList[i] + " = " + (total+numList[i]));
            //System.out.println(i);
            if (total + numList[i] == target){ 
                return true;
            }
            else if(total == target){
                return false;
            }
            if (total + numList[i] < target){
                total = total + numList[i];
                //System.out.println("Total: " + total);
            }
            if(total + numList[i] > target){
                //System.out.println("Skipped");
                continue;

            }
        }
        if(sumOfSubset(numList[numList.length-1-counter], numList, counter)){
            return true;
        }
        else{
            return false;

        }
        
        
            

            

    }
    


}

