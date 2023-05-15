package org.vacationcalculator.controllers;

import org.vacationcalculator.forms.VacationDataForm;
import org.vacationcalculator.services.calculation.CalculationService;
import org.vacationcalculator.utilities.loggers.ExceptionLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CalculationController {

    @Autowired
    @Qualifier("calculationService")
    CalculationService calculationService;

    @Autowired
    @Qualifier("consoleExceptionLogger")
    ExceptionLogger logger;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestParam String startDate,
                                            @RequestParam String endDate,
                                            @RequestParam double annualIncome){
        try{
            var form = new VacationDataForm(startDate, endDate, annualIncome);
            var result = calculationService.calculate(form);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex){
            logger.log(Thread.currentThread(), ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex){
            logger.log(Thread.currentThread(), ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
