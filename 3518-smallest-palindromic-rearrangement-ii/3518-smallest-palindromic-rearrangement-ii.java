class Solution {

    // Helper method to find Greatest Common Divisor
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Computes nCr safely and caps the result at limit (k) to prevent overflow
    private long nCr(int n, int r, long limit) {
        if (r < 0 || r > n) return 0;
        r = Math.min(r, n - r);
        
        long result = 1;
        for (int i = 1; i <= r; i++) {
            long num = n - r + i;
            long den = i;
            
            long g = gcd(result, den);
            result /= g;
            den /= g;
            
            num /= den;
            
            // Check for overflow before multiplying
            if (limit / result < num) {
                return limit;
            }
            result *= num;
        }
        
        return result;
    }

    public String smallestPalindrome(String s, long k) {
        int n = s.length();
        int[] count = new int[26];

        // 1. Count total frequencies of all characters
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char middle = '\0';

        // 2. Find the middle character (if any) and check if palindrome is possible
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        // If more than one character has an odd frequency, no palindrome can be formed
        if (oddCount > 1) {
            return ""; 
        }

        // We only need half of every frequency to build the left side
        for (int i = 0; i < 26; i++) {
            count[i] /= 2;
        }

        StringBuilder leftHalf = new StringBuilder();
        int halfLength = n / 2;

        // 3. Build the left half greedily
        for (int pos = 0; pos < halfLength; pos++) {
            boolean found = false;
            
            for (int ch = 0; ch < 26; ch++) {
                if (count[ch] == 0) {
                    continue;
                }

                // Try placing this character
                count[ch]--;

                long totalWays = 1;
                int letters = 0;

                // Total remaining spots to fill
                for (int c = 0; c < 26; c++) {
                    letters += count[c];
                }

                // Calculate permutations for the remaining multisets
                for (int c = 0; c < 26; c++) {
                    if (count[c] > 0) {
                        long combinations = nCr(letters, count[c], k);
                        
                        // Multiply safely capping at k
                        if (totalWays == 0 || k / totalWays < combinations) {
                            totalWays = k;
                        } else {
                            totalWays *= combinations;
                        }
                        
                        letters -= count[c];
                    }
                }

                if (totalWays >= k) {
                    // This character belongs to the k-th palindrome
                    leftHalf.append((char) ('a' + ch));
                    found = true;
                    break; 
                }

                // Skip all permutations beginning with this character
                k -= totalWays;

                // Restore frequency to try the next character
                count[ch]++;
            }
            
            // If we didn't find any valid character, k is larger than possible combinations
            if (!found) {
                return "";
            }
        }

        // 4. Assemble the final palindrome
        StringBuilder answer = new StringBuilder();
        answer.append(leftHalf);

        if (middle != '\0') {
            answer.append(middle);
        }

        answer.append(new StringBuilder(leftHalf).reverse());

        return answer.toString();
    }
}