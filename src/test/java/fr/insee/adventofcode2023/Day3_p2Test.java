package fr.insee.adventofcode2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

class Day3_p2Test {

    Day3_p2 day;

    @BeforeEach
    void init(){
        day= new Day3_p2();
    }

    @Test
    void sample_should_work(){
        assertEquals("467835",day.treat("d3_sample.txt"));
    }

    @Test
    void input_should_work(){
        assertEquals("82301120",day.treat("d3_input.txt"));
    }

}