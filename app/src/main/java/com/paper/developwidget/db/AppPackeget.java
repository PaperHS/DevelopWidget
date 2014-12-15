package com.paper.developwidget.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Paper on 14-12-11.
 */
@DatabaseTable(tableName = "paketages")
public class AppPackeget {
    public AppPackeget(){

    }
    public AppPackeget(String name,String pakage){
        this.name = name;
        this.pakage = pakage;

    }
    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField(columnName = "name")
    String name;
    @DatabaseField(columnName = "pakage")
    String pakage;
    @DatabaseField(columnName = "createtime")
    long createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPakage() {
        return pakage;
    }

    public void setPakage(String pakage) {
        this.pakage = pakage;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }
}
