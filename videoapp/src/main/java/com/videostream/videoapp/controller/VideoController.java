package com.videostream.videoapp.controller;

import com.videostream.videoapp.constant.URICONSTANT;
import com.videostream.videoapp.entity.Video;
import com.videostream.videoapp.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(URICONSTANT.BASE_PATH)
public class VideoController {

    private final VideoService videoService;
    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping(URICONSTANT.PUBLISH)
    public Video publishVideo(@RequestBody Video video) {
        System.out.println("URI for Publish: " + URICONSTANT.PUBLISH);
        logger.info("Publishing video: {}", video.getTitle());
        return videoService.saveVideo(video);
    }

    @DeleteMapping(URICONSTANT.DELIST)
    public void delistVideo(@PathVariable String id) {
        logger.info("Delisting video with ID: {}", id);
        videoService.delistVideo(id);
    }

    @GetMapping(URICONSTANT.LOAD)
    public Video loadVideo(@PathVariable String id) {
        logger.info("Loading video with ID: {}", id);
        return videoService.loadVideo(id);
    }

    @GetMapping(URICONSTANT.PLAY)
    public String playVideo(@PathVariable String id) {
        logger.info("Playing video with ID: {}", id);
        return videoService.playVideo(id);
    }

    @GetMapping()
    public List<Video> listAllVideos() {
        logger.info("Listing all videos.");
        return videoService.listAllVideos();
    }

    @GetMapping(URICONSTANT.SEARCH)
    public List<Video> searchVideos(@RequestParam String query) {
        logger.info("Searching for videos with query: {}", query);
        return videoService.searchVideos(query);
    }

    @GetMapping(URICONSTANT.STATISTICS)
    public String getVideoEngagement(@PathVariable String id) {
        logger.info("Getting engagement statistics for video with ID: {}", id);
        return videoService.getVideoEngagement(id);
    }
}
