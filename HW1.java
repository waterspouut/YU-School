import java.io.File;
import java.io.IOException;
import java.util.*;

public class HW1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("입력 파일 이름? ");
        String fname = sc.nextLine();
        sc.close();
        try{
            sc = new Scanner(new File(fname));
            double input_data = sc.nextDouble();
        } catch (IOException e){ System.out.println(e); return;}

        if (sc != null) sc.close();
    }
}
