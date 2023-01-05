package kr.co.mz.jira.jpa.domain;


import kr.co.mz.jira.support.code.DescriptionCode;

public enum IssueStatus implements DescriptionCode {
    ToDo("To Do", "TO DO"),
    Analysis("Analysis", "PM REVIEW"),
    InDesign( "In Design", "IN DESIGN"),
    InProgress("In Progress", "IN PROGRESS"),
    InReview("In Review", "CODE REVIEW, DEV COMPLETE, BETA TEST"),
    Confirmed("Confirmed", "CONFIRMED"),
    Done("Done", "DONE"),
    NA("N/A", "N/A");

    private final String description;
    private final String value;

    IssueStatus(String description, String value) {
        //
        this.description = description;
        this.value = value;
    }

    public static IssueStatus getStatus(String status) {
        if( status == null )
            return NA;
        String upperStatus = status.toUpperCase();

        if (ToDo.getValue().contains(upperStatus)) {
            return ToDo;
        } else if (Analysis.getValue().contains(upperStatus)) {
            return Analysis;
        } else if (InDesign.getValue().contains(upperStatus)) {
            return InDesign;
        } else if (InProgress.getValue().contains(upperStatus)) {
            return InProgress;
        } else if (InReview.getValue().contains(upperStatus)) {
            return InReview;
        } else if (Confirmed.getValue().contains(upperStatus)) {
            return Confirmed;
        } else if (Done.getValue().contains(upperStatus)) {
            return Done;
        } else {
            return NA;
        }
    }

    @Override
    public String getDescription() {
        //
        return description;
    }

    public String getValue() {
        //
        return this.value;
    }
}
