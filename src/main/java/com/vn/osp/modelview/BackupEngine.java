package com.vn.osp.modelview;

import com.vn.osp.common.util.SystemProperties;

/**
 * Created by VIETDUNG on 25/04/2017.
 */
public class BackupEngine {

    static private BackupEngine _instance;
    private String CHECK_BACKUP = "backup_check";

    /**
     * The constructor could be made private to prevent others from
     * instantiating this class. But this would also make it impossible to
     * create instances of Singleton subclasses.
     */

    public BackupEngine() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @return The unique instance of this class.
     */
    static public BackupEngine instance() {
        if (null == _instance) {
            _instance = new BackupEngine();
        }
        return _instance;
    }

    /**
     * @method Backup Database
     */

    public void BackupDatabase(){
        if(Boolean.parseBoolean(SystemProperties.getProperty(CHECK_BACKUP))){
            ConfigBackupDatabaseManager cfManager = new ConfigBackupDatabaseManager();
            if(cfManager.checkBackUp()){
                cfManager.backUpAutomatic();
            }
        }
    }
}
