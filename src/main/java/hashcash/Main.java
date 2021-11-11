package hashcash;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {


        List<Stats> statisticsForHashCashWith10zeroesRunned5Times = runHashCashForXTimesWithYzeroes(5, 5);
        List<Stats> statisticsForHashCashWith20zeroesRunned5Times = runHashCashForXTimesWithYzeroes(5, 10);
        List<Stats> statisticsForHashCashWith30zeroesRunned5Times = runHashCashForXTimesWithYzeroes(5, 15);
        List<Stats> statisticsForHashCashWith40zeroesRunned5Times = runHashCashForXTimesWithYzeroes(5, 20);
        List<Stats> statisticsForHashCashWith50zeroesRunned5Times = runHashCashForXTimesWithYzeroes(5, 25);

//        Double averageExecutionTime10 = statisticsForHashCashWith10zeroesRunned5Times
//                .stream()
//                .map(stats -> stats.executionInMilliseconds)
//                .collect(Collectors.averagingDouble(Double::doubleValue));
//
//        printTime(averageExecutionTime10, 5
//
//        );

//        Double averageExecutionTime20 = statisticsForHashCashWith20zeroesRunned5Times
//                .stream()
//                .map(stats -> stats.executionInMilliseconds)
//                .collect(Collectors.averagingDouble(Double::doubleValue));
//
//        printTime(averageExecutionTime20, 10);
//
//        Double averageExecutionTime30 = statisticsForHashCashWith30zeroesRunned5Times
//                .stream()
//                .map(stats -> stats.executionInMilliseconds)
//                .collect(Collectors.averagingDouble(Double::doubleValue));
//
//        printTime(averageExecutionTime30, 15);

        Double averageExecutionTime40 = statisticsForHashCashWith40zeroesRunned5Times
                .stream()
                .map(stats -> stats.executionInMilliseconds)
                .collect(Collectors.averagingDouble(Double::doubleValue));

        printTime(averageExecutionTime40, 20);

//        Double averageExecutionTime50 = statisticsForHashCashWith50zeroesRunned5Times
//                .stream()
//                .map(stats -> stats.executionInMilliseconds)
//                .collect(Collectors.averagingDouble(Double::doubleValue));
//
//        printTime(averageExecutionTime50, 25);

    }

    private static List<Stats> runHashCashForXTimesWithYzeroes(int x, int y) throws NoSuchAlgorithmException {
        List<Stats> result = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            result.add(new HashCash("email@mail.com", y, LocalDateTime.now()).execute());
        }
        return result;
    }

    private static void printTime(Double averageTime, int zeroes)
    {
        System.out.printf("Average execution time in milliseconds for %s zeroes after 5 tries : %s", String.valueOf(zeroes), averageTime.toString());
        System.out.println("\n----------------------------------------------------------------------");
    }

}
