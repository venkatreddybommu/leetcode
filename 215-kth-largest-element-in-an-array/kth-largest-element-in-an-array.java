import java.util.Random;

class Solution {

    Random rand = new Random();

    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        return quickSelect(nums, 0, nums.length - 1, target);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {

        while (left <= right) {

            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = nums[pivotIndex];

            // 3-way partition
            int lt = left;     // < pivot
            int gt = right;    // > pivot
            int i = left;

            while (i <= gt) {
                if (nums[i] < pivot) {
                    swap(nums, lt++, i++);
                } else if (nums[i] > pivot) {
                    swap(nums, i, gt--);
                } else {
                    i++;
                }
            }

            // Now:
            // left → lt-1 = smaller
            // lt → gt = equal
            // gt+1 → right = greater

            if (k < lt) {
                right = lt - 1;
            } else if (k > gt) {
                left = gt + 1;
            } else {
                return nums[k];   // inside equal zone
            }
        }

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}