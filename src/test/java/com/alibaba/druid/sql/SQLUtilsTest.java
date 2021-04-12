package com.alibaba.druid.sql;


import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.SQLStatement;
import junit.framework.TestCase;

import java.util.List;


public class SQLUtilsTest extends TestCase {

    public void testParseStatements() {

        String sql = "-- ###用户：brand\n" +
            "-- ###ddl\n" +
            "\n" +
            "-- ###上线\n" +
            "create table brand.V_ACT_WHITE_LIST\n" +
            "(\n" +
            "  id          NUMBER not null,\n" +
            "  limit_info  VARCHAR2(50),\n" +
            "  act_code    VARCHAR2(50),\n" +
            "  limit_num   NUMBER,\n" +
            "  use_num     NUMBER,\n" +
            "  create_time VARCHAR2(18)\n" +
            ");\n" +
            "\n" +
            "-- ###上线\n" +
            "comment on table brand.V_ACT_WHITE_LIST\n" +
            "  is '油卡营销活动白名单资格表';\n" +
            "-- ###上线\n" +
            "comment on column brand.V_ACT_WHITE_LIST.id\n" +
            "  is 'id';\n" +
            "-- ###上线\n" +
            "comment on column brand.V_ACT_WHITE_LIST.limit_info\n" +
            "  is '限制信息';\n" +
            "-- ###上线\n" +
            "comment on column brand.V_ACT_WHITE_LIST.act_code\n" +
            "  is '活动code';\n" +
            "-- ###上线\n" +
            "comment on column brand.V_ACT_WHITE_LIST.limit_num\n" +
            "  is '限制总数量';\n" +
            "-- ###上线\n" +
            "comment on column brand.V_ACT_WHITE_LIST.use_num\n" +
            "  is '使用数量';\n" +
            "-- ###上线\n" +
            "comment on column brand.V_ACT_WHITE_LIST.create_time\n" +
            "  is '创建时间';\n" +
            "  \n" +
            "\n" +
            "-- ###上线\n" +
            "create unique index UQ_V_ACT_WHITE_LIST on brand.V_ACT_WHITE_LIST (LIMIT_INFO, ACT_CODE);\n" +
            "\n" +
            "\n" +
            "-- ###上线\n" +
            "alter table brand.V_ACT_WHITE_LIST\n" +
            "  add constraint PK_V_ACT_WHITE_LIST primary key (ID);\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "-- ###上线\n" +
            "create sequence SEQ_V_ACT_WHITE_LIST\n" +
            "minvalue 9000000\n" +
            "maxvalue 999999999999999999999999999\n" +
            "start with 9000000\n" +
            "increment by 1\n" +
            "cache 500;\n" +
            "\n" +
            "-- 从结果中删除重复行。 \n" +
            "SELECT DISTINCT product_id, purchase_price\n" +
            "FROM Product;\n" +
            "\n" +
            "\n" +
            "/* 这条SELECT语句，  \n" +
            "    会从结果中删除重复行。*/ \n" +
            "SELECT DISTINCT product_id, purchase_price\n" +
            "    FROM Product;\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n";

        for (DbType dbType : new DbType[]{DbType.oracle, DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements2() {

        String sql = "-- ###ddeeee\n" +
            "UPDATE devtask\n" +
            "SET operator=TO_CHAR(REGEXP_SUBSTR(SUBSTR(REGEXP_SUBSTR(payload, '\"operator\":\"([^\"]+)\"'), 13), '[^\"]+'))\n" +
            "WHERE operator IS NULL\n" +
            "  AND payload LIKE '%\"operator\":\"%';\n" +
            "\n" +
            "-- ###ddeeee\n" +
            "UPDATE devtask\n" +
            "SET operator=TO_CHAR(REGEXP_SUBSTR(SUBSTR(REGEXP_SUBSTR(payload, '\\\\\"operator\\\\\":\\\\\"([^\"\\\\]+)\\\\\"'), 15), '[^\"\\\\]+'))\n" +
            "WHERE operator IS NULL\n" +
            "  AND payload LIKE '%\\\"operator\\\":\\\"%';\n" +
            "-- ###ddeeee\n" +
            "COMMIT;";

        for (DbType dbType : new DbType[]{DbType.oracle, DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements5() {

        String sql = "-- ###ddeeee\n" +
            "delete from ROLES_PERMISSIONS where permissionid=(select id from permission where permission='module:setStatus:submitToRelease');";

        for (DbType dbType : new DbType[]{DbType.oracle, DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements6() {

        String sql = "-- ###ddeeee\n" +
            "TRUNCATE  TABLE  dept30;";

        for (DbType dbType : new DbType[]{DbType.oracle, DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements7() {

        String sql = "-- ###ddeeee\n" +
            "drop VIEW release_module_view;\n" + "-- ###ddeeee\n" +
            "drop table release_module_view;\n";

        for (DbType dbType : new DbType[]{DbType.oracle, DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements8() {

        String sql = "-- ###ddeeee\n" +
            "create or replace function fun_dtime return varchar2\n" +
            "as\n" +
            "begin\n" +
            "return to_char(sysdate,'yyyy\"年\"mm\"月\"dd\"日\"');\n" +
            "end;";

        for (DbType dbType : new DbType[]{DbType.oracle}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements9() {

        String sql = "-- ###ddeeee\n" +
            "CREATE   function tabcmessalot (@title varchar(10))\n" +
            "Returns @ctable table(title varchar(10) null,des varchar(100) null)\n" +
            "As\n" +
            "Begin\n" +
            "Insert @ctable Select title,des from product WHERE title LIKE '%'+@title+'%'\n" +
            "return\n" +
            "End\n";

        for (DbType dbType : new DbType[]{DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements10() {

        String sql = "select d.id,a.sku_stock_id,a.sku_id,a.sku_sale_id,a.mdse_code,d.mdse_name,d.mdse_alias,nvl(e.business_channel_code,'未上架') business_channel_code,f.data_val top_uptype,g.supplier_code,e.appl_code,b.par_type_code,b.par_name,b.par_unit,c.par_price,h.sell_price,h.supplier_price,a.total_num,a.stock_num,a.sold_num,a.unbuy_num,a.is_default,a.describe, a.create_time,a.update_time,a.status\n" +
            "    from game.game_stock_sku          a,\n" +
            "         game.game_base_sku           b,\n" +
            "         game.game_par_original_price c,\n" +
            "         game.game_mdse_info d,\n" +
            "         game.game_mdse_extend f,\n" +
            "         game.game_mdse_base_price e,\n" +
            "         game.GAME_MDSE_SUPPLIER_LIST g,\n" +
            "         game.game_mdse_sell_info h\n" +
            "   where a.sku_id = b.sku_id\n" +
            "     and b.par_original_code = c.par_code\n" +
            "     and a.mdse_code = e.mdse_code(+)\n" +
            "     and a.sku_stock_id = e.sku_stock_id(+)\n" +
            "     and a.mdse_code = d.core_code\n" +
            "     and f.data_type = 'topUptype'\n" +
            "     and d.mdse_ext = f.data_code\n" +
            "     and a.mdse_code = g.mdse_code\n" +
            "     and a.sku_stock_id = g.sku_stockid\n" +
            "     and a.mdse_code = h.game_code\n" +
            "     and a.sku_stock_id = h.sku_stockid;";

        for (DbType dbType : new DbType[]{DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements3() {

        String sql = "-- ###ddeeee\n" +
            "GRANT CONNECT,RESOURCE TO zhang;\n" +
            "\n" +
            "-- ###ddeeee\n" +
            "GRANT SELECT,DELETE,UPDATE,INSERT ON zhang.person TO CHENMH ;\n" +
            "-- ###ddeeee\n" +
            "grant create view to chenmh;";

        for (DbType dbType : new DbType[]{DbType.oracle, DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    public void testParseStatements4() {

        String sql =
            "-- ###ddeeee\n" +
                "REVOKE CONNECT,RESOURCE FROM chenmh;\n" +
                // "-- ###ddeeee\n" +
                // "REVOKE CREATE FROM chenmh;\n"
                "-- ###ddeeee\n" +
                "REVOKE ALL PRIVILEGES ON zhang.person FROM chenmh;\n";

        for (DbType dbType : new DbType[]{DbType.oracle, DbType.mysql}) {
            System.out.println("================================");
            System.out.println(dbType);
            System.out.println("================================");
            parse(sql, dbType);
        }
    }


    private void parse(final String sql, final DbType dbType) {

        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        stmtList.forEach(stmt -> {
            System.out.println(stmt.toString());
            List<String> beforeComments = stmt.getBeforeCommentsDirect();
            System.out.println("beforeComments: " + beforeComments);
            List<String> afterComments = stmt.getAfterCommentsDirect();
            System.out.println("afterComments: " + afterComments);
            System.out.println();
            System.out.println();
        });
    }


    public void testName() {

        String sql =
            "#从结果中删除重复行。 \n" +
                "SELECT DISTINCT product_id, purchase_price\n" +
                "FROM Product;\n";

        parse(sql, DbType.mysql);
    }


    public void testMysql() {

        String sql =
            "#从结果中删除重复行。 \n" +
                "SELECT DISTINCT product_id, purchase_price\n" +
                "FROM Product;\n";

        parse(sql, DbType.mysql);
    }


    public void testMysql2() {

        String sql =
            "-- ### 用户: dlbxdb\n" +
                "\n" +
                "-- ### DDL\n" +
                "CREATE TABLE `dlop_pa_employee` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `order_no` varchar(50) NOT NULL COMMENT '订单号',\n" +
                "  `serial_no` int(11) COMMENT '序号',\n" +
                "  `name` varchar(120) DEFAULT NULL COMMENT '姓名',\n" +
                "  `sex` varchar(50) DEFAULT NULL COMMENT '性别',\n" +
                "  `certificate_no` varchar(50) DEFAULT NULL COMMENT '身份证号',\n" +
                "\t`moblie_no` varchar(50) DEFAULT NULL COMMENT '手机号',\n" +
                "\t`position_name` varchar(50) DEFAULT NULL COMMENT '职业',\n" +
                "\t`has_operation_crt` varchar(50) DEFAULT NULL COMMENT '有无操作证',\n" +
                "\t`health_state` varchar(50) DEFAULT NULL COMMENT '健康状况',\n" +
                "\t`relation_type` varchar(50) DEFAULT NULL COMMENT '方案',\n" +
                "\t`premium` DECIMAL(13,2) DEFAULT NULL COMMENT '保费',\n" +
                "\t`remark` varchar(255) DEFAULT NULL COMMENT '备注',\n" +
                "\t`start_date` datetime  DEFAULT NULL COMMENT '生效日期',\n" +
                "  `created_date` datetime DEFAULT NULL COMMENT '创建时间（public）',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=utf8mb4 COMMENT='平安雇主责任险-雇员信息';\n" +
                "\n" +
                "ALTER TABLE insur_order_user add `contact_name` varchar(64) COMMENT '联系人';";

        parse(sql, DbType.mysql);
    }


    public void testOracle() {

        String sql =
            "--### SHANGXIAN:1\n" +
                "INSERT INTO PROJECT_IMAGE_DEFINITION (ID, IMAGE_REPOSITORY, ARTIFACT_ID, PACKAGING, BOOT_TYPE, TIER, CREATED_TIME) VALUES (seq_image_definition_id.nextval, 'brand-core-tomcat-01', 'brand-CardTopupService', 'war', 2, 1, TO_DATE('2021-01-22 14:33:42', 'YYYY-MM-DD HH24:MI:SS'));\n" +
                "--### SHANGXIAN:1\n" +
                "INSERT INTO PROJECT_IMAGE_DEFINITION (ID, IMAGE_REPOSITORY, ARTIFACT_ID, PACKAGING, BOOT_TYPE, TIER, CREATED_TIME) VALUES (seq_image_definition_id.nextval, 'brand-core-tomcat-01', 'brand-MerchantService', 'war', 2, 1, TO_DATE('2021-01-22 14:33:42', 'YYYY-MM-DD HH24:MI:SS'));\n" +
                "--### SHANGXIAN:1\n" +
                "INSERT INTO PROJECT_IMAGE_DEFINITION (ID, IMAGE_REPOSITORY, ARTIFACT_ID, PACKAGING, BOOT_TYPE, TIER, CREATED_TIME) VALUES (seq_image_definition_id.nextval, 'brand-core-tomcat-01', 'brand-QueryService', 'war', 2, 1, TO_DATE('2021-01-22 14:33:42', 'YYYY-MM-DD HH24:MI:SS'));\n" +
                "--### SHANGXIAN:1\n" +
                "INSERT INTO PROJECT_IMAGE_DEFINITION (ID, IMAGE_REPOSITORY, ARTIFACT_ID, PACKAGING, BOOT_TYPE, TIER, CREATED_TIME) VALUES (seq_image_definition_id.nextval, 'brand-core-tomcat-01', 'core-merService', 'war', 2, 1, TO_DATE('2021-01-22 14:33:42', 'YYYY-MM-DD HH24:MI:SS'));\n" +
                "--### SHANGXIAN:1\n" +
                "INSERT INTO PROJECT_IMAGE_DEFINITION (ID, IMAGE_REPOSITORY, ARTIFACT_ID, PACKAGING, BOOT_TYPE, TIER, CREATED_TIME) VALUES (seq_image_definition_id.nextval, 'brand-core-tomcat-02', 'brand-VoucherService', 'war', 2, 1, TO_DATE('2021-01-22 14:33:40', 'YYYY-MM-DD HH24:MI:SS'));\n" +
                "--### SHANGXIAN:1\n" +
                "INSERT INTO PROJECT_IMAGE_DEFINITION (ID, IMAGE_REPOSITORY, ARTIFACT_ID, PACKAGING, BOOT_TYPE, TIER, CREATED_TIME) VALUES (seq_image_definition_id.nextval, 'brand-core-tomcat-02', 'brand-CatalogueService', 'war', 2, 1, TO_DATE('2021-01-22 14:33:40', 'YYYY-MM-DD HH24:MI:SS'));\n";

        parse(sql, DbType.oracle);
    }


    public void testMysql3() {

        String sql =
            "-- ### 用户: dlbxdb\n" +
                "\n" +
                "-- ### DML\n" +
                "\n" +
                "-- ###上线 96\n" +
                "INSERT INTO dlbxdb.dlop_uiep_cancer_basic (relation_type_code, genre, age_start, age_end, deductible_amount, has_security, premium, risk_code) VALUES\n" +
                "('FA006252', '0', 45, 45, '0', '1', 671.00, '1038')\n" +
                ",('FA006253', '0', 46, 50, '0', '1', 908.00, '1038')\n" +
                ",('FA006254', '0', 51, 55, '0', '1', 1135.00, '1038')\n" +
                ",('FA006255', '0', 56, 60, '0', '1', 1486.00, '1038')\n" +
                ",('FA006256', '0', 61, 65, '0', '1', 1832.00, '1038')\n" +
                ",('FA006257', '0', 66, 70, '0', '1', 2705.00, '1038')\n" +
                ",('FA006258', '0', 71, 75, '0', '1', 4162.00, '1038')\n" +
                ",('FA006259', '0', 76, 80, '0', '1', 5369.00, '1038')\n" +
                ",('FA006260', '0', 45, 45, '0', '0', 1409.00, '1038')\n" +
                ",('FA006261', '0', 46, 50, '0', '0', 1573.00, '1038')\n" +
                ",('FA006262', '0', 51, 55, '0', '0', 1996.00, '1038')\n" +
                ",('FA006263', '0', 56, 60, '0', '0', 2566.00, '1038')\n" +
                ",('FA006264', '0', 61, 65, '0', '0', 3124.00, '1038')\n" +
                ",('FA006265', '0', 66, 70, '0', '0', 5645.00, '1038')\n" +
                ",('FA006266', '0', 71, 75, '0', '0', 11685.00, '1038')\n" +
                ",('FA006267', '0', 76, 80, '0', '0', 15638.00, '1038')\n" +
                ",('FA006268', '0', 45, 45, '1', '1', 582.00, '1038')\n" +
                ",('FA006269', '0', 46, 50, '1', '1', 827.00, '1038')\n" +
                ",('FA006270', '0', 51, 55, '1', '1', 1035.00, '1038')\n" +
                ",('FA006271', '0', 56, 60, '1', '1', 1367.00, '1038')\n" +
                ",('FA006272', '0', 61, 65, '1', '1', 1660.00, '1038')\n" +
                ",('FA006273', '0', 66, 70, '1', '1', 2435.00, '1038')\n" +
                ",('FA006274', '0', 71, 75, '1', '1', 3374.00, '1038')\n" +
                ",('FA006275', '0', 76, 80, '1', '1', 4730.00, '1038')\n" +
                ",('FA006276', '0', 45, 45, '1', '0', 1359.00, '1038')\n" +
                ",('FA006277', '0', 46, 50, '1', '0', 1452.00, '1038')\n" +
                ",('FA006278', '0', 51, 55, '1', '0', 1840.00, '1038')\n" +
                ",('FA006279', '0', 56, 60, '1', '0', 2357.00, '1038')\n" +
                ",('FA006280', '0', 61, 65, '1', '0', 2832.00, '1038')\n" +
                ",('FA006281', '0', 66, 70, '1', '0', 5066.00, '1038')\n" +
                ",('FA006282', '0', 71, 75, '1', '0', 9656.00, '1038')\n" +
                ",('FA006283', '0', 76, 80, '1', '0', 14108.00, '1038')\n" +
                ",('FA006284', '0', 45, 45, '2', '1', 531.00, '1038')\n" +
                ",('FA006285', '0', 46, 50, '2', '1', 728.00, '1038')\n" +
                ",('FA006286', '0', 51, 55, '2', '1', 909.00, '1038')\n" +
                ",('FA006287', '0', 56, 60, '2', '1', 1226.00, '1038')\n" +
                ",('FA006288', '0', 61, 65, '2', '1', 1453.00, '1038')\n" +
                ",('FA006289', '0', 66, 70, '2', '1', 2136.00, '1038')\n" +
                ",('FA006290', '0', 71, 75, '2', '1', 3083.00, '1038')\n" +
                ",('FA006291', '0', 76, 80, '2', '1', 4309.00, '1038')\n" +
                ",('FA006292', '0', 45, 45, '2', '0', 1259.00, '1038')\n" +
                ",('FA006293', '0', 46, 50, '2', '0', 1343.00, '1038')\n" +
                ",('FA006294', '0', 51, 55, '2', '0', 1697.00, '1038')\n" +
                ",('FA006295', '0', 56, 60, '2', '0', 2177.00, '1038')\n" +
                ",('FA006296', '0', 61, 65, '2', '0', 2555.00, '1038')\n" +
                ",('FA006297', '0', 66, 70, '2', '0', 4566.00, '1038')\n" +
                ",('FA006298', '0', 71, 75, '2', '0', 8796.00, '1038')\n" +
                ",('FA006299', '0', 76, 80, '2', '0', 12968.00, '1038')\n" +
                ",('FA006300', '1', 45, 45, '0', '1', 1585.00, '1038')\n" +
                ",('FA006301', '1', 46, 50, '0', '1', 2096.00, '1038')\n" +
                ",('FA006302', '1', 51, 55, '0', '1', 2859.00, '1038')\n" +
                ",('FA006303', '1', 56, 60, '0', '1', 3964.00, '1038')\n" +
                ",('FA006304', '1', 61, 65, '0', '1', 5137.00, '1038')\n" +
                ",('FA006305', '1', 66, 70, '0', '1', 7713.00, '1038')\n" +
                ",('FA006306', '1', 71, 75, '0', '1', 12217.00, '1038')\n" +
                ",('FA006307', '1', 76, 80, '0', '1', 15990.00, '1038')\n" +
                ",('FA006308', '1', 45, 45, '0', '0', 2273.00, '1038')\n" +
                ",('FA006309', '1', 46, 50, '0', '0', 2893.00, '1038')\n" +
                ",('FA006310', '1', 51, 55, '0', '0', 3925.00, '1038')\n" +
                ",('FA006311', '1', 56, 60, '0', '0', 5539.00, '1038')\n" +
                ",('FA006312', '1', 61, 65, '0', '0', 7037.00, '1038')\n" +
                ",('FA006313', '1', 66, 70, '0', '0', 10659.00, '1038')\n" +
                ",('FA006314', '1', 71, 75, '0', '0', 17392.00, '1038')\n" +
                ",('FA006315', '1', 76, 80, '0', '0', 22303.00, '1038')\n" +
                ",('FA006316', '1', 45, 45, '1', '1', 1553.00, '1038')\n" +
                ",('FA006317', '1', 46, 50, '1', '1', 2031.00, '1038')\n" +
                ",('FA006318', '1', 51, 55, '1', '1', 2792.00, '1038')\n" +
                ",('FA006319', '1', 56, 60, '1', '1', 3888.00, '1038')\n" +
                ",('FA006320', '1', 61, 65, '1', '1', 4935.00, '1038')\n" +
                ",('FA006321', '1', 66, 70, '1', '1', 7395.00, '1038')\n" +
                ",('FA006322', '1', 71, 75, '1', '1', 12027.00, '1038')\n" +
                ",('FA006323', '1', 76, 80, '1', '1', 15616.00, '1038')\n" +
                ",('FA006324', '1', 45, 45, '1', '0', 2310.00, '1038')\n" +
                ",('FA006325', '1', 46, 50, '1', '0', 2849.00, '1038')\n" +
                ",('FA006326', '1', 51, 55, '1', '0', 3881.00, '1038')\n" +
                ",('FA006327', '1', 56, 60, '1', '0', 5503.00, '1038')\n" +
                ",('FA006328', '1', 61, 65, '1', '0', 6862.00, '1038')\n" +
                ",('FA006329', '1', 66, 70, '1', '0', 10405.00, '1038')\n" +
                ",('FA006330', '1', 71, 75, '1', '0', 17002.00, '1038')\n" +
                ",('FA006331', '1', 76, 80, '1', '0', 21563.00, '1038')\n" +
                ",('FA006332', '1', 45, 45, '2', '1', 1533.00, '1038')\n" +
                ",('FA006333', '1', 46, 50, '2', '1', 2008.00, '1038')\n" +
                ",('FA006334', '1', 51, 55, '2', '1', 2782.00, '1038')\n" +
                ",('FA006335', '1', 56, 60, '2', '1', 3900.00, '1038')\n" +
                ",('FA006336', '1', 61, 65, '2', '1', 4893.00, '1038')\n" +
                ",('FA006337', '1', 66, 70, '2', '1', 7244.00, '1038')\n" +
                ",('FA006338', '1', 71, 75, '2', '1', 11880.00, '1038')\n" +
                ",('FA006339', '1', 76, 80, '2', '1', 15392.00, '1038')\n" +
                ",('FA006340', '1', 45, 45, '2', '0', 2265.00, '1038')\n" +
                ",('FA006341', '1', 46, 50, '2', '0', 2802.00, '1038')\n" +
                ",('FA006342', '1', 51, 55, '2', '0', 3840.00, '1038')\n" +
                ",('FA006343', '1', 56, 60, '2', '0', 5320.00, '1038')\n" +
                ",('FA006344', '1', 61, 65, '2', '0', 6631.00, '1038')\n" +
                ",('FA006345', '1', 66, 70, '2', '0', 10053.00, '1038')\n" +
                ",('FA006346', '1', 71, 75, '2', '0', 16779.00, '1038')\n" +
                ",('FA006347', '1', 76, 80, '2', '0', 21486.00, '1038');";

        parse(sql, DbType.mysql);
    }
}
