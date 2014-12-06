package net.de1mos.jbox.playlist.entities;

public class Song {

	private String artist;
	private String title;
	private String duration;

	public Song(String artist, String title, String duration) {
		this.artist = artist;
		this.title = title;
		this.duration = duration;
	}

	public String getArtist() {
		return artist;
	}

	public String getTitle() {
		return title;
	}

	public String getDuration() {
		return duration;
	}
	
}
