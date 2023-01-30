package kr.co.mz.jira.jpa.domain;


import kr.co.mz.jira.support.code.DescriptionCode;

/*
In Progress
code Review
Dev Complete
Beta Test
Done
PM Review
In Review
Analysis
IN DESIGN
In Test
To Do
CONFIRMED
 */
public enum IssueStatus implements DescriptionCode {
    ToDo("To Do", "TO DO, TODO", 1),
    Analysis("Analysis", "PM REVIEW, ANALYSIS, PMREVIEW", 2),
    InDesign( "In Design", "IN DESIGN, INDESIGN", 3),
    InProgress("In Progress", "IN PROGRESS, INPROGRESS", 4),
    InReview("In Review", "CODE REVIEW, DEV COMPLETE, BETA TEST, IN REVIEW, CODEREVIEW, DEVCOMPLETE, BETATEST, INREVIEW", 5),
    InTest("In Test", "IN TEST, INTEST", 6),
    Confirmed("Confirmed", "CONFIRMED", 8),
    Done("Done", "DONE", 9),
    NA("N/A", "N/A", 99);

    private final String description;
    private final String value;
    private final Integer step;

    IssueStatus(String description, String value, Integer step) {
        //
        this.description = description;
        this.value = value;
        this.step = step;
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
        } else if (InTest.getValue().contains(upperStatus)) {
            return InTest;
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

    public Integer getStep() {
        //
        return this.step;
    }

    public boolean isBefore(IssueStatus status) {
        //
        return this.step < status.getStep();
    }
}
