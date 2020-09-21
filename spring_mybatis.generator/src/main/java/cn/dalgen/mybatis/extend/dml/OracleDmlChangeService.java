/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2019 All Rights Reserved.
 */
package cn.dalgen.mybatis.extend.dml;

import java.sql.Connection;
import java.util.Scanner;

import cn.dalgen.mybatis.extend.CustomExtendService;
import cn.dalgen.mybatis.gen.datasources.DBConnectionFactory;
import cn.dalgen.mybatis.gen.utils.ConfigUtil;

/**
 *
 * @author bangis.wangdf
 * @version cn.dalgen.mybatis.extend.dml: OracleDmlChangeService.java, v 0.1 2019-11-14 16:54 bangis.wangdf Exp $ 
 */
public class OracleDmlChangeService implements CustomExtendService {
    @Override
    public String extendCmd() {
        return "oralDmlChange";
    }

    @Override
    public String extendCmdMemo() {
        return "Oracle表结构变更对比";
    }

    @Override
    public String execute(Scanner cmdIn) {
        Connection connection = DBConnectionFactory.getConnection();

        return null;
    }

    /*


    SELECT TABLE_NAME,TABLE_TYPE,COMMENTS as TABLE_COMMENT from USER_TAB_COMMENTS;



select l.*,r.* from
(select * from all_tab_comments where owner='ABSSQR_QRY') l
FULL JOIN
(select * from all_tab_comments where owner='ABSSQR_TEST') r
on l.table_name=r.table_name
where l.table_type='TABLE' AND
(
  l.table_name is null or r.table_name is null
);



select * from (
SELECT
    t.owner,
    t.table_name,
    t.column_name,
    t.data_type,
    t.column_id        AS ordinal_position,
    t.data_default     AS column_default,
    t.data_length      AS c_length,
    t.data_precision   AS c_precision,
    t.data_scale       AS c_scale,
    c.comments         AS column_comment
FROM
    all_tab_columns    t
LEFT JOIN all_col_comments   c
ON t.owner=c.owner and t.table_name = c.table_name AND t.column_name = c.column_name
ORDER BY
   t.owner,
    t.table_name,
    t.column_id ASC)
where table_name in
(
    select l.table_name from
    (select * from all_tab_comments where owner='ABSSQR_QRY') l
    FULL JOIN
    (select * from all_tab_comments where owner='ABSSQR_TEST') r
    on l.table_name=r.table_name
    where l.table_type='TABLE' AND r.table_name is null
)

;


SELECT  username FROM DBA_USERS T WHERE T.USERNAME NOT IN ('SYS','SYSTEM','OUTLN') AND account_status ='OPEN';



     */
}
