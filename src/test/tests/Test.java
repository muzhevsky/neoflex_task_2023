package org.vacationcalculator.tests;

public abstract class Test {
    protected int testAmount;
    protected int successfulTestAmount;
    protected int failedTestAmount;

    public void init(){

    }

    public void run(){

    }

    public void logResults(){
        System.out.println("TestAmount: " + testAmount + "\n" +
                "Successful: " + successfulTestAmount + "\n" +
                "Failed: " + failedTestAmount);
    }
}
