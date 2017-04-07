package id.co.imastudio.movieapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MovieModel> listMovie;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sambungin ke xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //inisialisasi recyclerview
        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        //membuat list baru
        listMovie = new ArrayList<>();

        ambilData();

        //coba menampilkan data dummy
//        for (int i = 0; i <= 11; i++) {
//            MovieModel movieLists = new MovieModel("Judul " + i, "8.9", "Mantab jiwa", "https://image.tmdb.org/t/p/w500/h2mhfbEBGABSHo2vXG1ECMKAJa7.jpg");
//            listMovie.add(movieLists);
//        }
        //menampilkan data dari internet

        //set adapter
//        adapter = new MovieAdapter(listMovie, this);
//
//        GridLayoutManager gridLayout;
//        gridLayout = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(gridLayout);
//
//        recyclerView.setAdapter(adapter);
    }

    private void ambilData() {
        //saatnya ambil data
        //kasih loading
        final ProgressDialog loading = ProgressDialog.show(this, "Loading Data", "Mohon bersabar",false,false);

        JsonObjectRequest ambildata = new JsonObjectRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/popular?api_key=b08e3495841838f530552c2b261e00b1&language=en-US&page=1", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //kalau berhasil ambil url
                try {
                    loading.dismiss();//hilangkan loading
                    JSONArray array = response.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject json = array.getJSONObject(i);
                        Log.i("JSON ",""+json); //nampilin info
                        MovieModel dataMovie = new MovieModel();
                        dataMovie.setRatingMovie(json.getString("vote_average"));
                        dataMovie.setPosterMovie("https://image.tmdb.org/t/p/w500"+json.getString("poster_path"));
                        dataMovie.setJudulMovie(json.getString("original_title"));
                        dataMovie.setDeskripsiMovie(json.getString("overview"));
                        listMovie.add(dataMovie);
                    }
                    setAdapter(listMovie);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("Errornya ",""+e);
                    Toast.makeText(MainActivity.this, "Errornya "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //kalau gagal
                Log.i("Errornya ",""+error);
                Toast.makeText(MainActivity.this, "Errornya "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //bikin antrian biar nggak langsung ngeload semua
        RequestQueue antrian = Volley.newRequestQueue(this);
        antrian.add(ambildata);
    }

    private void setAdapter(List<MovieModel> listMovie) {

        adapter = new MovieAdapter(listMovie, this);

        GridLayoutManager gridLayout;
        gridLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayout);

        recyclerView.setAdapter(adapter);
    }
}

   