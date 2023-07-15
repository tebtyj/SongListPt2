package sg.edu.rp.c346.id22011050.songlistpt2;

import androidx.annotation.NonNull;

public class Songs {
    private int id;
    private String title;
    private String singers;
    private int years;
    private int stars;

    public Songs(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title =title;
        this.singers = singers;
        this.years = year;
        this.stars=stars;
    }


    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getSingers() { return singers;}

    public int getYear() { return years;}

    public int getStars() { return stars;}

    public String toString() {
        String StarST = "";
        if(stars == 1)
        {
            StarST = "*";
        }
        else if(stars == 2)
        {
            StarST = "**";
        }
        else if(stars == 3)
        {
            StarST = "***";
        }
        else if(stars == 4)
        {
            StarST = "****";
        }
        else if(stars == 5)
        {
            StarST = "*****";
        }

        return title + "\n" + singers + " - " + years + "\n" + StarST;

    }

}
