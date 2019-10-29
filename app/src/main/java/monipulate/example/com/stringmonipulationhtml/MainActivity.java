package monipulate.example.com.stringmonipulationhtml;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    int count=0;
    TextView txtView;
    String domain;
    String defaultString;
    String Name;
    ProgressDialog progressDialog;
    Pattern pat;
    Matcher mat;
    ImageView image;
    Button buttonStep1,buttonStep2,buttonStep3,buttonReset;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image_view1);


        // Locate the Button in activity_main.xml
        buttonStep1 = (Button) findViewById(R.id.button);

        buttonStep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count++;

                if (count == 1) {

                defaultString="<div class=\"carousel-poster\"><img src=\"http://cdn.posh24.se/images/:profile/0eadb0c57f35fdb62b53e0fb6641718d7\" alt=\"Ben Affleck\"/></div>";
                // Locate the TextView/ImageView in activity_main.xml
                txtView = (TextView) findViewById(R.id.textView);

               // Generate Url for Image

                pat=Pattern.compile("src=\"(.*?)\"");
                mat=pat.matcher(defaultString);

                while(mat.find())
                {
                    domain = mat.group(1);
                }

                new DownloadImage().execute(domain.toString());

                  // Generat Image Name

                       pat=Pattern.compile("alt=\"(.*?)\"");
                       mat= pat.matcher(defaultString);
                          while(mat.find())
                            {
                               Name = mat.group(1);
                             }
                              txtView.setText(Name);
                    Toast.makeText(MainActivity.this,
                            "First image", Toast.LENGTH_SHORT).show();
                    txtView.setTextColor(Color.parseColor("#a5331b"));

                } else if(count==2){


                    defaultString="<div class=\"carousel-poster\"><img src=\"http://cdn.posh24.se/images/:profile/c/90441\" alt=\"Ricky Martin\"/></div>";
                    // Locate the TextView/ImageView in activity_main.xml
                    txtView = (TextView) findViewById(R.id.textView);

                    // Generate Url for Image

                    pat=Pattern.compile("src=\"(.*?)\"");
                    mat=pat.matcher(defaultString);

                    while(mat.find())
                    {
                        domain = mat.group(1);
                    }

                    new DownloadImage().execute(domain.toString());

                    // Generat Image Name

                    pat=Pattern.compile("alt=\"(.*?)\"");
                    mat= pat.matcher(defaultString);
                    while(mat.find())
                    {
                        Name = mat.group(1);
                    }
                    txtView.setText(Name);
                    Toast.makeText(MainActivity.this,
                            "Second image", Toast.LENGTH_SHORT).show();
                    txtView.setTextColor(Color.parseColor("#f79106"));


                }else if(count==3){


                    defaultString="<div class=\"carousel-poster\"><img src=\"http://cdn.posh24.se/images/:profile/081e091efd98b96e82e81a8490a0fb4dd\" alt=\"Justin Bieber\"/></div>";
                    // Locate the TextView/ImageView in activity_main.xml
                    txtView = (TextView) findViewById(R.id.textView);

                    // Generate Url for Image

                    pat=Pattern.compile("src=\"(.*?)\"");
                    mat=pat.matcher(defaultString);

                    while(mat.find())
                    {
                        domain = mat.group(1);
                    }

                    new DownloadImage().execute(domain.toString());

                    // Generat Image Name

                    pat=Pattern.compile("alt=\"(.*?)\"");
                    mat= pat.matcher(defaultString);
                    while(mat.find())
                    {
                        Name = mat.group(1);
                    }
                    txtView.setText(Name);

                    Toast.makeText(MainActivity.this,
                            "Third image", Toast.LENGTH_SHORT).show();
                    txtView.setTextColor(Color.parseColor("#1ced07"));
                }

                else {

                    new DownloadImage().execute("");
                    Toast.makeText(MainActivity.this,
                            " Start Again ", Toast.LENGTH_LONG).show();
                    txtView.setText("Finished !");
                    txtView.setTextColor(Color.parseColor("#0615f7"));
                }
            }
        });
    }

    // DownloadImage AsyncTask
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Please wait...");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            image.setImageBitmap(result);
            // Close progressdialog
            mProgressDialog.dismiss();
        }
    }
}