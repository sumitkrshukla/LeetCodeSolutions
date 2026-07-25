class Solution {
    public int majorityElement(int[] nums) {
        // 2 2 1 1 1 2 2
        // sort -> 1 1 1 2 2 2 2
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}