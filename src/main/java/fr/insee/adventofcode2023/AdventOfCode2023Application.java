package fr.insee.adventofcode2023;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AdventOfCode2023Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdventOfCode2023Application.class, args);
    }

    @Autowired
    @Qualifier("Day2_p2")
    Day day;
    @Override
    public void run(String... args) {
        day.treat();
    }
}
