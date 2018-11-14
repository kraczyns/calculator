package exceptions;

import java.util.List;

public class NegativesNotAllowedException extends Exception {

    private List<Integer> negatives;

    public NegativesNotAllowedException(List<Integer> negatives) {
        this.negatives = negatives;
    }

    public String getMessage() {
        return "Negatives not allowed - " + negatives.toString();
    }
}
