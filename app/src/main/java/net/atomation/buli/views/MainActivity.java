package net.atomation.buli.views;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.DatePicker;

import net.atomation.buli.R;
import net.atomation.buli.databinding.ActivityMainBinding;
import net.atomation.buli.models.Age;
import net.atomation.buli.models.AgeCalculator;
import net.atomation.buli.models.BirthdayCalculator;
import net.atomation.buli.utils.Utils;
import net.atomation.buli.views.contents.MainActivityContent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DateId.AGE_CAL_DATE_OF_BIRTH, DateId.AGE_CAL_DEST_DATE, DateId.BIRTHDAY_CAL_DEST_DATE})
    public @interface DateId {
        int AGE_CAL_DATE_OF_BIRTH = 0;
        int AGE_CAL_DEST_DATE = 1;
        int BIRTHDAY_CAL_DEST_DATE = 2;
    }

    private static final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding mBinding;
    private MainActivityContent mContent = new MainActivityContent();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setEventHandler(this);
        mBinding.setContent(mContent);

        mContent.setAgeCalculator(new AgeCalculator());
        mContent.setBirthdayCalculator(new BirthdayCalculator());
        mContent.getAgeCalculator().setValid(true);

    }

    public void onSelectDateOfBirthClick() {
        onSelectDateClick(DateId.AGE_CAL_DATE_OF_BIRTH);
    }

    public void onSelectDestDateClick() {
        onSelectDateClick(DateId.AGE_CAL_DEST_DATE);
    }

    public void onCalculateAgeClick() {
        mContent.getAgeCalculator().setCalculatedAge(mContent.getAgeCalculator().calculateAge().toString());
    }

    private void onSelectDateClick(@DateId final int dateId) {

        mContent.getAgeCalculator().setCalculatedAge(null);

        Calendar date;
        switch (dateId) {
            case DateId.AGE_CAL_DATE_OF_BIRTH:
                date = mContent.getAgeCalculator().getDateOfBirth();
                break;
            case DateId.AGE_CAL_DEST_DATE:
                date = mContent.getAgeCalculator().getDestinationDate();
                break;
            case DateId.BIRTHDAY_CAL_DEST_DATE:
                date = mContent.getBirthdayCalculator().getDestDate();
                break;
            default:
                date = Calendar.getInstance();
                break;
        }

        int year = date.get(Calendar.YEAR);
        final int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        month += 1;
                        String dateStr = String.format(getString(R.string.date_format), day, month, year);
                        Calendar date = Utils.stringToCalender(dateStr);
                        switch (dateId) {
                            case DateId.AGE_CAL_DATE_OF_BIRTH:
                                mContent.getAgeCalculator().setDateOfBirth(date);
                                break;
                            case DateId.AGE_CAL_DEST_DATE:
                                mContent.getAgeCalculator().setDestinationDate(date);
                                break;
                            case DateId.BIRTHDAY_CAL_DEST_DATE:
                                mContent.getBirthdayCalculator().setDestDate(date);
                            default:
                                break;
                        }

                        validateDates();
                    }
                },
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.today_btn), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Calendar today = Calendar.getInstance();
                switch (dateId) {
                    case DateId.AGE_CAL_DATE_OF_BIRTH:
                        mContent.getAgeCalculator().setDateOfBirth(today);
                        break;
                    case DateId.AGE_CAL_DEST_DATE:
                        mContent.getAgeCalculator().setDestinationDate(today);
                        break;
                    case DateId.BIRTHDAY_CAL_DEST_DATE:
                        mContent.getBirthdayCalculator().setDestDate(today);
                        break;
                    default:
                        break;
                }
            }
        });

        dialog.show();

    }

    private void validateDates() {
        boolean validDates = mContent.getAgeCalculator().isValidDates(mContent.getAgeCalculator().getDateOfBirth(), mContent.getAgeCalculator().getDestinationDate());
        mContent.getAgeCalculator().setValid(validDates);
        if (!validDates) {
            mContent.getAgeCalculator().setCalculatedAge(getString(R.string.error_dates_validation));
        }
    }

    public void onClearAgeClick() {
        mContent.getAgeCalculator().setCalculatedAge(null);
    }

    public void onClearBirthdayClick() {
        mContent.getBirthdayCalculator().setCalculatedBirthday(null);
        mContent.getBirthdayCalculator().setError(null);
    }

    public void onCalculateBirthdayClick() {
        boolean isValid = validateBirthdayStuff();
        if (isValid) {
            mContent.getBirthdayCalculator().setCalculatedBirthday(mContent.getBirthdayCalculator().calculateBirthday());
        }
    }

    public void onBirthdayCalculatorDestDateClick() {
        onSelectDateClick(DateId.BIRTHDAY_CAL_DEST_DATE);
    }

    private boolean validateBirthdayStuff() {
        String ageYears = mBinding.edtAgeYears.getText().toString();
        String ageMonths = mBinding.edtAgeMonths.getText().toString();
        if (TextUtils.isEmpty(ageYears) || TextUtils.isEmpty(ageMonths)) {
            Log.d(TAG, "validateBirthdayStuff: ageyears = " + ageYears + "  agemonths = " + ageMonths);
            mContent.getBirthdayCalculator().setError(getString(R.string.error_bithday_cal_validation));
            return false;
        }
        Log.d(TAG, "validateBirthdayStuff: ");
        mContent.getBirthdayCalculator().setDestAge(new Age(1,Integer.parseInt(ageMonths),Integer.parseInt(ageYears)));
        mContent.getBirthdayCalculator().setError(null);
        return true;
    }


}
