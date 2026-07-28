class Solution {
    public String smallestPalindrome(String s) {

        int[] freq = new int[26];

        // Count frequency
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        StringBuilder middle = new StringBuilder();

        // Build left half and middle
        for (int i = 0; i < 26; i++) {

            // Add half of the characters to the left
            for (int j = 0; j < freq[i] / 2; j++) {
                left.append((char) ('a' + i));
            }

            // Odd frequency character goes in the middle
            if (freq[i] % 2 == 1) {
                middle.append((char) ('a' + i));
            }
        }

        // Right half = reverse of left
        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + middle.toString() + right.toString();
    }
}