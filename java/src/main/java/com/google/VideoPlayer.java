package com.google;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private Video currentVideo = null;
  private boolean paused = false;
  private List<String> playlists = new ArrayList<>();

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
      System.out.println("Here's a list of all available videos:");

      List<Video> videos = videoLibrary.getVideos();
      videos.sort(Comparator.comparing(Video::getTitle));

      for (Video video : videos) {
          System.out.printf("%s (%s) %s%n", video.getTitle(), video.getVideoId(), video.getTags().toString().replace(",", ""));
      }
  }

  public void playVideo(String videoId) {
      if (videoLibrary.getVideo(videoId) != null) {
          if (currentVideo != null) {
              System.out.printf("Stopping video: %s %n", currentVideo.getTitle());
              currentVideo = null;
          }
          currentVideo = videoLibrary.getVideo(videoId);

          paused = false;
          System.out.printf("Playing video: %s %n", currentVideo.getTitle());
      } else {
          System.out.println("Cannot play video: Video does not exist");
      }
  }

  public void stopVideo() {
      if (currentVideo != null) {
          System.out.printf("Stopping video: %s %n", currentVideo.getTitle());
          currentVideo = null;
      } else {
          System.out.println("Cannot stop video: No video is currently playing");
      }
  }

  public void playRandomVideo() {
      Random rand = new Random();
      Video randVideo = videoLibrary.getVideos().get(rand.nextInt(videoLibrary.getVideos().size()));
      playVideo(randVideo.getVideoId());
  }

  public void pauseVideo() {
      if (paused && currentVideo != null) {
          System.out.printf("Video already paused: %s %n", currentVideo.getTitle());
      } else {
          if (currentVideo != null) {
              System.out.printf("Pausing video: %s %n", currentVideo.getTitle());
              paused = true;
          } else {
              System.out.println("Cannot pause video: No video is currently playing");
          }
      }
  }

  public void continueVideo() {
      if (currentVideo != null) {
          if (paused) {
              paused = false;
              System.out.printf("Continuing video: %s %n", currentVideo.getTitle());
          } else {
              System.out.println("Cannot continue video: Video is not paused");
          }
      } else {
          System.out.println("Cannot continue video: No video is currently playing");
      }
  }

  public void showPlaying() {
      if (currentVideo != null) {
          if (paused) {
              System.out.printf("Currently playing: %s (%s) %s - PAUSED%n", currentVideo.getTitle(), currentVideo.getVideoId(), currentVideo.getTags().toString().replace(",", ""));
          } else {
              System.out.printf("Currently playing: %s (%s) %s%n", currentVideo.getTitle(), currentVideo.getVideoId(), currentVideo.getTags().toString().replace(",", ""));
          }
      } else {
          System.out.println("No video is currently playing");
      }
  }

  public void createPlaylist(String playlistName) {
      getPlaylists();

      if (validPlaylist(playlistName)) {
          try {
              FileWriter myWriter = new FileWriter(this.getClass().getResource("/playlists.txt").getFile());
              myWriter.write(playlistName);
              myWriter.close();
              System.out.printf("Successfully created new playlist: %s", playlistName);
          } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
          }
      }
  }

  public void getPlaylists() {
      try {
          File file = new File(this.getClass().getResource("/playlists.txt").getFile());

          Scanner scanner = new Scanner(file);
          while (scanner.hasNextLine()) {
              String line = scanner.nextLine();
              playlists.add(line);
          }
      } catch (FileNotFoundException e) {
          System.out.println("Couldn't find playlists.txt");
          e.printStackTrace();
      }
  }

  public boolean validPlaylist(String playlistName) {
      boolean valid = true;

      for (String playlist : playlists) {
          if (playlist.equalsIgnoreCase(playlistName)) {
              System.out.println("Cannot create playlist: A playlist with the same name already exists");
              valid = false;
              break;
          } else {
              valid = true;
          }
      }

      return valid;
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
      System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}