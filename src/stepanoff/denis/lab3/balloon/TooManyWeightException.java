package stepanoff.denis.lab3.balloon;

public class TooManyWeightException extends Exception {

    TooManyWeightException(double weight) {
        super("The balloon can't raise " + weight + "!!!!");
    }
}
