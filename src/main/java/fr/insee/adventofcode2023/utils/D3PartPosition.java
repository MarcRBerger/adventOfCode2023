package fr.insee.adventofcode2023.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class D3PartPosition {
    Integer value;
    Integer length;
    Integer startPosition;
}
