package net.de1mos.jbox.playlist.controller;

import net.de1mos.jbox.playlist.entities.Playlist;
import net.de1mos.jbox.playlist.entities.Song;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestManagePlaylist {
	
	@Test
	public void testAddSongToPlaylist() {
		Song song = new Song("Skrillex", "Kill everybody", "03:15");
		Playlist playlist = new Playlist();
		playlist.addSong(song);
		
		assertEquals(1, playlist.getSize());
	}
	
	@Test
	public void testAfterStartPlayingSongShoudBeDeletedFromQueue() {
		Song song = new Song("Skrillex", "Kill everybody", "03:15");
		Playlist playlist = new Playlist();
		playlist.addSong(song);
		
		Song s = playlist.popNextSong();
		assertEquals(0, playlist.getSize());
		
		assertEquals(s, song);
	}
}
