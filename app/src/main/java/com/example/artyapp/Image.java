package com.example.artyapp;

public class Image {
    private int id;
    private String imageName;
    private String imageDescription;
    private String image;
    private String username;

    // Constructor
    public Image(int id,String image,String imageName, String Description,  String username) {
        this.id = id;
        this.image = image;
        this.imageName = imageName;
        this.imageDescription = Description;
        this.username = username;

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
