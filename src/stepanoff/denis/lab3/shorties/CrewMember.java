package stepanoff.denis.lab3.shorties;

import stepanoff.denis.lab3.balloon.Basket;
import stepanoff.denis.lab3.balloon.Sandbag;

public abstract class CrewMember extends Shorty {

    private boolean isAsleep = true;
    protected Clothing clothing;

    public void wokeAndGetReady() {
        this.isAsleep = false;
        //this.spt.getOrDefault("getReady", ()->{}).doAction();
        System.out.println(this.name + " dressed in " + this.clothing);
    }

    protected void wokeAndGetReady(String customMsg) {
        this.isAsleep = false;
        System.out.println(customMsg);
    }

    public void putSandbag(Basket basket) {
        if (isAsleep) return;

        Sandbag s  = Sandbag.getANewOne();
        basket.putIn(s);
        System.out.println(s + " added by " + this.name);
    }

    @Override
    public double getWeight() {
        return this.selfWeight;
    }
}
