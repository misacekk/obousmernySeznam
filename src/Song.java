import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

class Song {
    private String title;
    private String artist;
    private String duration;
    private int playCount; // Sledování počtu přehrání pro bonusový úkol (m)

    public Song(String title, String artist, String duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.playCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void incrementPlayCount() {
        this.playCount++;
    }
}
