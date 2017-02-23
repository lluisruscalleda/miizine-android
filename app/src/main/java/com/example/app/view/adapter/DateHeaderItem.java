package com.example.app.view.adapter;

import java.sql.Date;

/**
 * Created by lluis on 25/10/2016.
 */

public class DateHeaderItem extends PillItem {

    private Date date;

    // here getters and setters
    // for title and so on, built
    // using date

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

}
