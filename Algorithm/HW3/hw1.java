// 22213485 박차오름

class Solution {
    int[] answer = new int[2];

    public int[] solution(int[][] arr) {
        combine(arr, 0, 0, arr.length);
        return answer;
    }

    public void combine(int[][] arr, int x, int y, int size) {
        if (canCombine(arr, x, y, size)) {
            answer[arr[x][y]]++;
            return;
        }
        
        size /= 2;
        combine(arr, x, y, size);
        combine(arr, x, y + size, size);
        combine(arr, x + size, y, size);
        combine(arr, x + size, y + size, size);
    }

    public boolean canCombine(int[][] arr, int x, int y, int size) {
        int value = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}