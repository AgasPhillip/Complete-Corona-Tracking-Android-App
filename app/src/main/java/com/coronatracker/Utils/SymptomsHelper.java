package com.coronatracker.Utils;

public class SymptomsHelper {
    int image;
    String title, description;

    public SymptomsHelper(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
