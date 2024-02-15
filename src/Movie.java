public class Movie {
    private String title;
    private String casts;
    private String directors;
    private String overview;
    private int runtime;
    private double userRating;

    public Movie(String title, String casts, String directors, String overview, int runtime, double userRating) {
        this.title = title;
        this.casts = casts;
        this.directors = directors;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }
    public String getTitle() {
        return title;
    }
    public String getCasts() {
        return casts;
    }
    public String getDirectors() {
        return directors;
    }
    public String getOverview() {
        return overview;
    }
    public int getRuntime() {
        return runtime;
    }
    public double getUserRating() {
        return userRating;
    }

    public String toString() {
        String text = "";
        text += "Title: " + title + "\n";
        text += "Runtime: " + runtime + " minutes\n";
        text += "Directed by: " + directors + "\n";
        text += "Cast: " + casts + "\n";
        text += "Overview: " + overview + "\n";
        text += "User Rating: " + userRating + "\n";
        return text;
    }
}
