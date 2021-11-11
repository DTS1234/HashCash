package hashcash;

public class Stats {

    double executionInMilliseconds;
    int numberOfTries;
    String headerBinary;
    String header;

    public Stats(double executionInMilliseconds, int numberOfTries, String headerBinary, String header) {
        this.executionInMilliseconds = executionInMilliseconds;
        this.numberOfTries = numberOfTries;
        this.headerBinary = headerBinary;
        this.header = header;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "executionInMilliseconds=" + executionInMilliseconds +
                ", numberOfTries=" + numberOfTries +
                ", headerBinary='" + headerBinary + '\'' +
                ", header='" + header + '\'' +
                '}';
    }
}
