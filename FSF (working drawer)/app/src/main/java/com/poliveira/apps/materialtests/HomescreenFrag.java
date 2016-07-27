/**
 * Created by Brandon on 5/18/2015.
 */

package com.poliveira.apps.materialtests;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomescreenFrag extends Fragment {
    Button button;
    View rootview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.homescreen_layout, container, false);
        return rootview;

    }

}