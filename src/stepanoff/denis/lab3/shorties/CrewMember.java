package stepanoff.denis.lab3.shorties;

import stepanoff.denis.lab3.balloon.Basket;
import stepanoff.denis.lab3.balloon.Sandbag;

public abstract class CrewMember extends Shorty {

    public void wokeAndGetReady() {
        this.spt.getOrDefault("getReady", ()->{}).doAction();
    }

    public void putSandbag(Basket basket) {
        Sandbag s  = Sandbag.getANewOne();
        basket.putIn(s);
        System.out.println(s + " added by " + this.name);
    }

    @Override
    public double getWeight() {
        return this.selfWeight;
    }
}
