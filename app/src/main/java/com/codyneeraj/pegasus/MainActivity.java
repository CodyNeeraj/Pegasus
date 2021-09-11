package com.codyneeraj.pegasus;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * The TwoActivities app contains two activities and sends messages
 * (intents) between them.
 */
public class MainActivity extends AppCompatActivity {

    EditText roll, name, course, contact;
    Uri imagePath;
    ImageView avatar;
    Button browse, upload;
    Bitmap bitmap;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference("testDB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Pegasus);
        setContentView(R.layout.activity_main);

        avatar = (ImageView) findViewById(R.id.img);
        upload = (Button) findViewById(R.id.upload);
        browse = (Button) findViewById(R.id.browse);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtofirebase();
            }
        });
    }

    private void SelectImage() {
        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagePath = data.getData();
            try {
//                InputStream inputStream = getContentResolver().openInputStream(imagePath);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                avatar.setImageBitmap(bitmap);
                avatar.setImageURI(imagePath);
            } catch (Exception ex) {
                toastMaker(ex.getMessage());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadtofirebase() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Data Uploader");
        dialog.show();

        name = (EditText) findViewById(R.id.t1);
        course = (EditText) findViewById(R.id.t2);
        contact = (EditText) findViewById(R.id.t3);
        roll = (EditText) findViewById(R.id.t4);

        UserInfoHolder obj = new UserInfoHolder(name.getText().toString(), contact.getText().toString(), course.getText().toString());
        root.child(roll.getText().toString()).setValue(obj);

        name.setText("");
        course.setText("");
        contact.setText("");
        roll.setText("");
        avatar.setImageResource(R.drawable.ic_launcher_background);
        dialog.dismiss();
        toastMaker("Uploaded");


//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference uploader = storage.getReference("img" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime()));

//        uploader.putFile(imagePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        dialog.dismiss();
//                        FirebaseDatabase db = FirebaseDatabase.getInstance();
//                        DatabaseReference root = db.getReference("users");
//
//                        UserInfoHolder obj = new UserInfoHolder(name.getText().toString(), contact.getText().toString(), course.getText().toString(), uri.toString());
//                        root.child(roll.getText().toString()).setValue(obj);
//
//                        name.setText("");
//                        course.setText("");
//                        contact.setText("");
//                        roll.setText("");
//                        avatar.setImageResource(R.drawable.ic_launcher_background);
//                        toastMaker("Uploaded");
//                    }
//                });
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                float percent = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                dialog.setMessage("Uploaded :" + (int) percent + " %");
//            }
//        });
    }

    private String getFileExtension(Uri mUri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
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

    private Toast toastMaker(String message) {
        return Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }
}
