package stepanoff.denis.lab3.balloon;

import java.util.ArrayList;
import java.util.Optional;

public class Boiler {

    private static final int VOLUME = 100; // percents

    private Fire fire;
    private final Pump pump = new Pump();
    private final ArrayList<OnAirWarmedListener> listeners = new ArrayList<>();

    public void setFire(Fire fire) {
        this.fire = fire;
    }

    public Pump getPump() {
        return this.pump;
    }

    public void warm() {
        int warmed = 0;
        while (warmed < VOLUME) {
            warmed += VOLUME * this.fire.getHeat();
            System.out.println("Air in boiler warmed up on " + warmed + "%");
        }

        this.fireAirWarmed();
    }

    public void addOnAirWarmedListener(OnAirWarmedListener listener) {
        this.listeners.add(listener);
    }

    private void fireAirWarmed() {
        this.listeners.forEach(OnAirWarmedListener::onAirWarmed);
    }

    public class Pump {
        private final double power = Math.random();
        private Balloon connection;

        public double pump() { // returns percentage of air in boiler, that pumps out for one pump
            return this.power;
        }

        public void connect(Balloon balloon) {
            this.connection = balloon;
        }

        public void disconnect() {
            this.connection = null;
        }

        public Optional<Balloon> getConnection() {
            return Optional.of(this.connection);
        }
    }
}
