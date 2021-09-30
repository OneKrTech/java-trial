package org.onekr.trial.datarest;

/**
 * @author billy-work
 */

public enum DbOpEnum {

    /**
     *
     */
    _eq("=="),
    _like("=like=");

    private String rsql;

    public String getRsql() {
        return rsql;
    }

    DbOpEnum(String rsql) {
        this.rsql = rsql;
    }
}
