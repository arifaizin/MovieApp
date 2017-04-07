package id.co.imastudio.movieapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //membuat list baru
        listMovie = new ArrayList<>();

        //coba menampilkan data dummy
        for (int i = 0; i <= 11; i++) {
            MovieModel movieLists = new MovieModel("Judul " + i, "8.9", "Mantab jiwa", "https://image.tmdb.org/t/p/w500/h2mhfbEBGABSHo2vXG1ECMKAJa7.jpg");
            listMovie.add(movieLists);
        }

        //set adapter
        adapter = new MovieAdapter(listMovie, this);

        GridLayoutManager gridLayout;
        gridLayout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayout);

        recyclerView.setAdapter(adapter);
    }
}

   