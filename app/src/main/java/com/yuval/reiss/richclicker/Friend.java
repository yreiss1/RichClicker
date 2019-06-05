package com.yuval.reiss.richclicker;

public class Friend {

    private String mUsername;
    private String mEmail;
    private String mImage;

    public Friend() {

    }
    public Friend(String username, String email, String image) {
        mUsername = username;
        mEmail = email;
        mImage = image;
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

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public void setImage(String mImage) {
        this.mImage = mImage;
    }
}
