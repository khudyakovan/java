package ru.sgnhp.dto;

import java.io.Serializable;


public class DocumentReportDto implements Serializable {

    private int reportType;
    private int reportYear;

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getReportYear() {
        return reportYear;
    }

    public void setReportYear(int reportYear) {
        this.reportYear = reportYear;
    }

}
