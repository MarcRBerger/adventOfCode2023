package fr.insee.adventofcode2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class Day2_p2Test {

    Day2_p2 day;

    @BeforeEach
    void init(){
       day= new Day2_p2();
    }
    @Test
    void sample_should_work(){
        assertEquals("2286",day.treat("d2_sample.txt"));
    }

    @Test
    void input_should_work(){
        assertEquals("77607",day.treat("d2_input.txt"));
    }
}