package net.de1mos.jbox.webclient.viewmodels;

public class Song {

	private String title;
	
	public Song(String title, String artist) {
		super();
		this.title = title;
		this.artist = artist;
	}

	private String artist;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}
