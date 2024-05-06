package com.vn.osp.modelview;

import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

/**
 * Created by VIETDUNG on 25/04/2017.
 */
public class ConfigBackupDatabaseManager {
    private static final String file_name_bat = "backup.bat";
    private static final String CONFIG_MYSQL_FORDER = "system_mysql_mysqldump_folder";
    private static final String CONFIG_BACKUP_DATABASE_FOLDER = "system_backup_database_folder";
    private static final String TIME_BACKUP = "time_bacup";
    private static final String DATE_BACKUP = "date_backup";

    public void createBackupFileBat(){
        try {
            File file = new File(SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER) + file_name_bat);
            File folder = new File(SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER));
            if(file.exists()){
                file.delete();
            } else{
                if(!folder.exists())
                    folder.mkdirs();
            }
            file.createNewFile();
            String database = SystemProperties.getProperty("database");
            String user = SystemProperties.getProperty("user");
            String pass = SystemProperties.getProperty("pass");
            String host = SystemProperties.getProperty("host");

            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println("cd " + SystemProperties.getProperty(CONFIG_MYSQL_FORDER));
            String oDia = SystemProperties.getProperty(CONFIG_MYSQL_FORDER).substring(0, 2);
            writer.println(oDia);
            writer.println("mysqldump -u" + user+ " -p" + pass + " -h" + host + " "  + database + " -r \"" + SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER) + "backup_%date:~7,2%-%date:~4,2%-%date:~10,4%.sql\"");
            writer.println("Exit");
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void backUpAutomatic(){
        try {
            createBackupFileBat();
            Runtime c = Runtime.getRuntime();
            String cmd = "cmd /c start " + SystemProperties.getProperty(CONFIG_BACKUP_DATABASE_FOLDER)+ "backup.bat";
            Process pro = c.exec(cmd , null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean checkBackUp(){
        boolean result = false;
        String timeBackUp = SystemProperties.getProperty(TIME_BACKUP);
        int hour = Integer.parseInt(timeBackUp.substring(0, 2));
        int minute = Integer.parseInt(timeBackUp.substring(3, 5));
        String pa = timeBackUp.substring(6);

        Calendar cal = Calendar.getInstance();
        int index = Calendar.DAY_OF_WEEK;
        if(cal.get(Calendar.DAY_OF_WEEK) == 1){
            index = 6;
        } else{
            index = cal.get(Calendar.DAY_OF_WEEK) - 2;
        }
        String dateBackUp = SystemProperties.getProperty(DATE_BACKUP);
        List<Boolean> listDatebackup = EditString.parseListDateBackup(dateBackUp);
        if(listDatebackup.get(index)){
            if(pa.equals("AM")){
                if(hour == cal.get(Calendar.HOUR_OF_DAY)){
                    if(minute == cal.get(Calendar.MINUTE)){
                        result = true;
                    } else{
                        result = false;
                    }
                } else{
                    result = false;
                }
            } else{
                if((hour + 12) == cal.get(Calendar.HOUR_OF_DAY)){
                    if(minute == cal.get(Calendar.MINUTE)){
                        result = true;
                    } else{
                        result = false;
                    }
                } else{
                    result = false;
                }
            }
        } else{
            return false;
        }
        return result;
    }
}
