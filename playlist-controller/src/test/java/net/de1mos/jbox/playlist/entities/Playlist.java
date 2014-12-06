package net.de1mos.jbox.playlist.entities;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	private List<PlaylistItem> items;
	
	public Playlist() {
		items = new ArrayList<>();
	}

	public PlaylistItem addSong(Song song) {
		PlaylistItem playlistItem = new PlaylistItem(song);
		items.add(playlistItem);
		return playlistItem;
		
	}

	public int getSize() {
		return items.size();
	}

}
