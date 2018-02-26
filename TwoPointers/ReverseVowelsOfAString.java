/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
*/

//Note: Capital letters!!!!!

//Method 1: Two Pointers
class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        if(s.length() <= 1) return s;
        char[] cs = s.toCharArray();
        int i = 0, j = cs.length - 1;
        while(i < j) {
            while(i < j && !isVowel(cs[i])) {
                i++;
            }
            while(i < j && !isVowel(cs[j])) {
                j--;
            }
            char temp = cs[i];
            cs[i++] = cs[j];
            cs[j--] = temp;
        }
        return new String(cs);
    }
    private boolean isVowel(char c) {
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
            return true;
        else
            return false;
    }
}

//How to quickly check if there's vowels in the String

1.
String vowels = "aeiouAEIOU";
vowels.indexOf(array[first]) == -1

2. HashSet to optimize search time to O(1)
Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

2.
public static boolean[] vowels = new boolean[256];
static{
    vowels['a'] = true;
    vowels['o'] = true;
    vowels['e'] = true;
    vowels['i'] = true;
    vowels['u'] = true;
    vowels['A'] = true;
    vowels['O'] = true;
    vowels['E'] = true;
    vowels['I'] = true;
    vowels['U'] = true;
}

//How to convert char array to String
1. String.valueOf(str)
2. new String(str)