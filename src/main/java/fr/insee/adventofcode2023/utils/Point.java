package fr.insee.adventofcode2023.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class Point {
    Integer value;
    Integer length;
    Integer startPosition;
}
