package fr.insee.adventofcode2023;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2_p2 {

    public static String treat(String inputFilename) {
        long totalPower = 0;
        try {
            Resource resource = new ClassPathResource(inputFilename);
            File file = resource.getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;

            Pattern pattern = Pattern.compile("(\\d+)\\s+(red|blue|green)");
            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] tirages = line.split(";");
                lineNumber++;
                int redMin = 0;
                int blueMin = 0;
                int greenMin = 0;
                for (String tirage : tirages) {
                    Matcher matcher = pattern.matcher(tirage);
                    while (matcher.find()) {
                        int number = Integer.parseInt(matcher.group(1));
                        String color = matcher.group(2);

                        switch (color) {
                            case "red":
                                redMin = (redMin < number) ? number : redMin;
                                break;
                            case "blue":
                                blueMin = (blueMin < number) ? number : blueMin;
                                break;
                            case "green":
                                greenMin = (greenMin < number) ? number : greenMin;
                                break;
                        }
                    }
                }
                System.out.println(lineNumber + ": " + " R:" + redMin + " G:" + greenMin + " B:" + blueMin + " - " + line);
                totalPower += (long) redMin * greenMin * blueMin;
            }
            bufferedReader.close();
            System.out.println("totalPower=" + totalPower);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(totalPower);
    }

    public static String treat() {
        return treat("d2_input.txt");
    }
}
