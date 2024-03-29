package com.dotCode.model.dto;

public class AttendanceDTO implements java.io.Serializable{

    private int empNo;
    private int totalDayCount;
    private int ontimeCount;
    private int lateCount;
    private int absentCount;
    private int totalScore;
    private String lastReset;

    public AttendanceDTO(){}

    public AttendanceDTO(int empNo, int totalDayCount, int ontimeCount, int lateCount, int absentCount, int totalScore, String lastReset) {
        this.empNo = empNo;
        this.totalDayCount = totalDayCount;
        this.ontimeCount = ontimeCount;
        this.lateCount = lateCount;
        this.absentCount = absentCount;
        this.totalScore = totalScore;
        this.lastReset = lastReset;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public int getTotalDayCount() {
        return totalDayCount;
    }

    public void setTotalDayCount(int totalDayCount) {
        this.totalDayCount = totalDayCount;
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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getLastReset() {
        return lastReset;
    }

    public void setLastReset(String lastReset) {
        this.lastReset = lastReset;
    }

    @Override
    public String toString() {
        return "근태 정보\n{" +
                " 사번=" + empNo +
                ", 총 근무일자=" + totalDayCount +
                ", 정시 출근=" + ontimeCount +
                ", 지각=" + lateCount +
                ", 결근=" + absentCount +
                ", 근태 점수=" + totalScore +
                ", 최종 초기화 날짜='" + lastReset + '\'' +
                '}';
    }
}

