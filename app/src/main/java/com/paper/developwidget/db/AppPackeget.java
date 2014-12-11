package com.paper.developwidget.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Paper on 14-12-11.
 */
@DatabaseTable(tableName = "paketages")
public class AppPackeget {
    AppPackeget(){

    }
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(columnName = "name")
    String name;
    @DatabaseField(columnName = "pakage")
    String pakage;
    @DatabaseField(columnName = "createtime")
    long createtime;
}
