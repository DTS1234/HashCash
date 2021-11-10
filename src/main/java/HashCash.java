import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class HashCash {

    private final String resource;
    private final int zeroBits;
    private final LocalDateTime timestamp;
    private final String randomString;

    public HashCash(String resource, int zeroBits, LocalDateTime timestamp) {
        this.resource = resource;
        this.zeroBits = zeroBits;
        this.timestamp = timestamp;
        final int randomValue = ThreadLocalRandom.current().nextInt(1000);
        this.randomString = Base64.encode(String.valueOf(randomValue).getBytes());
    }

    void init() throws NoSuchAlgorithmException {

        long startTime = System.nanoTime();
        boolean shouldContinue = true;
        int nonce = 0;
        int counter = 1;

        while (shouldContinue) {

            String header = "1:" + zeroBits + ":" + timestamp.toString() + ":" + resource + "::" + randomString + ":";
            nonce = ThreadLocalRandom.current().nextInt();
            String binaryNonce = Integer.toBinaryString(nonce);
            header = header + Base64.encode(binaryNonce.getBytes());
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] encodedhash = digest.digest(
                    header.getBytes(StandardCharsets.UTF_8));
            String hashStringBinary = "";
            hashStringBinary = getHashAsString(encodedhash, hashStringBinary);

            String xZ = getXZeroes(zeroBits);

            if (hashStringBinary.startsWith(xZ)) {

                System.out.println("Verified !");
                System.out.println(header);
                System.out.println("Number of tries : " + counter);
                shouldContinue = false;

                break;
            } else {
                nonce = ThreadLocalRandom.current().nextInt();
                counter++;
            }
        }

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        final long l = totalTime / 1000000000;

        System.out.println("Execution time : " + l + " seconds !");

    }

    private String getHashAsString(byte[] encodedhash, String hashStringBInary) {
        for (int i = 0; i < encodedhash.length; i++) {
            byte b1 = encodedhash[i];
            String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
            hashStringBInary += s1;
        }
        return hashStringBInary;
    }

    private String getXZeroes(int x) {
        String twentyZeroes = "";

        for (int i = 0; i < x; i++) {
            twentyZeroes += "0";
        }
        return twentyZeroes;
    }
}
