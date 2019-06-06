package com.yuval.reiss.richclicker;

public class Friend {

    private String mUsername;
    private String mEmail;
    private String mImage;
    private int mScore;
    private String mNotify_id;

    public Friend() {

    }
    public Friend(String username, String email, String image, int score, String notify_id) {
        mUsername = username;
        mEmail = email;
        mImage = image;
        mScore = score;
        this.mNotify_id = notify_id;

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

    public String getNotify_id() {
        return mNotify_id;
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

    public void setNotify_id(String mNotifyId) {
        this.mNotify_id = mNotifyId;
    }
}

