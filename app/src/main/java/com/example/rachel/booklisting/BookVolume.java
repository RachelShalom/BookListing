package com.example.rachel.booklisting;

/**
 * Created by Rachel on 07/07/2017.
 */

public class BookVolume {

    private String mTitle;
    private String mSubTitle;
    private String mAuthor;
    private String mPreview;
    private String mDescription;
    private String mLanguage;
    private String mBook_image;

    //Constructor
    public BookVolume(String title, String subTitle,String Author, String Preview,String description,String Language,String book_image){

        mTitle = title;
        mSubTitle = subTitle;
        mAuthor = Author;
        mPreview = Preview;
        mDescription = description;
        mLanguage =Language;
        mBook_image = book_image;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getSubTitle(){
        return mSubTitle;
    }
    public String getAuthor(){
        return mAuthor;
    }
    public String getPreview(){
        return mPreview;
    }

    public String getDescription(){
        return mDescription;
    }

    public String getLanguage(){
        return mLanguage;
    }
    public String getBookImage(){
        return mBook_image;
    }
}
