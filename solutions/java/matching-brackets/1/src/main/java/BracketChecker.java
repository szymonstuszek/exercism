import java.util.*;

class BracketChecker {
    private final ArrayDeque<String> deque = new ArrayDeque<>();
    private final String expression;
    private final static String LEFT_ROUND_BRACKET = "(";
    private final static String LEFT_CURLY_BRACKET = "{";
    private final static String LEFT_SQUARE_BRACKET = "[";
    private final static String RIGHT_ROUND_BRACKET = ")";
    private final static String RIGHT_CURLY_BRACKET = "}";
    private final static String RIGHT_SQUARE_BRACKET = "]";
    private final static Set<String> STARTING_BRACKETS = Set.of(
            LEFT_ROUND_BRACKET,
            LEFT_CURLY_BRACKET,
            LEFT_SQUARE_BRACKET
    );

    private final static Set<String> ENDING_BRACKETS = Set.of(
            RIGHT_ROUND_BRACKET,
            RIGHT_CURLY_BRACKET,
            RIGHT_SQUARE_BRACKET
    ) ;

    BracketChecker(final String expression) {
        this.expression = expression;
    }

    boolean areBracketsMatchedAndNestedCorrectly() {
        String[] charArray = expression.split("");

        for (String c : charArray) {

            if (STARTING_BRACKETS.contains(c)) {
                deque.add(c);
            }

            if (ENDING_BRACKETS.contains(c)) {
                String last = deque.peekLast();

                if (last == null) {
                    return false;
                }

                String expectedLast = findMatchingBracket(c);
                if (last.equals(expectedLast)) {
                    deque.pollLast();
                } else {
                    return false;
                }
            }
        }

        return deque.isEmpty();
    }

    private String findMatchingBracket(String bracket) {
        return switch(bracket) {
            case RIGHT_ROUND_BRACKET -> LEFT_ROUND_BRACKET ;
            case RIGHT_SQUARE_BRACKET -> LEFT_SQUARE_BRACKET;
            case RIGHT_CURLY_BRACKET -> LEFT_CURLY_BRACKET ;
            default -> "";
        };

    }

}