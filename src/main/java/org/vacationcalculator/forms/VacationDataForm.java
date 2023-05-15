package org.vacationcalculator.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VacationDataForm {
    private String startDate;
    private String endDate;
    private double annualIncome;

    public VacationDataForm(){

    }
}
