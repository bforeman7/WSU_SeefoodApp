package ActivityController;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import CustomViews.BaseView;
import CustomViews.ImageUploadView;
import test.hulbert.seefood.R;

public class ImageUploadActivity extends AppCompatActivity implements Controllable {

    private ArrayList<String> imagePaths;
    private BaseView imageUploadView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // we set the initial content view
        setContentView(R.layout.image_upload_final);
        //
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        imagePaths = getIntent().getStringArrayListExtra("imagePaths");
        imageUploadView = new ImageUploadView(this, view, this.getApplicationContext());
        ((ImageUploadView) imageUploadView).bindImages(imagePaths);
    }

    public void uploadImages(View view) {
        if(!imagePaths.isEmpty()) {
            new PostImages(){
                @Override public void onPostExecute(String result)
                {
                    Intent intent = new Intent(getApplicationContext(), SeefoodActivity.class);
                    intent.putStringArrayListExtra("imagePaths", imagePaths);
                    startActivityForResult(intent, 999);
                }

                @Override
                protected void onPreExecute() {
                    ((ImageUploadView) imageUploadView).showProgressBar();

                }

                @Override
                protected String doInBackground(String... params) {
                    for(int i=0;i<5;i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            }
            .execute("");
        }
        else {
            Toast.makeText(this, "You must select images before uploading",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void deleteImage(int index) {
        imagePaths.remove(index);
        updateView();
    }

    @Override
    public void updateView() {
        ((ImageUploadView) imageUploadView).bindImages(imagePaths);
    }

    /* This is the finish() method which is called when the user wants to exit the current activity i.e. clicked the back button. */
    @Override
    public void finish() {
        // Since this intent is now finished, we need to send the color selection choices back to the parent intent
        Intent data = new Intent();
        data.putStringArrayListExtra("imagePaths", imagePaths);
        setResult(RESULT_OK, data);
        super.finish();
    }


    /* This is the method that is called when the hardware back button is pressed. */
    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999 && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Failed to send images to server.", Toast.LENGTH_LONG).show();
        }
    }

    private abstract class PostImages extends AsyncTask<String, Void, String> {

    }
}
