package com.varun.android.listview.model;

/**
 * Created by VarunSasidharan_Nair on 31/07/2017.
 */

public class Reminder {
    public String id;
    public String title;
    public String recurringType;
    public String status;

    public Reminder(String _title, String _recurringType, String _status) {
        title = _title;
        recurringType = _recurringType;
        status = _status;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecurringType() {
        return recurringType;
    }

    public void setRecurringType(String recurringType) {
        this.recurringType = recurringType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
