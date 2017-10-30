package net.atomation.buli.models;

import android.annotation.SuppressLint;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import net.atomation.buli.BR;
import net.atomation.buli.R;
import net.atomation.buli.utils.Utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by noaraz on 16/10/2017.
 */

public class BirthdayCalculator extends BaseObservable {

    private static String DATE_STRING_FORMAT = "%d/%d/%d";

    private Age destAge;
    private Calendar destDate;
    private Calendar calculatedBirthday;
    private String error;

    public BirthdayCalculator() {
        destDate = Calendar.getInstance();
    }

    @Bindable
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        notifyPropertyChanged(BR.error);
    }

    @Bindable
    public Calendar getCalculatedBirthday() {
        return calculatedBirthday;
    }

    public void setCalculatedBirthday(Calendar calculatedBirthday) {
        this.calculatedBirthday = calculatedBirthday;
        notifyPropertyChanged(BR.calculatedBirthday);
    }

    @Bindable
    public Age getDestAge() {
        return destAge;
    }

    public void setDestAge(Age destAge) {
        this.destAge = destAge;
        notifyPropertyChanged(BR.destAge);
    }

    @Bindable
    public Calendar getDestDate() {
        return destDate;
    }

    public void setDestDate(Calendar destDate) {
        this.destDate = destDate;
        notifyPropertyChanged(BR.destDate);
    }

    @SuppressLint("DefaultLocale")
    public Calendar calculateBirthday() {
        int destDayYear = destDate.get(Calendar.YEAR);
        int ageYear = destAge.getYears();
        int retYear = destDayYear - ageYear;

        int destDayMonth = destDate.get(Calendar.MONTH) + 1 ;
        int ageMonth = destAge.getMonths();
        int retMonth = Utils.mod((destDayMonth - ageMonth) , 12);

        if (ageMonth > destDayMonth) {
            retYear -=1;
        }

        int retDay = destDate.get(Calendar.DAY_OF_MONTH);

        return Utils.stringToCalender(String.format(DATE_STRING_FORMAT, retDay, retMonth, retYear));
    }
}
