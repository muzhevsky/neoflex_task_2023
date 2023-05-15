package org.vacationcalculator.services.calculation;

import java.time.LocalDate;

public interface WorkingDayCountService {
    int getWorkingDaysCount(LocalDate startDate, LocalDate endDate);
}
