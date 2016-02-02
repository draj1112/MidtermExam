package sidespell.tech.midtermexam.apis;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sidespell.tech.midtermexam.entities.Album;
import sidespell.tech.midtermexam.utils.HttpUtils;

/**
 * Created by danica12 on 2/2/2016.
 */
public class AlbumApi {
    public static final String BASE_URL     = "http://ws.audioscrobbler.com/2.0/";
    //public static final String IMG_BASE_URL = "http://openweathermap.org/img/w/";

    //public static final int SUCCESS_CODE = 200;
    public static final String PARAM_METHOD = "method";
    public static final String PARAM_ALBUMNAME   = "album-name";
    public static final String PARAM_LIMIT    = "limit";
    public static final String PARAM_PAGE   = "page";
    public static final String PARAM_API_KEY = "appId";

    private static final String OWM_ALBUM_MATCHES   = "albummatches";
    private static final String OWM_NAME        = "name";
    private static final String OWM_ARTIST       = "artist";
    private static final String OWM_IMAGE        = "image";

    public static ArrayList<Album> getAlbum(Uri uri, @NonNull String requestMethod) {
        String json = HttpUtils.getResponse(uri, requestMethod);

        if (TextUtils.isEmpty(json)) {
            return null;
        }

        // Here we will now parse the json response and convert it into a Weather object.

        ArrayList<Album> albums = new ArrayList<>();
        String name;
        String icon;
        String artist;

        try {
            // 1) Convert the json string response into an actual JSON Object
            JSONObject jsonObject = new JSONObject(json);

            // 2) Get the status code
            //int statusCode = jsonObject.getInt(OWM_CODE);

            // 3) Check if the HTTP Status Code if it's valid or not
            //if (statusCode == SUCCESS_CODE) {
                // 4) Retrieve the individual bits of data that we need for our Weather model.
                // 5) Get the city name from the base jsonObject
            JSONArray albummatches = jsonObject.getJSONArray(OWM_ALBUM_MATCHES);
            int size = albummatches.length();

            for (int i = 0; i < size; i++) {
                JSONObject album = albummatches.getJSONObject(i);
                name = album.getString(OWM_NAME);
                icon = album.getString(OWM_IMAGE);
                artist = album.getString(OWM_ARTIST);

                Album w = new Album();
                w.setmAlbumArtist(artist);
                w.setmAlbumName(name);

                //Bitmap imageBitmap = HttpUtils.getImageBitmap(w.setmIconSrc(icon));
                //w.setmAlbumIcon(imageBitmap);

                albums.add(w);
            }

                // 10) Get the actual weather icon


                // 11) Return our weather data
                return albums;
            /*} else {
                return null;
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
