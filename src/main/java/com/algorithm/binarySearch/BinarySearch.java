package com.algorithm.binarySearch;

/**
 * 二分查找算法
 */
public class BinarySearch {
    // 有序数组中不存在重复元素
    public int binarySearch(int[] arr, int n, int value) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 二分查找的递归实现
    public int binarySearchRecursive(int[] arr, int n, int value) {
        return binarySearchInternally(arr, 0, n - 1, value);
    }

    private int binarySearchInternally(int[] arr, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (arr[mid] > value) {
            binarySearchInternally(arr, low, mid - 1, value);
        } else if (arr[mid] < value) {
            binarySearchInternally(arr, mid - 1, high, value);
        } else {
            return mid;
        }
        return -1;
    }

    // 查找与目标值相等的第一个位置
    public int binarySearch_1(int[] arr, int n, int value) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid - 1] != mid) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // 查找与目标值相等的最后一个位置
    public int binarySearch_2(int[] arr, int n, int value) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == n - 1 || arr[mid + 1] != mid) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    // 查找最后一个小于目标值的数
    public static int binarySearch_3(int[] arr, int n, int value) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                high = mid - 1;
            } else {
                if (mid == n - 1 || arr[mid + 1] >= value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    // 查找第一个大于目标值的数
    public static int binarySearch_4(int[] arr, int n, int value) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                if (mid == 0 || arr[mid - 1] <= value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // 查找旋转数组的最小元素（假设不存在重复数字）
    public static int binarySearch_5(int[] arr, int n) {
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if(arr[mid] > arr[high])
                low = mid + 1;
            else{
                high = mid;
            }
        }
        return arr[low];
    }

    // 查找旋转数组的最小元素（存在重复项）
    public static int binarySearch_6(int[] arr, int n) {
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else if (arr[mid] < arr[high]) {
                high = mid;
            } else {
                high--;
            }
        }
        return arr[low];
    }

    // 在旋转排序数组中搜索 (不考虑重复项)
    public static int binarySearch_7(int[] arr, int n, int target) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[low] <= arr[mid]) {
                if (target < arr[mid] && target >= arr[low]) {
                    low = mid - 1;
                } else {
                    high = mid + 1;
                }
            } else if (arr[mid] <= arr[high]) {
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return arr[low];
    }

    // 在旋转排序数组中搜索 (存在重复项)
    public static boolean binarySearch_8(int[] arr, int n, int target) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == target) {
                return true;
            } else if (arr[high] < arr[mid]) {
                if (target < arr[mid] && target >= arr[low]) {
                   high = mid;
                } else {
                    low = mid + 1;
                }
            } else if (arr[mid] < arr[high]) {
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            } else {
                high--;
            }
        }
        return false;
    }

}
