import java.util.Scanner;

public class ArrayOperations {

    // 1. Method to display array
    static void displayArray(int[] arr) {
        System.out.print("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 2. Method to find sum (returns int)
    static int findSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    // 3. Method to find max value (returns int)
    static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    // 4. Method to search element (returns index or -1)
    static int search(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // 5. Method to update element (return updated array)
    static int[] updateElement(int[] arr, int index, int value) {
        if (index >= 0 && index < arr.length) {
            arr[index] = value;
        }
        return arr;
    }

    // 6. Method to reverse array (returns new array)
    static int[] reverseArray(int[] arr) {
        int[] rev = new int[arr.length];
        int j = 0;

        for (int i = arr.length - 1; i >= 0; i--) {
            rev[j] = arr[i];
            j++;
        }
        return rev;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input elements
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Display array
        displayArray(arr);

        // Sum
        System.out.println("Sum of array: " + findSum(arr));

        // Maximum
        System.out.println("Maximum value: " + findMax(arr));

        // Search operation
        System.out.print("Enter number to search: ");
        int key = sc.nextInt();
        int index = search(arr, key);
        if (index != -1)
            System.out.println("Element found at index: " + index);
        else
            System.out.println("Element not found!");

        // Update element
        System.out.print("Enter index to update: ");
        int idx = sc.nextInt();
        System.out.print("Enter new value: ");
        int val = sc.nextInt();

        arr = updateElement(arr, idx, val);
        System.out.print("Updated ");
        displayArray(arr);

        // Reverse array
        arr = reverseArray(arr);
        System.out.print("Reversed ");
        displayArray(arr);
    }
}
