package com.poliveira.apps.materialtests;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Brandon Fernandez on 5/27/2015.
 */

public class BrowsescreenFrag extends Fragment {
    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.browse_campaigns_layout, container, false);
        return rootview;

    }

}
