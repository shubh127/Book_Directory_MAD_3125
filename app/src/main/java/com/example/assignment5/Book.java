package com.example.assignment5;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String title;
    private int coverImgId;
    private String authorName;
    private String publisherName;
    private int copiesAvailable;

    public Book(String title, int coverImgId, String authorName, String publisherName, int copiesAvailable) {
        this.title = title;
        this.coverImgId = coverImgId;
        this.authorName = authorName;
        this.publisherName = publisherName;
        this.copiesAvailable = copiesAvailable;
    }

    protected Book(Parcel in) {
        title = in.readString();
        coverImgId = in.readInt();
        authorName = in.readString();
        publisherName = in.readString();
        copiesAvailable = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoverImgId() {
        return coverImgId;
    }

    public void setCoverImgId(int coverImgId) {
        this.coverImgId = coverImgId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(coverImgId);
        parcel.writeString(authorName);
        parcel.writeString(publisherName);
        parcel.writeInt(copiesAvailable);
    }
}
