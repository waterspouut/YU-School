// 22213485 박차오름

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int index = 0;
        int length = number.length() - k;
        char max = '0';

        for(int i = 0; i < length; i++) {
            max = '0';
            for(int j = index; j <= i + k; j++) {
                if(max < number.charAt(j)) {
                    max = number.charAt(j);
                    index = j + 1;
                }
            }
            answer += max;
        }
        return answer;
    }
}