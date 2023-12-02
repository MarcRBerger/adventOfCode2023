package fr.insee.adventofcode2023;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Day2_p2Test {


    @Test
    void example_should_work(){
        assertEquals("2286",Day2_p2.treat("d2_sample.txt"));
    }
}