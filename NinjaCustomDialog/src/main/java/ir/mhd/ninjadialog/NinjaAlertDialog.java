package ir.mhd.ninjadialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class NinjaAlertDialog extends DialogFragment {
    private static final String EXTRA_KEY_TITLE = "title";
    private static final String EXTRA_KEY_SUBTITLE = "subtitle";
    private static final String EXTRA_KEY_POSITIVE_TEXT = "positive_text";
    private static final String EXTRA_KEY_NEGATIVE_TEXT = "negative_text";
    private static final String EXTRA_KEY_TITLE_TEXT_COLOR = "title_text_color";
    private static final String EXTRA_KEY_SUBTITLE_TEXT_COLOR = "subtitle_text_color";
    private static final String EXTRA_KEY_POSITIVE_TEXT_COLOR = "positive_text_color";
    private static final String EXTRA_KEY_NEGATIVE_TEXT_COLOR = "negative_text_color";
    private static final String EXTRA_KEY_ROOT_BACKGROUND = "root_background";
    private static final String EXTRA_KEY_POSITIVE_BACKGROUND = "positive_background";
    private static final String EXTRA_KEY_NEGATIVE_BACKGROUND = "negative_background";
    private static final String EXTRA_KEY_IMAGE = "image";

    private View btnContainer;
    private View rootView;
    private ImageView imageView;
    private TextView titleTextView;
    private TextView subTitleTextView;
    private Button positiveButton;
    private Button negativeButton;
    private FrameLayout frameLayout;

    private String title;
    private String subtitle;
    private String positiveText;
    private String negativeText;

    @ColorRes
    private int titleTextColor;
    @ColorRes
    private int subtitleTextColor;
    @ColorRes
    private int positiveTextColor;
    @ColorRes
    private int negativeTextColor;

    @DrawableRes
    private int rootBackground;
    @DrawableRes
    private int image;
    @DrawableRes
    private int positiveBackground;
    @DrawableRes
    private int negativeBackground;

    private View views;

    private OnClickListener.OnPositiveButtonClickListener positiveButtonClickListener;
    private OnClickListener.OnNegativeButtonClickListener negativeButtonClickListener;

    static NinjaAlertDialog newInstance(Builder builder) {

        Bundle args = new Bundle();

        args.putString(EXTRA_KEY_TITLE, builder.getTitle());
        args.putString(EXTRA_KEY_SUBTITLE, builder.getSubtitle());
        args.putString(EXTRA_KEY_POSITIVE_TEXT, builder.getPositiveText());
        args.putString(EXTRA_KEY_NEGATIVE_TEXT, builder.getNegativeText());
        args.putInt(EXTRA_KEY_TITLE_TEXT_COLOR, builder.getTitleTextColor());
        args.putInt(EXTRA_KEY_SUBTITLE_TEXT_COLOR, builder.getSubtitleTextColor());
        args.putInt(EXTRA_KEY_POSITIVE_TEXT_COLOR, builder.getPositiveTextColor());
        args.putInt(EXTRA_KEY_NEGATIVE_TEXT_COLOR, builder.getNegativeTextColor());
        args.putInt(EXTRA_KEY_IMAGE, builder.getImage());
        args.putInt(EXTRA_KEY_ROOT_BACKGROUND, builder.getRootBackground());
        args.putInt(EXTRA_KEY_POSITIVE_BACKGROUND, builder.getPositiveBackground());
        args.putInt(EXTRA_KEY_NEGATIVE_BACKGROUND, builder.getNegativeBackground());

        NinjaAlertDialog fragment = new NinjaAlertDialog();
        fragment.setArguments(args);
        fragment.setViews(builder.getViews());
        fragment.setPositiveButtonClickListener(builder.getPositiveButtonClickListener());
        fragment.setNegativeButtonClickListener(builder.getNegativeButtonClickListener());
        fragment.setCancelable(builder.isCancellable());
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        if (bundle != null) {
            title = bundle.getString(EXTRA_KEY_TITLE);
            subtitle = bundle.getString(EXTRA_KEY_SUBTITLE);
            positiveText = bundle.getString(EXTRA_KEY_POSITIVE_TEXT);
            negativeText = bundle.getString(EXTRA_KEY_NEGATIVE_TEXT);
            titleTextColor = bundle.getInt(EXTRA_KEY_TITLE_TEXT_COLOR);
            subtitleTextColor = bundle.getInt(EXTRA_KEY_SUBTITLE_TEXT_COLOR);
            positiveTextColor = bundle.getInt(EXTRA_KEY_POSITIVE_TEXT_COLOR);
            negativeTextColor = bundle.getInt(EXTRA_KEY_NEGATIVE_TEXT_COLOR);
            rootBackground = bundle.getInt(EXTRA_KEY_ROOT_BACKGROUND);
            image = bundle.getInt(EXTRA_KEY_IMAGE);
            positiveBackground = bundle.getInt(EXTRA_KEY_POSITIVE_BACKGROUND);
            negativeBackground = bundle.getInt(EXTRA_KEY_NEGATIVE_BACKGROUND);

        }
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(rootView);
        setupViews();
        bindData();

        AlertDialog alertDialog = builder.create();
        //to set transparent android dialog root view
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return alertDialog;
    }

    private void bindData() {
        if (image != 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(image);
        }

        if (title != null) {
            titleTextView.setVisibility(View.VISIBLE);
            titleTextView.setText(title);
        }

        if (subtitle != null) {
            subTitleTextView.setVisibility(View.VISIBLE);
            subTitleTextView.setText(subtitle);
        }

        if (positiveText != null) {
            btnContainer.setVisibility(View.VISIBLE);
            positiveButton.setVisibility(View.VISIBLE);
            positiveButton.setText(positiveText);
        }

        if (positiveBackground != 0) {
            positiveButton.setBackgroundResource(positiveBackground);
        }

        if (negativeText != null) {
            btnContainer.setVisibility(View.VISIBLE);
            negativeButton.setVisibility(View.VISIBLE);
            negativeButton.setText(negativeText);
        }

        if (views != null) {
            if (views.getParent() != null)
                ((ViewGroup) views.getParent()).removeAllViews();
            frameLayout.addView(views);
        }

        if (negativeBackground != 0) {
            negativeButton.setBackgroundResource(negativeBackground);
        }

        if (rootBackground != 0) {
            rootView.setBackgroundResource(rootBackground);
        }

        if (titleTextColor != 0) {
            titleTextView.setTextColor(getResources().getColor(titleTextColor));
        }

        if (subtitleTextColor != 0) {
            subTitleTextView.setTextColor(getResources().getColor(subtitleTextColor));
        }

        if (positiveTextColor != 0) {
            positiveButton.setTextColor(getResources().getColor(positiveTextColor));
        }

        if (negativeTextColor != 0) {
            negativeButton.setTextColor(getResources().getColor(negativeTextColor));
        }

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positiveButtonClickListener != null)
                    positiveButtonClickListener.onClick(v);
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (negativeButtonClickListener != null)
                    negativeButtonClickListener.onClick(v);
            }
        });

    }

    private void setupViews() {
        btnContainer = rootView.findViewById(R.id.ll_btnContainer);
        imageView = rootView.findViewById(R.id.image);
        titleTextView = rootView.findViewById(R.id.title);
        subTitleTextView = rootView.findViewById(R.id.subtitle);
        positiveButton = rootView.findViewById(R.id.btn_positive);
        negativeButton = rootView.findViewById(R.id.btn_negative);
        frameLayout = rootView.findViewById(R.id.frame_container);

    }

    void setViews(View views) {
        this.views = views;
    }

    public void setPositiveButtonClickListener(OnClickListener.OnPositiveButtonClickListener positiveButtonClickListener) {
        this.positiveButtonClickListener = positiveButtonClickListener;
    }

    public void setNegativeButtonClickListener(OnClickListener.OnNegativeButtonClickListener negativeButtonClickListener) {
        this.negativeButtonClickListener = negativeButtonClickListener;
    }

    public static class Builder {
        private boolean cancellable = true;
        private String title;
        private String subtitle;
        private String positiveText;
        private String negativeText;

        @ColorRes
        private int titleTextColor;
        @ColorRes
        private int subtitleTextColor;
        @ColorRes
        private int positiveTextColor;
        @ColorRes
        private int negativeTextColor;

        @DrawableRes
        private int rootBackground;
        @DrawableRes
        private int image;
        @DrawableRes
        private int positiveBackground;
        @DrawableRes
        private int negativeBackground;

        private View views;


        private OnClickListener.OnPositiveButtonClickListener positiveButtonClickListener;
        private OnClickListener.OnNegativeButtonClickListener negativeButtonClickListener;

        public boolean isCancellable() {
            return cancellable;
        }

        public Builder setCancellable(boolean cancellable) {
            this.cancellable = cancellable;
            return this;
        }

        public int getImage() {
            return image;
        }

        public Builder setImage(@DrawableRes int image) {
            this.image = image;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public Builder setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public String getPositiveText() {
            return positiveText;
        }

        public Builder setPositiveText(String positiveText) {
            this.positiveText = positiveText;
            return this;
        }

        public int getPositiveBackground() {
            return positiveBackground;
        }

        public Builder setPositiveBackground(@DrawableRes int positiveBackground) {
            this.positiveBackground = positiveBackground;
            return this;
        }

        public String getNegativeText() {
            return negativeText;
        }

        public Builder setNegativeText(String negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        public int getNegativeBackground() {
            return negativeBackground;
        }

        public Builder setNegativeBackground(@DrawableRes int negativeBackground) {
            this.negativeBackground = negativeBackground;
            return this;
        }

        public View getViews() {
            return views;
        }

        public Builder setViews(View views) {
            this.views = views;
            return this;
        }

        public int getRootBackground() {
            return rootBackground;
        }

        public Builder setRootBackground(@DrawableRes int rootBackground) {
            this.rootBackground = rootBackground;
            return this;
        }

        public int getTitleTextColor() {
            return titleTextColor;
        }

        public Builder setTitleTextColor(@ColorRes int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public int getSubtitleTextColor() {
            return subtitleTextColor;
        }

        public Builder setSubtitleTextColor(@ColorRes int subtitleTextColor) {
            this.subtitleTextColor = subtitleTextColor;
            return this;
        }

        public int getPositiveTextColor() {
            return positiveTextColor;
        }

        public Builder setPositiveTextColor(@ColorRes int positiveTextColor) {
            this.positiveTextColor = positiveTextColor;
            return this;
        }

        public int getNegativeTextColor() {
            return negativeTextColor;
        }

        public Builder setNegativeTextColor(@ColorRes int negativeTextColor) {
            this.negativeTextColor = negativeTextColor;
            return this;
        }

        public OnClickListener.OnPositiveButtonClickListener getPositiveButtonClickListener() {
            return positiveButtonClickListener;
        }

        public Builder setPositiveButtonClickListener(OnClickListener.OnPositiveButtonClickListener positiveButtonClickListener) {
            this.positiveButtonClickListener = positiveButtonClickListener;
            return this;
        }

        public OnClickListener.OnNegativeButtonClickListener getNegativeButtonClickListener() {
            return negativeButtonClickListener;
        }

        public Builder setNegativeButtonClickListener(OnClickListener.OnNegativeButtonClickListener negativeButtonClickListener) {
            this.negativeButtonClickListener = negativeButtonClickListener;
            return this;
        }

        public NinjaAlertDialog build() {
            return NinjaAlertDialog.newInstance(this);
        }

        public NinjaAlertDialog show(FragmentManager fragmentManager, String tag){
            NinjaAlertDialog ninjaAlertDialog=NinjaAlertDialog.newInstance(this);
            ninjaAlertDialog.show(fragmentManager, tag);
            return ninjaAlertDialog;
        }

        public NinjaAlertDialog show(FragmentManager fragmentManager){
            NinjaAlertDialog ninjaAlertDialog=NinjaAlertDialog.newInstance(this);
            ninjaAlertDialog.show(fragmentManager, null);
            return ninjaAlertDialog;
        }
    }
}