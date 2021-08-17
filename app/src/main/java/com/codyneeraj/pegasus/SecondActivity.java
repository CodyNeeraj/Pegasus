package com.codyneeraj.pegasus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * SecondActivity defines the second activity in the app. It is
 * launched from an intent with a message, and sends an intent
 * back with a second message.
 */
public class SecondActivity extends AppCompatActivity {
    // Unique tag for the intent reply.
    public static final String CODE = "com.example.android.twoactivities.extra.REPLY";

    // EditText for the reply.
    private EditText mReply;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize view variables.
        mReply = findViewById(R.id.editText_second);

        // Get the intent that launched this activity, and the message in
        // the intent extra.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.CODE);

        // Put that message into the text_message TextView
        TextView textView = findViewById(R.id.text_message);
        textView.setText(message);
    }

    /**
     * Handles the onClick for the "Reply" button. Gets the message from the
     * second EditText, creates an intent, and returns that message back to
     * the main activity.
     *
     * @param view The view (Button) that was clicked.
     */
    public void returnReply(View view) {
        // Get the reply message from the edit text.
        String reply = mReply.getText().toString();
        System.out.println(reply);

        // Create a new intent for the reply, add the reply message to it
        // as an extra, set the intent result, and close the activity.
        Intent replyIntent = new Intent();


        //request code will get sent automatically as a parameter because the first
        // activity is sending intent just in favor of getting right intent for
        // receiving right intent too, since an acitivity can send many intent at
        // once a confusion can start, what this intent is for ?

        replyIntent.putExtra(CODE, reply);
        if (!(reply.isEmpty() | reply.length() == 0)) {
            setResult(RESULT_OK, replyIntent);
            finish();
        } else {
            Toast.makeText(this, "Message can't be left empty", Toast.LENGTH_SHORT).show();
        }
    }
}