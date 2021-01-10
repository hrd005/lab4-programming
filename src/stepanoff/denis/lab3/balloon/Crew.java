package stepanoff.denis.lab3.balloon;

import stepanoff.denis.lab3.shorties.CrewMember;
import stepanoff.denis.lab3.shorties.Shorty;
import stepanoff.denis.lab3.util.ShortyCollection;
import stepanoff.denis.lab3.util.ShortySearcher;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Crew implements ShortyCollection {

    private final ArrayList<CrewMember> crewMembers = new ArrayList<>();
    private final Basket basket;

    public Crew(Basket basket) {
        this.basket = basket;
    }

    public void acceptNew(Shorty shorty) {
        this.crewMembers.add((CrewMember) shorty);
    }

    public void forEachMember(Consumer<CrewMember> action) {
        this.crewMembers.forEach(action);
    }

    public boolean getIntoBasket(String name) {
        try {
            Shorty cm = new ShortySearcher(this).getShorty(name);
            this.basket.putIn(cm);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Shorty[] toArray() {
        Shorty[] t = new Shorty[this.crewMembers.size()];
        return this.crewMembers.toArray(t);
    }
}
