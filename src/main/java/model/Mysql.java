package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mysql {
    //mysql5.6の予約語が格納された配列 mysqlList
    String[] mysqlList = {"accessible", "add", "all", "alter", "analyze", "and", "as", "asc", "asensitive",
            "before", "between", "bigint", "binary", "blob", "both", "by",
            "call", "cascade", "case", "change", "char", "character", "check", "collate", "column", "condition", "constraint", "continue", "create", "cross", "current_date", "current_time", "current_timestamp", "current_user", "cursor",
            "database", "databases", "day_hour", "day_microsecond", "dat_minute", "day_second", "dec", "decimal", "declare", "default", "delayed", "delete", "desc", "describe", "deterministic", "distinct", "distinctrow", "div", "double", "drop", "dual",
            "each", "else", "elseif", "enclosed", "escaped", "exists", "exit", "explain",
            "false", "fetch", "float", "float4", "float8", "for", "force", "foreign", "from", "fulltext",
            "get", "grant", "group",
            "having", "high_priority", "hour_microsecond", "hour_minute", "hour_second",
            "if", "ignore", "in", "index", "infile", "inner", "inout", "insensitive", "insert", "int", "int1", "int2", "int3", "int4", "int8", "integer", "interval", "into", "io_after_gtids", "io_before_gtids", "is", "iterate",
            "join",
            "key", "keys", "kill",
            "leading", "leave", "left", "like", "limit", "linear", "lines", "load", "localtime", "localtimestamp", "lock", "long", "longblob", "longtext", "lop", "low_priority",
            "master_bind", "master_ssl_verify_server_cert", "match", "maxvalue", "mediumblob", "mediumint", "mediumtext", "middleint", "minute_microsecond", "minute_second", "mod", "modifies",
            "natural", "not", "no_write_to_binlog", "null", "numeric",
            "on", "optimize", "option", "optionally", "or", "order", "out", "outer", "outfile",
            "partition", "precision", "primary", "procedure", "purge",
            "range", "read", "reads", "read_write", "real", "references", "regexp", "release", "rename", "repeat", "replace", "require", "restrict", "return", "revoke", "right", "rlike",
            "schema", "schemas", "second_microsecond", "select", "sensitive", "separator", "set", "show", "signal", "smallint", "spatial", "specific", "sql", "sqlexception", "sqlstate", "sqlwarning", "sql_big_result", "sql_calc_found_rows", "sql_small_result", "ssl", "starting", "straight_join",
            "table", "terminated", "then", "tinyblob", "tinyint", "tinytext", "to", "trailing", "trigger", "true",
            "undo", "union", "unique", "unlock", "unsigned", "update", "usage", "use", "using", "utc_date", "utc_time", "utc_timestamp",
            "values", "varbinary", "varchar", "varcharacter", "varying",
            "when", "where", "while", "with", "write",
            "xor",
            "year_month",
            "zerofill",
            "?", "=", "!", "*"
    };

    public String searchTable(String sql) {
        String result = null;
        String[] str = sql.split("[ (),]", 0);
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("from") || str[i].equals("update") || str[i].equals("into")) {
                result = str[i + 1];
                break;
            }
        }
        return result;
    }

    public String[] searchColumn(String sql) {
        String tableName = searchTable(sql);
        String[] str = sql.split("[ (),]");
        for (int count = 0; count < str.length; count++) {
            for (int j = 0; j < mysqlList.length; j++) {
                if (str[count].equals(mysqlList[j])) {
                    List<String> list = new ArrayList<>(Arrays.asList(str));
                    list.remove(count);
                    str = list.toArray(new String[list.size()]);
                    if (count == 0) {
                        count = -1;
                    } else {
                        count = count - 1;
                    }
                    break;
                }
            }

        }
        int i;
        for (i = 0; i < str.length; i++) {
            if (str[i].equals(tableName)){

                List<String> list = new ArrayList<>(Arrays.asList(str));
                list.remove(i);
                str = list.toArray(new String[list.size()]);
                if (i == 0) {
                    i = -1;
                } else {
                    i = i - 1;
                }
                continue;
            }
        }
        String[] results = new String[i];
        for(i = 0; i < results.length; i++){
            results[i] = str[i];
        }
        return results;
    }
}