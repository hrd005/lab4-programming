package stepanoff.denis.lab3.balloon;

import stepanoff.denis.lab3.WeightableEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Basket extends WeightableEntity {

    private final List<WeightableEntity> content = new ArrayList<>();

    Balloon balloon = new Balloon(this::getWeight);

    public Basket(WeightableEntity... passengers) {
        this.content.addAll(Arrays.asList(passengers));

        this.selfWeight = 23.0;

        while (this.getWeight() <= this.balloon.getUpwardForce()) {
            this.content.add(Sandbag.getANewOne());
        }
    }

    public Balloon getBalloon() {
        return balloon;
    }

    public void putIn(WeightableEntity... entities) {
        this.content.addAll(Arrays.asList(entities));
    }

    public void remove(WeightableEntity we) {
        this.content.remove(we);
    }

    public Optional<Sandbag> getRandomSandbag() {
        Optional<WeightableEntity> ret = this.content.stream().filter(we -> we instanceof Sandbag).findAny();
        ret.ifPresent(this::remove);
        return ret.map(entity -> (Sandbag) entity);
    }

    @Override
    public double getWeight() {
        return this.selfWeight + content.stream().mapToDouble(WeightableEntity::getWeight).sum();
    }

    @Override
    public String toString() {
        return "Basket [\n" + this.content.stream().map(
                w -> "\t" + w.toString() + "\n"
        ).collect(Collectors.joining()) + "] with " + this.balloon.toString();
    }
}
