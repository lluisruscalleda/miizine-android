package com.example.app.view.adapter;

import com.example.app.model.Pill;

/**
 * Created by lluis on 25/10/2016.
 */

public class EventItem extends PillItem {

    private Pill pill;

    // here getters and setters
    // for title and so on, built
    // using event

    @Override
    public int getType() {
        return TYPE_EVENT;
    }

}