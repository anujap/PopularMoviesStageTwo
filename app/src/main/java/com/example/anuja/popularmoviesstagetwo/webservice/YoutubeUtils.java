package com.example.anuja.popularmoviesstagetwo.webservice;

public class YoutubeUtils {
    public static final String YOUTUBE_API_KEY = "AIzaSyADBc-DWliub9IWz-F6pcTeDI_OG3jlv9I";
    public static final String YOUTUBE_URL = "https://www.googleapis.com/";
    public static final String YOUTUBE_ENDPOINT_PARAM = "youtube/v3/videos/";
    public static final String YOUTUBE_PARAM_KEY = "key=";
    public static final String YOUTUBE_PARAM_ID = "id";
    public static final String YOUTUBE_PARAM_SNIPPET = "part=snippet";
    public static final String YOUTUBE_PATH = YOUTUBE_ENDPOINT_PARAM + "?" + YOUTUBE_PARAM_KEY + YOUTUBE_API_KEY + "&" + YOUTUBE_PARAM_SNIPPET;
}
