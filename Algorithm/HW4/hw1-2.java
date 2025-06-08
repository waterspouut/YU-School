// 22213485 박차오름

class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;
        for(int i = height-2;i>=0;i--)
            for(int j = 0;j<triangle[i].length;j++)
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
        return triangle[0][0];
    }
}