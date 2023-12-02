package fr.insee.adventofcode2023;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static java.lang.System.exit;

public class Day1_p1 {

    public static String treat(){
        long sum=0;
        try {
            Resource resource = new ClassPathResource("d1_input.txt");
            File file = resource.getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = bufferedReader.readLine())!= null) {
                String digitString = line.replaceAll("\\D","");
                int length = digitString.length();
                char firstDigit = 0,lastDigit = 0;
                switch (length) {
                    case 0:
                        System.out.println("NO MATCH");
                        exit(-1);
                        break;
                    case 1:
                        firstDigit = digitString.charAt(0);
                        lastDigit = digitString.charAt(0);
                        System.out.println(String.format("1DG : %s => %s => %s %s",
                                line, digitString,firstDigit, lastDigit));
                        break;
                    default:
                        firstDigit = digitString.charAt(0);
                        lastDigit = digitString.charAt(length-1);
                        System.out.println(String.format("2DG : %s => %s => %s %s",
                                line, digitString,firstDigit, lastDigit));
                        break;
                }
                String valueString = String.valueOf(firstDigit)+lastDigit;
                System.out.println(String.format("\t\t==>%s",
                        valueString));
                sum+=Integer.valueOf(valueString);
                System.out.print("SUM="+sum);

            }
            bufferedReader.close();
            System.out.println("SUM="+sum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return String.valueOf(sum);
    }
}
