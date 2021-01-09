package stepanoff.denis.lab3;

import stepanoff.denis.lab3.balloon.Balloon;
import stepanoff.denis.lab3.balloon.Basket;
import stepanoff.denis.lab3.shorties.Crowd;
import stepanoff.denis.lab3.shorties.Siropchik;
import stepanoff.denis.lab3.shorties.Znaika;

public class Main {

    public static void main(String[] args) {
        Siropchik siropchik = new Siropchik();
        Znaika znaika = new Znaika();
        Crowd crowd = new Crowd();
        Basket basket = new Basket(siropchik, znaika);

        System.out.println("---Setting initialized---");
        System.out.println(siropchik.toString() + " created");
        System.out.println(znaika.toString() + " created");
        System.out.println(crowd.toString() + " created");
        System.out.println(basket.toString() + " created");
        System.out.println("\n\n");

        siropchik.rejoice();
        siropchik.speak();
        siropchik.leg.moveUp();

        znaika.takeSandbag(basket);
        znaika.trowOutSandbag();

        if (!basket.getBalloon().riseUp()) {
            System.out.println("\n\nZnaika have choose a too lightweight sandbag. A balloon will not rise. \n" +
                    "Also the story ends.");
            return;
        }
        crowd.understandZnaika(basket);

        Balloon.FlyingState fs = basket.getBalloon().getFlyingState();
        if (fs == Balloon.FlyingState.GOING_DOWN || fs == Balloon.FlyingState.GROUND) {
            System.out.println("\n\nSomething went wrong, but balloon successfully landed.");
            return;
        }

        crowd.clapInHands();
        znaika.arm.moveUp();
        znaika.speak("Шар летит, у нас все получилось!");
    }
}
