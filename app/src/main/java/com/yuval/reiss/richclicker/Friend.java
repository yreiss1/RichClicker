package com.yuval.reiss.richclicker;

public class Friend {

    private String mUsername;
    private String mEmail;
    private String mImage;
    private int mScore;

    public Friend() {

    }
    public Friend(String username, String email, String image, int score) {
        mUsername = username;
        mEmail = email;
        mImage = image;
        mScore = score;

    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getImage() {
        return mImage;
    }

    public int getScore() {
        return mScore;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }
}

