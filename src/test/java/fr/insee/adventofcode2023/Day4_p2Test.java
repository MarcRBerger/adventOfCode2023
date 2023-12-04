package fr.insee.adventofcode2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

class Day4_p2Test {

    Day4_p2 day;

    @BeforeEach
    void init(){
        day= new Day4_p2();
    }

    @Test
    void sample_should_work(){
        assertEquals("30",day.treat("d4_sample.txt"));
    }

    @Test
    void input_should_work(){
        assertEquals("14624680",day.treat("d4_input.txt"));
    }

}