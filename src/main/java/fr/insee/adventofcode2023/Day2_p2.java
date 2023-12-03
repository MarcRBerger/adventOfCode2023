package fr.insee.adventofcode2023;

import fr.insee.adventofcode2023.utils.FileParserHelper;
import fr.insee.adventofcode2023.utils.LineProcessor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("Day2_p2")
public class Day2_p2 implements LineProcessor, Day {

    Pattern pattern = Pattern.compile("(\\d+)\\s+(red|blue|green)");
    int lineNumber=0;
    long totalPower = 0;

    public String treat(String inputFilename) {
        FileParserHelper.parseFile(inputFilename,this);
        System.out.println("totalPower=" + totalPower);
        return String.valueOf(totalPower);
    }

    public String treat() {
        return treat("d2_input.txt");
    }

    @Override
    public void processLine(String line) {
        String[] tirages = line.split(";");
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
}
