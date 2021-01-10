package stepanoff.denis.lab3.balloon;

public class Balloon {

    private static final int VOLUME = 917;
    private static final int ROPE_LENGTH = 1;
    private int currVolume = 426;
    private int temperature = 0; // zero for cold, 1 for heat enough

    //private int baseUpwardForce = VOLUME*temperature;
    private int upwardForce = this.getBaseUpwardForce(); //will be in kilos, not newtons

    private int altitude = 0;

    public int getAltitude() {
        return this.altitude;
    }

    private boolean isRopeAttached = true;

    public void attachRope() {
        this.isRopeAttached = true;
    }

    public void deattachRope() {
        this.isRopeAttached = false;
    }

    public boolean empty() {
        this.upwardForce = 0;
        return this.changeVolume(-1.0);
    }

    public boolean changeVolume(double percent) {
        this.currVolume += percent*VOLUME;
        if (this.currVolume <= 0) this.currVolume = 0;
        if (this.currVolume >= VOLUME) this.currVolume = VOLUME;

        this.upwardForce = this.currVolume*this.temperature;

        return isFilled();
    }

    public boolean isFilled() {
        return this.currVolume == VOLUME;
    }

    private FlyingState flyingState = FlyingState.GROUND;
    private final WeightCounter weightCounter;

    public Balloon(WeightCounter weightCounter) {
        this.weightCounter = weightCounter;
    }

    public int getBaseUpwardForce() {
        return this.isFilled() ? VOLUME*this.temperature : 0;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        this.upwardForce = this.currVolume*this.temperature;
    }

    public void riseUp() throws TooManyWeightException {
        System.out.println(this.getBaseUpwardForce());
        System.out.println(this.weightCounter.getWeight());
        this.checkWeight();
        this.flyingState = FlyingState.GOING_UP;
    }

    public FlyingState getFlyingState() {
        FlyingState current = this.flyingState;

        if (Math.round(this.weightCounter.getWeight()) == upwardForce && !isRopeAttached || isRopeAttached && this.altitude == ROPE_LENGTH) {
            this.flyingState = FlyingState.FLYING;
        } else if (Math.round(this.weightCounter.getWeight()) < upwardForce) {
            this.flyingState = FlyingState.GOING_UP;
            this.upwardForce -= 1;
            this.altitude += this.isRopeAttached ? (this.altitude < ROPE_LENGTH ? 1 : 0) : 1;
        } else if (Math.round(this.weightCounter.getWeight()) > upwardForce) {
            this.flyingState = FlyingState.GOING_DOWN;
            this.upwardForce = this.upwardForce == this.getBaseUpwardForce() ? this.upwardForce : this.upwardForce + 1;
            this.altitude -= 1;
        } else if (Math.round(this.weightCounter.getWeight()) >= upwardForce && this.upwardForce == this.getBaseUpwardForce()) {
            this.flyingState = FlyingState.GROUND;
            this.altitude = 0;
        }

        return current;
    }

    private void checkWeight() throws TooManyWeightException {
        if (Math.round(weightCounter.getWeight()) > upwardForce) {
            throw new TooManyWeightException(this.weightCounter.getWeight());
        }
    }

    public interface WeightCounter {
        double getWeight();
    }

    public enum FlyingState {
        FLYING("The balloon is flying!"), GOING_UP("The balloon is still going up."),
        GOING_DOWN("The balloon is going down."), GROUND("The balloon is exactly not flying.");

        FlyingState(String description) {
            this.description = description;
        }

        private final String description;

        @Override
        public String toString() {
            return description;
        }
    }

    @Override
    public String toString() {
        return "The balloon [can raise " + this.upwardForce + "]";
    }
}
