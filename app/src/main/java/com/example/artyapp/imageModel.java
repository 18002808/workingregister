package com.example.artyapp;

public class imageModel {
    private int id;
    private String imageName;
    private String imageDescription;
    private String image;

    // Constructor
    public imageModel(String imageName, String Description, int id, String image) {
        this.id = id;
        this.imageName = imageName;
        this.imageDescription = Description;
        this.image = image;

    }
    public String getImage() {
        return image;
    }

    // Getter and Setter
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }


}
