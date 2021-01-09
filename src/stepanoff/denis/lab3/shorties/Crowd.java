package stepanoff.denis.lab3.shorties;

import stepanoff.denis.lab3.balloon.Balloon;
import stepanoff.denis.lab3.balloon.Basket;

import java.util.ArrayList;
import java.util.List;

public class Crowd {

    private static final int MAX_DEFAULT_CROWD_SIZE = 25;
    private static final int MIN_DEFAULT_CROWD_SIZE = 10;

    private final List<Shorty> shorties = new ArrayList<>();

    public Crowd() {
        this((int)(Math.random() * (MAX_DEFAULT_CROWD_SIZE - MIN_DEFAULT_CROWD_SIZE) + MIN_DEFAULT_CROWD_SIZE));
    }

    public Crowd(int size) {
        for (int i = 0; i < size; i++) {
            shorties.add(ShortiesFactory.getRandomShorty());
        }
    }

    public void clapInHands() {
        System.out.println("APPLAUSE! APPLAUSE! All shorties are applauding!");
    }

    public void understandZnaika(Basket basket) {

        while (basket.getBalloon().getFlyingState() == Balloon.FlyingState.GOING_UP) {
            System.out.println(Balloon.FlyingState.GOING_UP.toString());
        }

        if (basket.getBalloon().getFlyingState() == Balloon.FlyingState.FLYING) {
            System.out.println(Balloon.FlyingState.FLYING.toString());
            System.out.println("All shorties ("
                    + this.getAllNames()
                    + ") understood Znaika's wisdom."
            );
        }
    }

    @Override
    public String toString() {
        return "Crowd[" + this.getAllNames() + "]";
    }

    private String getAllNames() {
        return this.shorties.stream().map(Shorty::getName).reduce(
                (s, ac) -> ac.concat(", " + s)
        ).orElse(", ").substring(0);
    }
}
