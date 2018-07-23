package com.example.anuja.popularmoviesstagetwo.webservice;

public class YoutubeUtils {
    public static final String YOUTUBE_API_KEY = "";
    public static final String YOUTUBE_URL = "https://www.googleapis.com/";
    public static final String YOUTUBE_ENDPOINT_PARAM = "youtube/v3/videos/";
    public static final String YOUTUBE_PARAM_KEY = "key=";
    public static final String YOUTUBE_PARAM_ID = "id";
    public static final String YOUTUBE_PARAM_SNIPPET = "part=snippet";
    public static final String YOUTUBE_PATH = YOUTUBE_ENDPOINT_PARAM + "?" + YOUTUBE_PARAM_KEY + YOUTUBE_API_KEY + "&" + YOUTUBE_PARAM_SNIPPET;
}
