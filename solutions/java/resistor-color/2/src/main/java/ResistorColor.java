import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ResistorColor {

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

    int colorCode(String color) {
        return Arrays.stream(Resistor.values())
                .filter(resistor -> resistor.name().equalsIgnoreCase(color))
                .mapToInt(Resistor::getResistance)
                .findFirst()
                .orElse(-1);
    }

    String[] colors() {
        return (String[]) Arrays.stream(Resistor.values())
                .map(v -> v.name().toLowerCase())
                .toArray();
    }

}
