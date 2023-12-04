package fr.insee.adventofcode2023;

import fr.insee.adventofcode2023.utils.FileParserHelper;
import fr.insee.adventofcode2023.utils.LineProcessor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("Day4_p2")
public class Day4_p2 implements LineProcessor, Day  {

    int lineNumber=0;
    public String treat() {
        return treat("d4_input.txt");
    }
    List<Set<Integer>> winningNumbers = new ArrayList<>();
    List<Set<Integer>> myNumbers = new ArrayList<>();
    List<Integer> numberOfCards =  new ArrayList<>();

    @Override
    public String treat(String inputFilename) {
        lineNumber=0;
        FileParserHelper.parseFile(inputFilename,this);
        String sum = computeSum();
        System.out.println("sum=" + sum);
        return String.valueOf(sum);
    }

    public static String removeBeforeColon(String input) {
        int index = input.indexOf(':');
        return index != -1 ? input.substring(index + 1).trim() : input;
    }

    @Override
    public void processLine(String line) {
        line = removeBeforeColon(line);
        line=line.trim();
        String[] splittedLine = line.split("\\|");
        String winningPart = splittedLine[0].trim();
        String myNumbersPart = splittedLine[1].trim();
        Set<Integer> lineWinningNumbers = Arrays.stream(splittedLine[0].split("\\s+")).filter(str -> !str.isBlank())
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        Set<Integer> lineMyNumbers = Arrays.stream(splittedLine[1].split("\\s+")).filter(str -> !str.isBlank())
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        winningNumbers.add(lineWinningNumbers);
        myNumbers.add(lineMyNumbers);
        numberOfCards.add(1);

        System.out.println("Line Winning numbers: " + lineWinningNumbers);
        System.out.println("Line My      numbers: " + lineMyNumbers);
    }


    static Integer computeLineScore(Set<Integer> set){
        int setSize = set.size();
        if (setSize == 0) {
            return 0; // Score is zero for an empty set
        } else {
            return (int) Math.pow(2, setSize - 1);
        }
    }
    static Set<Integer> getResultOfLine(Set<Integer> lineWinningNumbers, Set<Integer> lineMyNumbers ){
        Set<Integer> intersection = new HashSet<>(lineWinningNumbers);
        intersection.retainAll(lineMyNumbers);
        return intersection;
    }

    String computeSum(){
        for (int i=0;i<myNumbers.size();i++) {
            Integer numberOfTheTreatedCard = numberOfCards.get(i);
            Set<Integer> resultOfLine = getResultOfLine(winningNumbers.get(i), myNumbers.get(i));
            Integer nbWinning=resultOfLine.size();
            for(int j=1;j<=nbWinning;j++){
                Integer cardNumber=i+j;
                if(cardNumber>=numberOfCards.size()){
                    continue;
                }
                //Add the won card  N times
                Integer numberOfThisCard = numberOfCards.get(cardNumber);
                numberOfCards.set(cardNumber,numberOfThisCard+numberOfTheTreatedCard);
            }

            //System.out.println("LINE "+i+" - score:"+lineScore+" - resultOfLine="+resultOfLine);

        }
        return String.valueOf(numberOfCards.stream().mapToInt(Integer::intValue).sum());
    }

}
