package com.mounacheikhna.challenge.ui.savedstops;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.SavedStop;

public class SavedStopViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.stop_name_tv) TextView stopNameTv;

    public SavedStopViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    static int getLayoutId() {
        return R.layout.stop_point_item_layout;
    }

    public void bind(SavedStop item) {
        stopNameTv.setText(item.commonName());
    }
}
