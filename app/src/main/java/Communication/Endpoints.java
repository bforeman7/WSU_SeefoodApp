package Communication;

import android.content.Context;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import ImageModel.ImageBundle;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

public class Endpoints {
    private final String myUrl = "http://18.220.189.219/image";
   // private RequestQueue requestQueue;
    private Context context;
    private DefaultHttpClient mHttpClient;
    private ImageBundle imageBundle;
    private String myPath = "";

    public Endpoints(Context context) {
        this.context = context;
    }

    //    public void GetImage(){
    //        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, params,new Response.Listener<String>() {
    //                    @Override
    //                    public void onResponse(String response) {
    //
    //                    }
    //                }, new Response.ErrorListener() {
    //            @Override
    //            public void onErrorResponse(VolleyError error) {
    //
    //            }
    //        }) {
    //            @Override
    //            protected Map<String,String> getParams(){
    //                Map<String, String> params = new HashMap<String, String>();
    //                params.put("image", encodedString);
    //                params.put("filename", fileName);
    //                return params;
    //            }
    //        };

    // Add the request to the RequestQueue.
    //        requestQueue.add(stringRequest);
    //    }

    /**
     * The way I understand this is that I need to convert the bitmap to a string to be sent to the server.  This will open
     * a url http connection.  Then it will get the outputstream of the url connection and using a bufferedwriter it will
     * write out image to the server.  This seemed easier than volley because when i tried volley it was not allowing me
     * to send images or strings without using a hashmap or more.
     */
//    public void postImageBundleToServer(ImageBundle imageBundle) {
//        OutputStream out = null;
//        File myFile = new File(imageBundle.getsBitmapPath());
//
//        ContentBody cbFile = new FileBody(myFile, "image/PNG");
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//       // imageBundle.getImages().get(0).compress(Bitmap.CompressFormat.PNG, 90, bos);
//        byte[] data = bos.toByteArray();
//        String temp = Base64.encodeToString(data, Base64.DEFAULT);
//
////        try {
////            //FileOutputStream fos = new FileOutputStream(f);
////            fos.write(data);
////            fos.flush();
////            fos.close();
////        } catch (Exception e) {
////        }
//
//        try {
//
//            HttpPost httppost = new HttpPost(myUrl);
//
//            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//            multipartEntity.addPart("image", cbFile);
//            multipartEntity.addPart("time_taken", new StringBody("10/31/1900"));
//            httppost.setEntity(multipartEntity);
//
//            //mHttpClient.execute(httppost, new PhotoUploadResponseHandler());
//
//        } catch (Exception e) {
//
//        }
//
//        //            try {
//        //                URL url = new URL(myUrl);
//        //                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        //                out = new BufferedOutputStream(urlConnection.getOutputStream());
//        //
//        //                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//        //                writer.write(temp);
//        //                writer.flush();
//        //                writer.close();
//        //                out.close();
//        //
//        //                urlConnection.connect();
//        //            } catch (Exception e) {
//        //                System.out.println(e.getMessage());
//        //            }
//    }
//
//    //        imageBundle.getImages().get(0);
//    //        requestQueue  = Volley.newRequestQueue(context);
//    //        StringRequest postReq = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
//    //            @Override
//    //            public void onResponse(String response) {
//    //                try {
//    //
//    //                } catch (Exception e) {
//    //                    e.printStackTrace();
//    //                }
//    //            }
//    //        },
//    //                new Response.ErrorListener() {
//    //                    @Override
//    //                    public void onErrorResponse(VolleyError error) {
//    //                        // code here for error response
//    //                    }
//    //                }
//    //        );
//    //
//    //        requestQueue.add(postReq);

    public static void get() {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://18.220.189.219/")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    // do something wih the result
                    System.out.println(response.body().string());
                    System.out.println("We did it");
                }
            }
        });
    }


    public JSONObject postFile(ImageBundle image, int imageNumber) {

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        imageBundle = image;
        //this will get the specific image numbers path.
        myPath = imageBundle.getImages().get(imageNumber).getsFilePath();

        try {

            final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");
            File f = new File(myPath);

            RequestBody req = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("image","1114181904a.jpg", RequestBody.create(MEDIA_TYPE_JPEG, f))
                    .addFormDataPart("time_taken", "4-4-1321").build();

            Request request = new Request.Builder().url(myUrl).post(req).build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        // do something wih the result
                        System.out.println(response.body().string());
                        System.out.println("We did it");
                    }
                }
            });

            //Log.d("response", "uploadImage:"+response.body().string());

           // return new JSONObject(response.body().string());

       // } catch (UnknownHostException | UnsupportedEncodingException e) {
            //Log.e(Tag, "Error: " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

//        String result = "";
//        HttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(myUrl);
//        File file = new File(myPath);
//        MultipartEntity mpEntity = new MultipartEntity();
//        ContentBody cbFile = new FileBody(file, "image/png");
//        StringBody stringBody = null;
//        JSONObject responseObject = null;
//        try {
//            stringBody = new StringBody(imageNumber + "");
//            mpEntity.addPart("file", cbFile);
//            mpEntity.addPart("id", stringBody);
//            httpPost.setEntity(mpEntity);
//            System.out.println("executing request " + httpPost.getRequestLine());
//            HttpResponse response = httpClient.execute(httpPost);
//            HttpEntity resEntity = response.getEntity();
//            result = resEntity.toString();
//            responseObject = new JSONObject(result);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return responseObject;
    }
}


//    public static ImageBundle getSpecificNumberOfImagesFromServer(int nNumber,int nNumber2){
//
//    }
//
//    public static int getPrimaryKeyForNewImageRecord(){
//
//    }

