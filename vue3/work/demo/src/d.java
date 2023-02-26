 class Solution {
        private final static Random random = new Random(System.currentTimeMillis());////获取当前的总毫秒数
        public int findKthLargest(int[] nums, int k) {
            // 法一:暴力解法
            // 时间复杂度O(NlogN)
            // int len = nums.length;
            // Arrays.sort(nums);
            // return nums[len - k];
            // 法二
            // 时间复杂度O(N)
            int len = nums.length;
            int target = len - k;//目标元素在数组中的下标
            int left = 0;//左
            int right = len - 1;//右
            while (true) {
                int pivotIndex = partition(nums, left, right);

                if (pivotIndex == target) {
                    return nums[pivotIndex];
                } else if (pivotIndex < target) {
                    left = pivotIndex + 1;
                } else {
                    // pivotIndex > target
                    right = pivotIndex - 1;
                }
            }
        }
        private int partition(int[] nums, int left, int right) {
            int randomIndex = left + random.nextInt(right - left + 1);
            swap(nums, left, randomIndex);
            // all in nums[left + 1..le) <= pivot;
            // all in nums(ge..right] >= pivot;
            int pivot = nums[left];
            int le = left + 1;
            int ge = right;
            while (true) {
                while (le <= ge && nums[le] < pivot) {
                    le++;
                }
                while (le <= ge && nums[ge] > pivot) {
                    ge--;
                }
                if (le >= ge) {
                    break;
                }
                swap (nums, le, ge);
                le++;
                ge--;
            }
            swap(nums, left, ge);
            return ge;
        }
        private void swap(int[] nums, int index1, int index2) {
            int temp = nums[index1];
            nums[index1] = nums[index2];
            nums[index2] = temp;
        }
    }
