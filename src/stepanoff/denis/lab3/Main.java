package stepanoff.denis.lab3;

import stepanoff.denis.lab3.balloon.*;
import stepanoff.denis.lab3.shorties.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("---Setting initialized---");

        Siropchik siropchik = new Siropchik();
        Znaika znaika = new Znaika();

        Basket basket = new Basket(siropchik, znaika);
        System.out.println(basket + " created");

        Crew crew = new Crew(basket);
        class Vintik extends CrewMember {

            {
                this.selfWeight = 49.0;
                this.name = "Винтик";
            }

            public Boiler produceBoiler() {
                return new Boiler();
            }
        }
        Vintik vintik = new Vintik();
        crew.acceptNew(vintik);
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Шпунтик")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Пулька")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Торопыжка")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Винтик")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Пончик")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Авоська")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Небоська")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Растеряйка")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Тюбик")
                        .action("getReady", () -> { } )
                        .build()
        );
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Гусля")
                        .action("getReady", () -> { } )
                        .build()
        );
        class Pilulkin extends CrewMember implements Crowd.GotInjuredListener {

            {
                this.name = "Пилюлькин";
                this.selfWeight = 39.0;
            }

            @Override
            public void onGotInjured(Shorty shorty) {
                if (basket.getBalloon().getFlyingState() == Balloon.FlyingState.GROUND) {
                    System.out.println(this.name + " heals injured " + shorty.getName());
                } else {
                    System.out.println(shorty.getName() + " got injured but " + this.name + " is far away.");
                }
            }
        }
        Pilulkin pilulkin = new Pilulkin();
        crew.acceptNew(pilulkin);
        crew.acceptNew(
                new Shorty.Builder()
                        .name("Незнайка")
                        .action("getReady", () -> { } )
                        .build()
        );

        System.out.println(siropchik + " created");
        System.out.println(znaika + " created");

        System.out.println("It is 5:00 am, and Znaika began to woke his crew.");
        znaika.wokeUpCrew(crew);

        System.out.println("Near 5:49 am the whole town collected on the square.");
        Crowd crowd = new Crowd();
        System.out.println(crowd + " created");
        crowd.addOnGotInjuredListener(pilulkin);

        crew.getIntoBasket("Торопыжка");
        crew.getIntoBasket("Незнайка");

        znaika.orderToPutSandbags(crew, basket);

        Fire fire = znaika.produceFire();
        Boiler boiler = vintik.produceBoiler();
        boiler.setFire(fire);
        boiler.addOnAirWarmedListener(() -> {
            znaika.emptyBalloon(basket.getBalloon());
            if (!basket.getBalloon().isFilled()) crowd.laugh(Crowd.LaughLevel.HIGH);
            znaika.connect(boiler.getPump(), basket.getBalloon());
            znaika.orderToPumpAir(crew, boiler.getPump());
            znaika.disconnect(boiler.getPump());
        });
        boiler.warm();

        System.out.println("\n\n");

        basket.putIn(crew.toArray());

        siropchik.rejoice();
        siropchik.speak();
        siropchik.leg.moveUp();

        znaika.takeSandbag(basket);
        znaika.trowOutSandbag();

        try {
            basket.getBalloon().riseUp();
        } catch (TooManyWeightException e) {
            // Give Znaika second chance!
            znaika.takeSandbag(basket);
            znaika.trowOutSandbag();

            try {
                basket.getBalloon().riseUp();
            } catch (TooManyWeightException ee) {
                ee.printStackTrace();
                System.exit(-2);
            }
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
        znaika.cutTheRope(basket.getBalloon());

        try {
            basket.getBalloon().riseUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (basket.getBalloon().getFlyingState() != Balloon.FlyingState.FLYING) {
            System.out.println(Balloon.FlyingState.FLYING.toString()
                    + " Altitude is " + basket.getBalloon().getAltitude() + " meters.");
        }

        crowd.clapInHands();

    }
}
