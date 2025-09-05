import java.util.Arrays;
import java.util.stream.Collectors;

class RnaTranscription {

    String transcribe(String dnaStrand) {
        return Arrays.stream(dnaStrand.split(""))
                .map(this::transcribeNucleotid)
                .collect(Collectors.joining());
    }

    String transcribeNucleotid(String input) {
        return switch(input) {
            case "G" -> "C";
            case "C" -> "G";
            case "T" -> "A";
            case "A" -> "U";
            case "" -> "";
            default -> throw new RuntimeException("Incorrect input");
        };
    }

}
