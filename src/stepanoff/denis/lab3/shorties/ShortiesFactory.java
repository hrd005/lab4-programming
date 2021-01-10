package stepanoff.denis.lab3.shorties;

import java.util.Arrays;
import java.util.List;

public class ShortiesFactory {

    private static final List<String> NAMES = Arrays.asList(
            "Незнайка", "Молчун", "Торопыжка",
            "Гунька", "Кнопочка", "Стекляшкин", "Мушка", "Цветик",
            "Микроша", "Ромашка", "Синеглазка", "Топик", "Белочка", "Галочка", "Ёлочка", "Кисонька", "Заинька",
            "Ласточка", "Кубышка", "Маргаритка", "Снежинка", "Пушинка", "Соломка", "Стрекоза", "Самоцветик", "Медуница",
            "Бублик", "Гвоздик", "Шурупчик", "Смекайло"
    );
    private static final int SHORTY_WEIGHT_DIAPASON_START = 30;
    private static final int SHORTY_WEIGHT_DIAPASON_SIZE = 30;

    public static Shorty getRandomShorty() {

        return new CrewMember() {
            {
                this.name = NAMES.get((int) (Math.random() * NAMES.size()));;
                this.selfWeight = Math.random() * SHORTY_WEIGHT_DIAPASON_SIZE + SHORTY_WEIGHT_DIAPASON_START;
            }

            @Override
            public String getName() {
                return super.getName();
            }

            @Override
            public double getWeight() {
                return this.selfWeight;
            }
        };
    }
}
