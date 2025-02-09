package com.videostream.videoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.videostream.videoapp.controller.VideoController;
import com.videostream.videoapp.entity.Video;
import com.videostream.videoapp.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class VideoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VideoService videoService;

    @InjectMocks
    private VideoController videoController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        objectMapper = new ObjectMapper();  // Manually instantiate the ObjectMapper
        mockMvc = MockMvcBuilders.standaloneSetup(videoController).build();
    }

    // Helper method to generate a new Video with a random UUID
    private Video generateRandomVideo() {
        String randomId = UUID.randomUUID().toString();
        return new Video(
                randomId, "Test Video", "Test synopsis", "Test Director", "Test Cast",
                2023, "Action", 120, "ACTIVE", 1000, 500, "http://video.url"
        );
    }

    @Test
    void testPublishVideo() throws Exception {
        Video video = generateRandomVideo();

        when(videoService.saveVideo(any(Video.class))).thenReturn(video);

        mockMvc.perform(post("/videos/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(video)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(video.getId()))  // Verify dynamic ID
                .andExpect(jsonPath("$.title").value("Test Video"))
                .andExpect(jsonPath("$.director").value("Test Director"))
                .andExpect(jsonPath("$.cast").value("Test Cast"))
                .andExpect(jsonPath("$.yearOfRelease").value(2023))
                .andExpect(jsonPath("$.genre").value("Action"))
                .andExpect(jsonPath("$.runningTime").value(120))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.impressions").value(1000))
                .andExpect(jsonPath("$.views").value(500))
                .andExpect(jsonPath("$.videoUrl").value("http://video.url"));

        verify(videoService, times(1)).saveVideo(any(Video.class));
    }

    @Test
    void testDelistVideo() throws Exception {
        String videoId = UUID.randomUUID().toString();

        doNothing().when(videoService).delistVideo(videoId);

        mockMvc.perform(delete("/videos/delist/{id}", videoId))
                .andExpect(status().isOk());

        verify(videoService, times(1)).delistVideo(videoId);
    }

    @Test
    void testLoadVideo() throws Exception {
        String videoId = UUID.randomUUID().toString();
        Video video = generateRandomVideo();

        when(videoService.loadVideo(videoId)).thenReturn(video);

        mockMvc.perform(get("/videos/{id}", videoId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Video"))
                .andExpect(jsonPath("$.director").value("Test Director"))
                .andExpect(jsonPath("$.cast").value("Test Cast"))
                .andExpect(jsonPath("$.yearOfRelease").value(2023))
                .andExpect(jsonPath("$.genre").value("Action"))
                .andExpect(jsonPath("$.runningTime").value(120))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.impressions").value(1000))
                .andExpect(jsonPath("$.views").value(500))
                .andExpect(jsonPath("$.videoUrl").value("http://video.url"));

        verify(videoService, times(1)).loadVideo(videoId);
    }

    @Test
    void testPlayVideo() throws Exception {
        String videoId = UUID.randomUUID().toString();
        String expectedResponse = "Playing video: Test Video";

        when(videoService.playVideo(videoId)).thenReturn(expectedResponse);

        mockMvc.perform(get("/videos/play/{id}", videoId))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(videoService, times(1)).playVideo(videoId);
    }

    @Test
    void testListAllVideos() throws Exception {
        List<Video> videos = Arrays.asList(
                generateRandomVideo(),
                generateRandomVideo()
        );

        when(videoService.listAllVideos()).thenReturn(videos);

        mockMvc.perform(get("/videos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(videos.get(0).getTitle()))
                .andExpect(jsonPath("$[1].title").value(videos.get(1).getTitle()));

        verify(videoService, times(1)).listAllVideos();
    }

    @Test
    void testSearchVideos() throws Exception {
        String query = "Action";
        List<Video> videos = Arrays.asList(
                generateRandomVideo(),
                generateRandomVideo()
        );

        when(videoService.searchVideos(query)).thenReturn(videos);

        mockMvc.perform(get("/videos/search")
                        .param("query", query))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(videos.get(0).getTitle()))
                .andExpect(jsonPath("$[1].title").value(videos.get(1).getTitle()));

        verify(videoService, times(1)).searchVideos(query);
    }

    @Test
    void testGetVideoEngagement() throws Exception {
        String videoId = UUID.randomUUID().toString();
        String engagementStats = "Impressions: 1000, Views: 500";

        when(videoService.getVideoEngagement(videoId)).thenReturn(engagementStats);

        mockMvc.perform(get("/videos/statistics/{id}", videoId))
                .andExpect(status().isOk())
                .andExpect(content().string(engagementStats));

        verify(videoService, times(1)).getVideoEngagement(videoId);
    }
}