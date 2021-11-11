package hashcash;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
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
        randomString = Base64.getEncoder().encodeToString(String.valueOf(randomValue).getBytes());
    }

    Stats execute() throws NoSuchAlgorithmException {

        long startTime = System.nanoTime();
        int nonce = ThreadLocalRandom.current().nextInt();
        int counter = 1;
        String xZ = getXZeroes(zeroBits);

        while (true) {
            String header = "1:" + zeroBits + ":" + timestamp.toString() + ":" + resource + "::" + randomString + ":";
            String binaryNonce = Integer.toBinaryString(nonce);
            header = header + Base64.getEncoder().encodeToString(binaryNonce.getBytes());
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] encodedhash = digest.digest(header.getBytes(StandardCharsets.UTF_8));
            String hashStringBinary = getHashAsBinary(encodedhash);


            if (hashStringBinary.startsWith(xZ)) {
                long endTime = System.nanoTime();
                long totalTime = endTime - startTime;
                final long executionTimeInMillis = totalTime / 1000000;
                return new Stats(executionTimeInMillis, counter, getHashAsBinary(encodedhash), header);
            } else {
                counter++;
                nonce += 1;




            }
        }

    }

    private String getHashAsBinary(byte[] encodedhash) {
        StringBuilder hashStringBInary = new StringBuilder();
        for (byte b1 : encodedhash) {
            hashStringBInary.append(String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0'));
        }
        return hashStringBInary.toString();
    }

    private String getXZeroes(int x) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < x; i++) {
            result.append("0");
        }
        return result.toString();
    }
}
