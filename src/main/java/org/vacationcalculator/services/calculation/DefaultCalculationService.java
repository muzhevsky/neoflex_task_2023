package org.vacationcalculator.services.calculation;

import jakarta.annotation.PostConstruct;
import org.vacationcalculator.forms.VacationDataForm;
import org.vacationcalculator.utilities.converters.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("calculationService")
public class DefaultCalculationService implements CalculationService{

    @Autowired
    @Qualifier("workingDayCountService")
    WorkingDayCountService workingDayCountService;

    @Autowired
    @Qualifier("stringToLocalDateConverter")
    Converter<String, LocalDate> localDateConverter;

    private int workingDaysOfLastYear;

    @PostConstruct
    private void init(){
        var currentYear = LocalDate.now().getYear();
        workingDaysOfLastYear = workingDayCountService.getWorkingDaysCount(
                LocalDate.of(currentYear - 1, 1, 1),
                LocalDate.of(currentYear, 1, 1)
        );
    }

    @Override
    public double calculate(VacationDataForm form) {
        if (form.getAnnualIncome() < 0) throw new IllegalArgumentException("wrong form format: income is negative");

        var currentDate = localDateConverter.convert(form.getStartDate(), LocalDate.class);
        var endDate = localDateConverter.convert(form.getEndDate(), LocalDate.class);
        var workDaysCounter = workingDayCountService.getWorkingDaysCount(currentDate, endDate);

        return workDaysCounter * form.getAnnualIncome() / workingDaysOfLastYear;
    }
}
