package fr.insee.adventofcode2023;

import fr.insee.adventofcode2023.utils.FileParserHelper;
import fr.insee.adventofcode2023.utils.LineProcessor;
import fr.insee.adventofcode2023.utils.D3PartPosition;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("Day3_p1")
public class Day3_p1 implements LineProcessor, Day  {

    int lineNumber=0;
    List<List<D3PartPosition>> number_positions_length=new ArrayList<>();
    List<List<Boolean>> symbols_positions=new ArrayList<>();

    public String treat() {
        return treat("d3_input.txt");
    }


    @Override
    public String treat(String inputFilename) {
        lineNumber=0;
        FileParserHelper.parseFile(inputFilename,this);
        String sum = computeSum();
        System.out.println("sum=" + sum);
        return String.valueOf(sum);
    }

    @Override
    public void processLine(String line) {
        boolean inNumber = false;
        Integer numberStart = -1;
        List<D3PartPosition> points= new ArrayList<>();
        List<Boolean> symbols= new ArrayList<>();

        number_positions_length.add(points);
        symbols_positions.add(symbols);

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) {
                symbols.add(Boolean.FALSE);
                if (!inNumber) {
                    inNumber = true;
                    numberStart = i;
                }
            } else {
                if (inNumber) {
                    inNumber = false;
                    Integer length = i - numberStart;
                    Integer value = Integer.valueOf(line.substring(numberStart,numberStart+length));
                    D3PartPosition p = D3PartPosition.builder().length(length).startPosition(numberStart).value(value).build();
                    points.add(p);
                }

                if (ch != '.') {
                    symbols.add(Boolean.TRUE);
                }
                else{
                    symbols.add(Boolean.FALSE);
                }
            }
        }

        // If the line ends with digit
        if (inNumber) {
            Integer length = line.length() - numberStart;
            Integer value = Integer.valueOf(line.substring(numberStart,numberStart+length));
            D3PartPosition p = D3PartPosition.builder().length(length).startPosition(numberStart).value(value).build();
            points.add(p);
        }
        // Print the collected positions and lengths
        System.out.println("Number positions: " + points);
        System.out.println("Symbol positions: " + symbols);
    }

    String computeSum(){
        int sum = 0;
        int lineNumber = 0;
        for (List<D3PartPosition> line : number_positions_length) {
            System.out.println("TREAT LINE : "+lineNumber);
            for (D3PartPosition p : line ) {
                System.out.println("TREAT POINT : "+p);

                boolean isOK = false;
                int previousPos = p.getStartPosition()-1;
                int nextPos = p.getStartPosition()+p.getLength();
                //Check
                isOK=checkBeforeAfterFor3Lines(lineNumber,previousPos,nextPos);
                System.out.println("\t ==> isOK="+isOK);




                if(isOK)
                    sum+=p.getValue();
            }
            lineNumber++;
        }
        return String.valueOf(sum);
    }

    boolean checkBeforeAfterFor3Lines(int lineNumber, int previousPos, int nextPos){
        boolean isOk = false;
        //Check line before
        if(lineNumber>0){
            isOk=checkBeforeAfterForLine(lineNumber-1,previousPos,nextPos);
            if(!isOk){
                isOk=checkPositionForLine(lineNumber-1,previousPos+1,nextPos-1);
            }
        }

        if(!isOk) {
            if (symbols_positions.size() > lineNumber+1) {
                isOk = checkBeforeAfterForLine(lineNumber + 1, previousPos, nextPos);
                if(!isOk){
                    isOk=checkPositionForLine(lineNumber+1,previousPos+1,nextPos-1);
                }
            }
        }

        if(!isOk){
            isOk = checkBeforeAfterForLine(lineNumber, previousPos, nextPos);
        }
        return isOk;
    }

    boolean checkPositionForLine(int lineNumber, int startPos, int endPos) {
        boolean isOk=false;
        for(int pos=startPos; pos<=endPos; pos++){
            if(symbols_positions.get(lineNumber).get(pos)==Boolean.TRUE){
                isOk=true;
                break;
            }
        }
        return isOk;
    }

    boolean checkBeforeAfterForLine(int lineNumber, int previousPos, int nextPos){
        boolean isOk = false;
        if(previousPos>=0 && symbols_positions.get(lineNumber).get(previousPos)){
            isOk=true;
        }
        else if(symbols_positions.get(lineNumber).size()>nextPos && symbols_positions.get(lineNumber).get(nextPos)){
            isOk=true;
        }
        return isOk;
    }
}
