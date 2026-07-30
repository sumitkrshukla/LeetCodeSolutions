class Solution {
    public int minimumPushes(String word) {

        int[] freq = new int[26];

        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int result = 0;
        int index = 0;

        // Traverse from highest frequency
        for (int i = 25; i >= 0; i--) {

            if (freq[i] == 0)
                break;

            result += freq[i] * (index / 8 + 1);

            index++;
        }

        return result;
    }
}