package org.onekr.trial.datarest;

/**
 * @author billy-work
 */

public enum DbOrderByEnum {

    /**
     *
     */
    asc("asc"),
    desc("desc");

    private String orderByStr;

    public String getOrderBy() {
        return orderByStr;
    }

    DbOrderByEnum(String orderByStr) {
        this.orderByStr = orderByStr;
    }
}
