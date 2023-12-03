package fr.insee.adventofcode2023;

import fr.insee.adventofcode2023.utils.FileParserHelper;
import fr.insee.adventofcode2023.utils.LineProcessor;
import fr.insee.adventofcode2023.utils.Point;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("Day3_p2")
public class Day3_p2 implements LineProcessor, Day {

    int lineNumber = 0;
    List<List<Point>> number_positions_length = new ArrayList<>();
    List<List<Boolean>> symbols_positions = new ArrayList<>();

    public String treat() {
        return treat("d3_input.txt");
    }


    @Override
    public String treat(String inputFilename) {
        lineNumber = 0;
        //Read the input; fills the structures
        FileParserHelper.parseFile(inputFilename, this);
        //Compute result
        String sum = computeSum();
        System.out.println("sum=" + sum);
        return String.valueOf(sum);
    }

    @Override
    public void processLine(String line) {
        boolean inNumber = false;
        Integer numberStart = -1;
        List<Point> points = new ArrayList<>();
        List<Boolean> symbols = new ArrayList<>();

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
                    Integer value = Integer.valueOf(line.substring(numberStart, numberStart + length));
                    Point p = Point.builder().length(length).startPosition(numberStart).value(value).build();
                    points.add(p);
                }

                if (ch == '*') {
                    symbols.add(Boolean.TRUE);
                } else {
                    symbols.add(Boolean.FALSE);
                }
            }
        }

        // If the line ends with digit
        if (inNumber) {
            Integer length = line.length() - numberStart;
            Integer value = Integer.valueOf(line.substring(numberStart, numberStart + length));
            Point p = Point.builder().length(length).startPosition(numberStart).value(value).build();
            points.add(p);
        }
        // Print the collected positions and lengths
        System.out.println("Number positions: " + points);
        System.out.println("Symbol positions: " + symbols);
    }

    String computeSum() {
        long sum = 0;
        int lineNumber = 0;
        for (List<Boolean> line : symbols_positions) {
            int charPos = 0;
            System.out.println("TREAT LINE : " + lineNumber);
            for (Boolean isSymbol : line) {
                if (isSymbol) {
                    System.out.println("TREAT symbol : pos=" + charPos);
                    //Check
                    long result = getGearRatioOfSymbol(lineNumber, charPos);
                    if (result != 0) System.out.println("\t ==> isOK=" + result);
                    if (result > 0) sum += result;
                }
                charPos++;
            }
            lineNumber++;
        }
        return String.valueOf(sum);
    }

    /**
     * Get the gear ratio for a symbol at line lineNumber, char position
     * @param lineNumber
     * @param position
     * @return the gear ratio
     */
    long getGearRatioOfSymbol(int lineNumber, int position) {
        long resultat = 0;
        long totalContact = 0;

        //On regarde la ligne d'avant; la ligne courrant et la ligne d'après
        List<Integer> lignesAChecker= List.of(lineNumber - 1,lineNumber, lineNumber + 1);
        for (Integer ligne: lignesAChecker ) {
            System.out.println("verify on line :"+ligne);
            if(ligne>=0 && ligne<symbols_positions.size()){
                Pair<Long, Long> res = findAdjacentPartForLine(ligne, position);
                if (res.getLeft() > 0) {
                    totalContact+=res.getLeft();
                    long number = res.getRight();
                    resultat = ((resultat == 0) ? number : resultat * number);
                }
            }
        }

        if (totalContact == 2) return resultat;
        else return 0;
    }

    //First:  nombre de nombres adjacents sur une ligne
    //Second : le produit des nombres adjacents (ou zero si aucun n'a été trouvé)
    Pair<Long, Long> findAdjacentPartForLine(int lineNumber, int pos) {
        long result = 0;
        long nb = 0;
        for (Point p : number_positions_length.get(lineNumber)) {
            int numberEndPos = p.getStartPosition() + p.getLength() - 1;

            if (
                //Le nombre "engloble le caractère spécial)
                    (pos >= p.getStartPosition() && pos <= numberEndPos) ||
                    //diagonale "avant"
                    (numberEndPos + 1 == pos) ||
                    //diagonale "après"
                    (p.getStartPosition() == pos + 1)) {
                if (result == 0) result = p.getValue();
                else result *= p.getValue();
                nb++;
            }
        }
        return Pair.of(nb, result);
    }

}
