package com.google;

import java.util.List;

class Playlist {
    private final String playlistName;
    private final List<Video> videos;

    public Playlist(String playlistName, List<Video> videos) {
        this.playlistName = playlistName;
        this.videos = videos;
    }
}
