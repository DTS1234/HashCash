import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        new HashCash("email@mail.com", 20, LocalDateTime.now()).init();
    }

    private static void runHashCashForXTimesWithYzeroes(int x, int y) throws NoSuchAlgorithmException {
        for (int i = 0; i < x; i++) {
            new HashCash("email@mail.com", y, LocalDateTime.now()).init();
        }
    }


}
