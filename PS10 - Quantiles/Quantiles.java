import java.util.*;
import java.io.*;

public class Quantiles {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        int k = userInput.nextInt();
        userInput.close();
        int[] nums = scanIntegersFromFile("Incomes.txt");
        int[] quantiles = findQuantiles(nums, k);
        for (int i = 0; i < k - 1; i++) {
            System.out.println(quantiles[i]);
        }
    }

    public static int[] findQuantiles(int[] nums, int k) {
        int n = nums.length;
        int[] quantiles = new int[k - 1];
        int start = 0;
        int end = n - 1;
        Random random = new Random();
        for (int i = 0; i < k - 1; i++) {
            int j = (i + 1) * n / k - 1;
            while (true) {
                int pivotIndex = randomPartition(nums, start, end, random);
                if (j == pivotIndex) {
                    quantiles[i] = nums[j];
                    break;
                } else if (j < pivotIndex) {
                    end = pivotIndex - 1;
                } else {
                    start = pivotIndex + 1;
                }
            }
            start = 0;
            end = n - 1;
        }
        return quantiles;
    }
    
    public static int randomPartition(int[] nums, int low, int high, Random random) {
        int pivotIndex = random.nextInt(high - low + 1) + low;
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, high);
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, storeIndex);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, high);
        return storeIndex;
    }
    
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    

    public static int[] scanIntegersFromFile(String filename) {
        List<Integer> nums = new ArrayList<Integer>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextInt()) {
                nums.add(scanner.nextInt());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] arr = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }
        return arr;
    }
}
