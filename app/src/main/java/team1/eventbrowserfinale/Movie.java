package team1.eventbrowserfinale;

/**
 * Created by Avneesh on 12/11/2016.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Model class for movie
 */
public class Movie {

    private String title;
    private String year;
    private String imdbId;
    private String type;
    private String posterUrl;

    /**
     *
     * @param jsonObject    {@link JSONObject} response, received in Volley success listener
     * @return  list of movies
     * @throws JSONException
     */
    public static List<Movie> parseJson(JSONObject jsonObject) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        // Check if the JSONObject has object with key "Search"
        if(jsonObject.has("results")){
            // Get JSONArray from JSONObject
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++){
                // Create new Movie object from each JSONObject in the JSONArray
                movies.add(new Movie(jsonArray.getJSONObject(i)));
            }
        }

        return movies;
    }

    /**
     * <p>Class constructor</p>
     * <p>Sample Movie JSONObject</p>
     * <pre>
     * {
     *  "Title": "Batman Begins",
     *  "Year": "2005",
     *  "imdbID": "tt0372784",
     *  "Type": "movie",
     *  "Poster": "https://images-na.ssl-images-amazon.com/images/M/MV5BNTM3OTc0MzM2OV5BMl5BanBnXkFtZTYwNzUwMTI3._V1_SX300.jpg"
     * }
     * </pre>
     * @param jsonObject    {@link JSONObject} from each item in the search result
     * @throws JSONException     when parser fails to parse the given JSON
     */
    private Movie(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("original_title")) this.setTitle(jsonObject.getString("original_title"));
        if(jsonObject.has("release_date")) this.setYear(jsonObject.getString("release_date"));
        if(jsonObject.has("id")) this.setImdbId(jsonObject.getString("id"));
        if(jsonObject.has("overview")) this.setType(jsonObject.getString("overview"));
        if(jsonObject.has("poster_path")) this.setPosterUrl("http://image.tmdb.org/t/p/w185"+jsonObject.getString("poster_path"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}

