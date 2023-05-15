package org.vacationcalculator.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OffdaysModel {
    private int year;
    private OffdaysMonth[] offdaysMonths;
}
