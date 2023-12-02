package fr.insee.adventofcode2023;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class Day1_p2 {

    public static String treat(){
        long sum=0;
        try {
            Resource resource = new ClassPathResource("d1_input.txt");
            File file = resource.getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            Pattern patternEnd = Pattern.compile(
                    "^.*((?:one|two|three|four|five|six|seven|eight|nine)|\\d).*?$");
            Pattern patternStart = Pattern.compile(
                    ".*?((?:one|two|three|four|five|six|seven|eight|nine)|\\d).*");

            while((line = bufferedReader.readLine())!= null) {
                Matcher matcherEnd = patternEnd.matcher(line);
                Matcher matcherStart = patternStart.matcher(line);

                Character firstDigit = null, lastDigit = null;
                if(matcherStart.matches()){
                    String matchedToken = matcherStart.group(1);
                    firstDigit=getDigit(matchedToken);
                }
                else{
                    System.out.println("NO MATCH : "+line);
                    exit(-1);
                }

                if(matcherEnd.matches()){
                    String matchedToken = matcherEnd.group(1);
                    lastDigit=getDigit(matchedToken);
                }
                else {
                    lastDigit=firstDigit;
                }

                String valueString = String.valueOf(firstDigit)+lastDigit;
                System.out.println(String.format("%s => %s %s => %s",
                        line, firstDigit, lastDigit,valueString));

                sum+=Integer.valueOf(valueString);
                System.out.println("\t\t==>SUM="+sum);

            }
            bufferedReader.close();
            System.out.println("SUM="+sum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(sum);
    }

    static Character getDigit(String matchedToken){
        Character digitValue=null;
        if (matchedToken.matches("\\d")) {
            digitValue =matchedToken.charAt(0);
        } else {
            switch (matchedToken.toLowerCase()) {

                case "one":
                    digitValue = '1';
                    break;
                case "two":
                    digitValue = '2';
                    break;
                case "three":
                    digitValue = '3';
                    break;
                case "four":
                    digitValue = '4';
                    break;
                case "five":
                    digitValue = '5';
                    break;
                case "six":
                    digitValue = '6';
                    break;
                case "seven":
                    digitValue = '7';
                    break;
                case "eight":
                    digitValue = '8';
                    break;
                case "nine":
                    digitValue = '9';
                    break;
                default:
                    System.out.println("ERROR");
                    exit(-1);
            }
        }
        return digitValue;
    }
}
