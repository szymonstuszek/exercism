class ReverseString {

    String reverse(String inputString) {
        return reverseString(inputString);
    }

    //n^2
    String reverseString(String inputString) {
        if (inputString.isEmpty()) {
            return inputString;
        }

        return inputString.charAt(inputString.length() - 1) +
                reverse(
                        inputString.substring(0, inputString.length() - 1)
                );
    }

    // try StringBuilder -> append, left to right, n
}
