// 22213485 박차오름

import java.util.*;

public class hw2 {
    public static List<List<Integer>> findSubsets(int n, int k) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        findAllSubsets(n, k, 1, subset, allSubsets);
        return allSubsets;
    }
    
    public static void findAllSubsets(int n, int k, int start, List<Integer> subset, List<List<Integer>> allSubsets) {
        if (subset.size() == k) {
            allSubsets.add(new ArrayList<>(subset));
            return;
        }
        
        for (int i = start; i <= n; i++) {
            subset.add(i);
            findAllSubsets(n, k, i + 1, subset, allSubsets);
            subset.remove(subset.size()-1);
        }
    }

    public static void main(String[] args) {
        Scanner sc;
        int n, k;
        List<List<Integer>> subsets;

        sc = new Scanner(System.in);
        
        System.out.print("정수 n과 k를 입력? ");
        n = sc.nextInt();
        k = sc.nextInt();     
        
        subsets = findSubsets(n, k);
        for(List<Integer> subset : subsets) {
            System.out.print(subset + " ");
        }
    }
}