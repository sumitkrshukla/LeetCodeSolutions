class Solution {
    public int removeDuplicates(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        int i=0, j=i;
        for(j=1; j<n; j++){
            if(nums[j] != nums[i]){
                nums[i+1] = nums[j];
                i++;
            }
        }
        return(i+1);
    }
}