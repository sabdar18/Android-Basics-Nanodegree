package com.example.sabdar.project5.pojo;

public class Location {
    private String mTitle;
    private String mSubTitle;
    private double mRating;
    private String mDescription;
    private String mAddress;
    private String mPhone;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mImage = NO_IMAGE_PROVIDED;

    public Location(String title, String subTitle, double rating, String description, String address, String phone) {
        this.mTitle = title;
        this.mSubTitle = subTitle;
        this.mRating = rating;
        this.mDescription = description;
        this.mAddress = address;
        this.mPhone = phone;
    }

    public Location(String title, String subTitle, double rating, String description, String address, String phone, int image) {
        this.mTitle = title;
        this.mSubTitle = subTitle;
        this.mRating = rating;
        this.mDescription = description;
        this.mAddress = address;
        this.mPhone = phone;
        this.mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public int getImage() {
        return mImage;
    }

    public double getRating() {
        return mRating;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getPhone() {
        return mPhone;
    }

    public boolean hasImage() {
        return mImage != NO_IMAGE_PROVIDED;
    }
}
