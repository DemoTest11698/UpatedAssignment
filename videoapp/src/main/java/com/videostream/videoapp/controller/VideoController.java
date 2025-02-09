package com.videostream.videoapp.controller;

import com.videostream.videoapp.entity.Video;
import com.videostream.videoapp.service.VideoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // Publish or Edit a video
    @PostMapping("/publish")
    public Video publishVideo(@RequestBody Video video) {

        return videoService.saveVideo(video);
    }

    // Soft delete (delist) a video
    @DeleteMapping("/delist/{id}")
    public void delistVideo(@PathVariable String id) {
        videoService.delistVideo(id);
    }

    // Load a video (metadata + content)
    @GetMapping("/{id}")
    public Video loadVideo(@PathVariable String id) {
        return videoService.loadVideo(id);
    }

    // Play a video (mock content)
    @GetMapping("/play/{id}")
    public String playVideo(@PathVariable String id) {
        return videoService.playVideo(id);
    }

    // List all available videos
    @GetMapping
    public List<Video> listAllVideos() {
        return videoService.listAllVideos();
    }

    // Search for videos (by Director, Genre, etc.)
    @GetMapping("/search")
    public List<Video> searchVideos(@RequestParam String query) {
        return videoService.searchVideos(query);
    }

    // Get engagement statistics (Impressions, Views)
    @GetMapping("/statistics/{id}")
    public String getVideoEngagement(@PathVariable String id) {
        return videoService.getVideoEngagement(id);
    }
}
