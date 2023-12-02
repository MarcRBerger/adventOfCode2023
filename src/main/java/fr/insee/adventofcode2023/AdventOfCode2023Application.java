package fr.insee.adventofcode2023;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.System.exit;

@SpringBootApplication
@Slf4j
public class AdventOfCode2023Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdventOfCode2023Application.class, args);
    }


    @Override
    public void run(String... args) {
        Day2_p2.treat();
    }
}
