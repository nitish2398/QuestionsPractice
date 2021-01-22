package sample;

// put - params (value double, long timeStamp) -
// get - Statistics (sum, average) - 1 minute


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pair {
    double price;
    double count;
}

public class Test {

    private static Map<Long, Map<Long, Double>> bookings = new HashMap<>(); // maxSize = 60

    private final int DELIMITER = 60;
    public static void main(String[] args) {
        System.currentTimeMillis();
    }


    public static boolean put(double price, long timeStamp) {
        long second = timeStamp/1000;
        long currentTimeInSecond = System.currentTimeMillis()/1000;

        long index =  currentTimeInSecond-second;


        if(!bookings.containsKey(second)) {
            bookings.put(index, new HashMap<>());
        }

        bookings.get(second).put(timeStamp, price);

        return true;
    }

    public static BookingStat get() {
        Long dateTimeNowInsec = System.currentTimeMillis();

        return new BookingStat();
    }
}

class BookingStat {
    int sum;
    int avg;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }
}
