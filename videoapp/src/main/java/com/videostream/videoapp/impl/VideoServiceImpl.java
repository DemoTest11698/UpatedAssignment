package com.videostream.videoapp.impl;

import com.videostream.videoapp.entity.Video;
import com.videostream.videoapp.entity.VideoStatus;
import com.videostream.videoapp.exception.ResourceNotFoundException;
import com.videostream.videoapp.exception.DuplicateUUIDException;
import com.videostream.videoapp.repository.VideoRepository;
import com.videostream.videoapp.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Value("${video.storage.path}")
    private String videoStoragePath;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public Video saveVideo(Video video) {
        String randomVideoId = UUID.randomUUID().toString();
        // Check for potential UUID collision (very rare)
        Optional<Video> existingVideo = videoRepository.findById(randomVideoId);

        if (existingVideo.isPresent()) {
            logger.error("UUID collision detected for video: {}", video.getTitle());
            throw new DuplicateUUIDException("UUID collision occurred for video: " + video.getTitle());
        }

        video.setId(randomVideoId);
        video.setStatus(VideoStatus.ACTIVE);  // Default status is ACTIVE
        logger.info("Saving video with title: {}", video.getTitle());
        return videoRepository.save(video);
    }

    @Override
    public void delistVideo(String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        if (video.isPresent()) {
            video.get().setStatus(VideoStatus.INACTIVE);  // Set status to INACTIVE using the enum
            videoRepository.save(video.get());
            logger.info("Video with ID {} has been delisted", videoId);
        } else {
            throw new ResourceNotFoundException("Video not found with ID: " + videoId);
        }
    }

    @Override
    public Video loadVideo(String videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + videoId));
    }

    @Override
    public String playVideo(String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        return video.map(v -> "Now playing video: " + v.getTitle() + " - Mock Content")
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + videoId));
    }

    @Override
    public List<Video> listAllVideos() {
        logger.info("Listing all videos.");
        return videoRepository.findAll();
    }

    @Override
    public List<Video> searchVideos(String searchCriteria) {
        Specification<Video> spec = VideoRepository.hasSearchCriteria(searchCriteria);
        return videoRepository.findAll(spec);
    }

    @Override
    public String getVideoEngagement(String videoId) {
        Optional<Video> video = videoRepository.findById(videoId);
        return video.map(v -> "Impressions: " + v.getImpressions() + ", Views: " + v.getViews())
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with ID: " + videoId));
    }
}
