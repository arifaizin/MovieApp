package id.co.imastudio.movieapp;

/**
 * Created by idn on 3/23/2017.
 */
public class MovieModel {
    private String judulMovie, ratingMovie, deskripsiMovie, posterMovie;

    public MovieModel() {
    }

    public MovieModel(String judulMovie, String ratingMovie, String deskripsiMovie,
                      String posterMovie) {
        this.judulMovie = judulMovie;
        this.ratingMovie = ratingMovie;
        this.deskripsiMovie = deskripsiMovie;
        this.posterMovie = posterMovie;
    }

    public String getJudulMovie() {
        return judulMovie;
    }

    public void setJudulMovie(String judulMovie) {
        this.judulMovie = judulMovie;
    }

    public String getRatingMovie() {
        return ratingMovie;
    }

    public void setRatingMovie(String ratingMovie) {
        this.ratingMovie = ratingMovie;
    }

    public String getDeskripsiMovie() {
        return deskripsiMovie;
    }

    public void setDeskripsiMovie(String deskripsiMovie) {
        this.deskripsiMovie = deskripsiMovie;
    }

    public String getPosterMovie() {
        return posterMovie;
    }

    public void setPosterMovie(String posterMovie) {
        this.posterMovie = posterMovie;
    }
}
