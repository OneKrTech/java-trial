package org.onekr.trial.datarest.model;

import lombok.Data;
import org.onekr.trial.datarest.DbOpEnum;
import org.onekr.trial.datarest.DbOrderByEnum;

import java.util.Map;

/**
 * @author billy-work
 */
@Data
public class ListReqModel {

    private int page;
    private int size;
    private String rsql;
    private Map<String, Map<DbOpEnum, Object>> where;
    private Map<String, DbOrderByEnum> order_by;

}
