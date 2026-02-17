class Solution {
    public boolean halvesAreAlike(String s) {
        int mid = s.length() / 2;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < mid; i++) {
            if (isVowel(s.charAt(i))) count1++;
            if (isVowel(s.charAt(i + mid))) count2++;
        }
        return count1 == count2;
    }
    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}
