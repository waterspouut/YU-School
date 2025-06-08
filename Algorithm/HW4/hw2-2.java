// 22213485 박차오름

import java.util.Scanner;

public class hw2 {
    public static class Solution {
        public String solution(String s, String t, int s_length, int t_length) {
            if(s_length == 0 || t_length == 0){
                return "";
            }
            
            String result = "";

            if(s.charAt(s_length-1) == t.charAt(t_length-1)){
                result = solution(s, t, s_length-1, t_length-1) + s.charAt(s_length-1);
            }
            else{
                String s1 = solution(s, t, s_length-1, t_length);
                String s2 = solution(s, t, s_length, t_length-1);
                result = (s1.length() >= s2.length()) ? s1 : s2;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution sol = new Solution();
        String s = sc.nextLine();
        String t = sc.nextLine();
        int s_length = s.length();
        int t_length = t.length();
        String result = sol.solution(s, t, s_length, t_length);
        System.out.println(result);
        System.out.println(result.length());
    }
}
