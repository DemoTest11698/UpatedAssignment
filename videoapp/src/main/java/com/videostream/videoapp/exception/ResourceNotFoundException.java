package com.videostream.videoapp.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException()
    {
        super("Video not found");
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}

