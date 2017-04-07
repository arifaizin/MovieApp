package id.co.imastudio.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    List<MovieModel> movieLists;
    Context context; // supaya bisa dipakai di kelas lain

    //buat constructor
    public MovieAdapter(List<MovieModel> movieLists, Context context) {
        this.movieLists = movieLists;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterMovie;
        TextView ratingMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            posterMovie = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            ratingMovie = (TextView) itemView.findViewById(R.id.textViewRating);
        }
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //nyambungin ke layout item
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.ViewHolder holder, final int position) {
        final MovieModel dataMovie = movieLists.get(position);

        Glide.with(context)
                .load(dataMovie.getPosterMovie())
                .centerCrop()
                .into(holder.posterMovie);

        holder.ratingMovie.setText(dataMovie.getRatingMovie());

        holder.posterMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Judul : "+dataMovie.getJudulMovie(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.posterMovie.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Deskripsi : "+dataMovie.getDeskripsiMovie(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        //banyaknya list
        return movieLists.size();
    }
}
