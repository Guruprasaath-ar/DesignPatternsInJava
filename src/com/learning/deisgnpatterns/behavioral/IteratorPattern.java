package com.learning.deisgnpatterns.behavioral;

import java.util.*;

/**
 * ITERATOR DESIGN PATTERN - JAVA IMPLEMENTATION
 *
 * Intent:
 * Provide a way to access elements of a collection sequentially 
 * without exposing the underlying representation.
 *
 * Real-World Analogy:
 * Think of a remote control's "next" button when scrolling through a YouTube playlist —
 * you don't need to know how the playlist is stored; you just navigate it.
 */

// Simple Video class (element in collection)
class Video {
    private String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}

// Playlist abstraction (Aggregate)
interface Playlist {
    PlaylistIterator createIterator();  // Returns an iterator to traverse videos
}

// Concrete playlist class implementing the collection logic
class YoutubePlaylist implements Playlist {
    private List<Video> videos;

    public YoutubePlaylist() {
        videos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    /**
     * Factory method to create the iterator for this playlist.
     */
    public PlaylistIterator createIterator() {
        return new YoutubePlaylistIterator(videos);
    }
}

// Iterator interface
interface PlaylistIterator {
    boolean hasNext();
    Video next();
}

// Concrete iterator implementation for YoutubePlaylist
class YoutubePlaylistIterator implements PlaylistIterator {

    private int position;
    private List<Video> videos;

    public YoutubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
        this.position = 0;  // Start from first video
    }

    /**
     * Returns true if there is a next element.
     */
    public boolean hasNext() {
        return position < videos.size();
    }

    /**
     * Returns the next video and moves the pointer forward.
     */
    public Video next() {
        Video video = videos.get(position);
        position++;
        return video;
    }
}

// Client code
public class IteratorPattern {

    public static void main(String[] args) {
        
        YoutubePlaylist lld = new YoutubePlaylist();
        lld.addVideo(new Video("Introduction to LLD"));
        lld.addVideo(new Video("Creational Patterns"));

        PlaylistIterator itr = lld.createIterator();

        // Using the iterator to loop through videos
        while (itr.hasNext()) {
            System.out.println(itr.next().getTitle());
        }
    }
}
