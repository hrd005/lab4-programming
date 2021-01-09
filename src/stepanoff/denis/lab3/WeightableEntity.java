package stepanoff.denis.lab3;

abstract public class WeightableEntity {

    protected double selfWeight;
    public abstract double getWeight();

    @Override
    public String toString() {
        return "I do not know what I am, but I know I weight " + this.getWeight();
    }
}
