package com.videostream.videoapp.entity;

import jakarta.persistence.*;

@Entity
public class Video {

    @Id
    private String id;

    public Video(String id, String title, String synopsis, String director, String cast, int yearOfRelease, String genre, int runningTime, VideoStatus videoStatus, int impressions, int views, String videoUrl) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.yearOfRelease = yearOfRelease;
        this.genre = genre;
        this.runningTime = runningTime;
        this.videoStatus = videoStatus;
        this.impressions = impressions;
        this.views = views;
        this.videoUrl = videoUrl;
    }

    public Video() {
    }

    private String title;
    private String synopsis;
    private String director;
    private String cast;
    private int yearOfRelease;
    private String genre;
    private int runningTime; // in minutes
    @Enumerated(EnumType.STRING)
    private VideoStatus videoStatus; // Enum for Video Status (ACTIVE, INACTIVE)
    private int impressions;
    private int views;
    @Column(unique = true)
    private String videoUrl;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public VideoStatus getStatus() {
        return videoStatus;
    }

    public void setStatus(VideoStatus videoStatus) {
        this.videoStatus = videoStatus;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
