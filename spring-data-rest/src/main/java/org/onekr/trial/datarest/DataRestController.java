package org.onekr.trial.datarest;


import io.github.perplexhub.rsql.RSQLJPASupport;
import org.apache.logging.log4j.util.Strings;
import org.onekr.trial.datarest.model.ListReqModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author billy-work
 */
@RestController()
@RequestMapping("{table}")
public class DataRestController {


    @PersistenceContext
    EntityManager entityManager;

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("search")
    public Page<Object> getList(@PathVariable String table, @RequestBody ListReqModel body) {

                /*
                * filter = "id=bt=(2,4)";// id>=2 && id<=4 //between
filter = "id=nb=(2,4)";// id<2 || id>4 //not between
filter = "company.code=like=em"; //like %em%
filter = "company.code=ilike=EM"; //ignore case like %EM%
filter = "company.code=icase=EM"; //ignore case equal EM
filter = "company.code=notlike=em"; //not like %em%
filter = "company.code=inotlike=EM"; //ignore case not like %EM%
filter = "company.code=ke=e*m"; //like %e*m%
filter = "company.code=ik=E*M"; //ignore case like %E*M%
filter = "company.code=nk=e*m"; //not like %e*m%
filter = "company.code=ni=E*M"; //ignore case not like %E*M%
filter = "company.code=ic=E^^M"; //ignore case equal E^^M
filter = "company.code==demo"; //equal
filter = "company.code=='demo'"; //equal
filter = "company.code==''"; //equal to empty string
filter = "company.code==dem*"; //like dem%
filter = "company.code==*emo"; //like %emo
filter = "company.code==*em*"; //like %em%
filter = "company.code==^EM"; //ignore case equal EM
filter = "company.code==^*EM*"; //ignore case like %EM%
filter = "company.code=='^*EM*'"; //ignore case like %EM%
filter = "company.code!=demo"; //not equal
filter = "company.code=in=(*)"; //equal to *
filter = "company.code=in=(^)"; //equal to ^
filter = "company.code=in=(demo,real)"; //in
filter = "company.code=out=(demo,real)"; //not in
filter = "company.id=gt=100"; //greater than
filter = "company.id=lt=100"; //less than
filter = "company.id=ge=100"; //greater than or equal
filter = "company.id=le=100"; //less than or equal
filter = "company.id>100"; //greater than
filter = "company.id<100"; //less than
filter = "company.id>=100"; //greater than or equal
filter = "company.id<=100"; //less than or equal
filter = "company.code=isnull=''"; //is null
filter = "company.code=null=''"; //is null
filter = "company.code=na=''"; //is null
filter = "company.code=nn=''"; //is not null
filter = "company.code=notnull=''"; //is not null
filter = "company.code=isnotnull=''"; //is not null

filter = "company.code=='demo';company.id>100"; //and
filter = "company.code=='demo' and company.id>100"; //and

filter = "company.code=='demo',company.id>100"; //or
filter = "company.code=='demo' or company.id>100"; //or
                * */

//        var page = body.getOffset() / body.getLimit();
//
//        var pageable = PageRequest.of(page, body.getLimit());

        table = table.substring(0, table.length() - 1);

        var pageable = PageRequest.of(body.getPage(), body.getSize());
        var filter = body.getRsql();

        if (Strings.isBlank(filter)) {
            var wheres = new ArrayList<String>();
            body.getWhere().forEach((key, value) -> {
                wheres.add(getCondition(key, value));
            });
            filter = String.join(";", wheres);
        }

        var sorts = new ArrayList<String>();
        body.getOrder_by().forEach((key, value) -> {
            sorts.add(key + "," + value.getOrderBy());
        });

        var repo = SpringDataRestApplication.app.getBean(table + "Repository", org.springframework.data.jpa.repository.JpaSpecificationExecutor.class);

        var specification = RSQLJPASupport.toSpecification(filter)
                .and(RSQLJPASupport.toSort(String.join(";", sorts)));

        var list = repo.findAll(specification, pageable);

        return list;

    }

    private String getCondition(String columnName, Map<DbOpEnum, Object> value) {
        var conditions = new ArrayList<String>();
        value.forEach((op, columnValue) -> {
            conditions.add(columnName + op.getRsql() + columnValue);
        });
        return String.join(";", conditions);
    }

}
