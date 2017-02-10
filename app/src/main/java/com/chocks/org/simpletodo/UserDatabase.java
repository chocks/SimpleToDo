package com.chocks.org.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Chocks on 2/7/17.
 */
@Database(name = UserDatabase.NAME, version = UserDatabase.VERSION)
public class UserDatabase {
    public static final String NAME = "ItemsDB";

    public static final int VERSION = 1;
}
