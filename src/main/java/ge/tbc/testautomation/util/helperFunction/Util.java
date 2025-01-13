package ge.tbc.testautomation.util.helperFunction;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {


    // getGuestsCount-იდან რიცხვების გამოყოფა
    public static int[] extractMinMax(String text) {
        String[] numbers = text.replaceAll("[^0-9-]", "").split("-");
        int min = Integer.parseInt(numbers[0]);
        int max = Integer.parseInt(numbers[1]);
        return new int[]{min, max};
    }


    public static void validateNumbersInRangeUtil(ElementsCollection offersDescription, int min, int max) {
        List<List<Integer>> extractedNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+)([+/\\-])?(\\d+)?\\s+(სტუმარზე|ადამიანზე)");


        offersDescription.shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (SelenideElement element : offersDescription) {
            String text = element.getText();
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                List<Integer> pair = new ArrayList<>();
                pair.add(Integer.parseInt(matcher.group(1))); // პირველი რიცხვი

                if (matcher.group(3) != null && !matcher.group(3).isEmpty()) {
                    try {
                        pair.add(Integer.parseInt(matcher.group(3))); // მეორე რიცხვი
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format: " + matcher.group(3));
                    }
                }

                extractedNumbers.add(pair); // წყვილის დამატება სიაში
            }
        }

        // ყველა წყვილის დადასტურება მინ/მაქს დიაპაზონში
        boolean allPairsIsValid = extractedNumbers.stream()
                .allMatch(pair -> pair.stream().anyMatch(num -> num >= min && num <= max));

        System.out.println("Extracted Numbers: " + extractedNumbers);
        Assert.assertTrue(allPairsIsValid, "Some numbers are out of the valid range!");
    }
}