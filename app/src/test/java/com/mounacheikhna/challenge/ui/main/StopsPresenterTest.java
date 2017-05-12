package com.mounacheikhna.challenge.ui.main;

import com.mounacheikhna.challenge.api.TflApi;
import com.mounacheikhna.challenge.helpers.GoogleApiClientProvider;
import com.mounacheikhna.challenge.helpers.LocationRequester;
import com.mounacheikhna.challenge.helpers.PermissionManager;
import com.mounacheikhna.challenge.ui.main.stops.StopsPresenter;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import rx.schedulers.Schedulers;

public class StopsPresenterTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    private StopsPresenter stopsPresenter;

    @Mock private GoogleApiClientProvider googleApiClientProvider;
    @Mock private LocationRequester locationRequester;
    @Mock private TflApi tflApi;
    @Mock private PermissionRequester requester;
    @Mock private PermissionManager permissionManager;

    @Before
    public void setUp() throws Exception {
        stopsPresenter = new StopsPresenter(googleApiClientProvider, locationRequester, tflApi,
            requester, permissionManager, AndroidSchedulers.mainThread());
    }


}
