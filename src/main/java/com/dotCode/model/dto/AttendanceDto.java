package com.dotCode.model.dto;

public class AttendanceDto implements java.io.Serializable{

    private int ontimeCount;
    private int lateCount;
    private int absentCount;
    private int attendanceScore;
    private int offtimeStatus;

    public AttendanceDto(){}

    public AttendanceDto(int ontimeCount, int lateCount, int absentCount, int attendanceScore, int offtimeStatus) {
        this.ontimeCount = ontimeCount;
        this.lateCount = lateCount;
        this.absentCount = absentCount;
        this.attendanceScore = attendanceScore;
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

    public int getAttendanceScore() {
        return attendanceScore;
    }

    public void setAttendanceScore(int attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    public int getOfftimeStatus() {
        return offtimeStatus;
    }

    public void setOfftimeStatus(int offtimeStatus) {
        this.offtimeStatus = offtimeStatus;
    }

    @Override
    public String toString() {
        return "AttendanceDto{" +
                "ontimeCount=" + ontimeCount +
                ", lateCount=" + lateCount +
                ", absentCount=" + absentCount +
                ", attendanceScore=" + attendanceScore +
                ", offtimeStatus=" + offtimeStatus +
                '}';
    }
}
