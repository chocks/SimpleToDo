package com.chocks.org.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;

/**
 * Created by Chocks on 2/7/17.
 */
@Table(database = UserDatabase.class)
@Parcel(analyze={Items.class})
public class Items extends BaseModel {
    @PrimaryKey(rowID = true)
    @Column
    long id;

    @Column
    String text;
}
