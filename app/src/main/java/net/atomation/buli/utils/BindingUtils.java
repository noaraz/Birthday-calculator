package net.atomation.buli.utils;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by noaraz on 16/10/2017.
 */

public class BindingUtils {

    @BindingAdapter("bind:level")
    public static void setLevel(ImageView view, int level) {
        view.setImageLevel(level);
    }

    @BindingAdapter("bind:level")
    public static void setLevel(ImageButton view, int level) {
        view.setImageLevel(level);
    }

    @BindingAdapter("bind:error")
    public static void setError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
        textInputLayout.setErrorEnabled(error != null);
    }

    @BindingAdapter("bind:refreshing")
    public static void setRefreshing(SwipeRefreshLayout view, boolean refreshing) {
        view.setRefreshing(refreshing);
    }

}
