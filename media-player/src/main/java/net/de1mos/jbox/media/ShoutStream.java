package net.de1mos.jbox.media;

import net.de1mos.jbox.log.LogWrapper;

public class ShoutStream {
	
	private static final LogWrapper log = LogWrapper.get(ShoutStream.class);

	private GstreamerProperties gstreamerProperties;
	
	private String mountPoint;
	
	public enum State {
		STOPPED, PLAYING;
	}
	
	private State state = State.STOPPED;
	
	ShoutStream(GstreamerProperties properties) {
		this.gstreamerProperties = properties;
		this.mountPoint = properties.getMountPoint();
	}

	public String getMountPoint() {
		return mountPoint;
	}
	
	public State getState() {
		return state;
	}
	
	public void PlaySong(Song song) {
		log.info(String.format("stop playing: %s", gstreamerProperties.getFileSrc().get("location")));
		gstreamerProperties.getPipeline().stop();
		gstreamerProperties.getFileSrc().set("location", song.location);
		gstreamerProperties.getTagInject().set("tags", String.format("title=\"%s\",artist=\"%s\"", song.title, song.artist));
		log.info(String.format("start playing2: %s - %s", song.artist, song.title));
		gstreamerProperties.getPipeline().play();
		state = State.PLAYING;
	}

	void dispose() {
		gstreamerProperties.getPipeline().stop();
	}
}
