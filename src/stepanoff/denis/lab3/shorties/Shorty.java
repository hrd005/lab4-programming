package stepanoff.denis.lab3.shorties;

import stepanoff.denis.lab3.WeightableEntity;

import java.util.Hashtable;

public abstract class Shorty extends WeightableEntity {

    protected String name;

    protected Hashtable<String, SpecialAction> spt = new Hashtable<>();

    //protected Clothing clothing;

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Shorty " + this.getName();
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Shorty) {
            return ((Shorty) o).name.equals(this.name);
        }
        else return false;
    }

    public static class Builder {

        private final Shorty shorty = ShortiesFactory.getRandomShorty();

        public Builder name(String name) {
            this.shorty.name = name;
            return this;
        }

        public Builder action(String actionTitle, SpecialAction specialAction) {
            this.shorty.spt.put(actionTitle, specialAction);
            return this;
        }

        public Shorty build() {
            return this.shorty;
        }
    }

    @FunctionalInterface
    public interface SpecialAction {

        void doAction();
    }
}
