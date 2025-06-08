// 22213485 박차오름

import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Solution {
    public int solution(String dartResult) {
        int count = 0;
        int sum[] = new int[3];
        int result = 0;

        Pattern p = Pattern.compile("(\\d+)([SDT])([*#]?)");
        Matcher m = p.matcher(dartResult);
        while (m.find()) {
            int score = Integer.parseInt(m.group(1));
            String bonus = m.group(2);
            String option = m.group(3);
            
            sum[count] = (int) Math.pow(score, bonus.equals("S") ? 1 : bonus.equals("D") ? 2 : 3);
            if (option.equals("*")) {
                sum[count] *= 2;
                if(count > 0){
                    sum[count-1] *= 2;
                }
            } else if (option.equals("#")) {
                sum[count] *= -1;
            }
            count++;
        }

        for (int i = 0; i < 3; i++) {
            result += sum[i];
        }

        return result;
    }
}