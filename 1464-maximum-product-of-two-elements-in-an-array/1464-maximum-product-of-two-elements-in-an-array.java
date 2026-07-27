class Solution {
    public int maxProduct(int[] nums) {
        int n=nums.length;
        int i = n-1, j = n-2;
        Arrays.sort(nums);
        return (nums[i] - 1) * (nums[j] - 1);
    }
}