package com.cs3605.orderpicking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs3605.orderpicking.bluetooth.GlassClientBluetoothInterface;
import com.cs3605.orderpicking.createExperiment.CreateExperimentActivity;
import com.cs3605.orderpicking.newExperiment.ExperimentActivity;
import com.cs3605.orderpicking.newExperiment.NewExperimentClickListener;
import com.cs3605.orderpicking.newExperiment.NewExperimentDialogFragment;
import com.cs3605.orderpicking.savedExperiments.SavedExperimentsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NewExperimentClickListener {

    private static final String DIALOG_NEW_EXPERIMENT = "new_experiment_dialog";

    @BindView(R.id.new_experiment_button)
    Button newExpButton;

    @BindView(R.id.create_experiment_button)
    Button createExperimentsButton;

    @BindView(R.id.view_saved_experiments_button)
    Button viewSavedExperimentsButton;

    // TODO: Remove
    @BindView(R.id.send_to_glass_et)
    EditText glassMessageET;

    @BindView(R.id.send_to_glass_button)
    Button sendToGlassButton;

//    private GlassClientBluetoothInterface btInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupViews();

//        setupBT();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        btInterface.stop();
    }

    private void setupViews() {
        newExpButton.setOnClickListener(this);
        createExperimentsButton.setOnClickListener(this);
        viewSavedExperimentsButton.setOnClickListener(this);
        sendToGlassButton.setOnClickListener(this);
    }

//    private void setupBT() {
//        btInterface = new GlassClientBluetoothInterface(this);
//        btInterface.connectToGlass();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_experiment_button:
                NewExperimentDialogFragment.newInstance().show(getSupportFragmentManager(), DIALOG_NEW_EXPERIMENT);
                break;
            case R.id.create_experiment_button:
                startActivity(CreateExperimentActivity.newIntent(this));
                break;
            case R.id.view_saved_experiments_button:
                startActivity(SavedExperimentsActivity.newIntent(this));
                break;

//            case R.id.send_to_glass_button:
//                btInterface.sendString(glassMessageET.getText().toString().trim());
//                break;
        }
    }

    @Override
    public void onClick(int experimentId) {
        startActivity(ExperimentActivity.getIntent(this, experimentId));
    }
}
