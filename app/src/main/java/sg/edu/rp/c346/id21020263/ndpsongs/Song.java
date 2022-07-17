package sg.edu.rp.c346.id21020263.ndpsongs;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singers;
    private String year;
    private String ratings;

    public Song(int id, String title, String singers, String year, String ratings) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.ratings = ratings;
    }

    public int getId() {return id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getSingers() {return singers;}

    public void setSingers(String singers) {this.singers = singers;}

    public String getYear() {return year;}

    public void setYear(String year) {this.year = year;}

    public String getRatings() {return ratings;}

    public void setRatings(String ratings) {this.ratings = ratings;}

}
