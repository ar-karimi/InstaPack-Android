package ir.mhd.ninjadialog;

import android.view.View;

public class OnClickListener {

    public interface OnPositiveButtonClickListener {
        void onClick(View v);
    }

    public interface OnNegativeButtonClickListener {
        void onClick(View v);
    }
}

