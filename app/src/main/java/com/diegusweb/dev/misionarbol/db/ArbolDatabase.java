package com.diegusweb.dev.misionarbol.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by HP on 26/01/2017.
 */

@Database(name = ArbolDatabase.NAME, version = ArbolDatabase.VERSION)
public class ArbolDatabase {
    public static final int VERSION = 1;
    public static final String NAME = "ArbolDB";
}
