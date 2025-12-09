import java.util.List;

public class MovieShelf {
    private String category;
    private List<Movie> movies;

    @Override
    public String toString() {
        return movies + "\n";
    }

    public MovieShelf(List<Movie> movies, String category) {
        this.movies = movies;
        this.category = category;
    }

    public List<Movie> getMovies() {
        return movies;
    }


    public Movie getMovieByEidr(String eidr) {
        for (Movie m : movies) {
            if (m.getEidr().equals(eidr)) {
                return m;
            }
        }
        System.out.println("Film ei leitud");
        return null;
    }


    public String getCategory() {
        return category;
    }

    public void addMovie(Movie movie) {
        if (category.equals(movie.getKategooria())) {
            movies.add(movie);
        } else {
            throw new IllegalArgumentException("Film kuulub teise kategooriasse");
        }
    }
}
