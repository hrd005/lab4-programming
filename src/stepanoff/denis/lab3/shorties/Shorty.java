package stepanoff.denis.lab3.shorties;

import stepanoff.denis.lab3.WeightableEntity;

public abstract class Shorty extends WeightableEntity {

    protected String name;

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Shorty " + this.getName();
    }
}
