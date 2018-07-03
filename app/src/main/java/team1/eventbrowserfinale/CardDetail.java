package team1.eventbrowserfinale;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CardDetail extends AppCompatActivity {
    TextView cardName;
    TextView cardDescription;
    ImageView cardPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    setContentView(R.layout.content_card_detail);
    cardName=(TextView)findViewById(R.id.card_name);
        cardDescription=(TextView)findViewById(R.id.card_description);
        cardPhoto=(ImageView)findViewById(R.id.card_poster);
        String title=getIntent().getStringExtra("name");
        String des=getIntent().getStringExtra("description");
        String im=getIntent().getStringExtra("poster");


        cardName.setText(title);
        cardDescription.setText(des);
        new DownloadImageTask(cardPhoto).execute(im);


    }

}


class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private ProgressDialog mDialog;
    private ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected void onPreExecute() {

       // mDialog = ProgressDialog.show(null,"Please wait...", "Retrieving data ...", true);
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", "image download error");
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        //set image of your imageview
        bmImage.setImageBitmap(result);
        //close
        //mDialog.dismiss();
    }
}