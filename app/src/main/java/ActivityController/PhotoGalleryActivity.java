package ActivityController;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import CustomViews.GalleryView;
import ImageModel.ImageBundle;
import test.hulbert.seefood.R;

public class PhotoGalleryActivity extends AppCompatActivity implements Controllable {

    private ImageBundle imageBundle;
   // private ImageBundleView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void displayGallery(){

    }

    public void getSpecificNumberOfImagesFromServer(int nNumber1, int nNumber2){

    }

    public void viewImageDetails(View view){

    }

    public void viewNextPageOfGallery(View view){

    }

    @Override
    public void updateView(){

    }
}