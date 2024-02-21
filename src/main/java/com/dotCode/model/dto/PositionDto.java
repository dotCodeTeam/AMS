package com.dotCode.model.dto;

public class PositionDto {

    private String positionId;
    private String positionName;

    public PositionDto(){}

    public PositionDto(String positionId, String positionName) {
        this.positionId = positionId;
        this.positionName = positionName;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "PositionDto{" +
                "positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
