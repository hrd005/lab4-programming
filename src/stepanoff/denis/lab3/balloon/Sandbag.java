package stepanoff.denis.lab3.balloon;

import stepanoff.denis.lab3.WeightableEntity;

public class Sandbag extends WeightableEntity {

    private static final double MAX_WEIGHT = 15.0;
    private static final double MIN_WEIGHT = 0.5;

    public Sandbag(double weight) {
        this.selfWeight = weight;
    }

    @Override
    public double getWeight() {
        return this.selfWeight;
    }

    public static Sandbag getANewOne() {
        return new Sandbag(Math.random()*(MAX_WEIGHT-MIN_WEIGHT) + MIN_WEIGHT);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Sandbag) {
            return ((Sandbag) object).selfWeight == this.selfWeight;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Plain sandbag [weight=%.2f]", this.getWeight());
    }
}
