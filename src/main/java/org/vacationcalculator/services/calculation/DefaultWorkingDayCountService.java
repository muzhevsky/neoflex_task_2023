package org.vacationcalculator.services.calculation;

import org.vacationcalculator.models.OffdaysModel;
import org.vacationcalculator.models.OffdaysMonth;
import org.vacationcalculator.repos.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service("workingDayCountService")
public class DefaultWorkingDayCountService implements WorkingDayCountService {
    @Autowired
    @Qualifier("offdaysRepository")
    Repository<OffdaysModel, Integer> offdaysRepository;

    public int getWorkingDaysCount(LocalDate startDate, LocalDate endDate){
        var currentDate = startDate;

        if (currentDate.isAfter(endDate)) throw new IllegalArgumentException("wrong form format: start date is after end date");

        var year = currentDate.getYear();
        var month = currentDate.getMonth().getValue();
        var day = currentDate.getDayOfMonth();
        var offdaysModel = offdaysRepository.getById(year);
        var workingDaysCounter = 0;

        if (year != endDate.getYear()){
            workingDaysCounter += getWorkingDaysOfMonth(offdaysModel, year, month, day);
            month++;
            day = 1;
        }

        while (year != endDate.getYear()){
            while(month < 13){
                workingDaysCounter += getWorkingDaysOfMonth(offdaysModel, year, month);
                month++;
            }
            month = 1;
            year++;
            offdaysModel = offdaysRepository.getById(year);
        }

        if (month != endDate.getMonth().getValue()){
            workingDaysCounter += getWorkingDaysOfMonth(offdaysModel, year, month, day);
            month++;
            day = 1;
        }

        while(month < endDate.getMonth().getValue()){
            workingDaysCounter += getWorkingDaysOfMonth(offdaysModel, year, month);
            month++;
        }


        var monthModel = offdaysModel.getOffdaysMonths()[month - 1];
        workingDaysCounter += endDate.getDayOfMonth() - day + 1 - getOffdaysOfMonthPeriod(monthModel, day, endDate.getDayOfMonth());

        return workingDaysCounter;
    }


    // returns working days amount from 1st day of month to the last one
    private int getWorkingDaysOfMonth(OffdaysModel offdaysModel, int year, int month){
        var monthModel = offdaysModel.getOffdaysMonths()[month - 1];
        int monthMaxDays = YearMonth.of(year, month).lengthOfMonth();
        return monthMaxDays - monthModel.getDays().length;
    }

    // returns working days amount from 'startDay' value to the end of month
    private int getWorkingDaysOfMonth(OffdaysModel offdaysModel, int year, int month, int startDay){
        var monthModel = offdaysModel.getOffdaysMonths()[month - 1];
        int monthMaxDays = YearMonth.of(year, month).lengthOfMonth();

        var daysCount = monthMaxDays - startDay + 1;
        return daysCount - getOffdaysOfMonthPeriod(monthModel, startDay, monthMaxDays);
    }


    // returns offdays amount from 'startDay' day to 'endDay' day of month
    private int getOffdaysOfMonthPeriod(OffdaysMonth month, int startDay, int endDay){
        var offdays = month.getDays();
        var startIndex = 0;
        for (; startIndex < offdays.length && offdays[startIndex] < startDay; startIndex++){}

        var endIndex = startIndex;
        for (; endIndex < offdays.length && offdays[endIndex] <= endDay; endIndex++) {}

        return endIndex - startIndex;
    }
}
