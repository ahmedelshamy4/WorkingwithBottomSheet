package com.example.ahmed.workingwithbottomsheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    Button buttonBottomSheet, buttonBottomDaialog, buttonBottomDialogFragment;
    LinearLayout linearLayout;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        //Persistent Bottom Sheet.
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        buttonBottomSheet.setText("Close sheet");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        buttonBottomSheet.setText("Expand sheet");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    private void initViews() {
        buttonBottomSheet = findViewById(R.id.btn_bottom_sheet);
        buttonBottomDaialog = findViewById(R.id.btn_bottom_sheet_dialog);
        buttonBottomDialogFragment = findViewById(R.id.btn_bottom_sheet_dialog_fragment);
        linearLayout = findViewById(R.id.bottom_sheet);

        buttonBottomSheet.setOnClickListener(this);
        buttonBottomDaialog.setOnClickListener(this);
        buttonBottomDialogFragment.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_bottom_sheet) {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                buttonBottomSheet.setText("Close sheet");
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                buttonBottomSheet.setText("Expand sheet");
            }
// showing bottom sheet dialog
        } else if (v.getId() == R.id.btn_bottom_sheet_dialog) {
            View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
            BottomSheetDialog dialog = new BottomSheetDialog(this);
            dialog.setContentView(view);
            dialog.show();
            /**
             * showing bottom sheet dialog fragment
             * same layout is used in both dialog and dialog fragment
             */
        } else if (v.getId() == R.id.btn_bottom_sheet_dialog_fragment) {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
           // bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        }
    }
}
