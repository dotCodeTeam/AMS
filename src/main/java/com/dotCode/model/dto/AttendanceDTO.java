package com.dotCode.model.dto;

public class AttendanceDTO implements java.io.Serializable{

    private int ontimeCount;
    private int lateCount;
    private int absentCount;
    private int totalAttendanceScore;
    private String offtimeStatus;

    public AttendanceDTO(){}

    public AttendanceDTO(int ontimeCount, int lateCount, int absentCount, int totalAttendanceScore, String offtimeStatus) {
        this.ontimeCount = ontimeCount;
        this.lateCount = lateCount;
        this.absentCount = absentCount;
        this.totalAttendanceScore = totalAttendanceScore;
        this.offtimeStatus = offtimeStatus;
    }

    public int getOntimeCount() {
        return ontimeCount;
    }

    public void setOntimeCount(int ontimeCount) {
        this.ontimeCount = ontimeCount;
    }

    public int getLateCount() {
        return lateCount;
    }

    public void setLateCount(int lateCount) {
        this.lateCount = lateCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public int gettotalAttendanceScore() {
        return totalAttendanceScore;
    }

    public void settotalAttendanceScore(int totalAttendanceScore) {
        this.totalAttendanceScore = totalAttendanceScore;
    }

    public String getOfftimeStatus() {
        return offtimeStatus;
    }

    public void setOfftimeStatus(String offtimeStatus) {
        this.offtimeStatus = offtimeStatus;
    }

    @Override
    public String toString() {
        return "AttendanceDto{" +
                "ontimeCount=" + ontimeCount +
                ", lateCount=" + lateCount +
                ", absentCount=" + absentCount +
                ", attendanceScore=" + totalAttendanceScore +
                ", offtimeStatus=" + offtimeStatus +
                '}';
    }
}
