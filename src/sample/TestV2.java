package sample;

import java.util.*;

class MyObject {
    int deadline;
    int reward;
    int time;

    public MyObject(int deadline, int reward, int time) {
        this.deadline = deadline;
        this.reward = reward;
        this.time = time;
    }

    public static Comparator comparator = new Comparator<MyObject>() {
        @Override
        public int compare(MyObject o1, MyObject o2) {
            return o1.deadline - o2.deadline;
        }
    };
}
class TestClass1 {
    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        while(t != 0) {
            int n = scan.nextInt();
            scan.nextLine();
            MyObject [] input = new MyObject[n];
            MyObject [] result = new MyObject[n];
            for(int i = 0; i < n; i++) {
                String [] temp = scan.nextLine().split(" ");
                int k = 0;
                int values [] = new int[3];
                for(int j = 0; j < temp.length; j++){
                    try {
                        int x = Integer.valueOf(temp[j]);
                        values[k++] = x;
                    } catch (Exception e) {
                        continue;
                    }
                }

                MyObject obj = new MyObject(values[0], values[1], values[2]);
                input[i] = obj;
            }

            Arrays.sort(input, MyObject.comparator);

            for(int i = 0; i < n; i++) {
                result[i] = input[i];
                if(input[i].time > input[i].deadline) {
                   result[i].reward = 0;
                }
                if(i > 0) {
                    if(result[i-1].time + result[i].time <= result[i].deadline) {
                        result[i].reward = result[i].reward + result[i-1].reward;
                        result[i].time = result[i].time + result[i-1].time;
                    } else if(result[i-1].reward > result[i].reward &&
                            result[i-1].time <= result[i].deadline) {
                        result[i].reward = result[i-1].reward;
                        result[i].time = result[i-1].time;
                    }
                }
            }

            System.out.println(result[n-1].reward);
            t--;
        }
    }
}
