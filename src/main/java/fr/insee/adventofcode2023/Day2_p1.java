package fr.insee.adventofcode2023;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class Day2_p1 {

    final static Integer MAX_RED=12;
    final static Integer MAX_GREEN=13;
    final static Integer MAX_BLUE=14;

    public static String treat(){
        long sum=0;
        try {
            Resource resource = new ClassPathResource("d2_input.txt");
            File file = resource.getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;

            Pattern pattern = Pattern.compile("(\\d+)\\s+(red|blue|green)");
            int lineNumber=0;
            while((line = bufferedReader.readLine())!= null) {
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
            bufferedReader.close();
            System.out.println("SUM="+sum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(sum);
    }
}
