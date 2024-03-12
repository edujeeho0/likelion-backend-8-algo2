package dnc;

import java.util.Arrays;

public class MergeSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSort(arr, 0, arr.length - 1);
    }

    // 정렬을 위해 나누는 단계
    private static void mergeSort(
            int[] arr,
            // 정렬 대상 시작점
            int left,
            // 정렬 대상 종단점
            int right
    ) {
        // left == right 라면 나눠진 배열의 크기가 1
        if (left < right) {
            // 가운데 index를 찾는다.
            // 왼쪽이 시작, 나눈 배열의 크기는 (right - left) / 2
            int mid = left + (right - left) / 2;

            // 반으로 나눠서 재귀호출한다.
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            // 나눠진 배열을 합친다.
            merge(arr, left, right, mid);
        }
    }

    // 나누어진 배열을 합치는 단계
    private static void merge(
            int[] arr,
            int left,
            int right,
            // 합치는 배열이 어디서 나눠지는지 알기 위한 mid
            int mid
    ) {
        // 각 배열은 arr[left:mid], arr[mid+1:right]
        // 각 배열의 크기를 구한다. 각 배열의 끝에 도달했을때 알기 위해서
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 각 배열의 크기만큼 복사한다.
        int[] leftArr = Arrays.copyOfRange(arr, left, left + n1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, mid + 1 + n2);

        // 반복문으로 각 배열의 제일 앞쪽부터 비교해가며 원본 배열에 덮어쓴다.
        // 왼쪽 배열 index
        int i = 0;
        // 오른쪽 배열 index
        int j = 0;
        // arr의 index
        int k = left;
        while (i < n1 && j < n2) {
            // 앞쪽 원소 중 왼쪽의 원소가 더 작거나 같을 경우,
            if (leftArr[i] <= rightArr[j]) {
                // 원본에 왼쪽 원소를 저장하고
                arr[k] = leftArr[i];
                // 다음 원소로 넘어간다.
                i++;
            }
            // 오른쪽 원소의 경우
            else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // 왼쪽 배열이 끝나지 않았다면, 마저 저장한다.
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        // 오른쪽 배열이 끝나지 않았다면, 마저 저장한다.
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{25, 36, 18, 12, 15, 41, 29, 19};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
