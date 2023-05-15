package org.vacationcalculator.services.calculation;

import org.vacationcalculator.forms.VacationDataForm;

public interface CalculationService {
    double calculate(VacationDataForm form);
}
