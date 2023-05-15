package org.vacationcalculator;

import org.vacationcalculator.tests.DefaultCalculationServiceTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.vacationcalculator")
public class Main {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(Main.class);
        var test = ctx.getBean(DefaultCalculationServiceTest.class);
        test.run();
        test.logResults();
    }
}