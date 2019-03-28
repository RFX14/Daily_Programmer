import java.util.*;
public class Main {
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input a number");
        System.out.print(">> ");
        System.out.println(numShift(sc.nextInt()));

        sc.close();
    }

    public static int numShift(int x) {
        double temp = 0;
        int num = 0;
        ArrayList<Integer> numList = new ArrayList<Integer>();

        if(x < 10) {
            return x+1;
        }

        while(x > 0) {
            numList.add((x % 10) + 1);
            x /= 10;
        }

        for(int i = numList.size() - 1; i >= 0; i--) {
            temp *= Math.pow(10, i+1);
            temp += numList.get(i);
        }
        num = (int)temp;
        return num;
    }
        
}