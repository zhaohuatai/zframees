package com.zht.common.db;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackUpMySQL {

    private static Logger log = LoggerFactory.getLogger(BackUpMySQL.class);

    private static final String backup_path_linux = "";
    private static final String backup_path_windews = "D://";
    private static final String sqlname = new Date().toLocaleString() + ".sql";

    public static void windowsBackUp() {
        try {
            String mysql = "mysqldump -u root --password=root thinker > " +
                    backup_path_windews + sqlname;
            java.lang.Runtime.getRuntime().exec("cmd /c " + mysql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void linuxBackUp() {
        try {
            String mysql = "mysqldump -u root --password=root thinker > " +
                    backup_path_linux + sqlname;
            java.lang.Runtime.getRuntime().exec(
                    new String[]{"sh", "-c", mysql});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
