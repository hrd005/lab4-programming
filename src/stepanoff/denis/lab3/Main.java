package stepanoff.denis.lab3;

import stepanoff.denis.lab3.balloon.*;
import stepanoff.denis.lab3.shorties.*;
import stepanoff.denis.lab3.util.ShortySearcher;

public class Main {

    public static void main(String[] args) {

        System.out.println("---Setting initialized---");

        Siropchik siropchik = new Siropchik();
        Znaika znaika = new Znaika();

        Basket basket = new Basket();
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

            @Override
            public void wokeAndGetReady() {
                this.clothing = new Clothing.Builder().title("Куртка").material("Кожа").build();
                super.wokeAndGetReady();
            }
        }
        Vintik vintik = new Vintik();
        crew.acceptNew(vintik);

        crew.acceptNew(new CrewMember() {

            {
                this.selfWeight = 49.0;
                this.name = "Шпунтик";
            }

            @Override
            public void wokeAndGetReady() {
                this.clothing = new Clothing.Builder().title("Куртка").material("Кожа").build();
                super.wokeAndGetReady();
            }
        });

        crew.acceptNew(new CrewMember() {

            {
                this.selfWeight = 53.0;
                this.name = "Пулька";
            }

            @Override
            public void wokeAndGetReady() {
                this.clothing = new Clothing.Builder().title("Сапоги").material("Кожа").build();
                super.wokeAndGetReady();
            }
        });

        crew.acceptNew(new CrewMember() {

            {
                this.selfWeight = 40.0;
                this.name = "Торопыжка";
            }

            @Override
            public void wokeAndGetReady() {
                this.clothing = new Clothing.Builder().title("Костюм-молния").build();
                super.wokeAndGetReady();
            }
        });

        crew.acceptNew(new CrewMember() {

            {
                this.selfWeight = 61.0;
                this.name = "Пончик";
            }

            @Override
            public void wokeAndGetReady() {
                this.clothing = new Clothing.Builder()
                        .title("Костюм с карманами")
                        .parts("Куртка", "Брюки")
                        .pockets(17)
                        .build();
                super.wokeAndGetReady();
            }
        });

        crew.acceptNew(siropchik);

        crew.acceptNew(new CrewMember() {

            {
                this.name = "Авоська";
                this.selfWeight = 56.0;
            }

            @Override
            public void wokeAndGetReady() {
                this.clothing = new Clothing.Builder().title("Лыжный костюм").build();
                super.wokeAndGetReady();
            }
        });

        crew.acceptNew(new CrewMember() {

            {
                this.selfWeight = 49.0;
                this.name = "Небоська";
            }

            @Override
            public void wokeAndGetReady() {
                this.clothing = new Clothing.Builder()
                        .title("Полосатый костюм")
                        .parts("Фуфайка", "Гетры", "Шарф")
                        .build();
                super.wokeAndGetReady();
            }
        });

        crew.acceptNew(new CrewMember() {

            {
                this.name = "Растеряйка";
                this.selfWeight = 43.0;
            }

            @Override
            public void wokeAndGetReady() {
                System.out.println(this.name + " не нашел свою куртку");
                System.out.println(this.name + " не нашел свою кепку");
                this.clothing = new Clothing.Builder().title("Зимняя шапка").build();
                super.wokeAndGetReady();
            }
        });

        crew.acceptNew(new CrewMember() {

            {
                this.selfWeight = 48.0;
                this.name = "Тюбик";
            }

            @Override
            public void wokeAndGetReady() {
                super.wokeAndGetReady(this.name + " взял краски и кисточку.");
            }
        });

        crew.acceptNew(new CrewMember() {

            {
                this.selfWeight = 47.0;
                this.name = "Гусля";
            }

            @Override
            public void wokeAndGetReady() {
                super.wokeAndGetReady(this.name + " взял флейту.");
            }
        });

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

            @Override
            public void wokeAndGetReady() {
                super.wokeAndGetReady(this.name + " взял аптечку.");
            }
        }
        Pilulkin pilulkin = new Pilulkin();
        crew.acceptNew(pilulkin);

        crew.acceptNew(new CrewMember() {

            {
                this.name = "Незнайка";
                this.selfWeight = 40.0;
            }

            @Override
            public void wokeAndGetReady() {
                super.wokeAndGetReady(this.name + " просто проснулся.");
            }
        });

        System.out.println(siropchik + " created");
        System.out.println(znaika + " created");

        System.out.println("It is 5:00 am, and Znaika began to woke his crew.");
        znaika.wokeUpCrew(crew);

        System.out.println("Near 5:49 am the whole town collected on the square.");
        Crowd crowd = new Crowd();
        System.out.println(crowd + " created");
        crowd.addOnGotInjuredListener(pilulkin);

        crowd.addSpecialCharacter(new Shorty.Builder().name("Стекляшкин").action(() -> {
            System.out.println("Стекляшкин наблюдает за шаром в телескоп.");
            System.out.println("Shorties sees a balloon only in " + basket.getBalloon().getVisibleSize() + "% of real size.");
        }).build());

        crowd.addSpecialCharacter(new Shorty.Builder().name("Цветик").action(() -> {
            System.out.println("Цветик читает стихи: \n\tОгромный шар, надутый паром,\n\tПоднялся в воздух он недаром.\n" +
                    "\tНаш коротышка хоть не птица,\n\tЛетать он все-таки годится.\n\tИ все доступно уж, эхма!\n" +
                    "\tТеперь для нашего ума!");
        }).build());

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

        crowd.startQuarrel((int)(Math.random()*15 + 5));

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
        new ShortySearcher(crowd).getShorty("Стекяляшкин").performAction();

        while (crowd.getNoisiness() != 0) {
            crowd.silence();
        }
        new ShortySearcher(crowd).getShorty("Цветик").performAction();
    }
}
