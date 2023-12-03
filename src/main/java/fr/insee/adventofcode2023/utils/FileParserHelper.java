package fr.insee.adventofcode2023.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileParserHelper {

    public static void parseFile(String inputFilename, LineProcessor processor) {
        Resource resource = new ClassPathResource(inputFilename);
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                processor.processLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}