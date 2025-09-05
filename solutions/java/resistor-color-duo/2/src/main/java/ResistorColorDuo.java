import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ResistorColorDuo {

    enum Resistor {
        BLACK(0),
        BROWN(1),
        RED(2),
        ORANGE(3),
        YELLOW(4),
        GREEN(5),
        BLUE(6),
        VIOLET(7),
        GREY(8),
        WHITE(9);
        private final int resistance;

        Resistor(int resistance) {
            this.resistance = resistance;
        }

        public int getResistance() {
            return resistance;
        }
    }

    int value(String[] colors) {

        return getResistance(colors[0]) * 10 + getResistance(colors[1]);
    }

    int getResistance(String color) {
        return Arrays.stream(Resistor.values())
                .filter(resistor -> resistor.name().equalsIgnoreCase(color))
                .mapToInt(Resistor::getResistance)
                .findFirst()
                .orElse(-1);
    }
}
