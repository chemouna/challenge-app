package com.mounacheikhna.challenge.ui.main.stops;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mounacheikhna.challenge.R;
import com.mounacheikhna.challenge.model.Arrival;
import com.mounacheikhna.challenge.model.CompleteStopPoint;
import com.mounacheikhna.challenge.model.StopPoint;
import com.mounacheikhna.challenge.ui.stopdetails.StopDetailsActivity;
import io.reactivex.subjects.PublishSubject;
import java.text.DecimalFormat;
import org.joda.time.DateTime;
import org.joda.time.Duration;

class StopPointViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.stop_name_tv) TextView stopNameTv;
    @BindView(R.id.departures_tv) TextView arrivalsTv;
    @BindView(R.id.closest_indicator) TextView closestIndicator;
    @BindView(R.id.distance_to_tv) TextView distanceToTv;
    @BindView(R.id.overflow_menu) ImageButton overflowImageButton;
    @BindView(R.id.stop_info_container) View stopInfoContainer;

    StopPointViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    static int getLayoutId() {
        return R.layout.stop_point_item_layout;
    }

    void bind(final CompleteStopPoint item, boolean closeToLocation,
        PublishSubject<StopPoint> stopPointSave) {
        stopNameTv.setText(item.stopPoint().commonName());
        final StringBuilder content = new StringBuilder("in ");
        for (int i = 0; i < item.arrivals().size() && i < 3; i++) {
            final Arrival arrival = item.arrivals().get(i);
            final DateTime dateTime = DateTime.parse(arrival.expectedArrival());
            String dt = formattedDurationFromNow(dateTime);
            if (!TextUtils.isEmpty(content) && i > 0) {
                content.append(", ");
            }
            content.append(dt);
        }
        content.append(itemView.getContext().getString(R.string.minutes));
        arrivalsTv.setText(content.toString());
        closestIndicator.setVisibility(closeToLocation ? View.VISIBLE : View.GONE);

        distanceToTv.setText(new DecimalFormat("##").format(item.stopPoint().distance()));

        PopupMenu popupMenu = new PopupMenu(itemView.getContext(), overflowImageButton);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.stop_item_menu, popupMenu.getMenu());

        overflowImageButton.setOnClickListener(v -> popupMenu.show());
        popupMenu.setOnMenuItemClickListener(item1 -> {
            switch (item1.getItemId()) {
                case R.id.save:
                    stopPointSave.onNext(item.stopPoint());
                    break;
            }
            return false;
        });

        stopInfoContainer.setOnClickListener(
            v -> StopDetailsActivity.start(itemView.getContext(), item));
    }

    //TODO: use Clock and put this in a place where it can be tested
    @NonNull
    private static String formattedDurationFromNow(DateTime dateTime) {
        Duration duration = new Duration(DateTime.now(), dateTime);
        return new StringBuilder().append(
            new DecimalFormat("##").format(duration.getStandardMinutes())).toString();
    }

}
