package com.codyneeraj.pegasus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;


/**
 * The TwoActivities app contains two activities and sends messages
 * (intents) between them.
 */
public class MainActivity extends AppCompatActivity {
    // Unique tag required for the intent extra
    public static final String CODE = "com.example.android.twoactivities.extra.MESSAGE";
    // Unique tag for the intent reply
    public static final int INTENT_REQUEST = 1;
    // Class name for Log tag

    // the above code snippet will not change the layout such that the bottom
    // components get revert back to normal state after pressing or getting
    // both the navigation bar and StatusBar back

    int UIforHiding = View.SYSTEM_UI_FLAG_IMMERSIVE
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN;
    int UIforShowing = 0;
    boolean SameAsInRsrcs;
    // EditText view for the message
    private EditText mMessageEditText;
    // TextView for the reply header
    private TextView mReplyHeadTextView;
    // TextView for the reply body
    private TextView mReplyTextView;
    private Switch themetoggler;
    private Button barBtn;
    private ActionBar bar;
    private View decorView;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Pegasus);
        setContentView(R.layout.activity_main);

        // Initialize all the view variables.
        bar = getSupportActionBar();
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
        barBtn = findViewById(R.id.hideMainBar);
        themetoggler = findViewById(R.id.switch1);
        decorView = getWindow().getDecorView();
        themetoggler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (themetoggler.isChecked()) {
//                    setTheme(R.style.DarkTheme);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                if (!themetoggler.isChecked()) {
                    toastMaker("switch disabled").show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    /**
     * Handles the onClick for the "Send" button. Gets the value of the main EditText,
     * creates an intent, and launches the second activity with that intent.
     * <p>
     * The return intent from the second activity is onActivityResult().
     *
     * @param view The view (Button) that was clicked.
     */
    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();

        if (!message.trim().isEmpty()) {
            intent.putExtra(CODE, message);
            startActivityForResult(intent, INTENT_REQUEST);
            //startActivity(intent);
        } else {
            Toast.makeText(this, "Message can't be left empty", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Handles the data in the return intent from SecondActivity.
     *
     * @param requestCode Code for the SecondActivity request.
     * @param resultCode  Code that comes back from SecondActivity.
     * @param data        Intent data sent back from SecondActivity.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Test for the right intent reply.
        if (requestCode == INTENT_REQUEST) {
            // Test to make sure the intent reply result was good.
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.CODE);

                // Make the reply head visible.
                mReplyHeadTextView.setVisibility(View.VISIBLE);

                // Set the reply and make it visible.
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    // Declare the onBackPressed method when the back button is presse this method will call
    @Override
    public void onBackPressed() {

        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Do you really want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain on screem
        builder.setCancelable(false);

        // Set the positive button with yes name OnClickListener method is use of DialogInterface interface.

        builder.setPositiveButton("Yes", (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        // Set the Negative button with No name OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }

    public void hideComponents(View view) {
        System.out.println(getWindow().getStatusBarColor());
        getWindow().setStatusBarColor(getResources().getColor(R.color.teal_200));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.teal_200));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.purple_200));
        getWindow().setStatusBarColor(getResources().getColor(R.color.purple_200));
        SameAsInRsrcs = getWindow().getStatusBarColor() == getResources().getColor(R.color.purple_200);
    }

    private Toast toastMaker(String message) {
        return Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }
}
