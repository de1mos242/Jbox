package net.de1mos.jbox.playlist.entities;

import java.util.LinkedList;
import java.util.List;

public class Playlist {
	
	private List<PlaylistItem> items;
	
	public Playlist() {
		items = new LinkedList<PlaylistItem>();
	}

	public PlaylistItem addSong(Song song) {
		PlaylistItem playlistItem = new PlaylistItem(song);
		items.add(playlistItem);
		return playlistItem;
		
	}

	public int getSize() {
		return items.size();
	}

	public Song popNextSong() {
		if (items.isEmpty()) {
			return null;
		}
		return items.remove(0).getSong();
	}

}
