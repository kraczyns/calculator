import exceptions.InvalidCalculatorInputException;
import exceptions.NegativesNotAllowedException;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    int add(String numbers) throws NegativesNotAllowedException, InvalidCalculatorInputException {
        numbers = numbers.trim().replaceAll(Constants.WHITESPACE, Constants.EMPTY_STRING);
        if (!numbers.isEmpty()) {
            List<Integer> extractedNumbers =
                    convertStringNumbersToInt(extractNumbersFromStringIfCustomDelimiter(numbers), getDelimiter(numbers));
            return sum(extractedNumbers);
        }
        return 0;
    }

    private List<Integer> convertStringNumbersToInt(String s, String delimiter) throws InvalidCalculatorInputException {
        String numbers[] = validateThatDelimiterIsNotTheLastCharacter(s).split(delimiter);
        List<Integer> numbersInt = new ArrayList<>();
        for (String number : numbers) {
            numbersInt.add(stringAbleToConvertToInt(number));
        }
        return numbersInt;
    }

    private String extractNumbersFromStringIfCustomDelimiter(String numbers) {
        if (isCustomDelimiterSet(numbers)) {
            return numbers.substring(numbers.indexOf(Constants.NEW_LINE) + Constants.NEW_LINE.length());
        }
        return numbers;
    }

    private String getDelimiter(String numbers) {
        if (isCustomDelimiterSet(numbers)) {
            return convertStringToREGEX(extractDelimiterPatternFromString(numbers));
        }
        return Constants.DEFAULT_DELIMITER_REGEX;
    }

    private String convertStringToREGEX(String stringDelimiter) {
        StringBuilder regexDelimiterBuilder = new StringBuilder();
        char previousCharacter = 0;
        for (char character : stringDelimiter.toCharArray()) {
            if (previousCharacter != character && previousCharacter != 0) {
                regexDelimiterBuilder.append(Constants.OTHER_PATTERN_REGEX);
            }
            regexDelimiterBuilder.append(Constants.ESCAPE_SPECIAL_CHARACTER_REGEX).append(character);
            previousCharacter = character;
        }
        return regexDelimiterBuilder.toString();
    }

    private boolean isCustomDelimiterSet(String numbers) {
        return numbers.contains(Constants.DELIMITER_START_MARKER);
    }

    private String extractDelimiterPatternFromString(String s) {
        int beginIndexOfDelimiter = s.indexOf(Constants.DELIMITER_START_MARKER) + Constants.DELIMITER_START_MARKER.length();
        int endIndexOfDelimiter = s.indexOf(Constants.NEW_LINE);
        return s.substring(beginIndexOfDelimiter, endIndexOfDelimiter);
    }

    private String validateThatDelimiterIsNotTheLastCharacter(String numbers) {
        if (!Character.isDigit(numbers.charAt(numbers.length() - 1))) {
            return numbers + Constants.WHITESPACE;
        }
        return numbers;
    }

    private int stringAbleToConvertToInt(String string) throws InvalidCalculatorInputException {
        if (string.matches(Constants.INTEGER_REGEX)) {
            return Integer.parseInt(string);
        }
        throw new InvalidCalculatorInputException(string);
    }


    private int sum(List<Integer> numbers) throws NegativesNotAllowedException {
        if (existsNegativeNumber(numbers)) {
            List<Integer> negatives = extractNegativeNumbers(numbers);
            throw new NegativesNotAllowedException(negatives);
        }
        return numbers
                .stream()
                .map(this::getValidNumber)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int getValidNumber(int number) {
        return number > Constants.IGNORE_NUMBER ? 0 : number;
    }

    private List<Integer> extractNegativeNumbers(List<Integer> numbers) {
        List<Integer> negatives = new ArrayList<>();
        for (int number : numbers) {
            if (number < 0) {
                negatives.add(number);
            }
        }
        return negatives;
    }

    private boolean existsNegativeNumber(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 0) {
                return true;
            }
        }
        return false;
    }
}