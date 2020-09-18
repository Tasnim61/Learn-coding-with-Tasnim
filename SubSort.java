/*
Problem:
Given an array of interger, write a method to find indices m and n such that if
you sorted elements m through n, the entire array would be sorted. Minimize n - m 
(that is, find the smallest such sequence)

Example:
Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
Output: ((3, 9)
// 1 3 5 7 9 3 5 6 7 9

Solution:
1. Find the portion of unsorted array
    a. check from left to right of the array to get end_left index before unsorted array
    b. Similarly check from right to left to get start_righ index before unsorted array
    From above example: (1, 2, 4, 7, 10, 11) +    (7, 12)   + (6, 7, 16, 18, 19)
                           left sorted   |      unsorted       |        right sorted
                                    end_left                right_start
2.                                      
    a. Get Max and Min from End(left) to start(right)
    b. Now from left sorted array, find the first element which is > min / shrink left until get <= min 
    c. Now from right sorted array, find the last element which is < max /shrink right until get >= max 

Time Complexity: O(N) where N is size of array

*/

class SubSort
{
    static void UnsortedIndexRange(final int[] arr)
    {
        int n = arr.length;
        if(n == 0)
            return;
        
        // 1.a -> check from left of the array to get end_left index before unsorted arr
        int end_left = n-1;
        for(int i = 1 ; i < n; i++)
        {
            if(arr[i] < arr[i - 1])
            {
                end_left = i - 1;
                break;
            }
        }
        if(end_left >= n - 1)
        {
            System.out.println("Array already sorted\n");
            return;
        }

         // 1. b -> check from right of the array to get start_righ index before unsorted arr
         int start_right = 0;
         for(int i = n - 2; i >= 0; i--)
         {
             if(arr[i] > arr[i + 1])
             {
                 start_right = i + 1;
                 break;
             }
         }
         
         System.out.println("End_left: " + end_left + "\nStart_right: " + start_right );

         // 2. a -> Get Max and Min from End(left) to start(right)
         int max = Integer.MIN_VALUE;
         int min = Integer.MAX_VALUE;
         for(int i = end_left; i <= start_right; i++)
         {
            if(arr[i] > max)
                max = arr[i];

            if(arr[i] < min)
                min = arr[i];
         }

         // 2.b -> shrink left until you get <= min
         for(int i = end_left - 1; i > 0; i--)
         {
             if(arr[i] <= min)
             {
                 end_left = i + 1;
                 break;
             }     
         }

         // 2.c -> shrink right until >= max
         for(int i = start_right + 1; i < n; i++)
         {
             if(arr[i] >= max)
             {
                 start_right = i - 1;
                 break;
             }
         }
         System.out.println("Range need to be sorted from: " + end_left + " to " + start_right);
    }
    
    public static void main(final String[] args)
    {
        final int arr[] = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19}; 
        //final int arr[] = {1, 2, 4, 5, 7, 6, 8, 9, 12};
        //final int arr[] = {2,1};
        //final int arr[] = {1, 2, 3, 7, 6};
        UnsortedIndexRange(arr); 

    }
}