package fr.insee.adventofcode2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class Day2_p1Test {

    Day2_p1 day;

    @BeforeEach
    void init(){
        day= new Day2_p1();
    }
    @Test
    void sample_should_work(){
        assertEquals("8",day.treat("d2_sample.txt"));
    }

    @Test
    void input_should_work(){
        assertEquals("2679",day.treat("d2_input.txt"));
    }
}