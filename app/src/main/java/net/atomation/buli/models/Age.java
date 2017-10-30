package net.atomation.buli.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by noaraz on 10/10/2017.
 */


public class Age extends BaseObservable {
    private int days;
    private int months;
    private int years;

    private Age()
    {
        //Prevent default constructor
    }

    public Age(int days, int months, int years)
    {
        this.days = days;
        this.months = months;
        this.years = years;
    }
    @Bindable
    public int getDays()
    {
        return this.days;
    }
    @Bindable
    public int getMonths()
    {
        return this.months;
    }
    @Bindable
    public int getYears()
    {
        return this.years;
    }

    @Override
    public String toString()
    {
        return years + " Years, " + months + " Months, " + days + " Days";
    }
}
