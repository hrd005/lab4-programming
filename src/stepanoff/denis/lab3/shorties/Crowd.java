package stepanoff.denis.lab3.shorties;

import stepanoff.denis.lab3.balloon.Balloon;
import stepanoff.denis.lab3.balloon.Basket;
import stepanoff.denis.lab3.util.ShortyCollection;
import stepanoff.denis.lab3.util.ShortySearcher;

import java.util.ArrayList;
import java.util.List;

public class Crowd implements ShortyCollection {

    private static final int MAX_DEFAULT_CROWD_SIZE = 25;
    private static final int MIN_DEFAULT_CROWD_SIZE = 10;

    private final List<Shorty> shorties = new ArrayList<>();
    private final List<GotInjuredListener> listeners = new ArrayList<>();

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

    public void laugh(LaughLevel laughLevel) {
        System.out.println("All shorties are laughing! :-D :-D :-D");
        if (laughLevel == LaughLevel.HIGH) {
            try {
                this.gotInjured(new ShortySearcher(this).getShorty("Гунька"));
            } catch (RuntimeException e) {
                System.out.println("Really, we don't care, if he is present, but: " + e.getMessage());
            }
        }
    }

    public enum LaughLevel {
        HIGH, LOW
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

    public void addOnGotInjuredListener(GotInjuredListener listener) {
        this.listeners.add(listener);
    }

    private void gotInjured(Shorty shorty) {
        this.listeners.forEach((l) -> l.onGotInjured(shorty));
    }

    @Override
    public Shorty[] toArray() {
        Shorty[] t = new Shorty[this.shorties.size()];
        return this.shorties.toArray(t);
    }

    @FunctionalInterface
    public interface GotInjuredListener {
        void onGotInjured(Shorty shorty);
    }
}
