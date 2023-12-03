package fr.insee.adventofcode2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class Day3_p1Test {

    Day3_p1 day;

    @BeforeEach
    void init(){
        day= new Day3_p1();
    }

    @Test
    void sample_should_work(){
        assertEquals("4361",day.treat("d3_sample.txt"));
    }

    @Test
    void input_should_work(){
        assertEquals("532331",day.treat("d3_input.txt"));
    }

}