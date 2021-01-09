package stepanoff.denis.lab3.balloon;

public class Balloon {

    private final static int BASE_UPWARD_FORCE = 426;
    private int upwardForce = BASE_UPWARD_FORCE; //will be in kilos, not newtons

    private FlyingState flyingState = FlyingState.GROUND;
    private final WeightCounter weightCounter;

    public Balloon(WeightCounter weightCounter) {
        this.weightCounter = weightCounter;
    }

    public int getUpwardForce() {
        return upwardForce;
    }

    public boolean riseUp() {
        if (this.checkWeight()) {
            this.flyingState = FlyingState.GOING_UP;
            return true;
        } else return false;
    }

    public FlyingState getFlyingState() {
        FlyingState current = this.flyingState;

        if (Math.round(this.weightCounter.getWeight()) == upwardForce) {
            this.flyingState = FlyingState.FLYING;
        } else if (Math.round(this.weightCounter.getWeight()) < upwardForce) {
            this.flyingState = FlyingState.GOING_UP;
            this.upwardForce -= 1;
        } else if (Math.round(this.weightCounter.getWeight()) > upwardForce) {
            this.flyingState = FlyingState.GOING_DOWN;
            this.upwardForce = this.upwardForce == BASE_UPWARD_FORCE ? this.upwardForce : this.upwardForce + 1;
        } else if (Math.round(this.weightCounter.getWeight()) >= upwardForce && this.upwardForce == BASE_UPWARD_FORCE) {
            this.flyingState = FlyingState.GROUND;
        }

        return current;
    }

    private boolean checkWeight() {
        return Math.round(weightCounter.getWeight()) <= upwardForce;
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
