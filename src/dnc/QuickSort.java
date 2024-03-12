package dnc;

import java.util.Arrays;

public class QuickSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // partition을 진행하고, pivot의 위치를 받는다.
            int pivotIndex = partition(arr, left, right);

            // 나눈 배열을 각각 Quicksort 한다.
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        // 오른쪽 끝을 pivot으로 설정한다.
        int pivot = arr[right];
        // pivot보다 작은 원소를 어디로 할당할지를 결정하기 위한 index
        int i = left - 1;

        // 나눠진 배열을 전부 확인한다.
        for (int j = left; j < right; j++) {
            // 만약 현재 배열의 원소의 값이 pivot보다 작다면,
            if (arr[j] <= pivot) {
                // 이 원소가 위치해야 하는 곳을 조정하고,
                i++;
                // 현재 원소를 왼쪽으로 보낸다.
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // pivot과 i + 1의 위치를 바꾼다.
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{25, 36, 18, 12, 15, 41, 29, 19};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
