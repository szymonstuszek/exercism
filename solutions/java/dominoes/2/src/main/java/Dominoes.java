import java.util.*;
import java.util.stream.Collectors;

class Dominoes {

    List<Domino> formChain(List<Domino> inputDominoes) throws ChainNotFoundException {
        if (inputDominoes.isEmpty()) {
            return inputDominoes;
        }

        if (inputDominoes.size() == 1) {
            return isValid(inputDominoes) ? inputDominoes : fail();
        }

        Domino domino = inputDominoes.getFirst();
        List<Domino> currentChain = new ArrayList<>(List.of(domino));
        List<Domino> remainingChain = remove(inputDominoes, domino);

        List<Domino> solution = next(currentChain, remainingChain);
        if (!solution.isEmpty() && isValid(solution)) {
            return solution;
        }

        return fail();
    }

    public List<Domino> next(List<Domino> current, List<Domino> remaining) {
        if (remaining.isEmpty()) {
            return Collections.emptyList();
        }

        Domino lastDomino = current.getLast();

        if (remaining.size() == 1 && matches(lastDomino, remaining.getFirst())) {
            Domino domino = buildNext(lastDomino, remaining.getFirst()).get();
            current.add(domino);
            return current;
        }

        List<Domino> matchingDominoes = remaining.stream()
                .filter(d -> matches(lastDomino, d))
                .toList();

        for (Domino matchingDomino : matchingDominoes) {
            List<Domino> currentCopy = new ArrayList<>(current);
            currentCopy.add(buildNext(lastDomino, matchingDomino).get());

            List<Domino> nextChain = next(currentCopy, remove(remaining, matchingDomino));

            if (!nextChain.isEmpty()) {
                return nextChain;
            }
        }

        return Collections.emptyList();
    }

    public Optional<Domino> buildNext(Domino current, Domino other) {
        if (current.getRight() == other.getLeft()) {
            return Optional.of(other);
        }

        if (current.getRight() == other.getRight()) {
            return Optional.of(flip(other));
        }

        return Optional.empty();
    }

    private boolean isValid(List<Domino> dominoes) {
        return dominoes.getFirst().getLeft() == dominoes.getLast().getRight();
    }

    private Domino flip(Domino domino) {
        return new Domino(domino.getRight(), domino.getLeft());
    }

    private boolean matches(Domino current, Domino other) {
        return current.getRight() == other.getLeft() || current.getRight() == other.getRight();
    }

    private List<Domino> remove(List<Domino> dominoes, Domino domino) {
        var copy = new ArrayList<>(dominoes);
        copy.remove(domino); // removes only the first occurrence
        return copy;
    }

    private <T> T fail() throws ChainNotFoundException {
        throw new ChainNotFoundException("No domino chain found.");
    }
}