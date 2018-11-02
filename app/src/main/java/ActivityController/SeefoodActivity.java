package ActivityController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import CustomViews.SeefoodView;
import ImageModel.Image;
import ImageModel.ImageBundle;
import test.hulbert.seefood.R;

public class SeefoodActivity extends AppCompatActivity implements Controllable {

    private ImageBundle imageBundle;
    private SeefoodView BaseView;
    private ScrollView myScrollView;
    private LinearLayout myLinearLayout;
    private ImageView imageView[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seefood);

        //need to set the scrollview and linear layout to variables
        displaySelectedImages();
    }

    /**
     * This will load multiple imageviews based on the number of images
     * in an imagebundle.  That way the user can load specific number of
     * images to the view at a time.
     * */
    private void displaySelectedImages(){
        for (int i = 0; i < imageBundle.getImages().size(); i++){
            imageView[i] = new ImageView(this);
        }
        for (int i = 0; i < imageBundle.getImages().size(); i++){
            myLinearLayout.addView(imageView[i],i);
            imageView[i].setImageBitmap(imageBundle.getImageByID(i));
        }
    }

    public void updateConfidenceRating(int nRatingIncrease){

    }

    public void sendImagesToServer(View view){

    }

    @Override
    public void updateView(){

    }
}
