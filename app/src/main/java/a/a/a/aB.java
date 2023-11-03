package a.a.a;

import static mod.SketchwareUtil.getDip;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.DrawableRes;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

/**
 * Sketchware dialog that wrappes the material alert dialog builder.
 */
public class aB extends MaterialAlertDialogBuilder {

    public aB(Activity activity) {
        super(activity);
    }

    /**
     * Set the dialog's image's resource ID
     */
    public void a(@DrawableRes int resId) {
        setIcon(resId);
    }

    /**
     * Set the dialog's custom view
     */
    public void a(View customView) {
        FrameLayout view = new FrameLayout(getContext());
        view.setLayoutParams(
            new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
        );
        view.setPadding((int) getDip(24), (int) getDip(8), (int) getDip(24), (int) getDip(8));
        view.addView(customView);
        setView(view);
    }

    /**
     * Set the dialog's message
     */
    public void a(String message) {
        setMessage(message);
    }

    /**
     * Set the dialog's "No" button text and listener
     */
    public void a(String noText, OnClickListener noListener) {
        setNegativeButton(noText, noListener);
    }

    /**
     * Set the dialog's title
     */
    public void b(String title) {
        setTitle(title);
    }

    /**
     * Set the dialog's "Yes" button text and listener
     */
    public void b(String yesText, OnClickListener yesListener) {
        setPositiveButton(yesText, yesListener);
    }

    public void configureDefaultButton(String defaultText, OnClickListener defaultListener) {
        setNeutralButton(defaultText, defaultListener);
    }
}
