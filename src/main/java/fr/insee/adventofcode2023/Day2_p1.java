package fr.insee.adventofcode2023;

import fr.insee.adventofcode2023.utils.FileParserHelper;
import fr.insee.adventofcode2023.utils.LineProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("Day2_p1")
public class Day2_p1 implements LineProcessor, Day  {

    final static Integer MAX_RED=12;
    final static Integer MAX_GREEN=13;
    final static Integer MAX_BLUE=14;
    final static  Pattern pattern = Pattern.compile("(\\d+)\\s+(red|blue|green)");
    int lineNumber=0;
    long sum=0;

    public String treat() {
        return treat("d2_input.txt");
    }


    @Override
    public String treat(String inputFilename) {
        lineNumber=0;
        sum=0;

        FileParserHelper.parseFile(inputFilename,this);
        System.out.println("sum=" + sum);
        return String.valueOf(sum);
    }

    @Override
    public void processLine(String line) {
        String[] tirages = line.split(";");
        lineNumber++;
        boolean isLineOk=true;
        for (String tirage: tirages) {
            Matcher matcher = pattern.matcher(tirage);
            int redCount = 0;
            int blueCount = 0;
            int greenCount = 0;
            while (matcher.find()) {
                int number = Integer.parseInt(matcher.group(1));
                String color = matcher.group(2);

                switch (color) {
                    case "red":
                        redCount += number;
                        break;
                    case "blue":
                        blueCount += number;
                        break;
                    case "green":
                        greenCount += number;
                        break;
                }
            }
            String errorMsg = "";
            if(redCount>MAX_RED)
                errorMsg=errorMsg+" KO-RED";
            if(blueCount>MAX_BLUE)
                errorMsg=errorMsg+" KO-BLUE";
            if(greenCount>MAX_GREEN)
                errorMsg=errorMsg+" KO-GREEN";

            if(!errorMsg.isEmpty()) {
                System.out.println(errorMsg+":" + lineNumber + " " + line);
                isLineOk=false;
                break;
            }
        }
        if (isLineOk) {
            sum += lineNumber;
        }
    }
}
