package org.vacationcalculator.tests;

import jakarta.annotation.PostConstruct;
import org.vacationcalculator.forms.VacationDataForm;
import org.vacationcalculator.services.calculation.DefaultCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultCalculationServiceTest extends Test{
    @Autowired
    DefaultCalculationService testUnit;

    int workingDaysOfLastYear = 244;

    CalculationServiceTestCase[] testCases;
    @Override
    @PostConstruct
    public void init(){
        testAmount = 5;
        testCases = new CalculationServiceTestCase[testAmount];

        testCases[0] = new CalculationServiceTestCase(
                new VacationDataForm(
                        "2023-05-01",
                        "2023-05-15",
                        250000
                ), 8.0 * 250000 / workingDaysOfLastYear);

        testCases[1] = new CalculationServiceTestCase(
                new VacationDataForm(
                        "2022-11-26",
                        "2023-02-02",
                        1000000
                ), 44.0 * 1000000 / workingDaysOfLastYear);

        testCases[2] = new CalculationServiceTestCase(
                new VacationDataForm(
                        "2023-03-27",
                        "2023-05-02",
                        500000
                ), 26.0 * 500000 / workingDaysOfLastYear);

        testCases[3] = new CalculationServiceTestCase(
                new VacationDataForm(
                        "2023-11-26",
                        "2022-02-02",
                        5000
                ), -1);

        testCases[4] = new CalculationServiceTestCase(
                new VacationDataForm(
                        "2023-03-27",
                        "2023-05-02",
                        -1000
                ), -1);
    }

    @Override
    public void run(){
        for (var testCase : testCases){
            try {
                var result = testUnit.calculate(testCase.form);
                if (Math.abs(result - testCase.expectedResult) <= 1e-2)
                    successfulTestAmount++;
                else
                    failedTestAmount++;
            }
            catch (IllegalArgumentException ex){
                if (testCase.expectedResult == -1){
                    successfulTestAmount++;
                }
            }
        }
    }

    class CalculationServiceTestCase{
        VacationDataForm form;
        double expectedResult;

        CalculationServiceTestCase(VacationDataForm form, double expectedResult){
            this.form = form;
            this.expectedResult = expectedResult;
        }
    }
}
