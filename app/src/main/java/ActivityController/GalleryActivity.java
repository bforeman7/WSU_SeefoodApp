package ActivityController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Communication.Endpoints;
import CustomViews.GalleryView;
import CustomViews.ImageBundleView;
import CustomViews.SeefoodView;
import ImageModel.ImageBundle;
import test.hulbert.seefood.R;

public class GalleryActivity extends AppCompatActivity implements Controllable {
    private ImageBundle imageBundle;
    private SeefoodView BaseView;
    private ScrollView myScrollView;
    private LinearLayout myLinearLayout;
    private ImageView imageView[];
    private ArrayList<String> imagePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery_final);

        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        ImageBundleView galleryView = new GalleryView(this, view);
        imageBundle = new ImageBundle();

        JSONObject jsonResponses;
        jsonResponses = Endpoints.getImages(1, 1000);
        if(jsonResponses == null) {
            Toast.makeText(this, "Could not connect to the server.  Please try again when the server is running.", Toast.LENGTH_LONG).show();
            returnHome();
        }else {
            JSONArray jsonArray = null;
            try {
                jsonArray = jsonResponses.getJSONArray("images");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    imageBundle.addImageThroughJSON(jsonArray.getJSONObject(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ((GalleryView) galleryView).bindImageBundle(imageBundle);
        }

    }

    /*
    returns user to home screen and clears all other activates from activity stack
     */
    public void returnHome() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    public void updateConfidenceRating(int nRatingIncrease){

    }

    public void sendImagesToServer(View view){
    }

    @Override
    public void updateView(){

    }
}