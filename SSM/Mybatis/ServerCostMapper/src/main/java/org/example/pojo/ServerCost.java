package org.example.pojo;
import java.sql.Timestamp;
public class ServerCost {
    String costName;
    float costValue;
    Timestamp lastUpdate;
    String comment;
    float defaultValue;

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public float getCostValue() {
        return costValue;
    }

    public void setCostValue(float costValue) {
        this.costValue = costValue;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(float defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return "ServerCost{" +
                "costName='" + costName + '\'' +
                ", costValue=" + costValue +
                ", lastUpdate=" + lastUpdate +
                ", comment='" + comment + '\'' +
                ", defaultValue=" + defaultValue +
                '}';
    }
}
