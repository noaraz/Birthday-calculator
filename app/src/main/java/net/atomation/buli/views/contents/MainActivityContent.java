package net.atomation.buli.views.contents;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import net.atomation.buli.BR;
import net.atomation.buli.models.AgeCalculator;
import net.atomation.buli.models.BirthdayCalculator;

import java.util.Calendar;

/**
 * Created by noaraz on 03/10/2017.
 */

public class MainActivityContent extends BaseObservable {

    private AgeCalculator ageCalculator;
    private BirthdayCalculator birthdayCalculator;

    @Bindable
    public AgeCalculator getAgeCalculator() {
        return ageCalculator;
    }

    public void setAgeCalculator(AgeCalculator ageCalculator) {
        this.ageCalculator = ageCalculator;
        notifyPropertyChanged(BR.ageCalculator);
    }
    @Bindable
    public BirthdayCalculator getBirthdayCalculator() {
        return birthdayCalculator;
    }

    public void setBirthdayCalculator(BirthdayCalculator birthdayCalculator) {
        this.birthdayCalculator = birthdayCalculator;
        notifyPropertyChanged(net.atomation.buli.BR.birthdayCalculator);
    }
}
