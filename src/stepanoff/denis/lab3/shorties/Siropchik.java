package stepanoff.denis.lab3.shorties;

public class Siropchik extends Shorty implements Speakable {

    private boolean isRejoiced = false;
    private boolean isFearingToFly = true;
    public Leg leg = new Leg();

    {
        this.selfWeight = 59.0;
        this.name = "Сиропчик";
    }

    @Override
    public double getWeight() {
        return this.selfWeight;
    }

    @Override
    public void speak() {
        if (this.isRejoiced && !isFearingToFly) {
            this.speak("Вот и хорошо, даже шар боится лететь!");
        }
    }

    @Override
    public void speak(String speech) {
        if (!isFearingToFly) {
            System.out.println(this.getName() + " said: \"" + speech + "\".");
        }
    }

    public void rejoice() {
        this.isRejoiced = true;
        this.isFearingToFly = false;
    }

    public void setFearingToFly(boolean isFearingToFly) {
        this.isFearingToFly = isFearingToFly;
    }

    public class Leg implements Movable {

        @Override
        public void moveUp() {
            if (isFearingToFly) {
                System.out.println(getName() + " is trying to escape!");
            } else {
                System.out.println(getName() + " is not fearing to fly already!");
            }
        }
    }
}
