package com.example.homework2.models;

public class Photo extends Entertainment {

    public String id;
    public String albumId;
    public String title;
    public String url;
    public String thumbnailUrl;

    public Photo(String id, String albumId, String title, String url, String thumbnailUrl) {
        super(EntertainmentType.PHOTO);
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}