package com.videostream.videoapp.impl;

import com.videostream.videoapp.entity.Video;
import com.videostream.videoapp.exception.ResourceNotFoundException;
import com.videostream.videoapp.repository.VideoRepository;
import com.videostream.videoapp.service.VideoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Value("${video.storage.path}")
    private String videoStoragePath;


    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    // Save or update a video (Publish or Edit)
    @Override
    public Video saveVideo(Video video) {
        String randomVideoId= UUID.randomUUID().toString();
        video.setId(randomVideoId);
        return videoRepository.save(video);
    }

    // Soft delete a video by changing its "active" status
    @Override
    public void delistVideo(String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        video.ifPresent(v -> {
            v.setStatus("InActive");  // Soft delete by marking as inactive
            videoRepository.save(v);
        });
    }

    // Retrieve a video and its metadata
    @Override
    public Video loadVideo(String videoId) {
        return videoRepository.findById(videoId).orElseThrow(()->new ResourceNotFoundException("Video not found with given id"));
    }

    // Play a video – return a mock content string
    @Override
    public String playVideo(String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        return video.map(v -> "Now playing video: " + v.getTitle() + " - Mock Content").orElse("Video not found.");
    }

    // List all videos with metadata (Title, Director, Main Actor, Genre, Running Time)
    @Override
    public List<Video> listAllVideos() {
        return videoRepository.findAll();
    }

    // Search for videos based on criteria (e.g., Director, Genre)
    @Override
    public List<Video> searchVideos(String searchCriteria) {
        Specification<Video> spec = VideoRepository.hasSearchCriteria(searchCriteria);
        return videoRepository.findAll(spec);
    }

    // Retrieve engagement statistics (Impressions and Views)
    @Override
    public String getVideoEngagement(String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isPresent()) {
            int impressions = video.get().getImpressions();
            int views = video.get().getViews();
            return "Impressions: " + impressions + ", Views: " + views;
        } else {
            return "Video not found.";
        }
    }
}
