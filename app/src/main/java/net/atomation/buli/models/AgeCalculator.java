package net.atomation.buli.models;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.util.Log;
import net.atomation.buli.BR;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by noaraz on 03/10/2017.
 */

public class AgeCalculator extends BaseObservable {
    private static final String TAG = AgeCalculator.class.getSimpleName();



    private Calendar dateOfBirth;
    private Calendar destinationDate;
    private String calculatedAge;
    private boolean isValid;

    public AgeCalculator() {
        dateOfBirth = Calendar.getInstance();
        destinationDate = Calendar.getInstance();
    }

    @Bindable
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
        notifyPropertyChanged(BR.valid);
    }

    @Bindable
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        notifyPropertyChanged(BR.dateOfBirth);
    }
    @Bindable
    public Calendar getDestinationDate() {
        return destinationDate;
    }

    public void setDestinationDate(Calendar destinationDate) {
        this.destinationDate = destinationDate;
        notifyPropertyChanged(BR.destinationDate);
    }

    @Bindable
    public String getCalculatedAge() {
        return calculatedAge;
    }

    public void setCalculatedAge(String calculatedAge) {
        this.calculatedAge = calculatedAge;
        notifyPropertyChanged(BR.calculatedAge);
    }



    public boolean isValidDates(Calendar birthDate, Calendar destDate) {
        Log.d(TAG, "isValidDates: ");
        int birthYear = birthDate.get(Calendar.YEAR);
        int destYear = destDate.get(Calendar.YEAR);
        if (birthYear > destYear) {
            Log.d(TAG, "isValidDates: year fault");
            return false;
        }

        if (birthYear == destYear) {
            Log.d(TAG, "isValidDates: years eq");
            int birthMonth = birthDate.get(Calendar.MONTH);
            int destMonth = destDate.get(Calendar.MONTH);

            if (birthMonth > destMonth) {
                Log.d(TAG, "isValidDates: month fault");
                return false;
            }

            if (birthMonth == destMonth) {
                int birthDay = birthDate.get(Calendar.DAY_OF_MONTH);
                int destDay = destDate.get(Calendar.DAY_OF_MONTH);

                if (birthDay > destDay) {
                    Log.d(TAG, "isValidDates: day fault");
                    return false;
                }
            }
        }

        return true;
    }

    public Age calculateAge() {

        int years = 0;
        int months = 0;
        int days = 0;

        //Get difference between years
        years = destinationDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        int currMonth = destinationDate.get(Calendar.MONTH) + 1;
        int birthMonth = dateOfBirth.get(Calendar.MONTH) + 1;
        //Get difference between months
        months = currMonth - birthMonth;
        //if month difference is in negative then reduce years by one and calculate the number of months.
        if (months < 0)
        {
            years--;
            months = 12 - birthMonth + currMonth;
            if (destinationDate.get(Calendar.DATE) < dateOfBirth.get(Calendar.DATE))
                months--;
        } else if (months == 0 && destinationDate.get(Calendar.DATE) < dateOfBirth.get(Calendar.DATE))
        {
            years--;
            months = 11;
        }
        //Calculate the days
        if (destinationDate.get(Calendar.DATE) > dateOfBirth.get(Calendar.DATE))
            days = destinationDate.get(Calendar.DATE) - dateOfBirth.get(Calendar.DATE);
        else if (destinationDate.get(Calendar.DATE) < dateOfBirth.get(Calendar.DATE))
        {
            int today = destinationDate.get(Calendar.DAY_OF_MONTH);
            destinationDate.add(Calendar.MONTH, -1);
            days = destinationDate.getActualMaximum(Calendar.DAY_OF_MONTH) - dateOfBirth.get(Calendar.DAY_OF_MONTH) + today;
        } else
        {
            days = 0;
            if (months == 12)
            {
                years++;
                months = 0;
            }
        }

        return new Age(days,months, years);
    }

}

