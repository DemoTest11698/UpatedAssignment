package com.videostream.videoapp.service;

import com.videostream.videoapp.entity.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideoService {

    // Publish a video (Create or Update)
    Video saveVideo(Video video);

    // Soft delete a video by ID
    void delistVideo(String videoId);

    // Retrieve a video by ID (Load video metadata and content)
    Video loadVideo(String videoId);

    // Play a video (return mock content for now)
    String playVideo(String videoId);

    // List all videos with basic metadata (Title, Director, Main Actor, Genre, Running Time)
    List<Video> listAllVideos();

    // Search videos based on metadata criteria (e.g., Director)
    List<Video> searchVideos(String searchCriteria);

    // Get engagement statistics for a specific video (Impressions and Views)
    String getVideoEngagement(String videoId);



}
