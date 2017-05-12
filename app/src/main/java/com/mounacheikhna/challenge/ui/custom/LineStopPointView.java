package com.mounacheikhna.challenge.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;

public class LineStopPointView extends LinearLayout {

    protected boolean selected;

    @BindView(R.id.location_item_top_line) View topLine;
    @BindView(R.id.location_item_bottom_line) View bottomLine;
    @BindView(R.id.location_item_ring) ImageView ring;
    @BindView(R.id.location_item_title) TextView title;
    @BindView(R.id.location_item_subtitle) TextView subtitle;

    public LineStopPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineStopPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate();
        ButterKnife.bind(this, view);
        init(attrs, defStyleAttr);
    }

    public View inflate() {
        return inflate(getContext(), R.layout.location_item, this);
    }

    public void init(AttributeSet attrs, int defStyleAttr) {
        setupParent(getContext());

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LineStopPointView, defStyleAttr, 0);
        setTitle(a.hasValue(R.styleable.LineStopPointView_title) ? a.getString(R.styleable.LineStopPointView_title) : "");
        setSubtitle(
            a.hasValue(R.styleable.LineStopPointView_subtitle) ? a.getString(R.styleable.LineStopPointView_subtitle) : "");
        setSelected(a.getBoolean(R.styleable.LineStopPointView_selected, false));

        topLine.setVisibility(VISIBLE);
        bottomLine.setVisibility(VISIBLE);
        a.recycle();
    }

    private void setupParent(Context ctx) {
        setMinimumHeight(ctx.getResources().getDimensionPixelSize(R.dimen.space_56));
        setGravity(Gravity.CENTER_VERTICAL);
        setOrientation(HORIZONTAL);
        setBaselineAligned(true);
    }

    public LineStopPointView setTitle(String titleStr) {
        if (titleStr != null && !titleStr.isEmpty()) {
            title.setText(titleStr);
            title.setVisibility(VISIBLE);
        } else {
            title.setVisibility(GONE);
        }
        return this;
    }

    public LineStopPointView setSubtitle(String subtitleStr) {
        if (subtitleStr != null && !subtitleStr.isEmpty()) {
            subtitle.setText(subtitleStr);
            subtitle.setVisibility(VISIBLE);
        } else {
            subtitle.setVisibility(GONE);
        }
        return this;
    }

    public LineStopPointView selectedStopPoint(boolean selected) {
        this.selected = selected;

        LayoutParams params = (LayoutParams) ring.getLayoutParams();

        if (selected) {
            int margin = getContext().getResources().getDimensionPixelSize(R.dimen.space_4);
            params.setMargins(margin, margin, margin, margin);
            ring.setImageResource(R.drawable.stop_point);
            subtitle.setTextColor(ContextCompat.getColor(getContext(), R.color.gray5));
        } else {
            params.setMargins(0, 0, 0, 0);
            ring.setImageResource(R.drawable.point_from_to);
            subtitle.setTextColor(ContextCompat.getColor(getContext(), R.color.gray4));
        }
        ring.setLayoutParams(params);
        invalidate();
        return this;
    }

    static class SavedState extends BaseSavedState {

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

            @Override
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean selected;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            selected = source.readByte() == 1;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeByte(selected ? (byte) 1 : (byte) 0);
        }
    }
}
