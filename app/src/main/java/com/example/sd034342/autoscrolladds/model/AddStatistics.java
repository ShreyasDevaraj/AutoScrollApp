package com.example.sd034342.autoscrolladds.model;

public class AddStatistics {

    private int numberOfVisits = 0;
    private int numberOfPositiveResponses = 0;
    private int numberOfNegativeResponses = 0;
    private long timeforPositiveResponses = 0;
    private long timeforNegativeResponses = 0;


    public int getNumberOfPositiveResponses() {
        return numberOfPositiveResponses;
    }

    public void setNumberOfPositiveResponses(int numberOfPositiveResponses) {
        this.numberOfPositiveResponses = numberOfPositiveResponses;
    }

    public long getTimeforPositiveResponses() {
        return timeforPositiveResponses;
    }

    public void setTimeforPositiveResponses(long timeforPositiveResponses) {
        this.timeforPositiveResponses += timeforPositiveResponses;
    }

    public long getTimeforNegativeResponses() {
        return timeforNegativeResponses;
    }

    public void setTimeforNegativeResponses(long timeforNegativeResponses) {
        this.timeforNegativeResponses += timeforNegativeResponses;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public int getNumberOfNegativeResponses() {
        return numberOfNegativeResponses;
    }

    public void setNumberOfNegativeResponses(int numberOfNegativeResponses) {
        this.numberOfNegativeResponses = numberOfNegativeResponses;
    }
}
