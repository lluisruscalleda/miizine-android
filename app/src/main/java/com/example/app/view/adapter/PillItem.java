package com.example.app.view.adapter;

/**
 * Created by lluis on 25/10/2016.
 */

public abstract class PillItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_EVENT = 1;

    abstract public int getType();
}
