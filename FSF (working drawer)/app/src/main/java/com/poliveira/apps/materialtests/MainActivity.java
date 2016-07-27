package com.poliveira.apps.materialtests;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks {

    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    //DB stuff
    DBAdapter myDb;
    //--------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_topdrawer);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        /**
        //DB stuff
        openDB();
        populateListViewFromDB();
        registerListClickCallback();
        //---------------------------
        */
    }

    //nav drawer stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        android.app.Fragment objFragment = null;


        switch (position) {
            case 0:
                objFragment = new HomescreenFrag();
                Toast.makeText(this, position + ") 'Home' selected", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                objFragment = new AboutPageFrag();
                Toast.makeText(this,  position + ") 'About' selected", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                objFragment = new BrowsescreenFrag();
                Toast.makeText(this,  position + ") 'Browse Campaigns' selected", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                objFragment = new StartCampaignFrag();
                Toast.makeText(this,  position + ") 'Start A Campaign' selected", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                objFragment = new SupportPageFrag();
                Toast.makeText(this,  position + ") 'Help & Support' selected", Toast.LENGTH_SHORT).show();
                break;

        }
        // update the main content by replacing fragments
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .commit();
    }
    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }
    /////////////////////////////////////////////////////////////////////
    //Moe DB stuff

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //closeDB();
    }
    /**
    private void openDB() {
        myDb = new DBAdapter(this);
        myDb.open();
    }
    private void closeDB() {
        myDb.close();
    }

    //////////////////////////////////////////////////////////////////
    //Populating DB
    //TODO: edit DB population to match campign billboard use

    private void populateListViewFromDB() {
        Cursor cursor = myDb.getAllRows();

        // Allow activity to manage lifetime of the cursor.
        // DEPRECATED! Runs on the UI thread, OK for small/short queries.
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields:
        // BRANDON TODO: Might be able to add or subtract info IN DBAdapter and pass as single param here in main
        // ie. combine F & L name to one string then call here, or diff(end-start date) and only pass days remaining
        String[] fromFieldNames = new String[]
                {DBAdapter.KEY_recipFName, DBAdapter.KEY_recipLName, DBAdapter.KEY_previewImage, DBAdapter.KEY_goalAmount, DBAdapter.KEY_raised, DBAdapter.KEY_startDate, DBAdapter.KEY_endDate, DBAdapter.KEY_city, DBAdapter.KEY_state, DBAdapter.KEY_customURL};
        //BRANDON TODO: IDs are the ids associated with the empty TextViews on billboard to be filled in by DB INFO
        int[] toViewIDs = new int[]
                {R.id.kidName, R.id.kidPic, R.id.goal, R.id.amtRaised, R.id.timeRemaining, R.id.location, R.id.kidURL};

        // Create adapter to may columns of the DB onto elemesnt in the UI.
        SimpleCursorAdapter myCursorAdapter =
                new SimpleCursorAdapter(
                        this,		// Context
                        R.layout.campaign_billboard_layout,	// Row layout template
                        cursor,					// cursor (set of DB records to map)
                        fromFieldNames,			// DB Column names
                        toViewIDs				// View IDs to put information in
                );

        // Set the adapter for the list view
        ListView myList = (ListView) findViewById(R.id.listViewFromDB); // BRANDON TODO:find what layout this is to me = DONE
        myList.setAdapter(myCursorAdapter);
    }


    private void registerListClickCallback() {
        ListView myList = (ListView) findViewById(R.id.listViewFromDB);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long idInDB) {

                updateItemForId(idInDB);
                //displayToastForId(idInDB);
            }
        });
    }

    //BRANDON TODO:change feilds to match mine
    //TODO = DONE
    private void updateItemForId(long idInDB) {
        Cursor cursor = myDb.getRow(idInDB);
        if (cursor.moveToFirst()) {
            long idDB = cursor.getLong(DBAdapter.COL_ROWID);
            String dateCreatedStr = cursor.getString(DBAdapter.COL_dateCreated);
            String startDateStr = cursor.getString(DBAdapter.COL_startDate);
            String endDateStr = cursor.getString(DBAdapter.COL_endDate);
            String fnameStr = cursor.getString(DBAdapter.COL_recipFName);
            String lnameStr = cursor.getString(DBAdapter.COL_recipLName);
            int goalAmt = cursor.getInt(DBAdapter.COL_goalAmount);
            int raisedAmt = cursor.getInt(DBAdapter.COL_raised);
            String urlStr = cursor.getString(DBAdapter.COL_customURL);
            String cityStr = cursor.getString(DBAdapter.COL_city);
            String stateStr = cursor.getString(DBAdapter.COL_state);
            //BRANDON TODO: Image needs to be grabbed too
            //image img = cursor.getImg(dbAdapter.COL_previewImage);

            //Below 2 lines are old not sure if needed
            //favColour += "!";
            //myDb.updateRow(idInDB, name, studentNum, favColour);
        }
        cursor.close();
        populateListViewFromDB();
    }
    /**
    //BRANDON TODO: fix feilds for toast
    private void displayToastForId(long idInDB) {
        Cursor cursor = myDb.getRow(idInDB);
        if (cursor.moveToFirst()) {
            long idDB = cursor.getLong(DBAdapter.COL_ROWID);
            String name = cursor.getString(DBAdapter.COL_NAME);
            int studentNum = cursor.getInt(DBAdapter.COL_STUDENTNUM);
            String favColour = cursor.getString(DBAdapter.COL_FAVCOLOUR);

            String message = "ID: " + idDB + "\n"
                    + "Name: " + name + "\n"
                    + "Std#: " + studentNum + "\n"
                    + "FavColour: " + favColour;
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }
    */
}

