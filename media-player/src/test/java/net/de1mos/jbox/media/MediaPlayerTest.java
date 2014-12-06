package net.de1mos.jbox.media;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class MediaPlayerTest {
	
	private static MediaPlayer player;

	@BeforeClass
	public static void startPlayer() {
		GstreamerConfig config = new GstreamerConfig("127.0.0.1", "8000", "hackme");
		player = new MediaPlayer(config);
		player.init();
	}

	@AfterClass
	public static void stopPlayer(){
		player.dispose();
	}
	
	//@Test
	public void testPlaySong() throws InterruptedException {
		ShoutStream stream = player.getStream("testMount.mp3");
		stream.PlaySong(new Song("/home/denis/song.mp3", "Achive", "Bullets"));
		Assert.assertEquals(ShoutStream.State.PLAYING, stream.getState());
		
		Thread.sleep(10000);
	}
	
	//@Test
	public void testPlay2Songs() throws InterruptedException {
		ShoutStream stream = player.getStream("testMount.mp3");
		stream.PlaySong(new Song("/home/denis/song.mp3", "Achive", "Bullets"));
		Assert.assertEquals(ShoutStream.State.PLAYING, stream.getState());
		
		Thread.sleep(10000);
		
		stream.PlaySong(new Song("/home/denis/song2.mp3", "Ляпис трубецкой", "Священный огонь"));
		Assert.assertEquals(ShoutStream.State.PLAYING, stream.getState());
		
		Thread.sleep(10000);
	}
}
