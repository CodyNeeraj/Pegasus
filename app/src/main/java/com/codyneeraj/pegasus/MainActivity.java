package com.codyneeraj.pegasus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


/**
 * The TwoActivities app contains two activities and sends messages
 * (intents) between them.
 */
public class MainActivity extends AppCompatActivity {
    // Class name for Log tag
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // Unique tag required for the intent extra
    public static final String CODE = "com.example.android.twoactivities.extra.MESSAGE";
    // Unique tag for the intent reply
    public static final int INTENT_REQUEST = 1;

    // EditText view for the message
    private EditText mMessageEditText;
    // TextView for the reply header
    private TextView mReplyHeadTextView;
    // TextView for the reply body
    private TextView mReplyTextView;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all the view variables.
        mMessageEditText = findViewById(R.id.editText_main);
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
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

        if (!(message.isEmpty() | message.length() == 0)) {
            intent.putExtra(CODE, message);
            startActivityForResult(intent,INTENT_REQUEST);
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


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MainActivity.class.getSimpleName(), "onStart Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.class.getSimpleName(), "onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.class.getSimpleName(), "onDestroyed Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.class.getSimpleName(), "onPause Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.class.getSimpleName(), "onResume Called");
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        txt = (TextView) findViewById(R.id.Neeraj);
//        Log.d("Neeraj", "Error");
//    }
//
//    public void clicked(View view) {
//        txt.setText(R.string.onClick);
//        Toast.makeText(this, "Textview CLicked !", Toast.LENGTH_SHORT).show();
//    }

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

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // When the user click yes button then app will close
                finish();
            }
        });

        // Set the Negative button with No name OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // If user click no then dialog box is canceled.
                dialog.cancel();
            }
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the Alert Dialog box
        alertDialog.show();
    }
}