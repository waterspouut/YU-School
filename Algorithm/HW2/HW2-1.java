import java.io.*;
import java.util.*;

public class HW2 {
    class Node<K,V>{
        K key;
        V value;
        HW2.Node<K,V> left, right;

        int N;
        int aux;
        HW2.Node<K,V> parent;

        protected Node(K key, V val){
            this.key = key; this.value = val;
            this.N = 1;
        }
        protected int getAux(){return aux;}
        protected void setAux(int value) {aux = value;}
    }

    protected class BST<K extends Comparable<K>,V>{
        protected HW2.Node<K,V> root;

        protected HW2.Node<K,V> treeSearch(K key){
            HW2.Node<K,V> x = root;
            while(true){
                int cmp = key.compareTo(x.key);
                if(cmp == 0) return x;
                else if(cmp<0){
                    if(x.left == null) return x;
                    else x=x.left;
                }
                else {
                    if(x.right == null) return x;
                    else x = x.right;
                }
            }
        }

        protected int size(){return (root!=null)?root.N:0;}

        protected V get(K key){
            if(root==null)return null;
            HW2.Node<K,V> x = treeSearch(key);
            if(key.equals(x.key))
                return x.value;
            else return null;
        }

        protected void put(K key, V val){
            if(root == null) {root = new HW2.Node<K,V>(key,val); return;}
            HW2.Node<K,V> x = treeSearch(key);
            int cmp = key.compareTo(x.key);
            if(cmp == 0) x.value = val;
            else{
                HW2.Node<K,V> newNode = new HW2.Node<K,V>(key,val);
                if(cmp < 0) x.left = newNode;
                else x.right = newNode;
                newNode.parent = x;
                rebalanceInsert(newNode);
            }
        }

        protected void rebalanceInsert(HW2.Node<K,V> x){
            resetSize(x.parent, 1);
        }

        protected void rebalanceDelete(HW2.Node<K,V> p, HW2.Node<K,V> deleted){
            resetSize(p,-1);
        }

        private void resetSize(HW2.Node<K,V> x, int value){
            for(;x!=null;x=x.parent)
                x.N += value;
        }

        protected Iterable<K> keys(){
            if(root == null) return null;
            ArrayList<K> keyList = new ArrayList<K>(size());
            inorder(root, keyList);
            return keyList;
        }

        private void inorder(HW2.Node<K,V> x, ArrayList<K> keyList){
            if(x!=null){
                inorder(x.left, keyList);
                keyList.add(x.key);
                inorder(x.right, keyList);
            }
        }

        protected void delete(K key){
            if(root==null) return;
            HW2.Node<K,V> x, y, p;
            x = treeSearch(key);

            if(!key.equals(x.key)) return;

            if(x == root || isTwoNode(x)){
                if(isLeaf(x)) {root = null; return;}
                else if (!isTwoNode(x)){
                    root = (x.right == null)? x.left:x.right;
                    root.parent = null;
                    return;
                }
                else{
                    y=min(x.right);
                    x.key = y.key;
                    x.value = y.value;
                    p=y.parent;
                    relink(p, y.right, y == p.left);
                    rebalanceDelete(p,y);
                }
            }
            else{
                p=x.parent;
                if(x.right == null)
                    relink(p, x.left, x == p.left);
                else if(x.left == null)
                    relink(p, x.right, x==p.left);
                rebalanceDelete(p,x);
            }
        }

        protected boolean isLeaf(HW2.Node<K,V> x){return x.left == null && x.right == null;}

        protected boolean isTwoNode(HW2.Node<K,V> x){return x.left == null && x.right != null;}

        protected void relink(HW2.Node<K,V> parent, HW2.Node<K,V> child, boolean makeLeft){
            if(child !=null) child.parent = parent;
            if(makeLeft) parent.left = child;
            else parent.right = child;
        }

        protected HW2.Node<K,V> min(HW2.Node<K,V> x){while(x.left != null) x = x.left; return x;}
    }

    private static List<String> makeTokenList(String filename) throws FileNotFoundException {
        Scanner javaAll = null;
        String emptyFile= null;
        List<String> tokenList = new ArrayList<>();
        List<String> currentToken = new ArrayList<>();
        List<String> resultToken = new ArrayList<>();
        int tokenRange = 5, i = 0;

        javaAll = new Scanner(new File(filename));
        javaAll.useDelimiter("\\A");
        emptyFile = javaAll.hasNext() ? javaAll.next() : "";

        StringTokenizer javaToken = new StringTokenizer(emptyFile, " \t\n=;,<>()");
        while(javaToken.hasMoreTokens()){
            tokenList.add(javaToken.nextToken());
        }
        javaAll.close();

        if(tokenList.size() >= tokenRange){
            for(i=0; i<=tokenList.size() - tokenRange; i++){
                currentToken = tokenList.subList(i, i + tokenRange);
                String finalToken = String.join("", currentToken);
                resultToken.add(finalToken);
            }
        }

        return resultToken;
    }

    private static void makeEachBST(List<String> keyList, BST<String, Integer> bst){
        for(String key: keyList){
            Integer count = bst.get(key);
            if(count == null) bst.put(key, 1);
            else bst.put(key, count + 1);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = null;
        String filename1 = null, filename2 = null;
        List<String> resulth = new ArrayList<>();
        List<String> resultt = new ArrayList<>();

        sc = new Scanner(System.in);

        System.out.print("첫번째 파일 이름? ");
        filename1 = sc.nextLine();
        System.out.print("두번째 파일 이름? ");
        filename2 = sc.nextLine();
        sc.close();

        resulth = makeTokenList(filename1);
        resultt = makeTokenList(filename2);

        System.out.println("파일 " + filename1 + "의 Shingle의 수 = " + resulth.size());
        System.out.println("파일 " + filename2 + "의 Shingle의 수 = " + resultt.size());

        HW2 hw2Instance = new HW2();
        BST<String, Integer> BST_H = hw2Instance.new BST<>();
        BST<String, Integer> BST_T = hw2Instance.new BST<>();

        makeEachBST(resulth, BST_H);
        makeEachBST(resultt, BST_T);

        int intersection = 0, union = 0;

        Iterable<String> keys_H = BST_H.keys();
        for(String keyH: keys_H){
            Integer value_H = BST_H.get(keyH);
            Integer value_T = BST_T.get(keyH);

            if(value_H != null && value_T != null){
                intersection += Math.min(value_H, value_T);
                union += Math.max(value_H, value_T);
            }else{
                union += value_H;
            }
        }

        Iterable<String> keys_T = BST_T.keys();
        for(String keyT: keys_T){
            Integer valueCheck_H = BST_H.get(keyT);

            if(valueCheck_H == null){
                Integer value_T = BST_T.get(keyT);
                if(value_T != null){
                    union += value_T;
                }
            }
        }

        intersection = Math.abs(intersection);
        union = Math.abs(union);

        System.out.println("두 파일에서 공통된 shingle의 수 = " + intersection);
        double result = Double.valueOf(intersection) / Double.valueOf(union);
        System.out.println(filename1 + "과 " + filename2 + "의 유사도 = " + result);
    }
}
