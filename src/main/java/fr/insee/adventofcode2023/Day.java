package fr.insee.adventofcode2023;

import org.springframework.stereotype.Service;

@Service
public interface Day {
    String treat();
    String treat(String inputFilepath);

}
