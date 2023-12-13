package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";

        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//\\[([^\\]]+)\\]\n(.*)").matcher(numbers);
            if (matcher.matches()) {
                delimiter = delimiter + "|[" + matcher.group(1) + "]";
                numbers = matcher.group(2);
            }
        }

        String[] nums = numbers.split(delimiter);
        return calculateSum(nums);
    }

    private static int calculateSum(String[] nums) {
        int sum = 0;
        StringBuilder negatives = new StringBuilder();

        for (String num : nums) {
            int n = Integer.parseInt(num);
            if (n < 0) {
                if (negatives.length() > 0) {
                    negatives.append(",");
                }
                negatives.append(n);
            } else if (n <= 1000) {
                sum += n;
            }
        }

        if (negatives.length() > 0) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives.toString());
        }

        return sum;
    }
}