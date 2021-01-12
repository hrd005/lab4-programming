package stepanoff.denis.lab3.shorties;

import stepanoff.denis.lab3.WeightableEntity;
import stepanoff.denis.lab3.balloon.*;

import java.util.Optional;

public class Znaika extends CrewMember implements Speakable {

    private static final String BASE_SPEECH = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eleifend " +
            "eleifend molestie. Etiam gravida lacus vitae viverra convallis. Nam iaculis purus non est dapibus malesuada. " +
            "Integer et venenatis arcu, id blandit mi. Proin ut dui interdum, consequat arcu eget, condimentum nunc. " +
            "Vivamus tincidunt nisl eget neque sodales mattis. Aenean commodo mi sollicitudin metus ultricies tincidunt. " +
            "Praesent odio nisi, lacinia eu velit ac, aliquam malesuada leo. Donec molestie massa facilisis, lobortis " +
            "dui et, suscipit neque.";

    public Arm arm = new Arm();

    {
        this.name = "Знайка";
        this.selfWeight = 45.0;
    }

    @Override
    public double getWeight() {
        return this.selfWeight + this.arm.lookAtSubject().orElse(new WeightableEntity(){
            @Override
            public double getWeight() {
                return 0;
            }
        }).getWeight();
    }

    @Override
    public void speak() {
        this.speak(produceSpeech());
    }

    @Override
    public void speak(String speech) {
        System.out.println(this.getName() + " said: \"" + speech + "\".");
    }

    public void takeSandbag(Basket basket) {
        basket.getRandomSandbag().ifPresent(
                sandbag -> this.arm.takeSubject(sandbag)
        );
    }

    public void trowOutSandbag() {
        this.arm.lookAtSubject().ifPresent(
                we -> {
                    if (we instanceof Sandbag) {
                        System.out.println(we.toString() + " is thrown away");
                        this.arm.removeSubject();
                    }
                }
        );
    }

    private String produceSpeech() {
        return BASE_SPEECH;
    }

    public void wokeUpCrew(Crew crew) {
        crew.forEachMember(CrewMember::wokeAndGetReady);
    }

    public void orderToPutSandbags(Crew crew, Basket basket) {
        crew.forEachMember((s) -> s.putSandbag(basket));
    }

    public Fire produceFire() {
        return new Fire();
    }

    public void emptyBalloon(Balloon balloon) {
        balloon.empty();
    }

    public void orderToPumpAir(Crew crew, Boiler.Pump pump) {
        crew.forEachMember((s) -> {
            pump.getConnection().ifPresent(balloon -> {
                if (!balloon.isFilled()) {
                    double percentAmount = pump.pump();
                    balloon.changeVolume(percentAmount);
                    System.out.println(s.getName() + " pumped " + percentAmount*100 + "% of balloon volume");
                }
            });
        });

        pump.getConnection().ifPresent(balloon -> {
            if (!balloon.isFilled()) this.orderToPumpAir(crew, pump);
        });
    }

    public void connect(Boiler.Pump pump, Balloon balloon) {
        balloon.setTemperature(1);
        pump.connect(balloon);
    }

    public void disconnect(Boiler.Pump pump) {
        pump.disconnect();
    }

    public void cutTheRope(Balloon balloon) {
        balloon.deattachRope();
    }

    @Override
    public void wokeAndGetReady() {} // Znaika is already ready. He is responsible.

    public class Arm implements Movable {

        private WeightableEntity subject = null;

        private void takeSubject(WeightableEntity subject) {
            this.subject = subject;
        }

        private Optional<WeightableEntity> removeSubject() {
            Optional<WeightableEntity> ret = Optional.ofNullable(this.subject);
            this.subject = null;
            return ret;
        }

        private Optional<WeightableEntity> lookAtSubject() {
            return Optional.ofNullable(subject);
        }

        @Override
        public void moveUp() {

            if (subject != null) {
                if (subject.getWeight() >= 1.5) {
                    System.out.println("Znaika holds something heavy, so he can't to raise his hand");
                    return;
                }
            }

            System.out.println("Znaika raised his hand.");
        }
    }
}
