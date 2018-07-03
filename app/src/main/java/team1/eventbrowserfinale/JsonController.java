package team1.eventbrowserfinale;

/**
 * Created by Avneesh on 12/11/2016.
 */


import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import team1.eventbrowserfinale.App;
import team1.eventbrowserfinale.Movie;
import team1.eventbrowserfinale.JsonRequest;
import team1.eventbrowserfinale.VolleySingleton;

import java.util.List;



/**
 * <p> Provides interface between {@link android.app.Activity} and {@link com.android.volley.toolbox.Volley} </p>
 */
public class JsonController {

    private final int TAG = 100;

    private OnResponseListener responseListener;

    /**
     *
     * @param responseListener  {@link OnResponseListener}
     */
    public JsonController(OnResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    /**
     * Adds request to volley request queue
     * @param query query term for search
     */
    public void sendRequest(String query){

        // Request Method
        int method = Request.Method.GET;

        // Url with GET parameters
        String url = "http://api.themoviedb.org/3/search/movie?query=" + Uri.encode(query) + "&api_key=e8dfaccac9b18b49be51232e4dc3879c";

        // Create new request using JsonRequest
        JsonRequest request
                = new JsonRequest(
                method,
                url,
                new Response.Listener<List<Movie>>() {
                    @Override
                    public void onResponse(List<Movie> movies) {
                        responseListener.onSuccess(movies);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseListener.onFailure(error.getMessage());
                    }
                }
        );

        // Add tag to request
        request.setTag(TAG);

        // Get RequestQueue from VolleySingleton
        VolleySingleton.getInstance(App.getContext()).addToRequestQueue(request);
    }

    /**
     * <p>Cancels all request pending in request queue,</p>
     * <p> There is no way to control the request already processed</p>
     */
    public void cancelAllRequests() {
        VolleySingleton.getInstance(App.getContext()).cancelAllRequests(TAG);
    }

    /**
     *  Interface to communicate between {@link android.app.Activity} and {@link JsonRequest}
     *  <p>Object available in {@link JsonRequest} and implemented in {@link team1.eventbrowserfinale.MainActivity}</p>
     */
    public interface OnResponseListener {
        void onSuccess(List<Movie> movies);
        void onFailure(String errorMessage);
    }

}