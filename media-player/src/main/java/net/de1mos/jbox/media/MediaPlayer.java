package net.de1mos.jbox.media;

import org.gstreamer.Gst;
import org.gstreamer.lowlevel.MainLoop;

public class MediaPlayer {

	private boolean initialized = false;
	
	private GstreamerConfig config;
	
	private ShoutStreamFactory factory;

	@SuppressWarnings("unused")
	private MainLoop mainLoop;

	public MediaPlayer(GstreamerConfig config) {
		if (config == null) {
			throw new IllegalArgumentException("Empty config passed");
		}
		this.config = config;
		this.factory = new ShoutStreamFactory(this.config);
	}
	
	public void init() {
		if (initialized) {
			throw new IllegalStateException("Media player already initialized");
		}
		Gst.init();
		mainLoop = new MainLoop();
		initialized = true;
	};
	
	public void dispose() {
		if (!initialized) {
			throw new IllegalStateException("Media player not initialized or already disposed");
		}
		initialized = false;
		factory.dispose();
		Gst.deinit();
	}

	public boolean isInitialized() {
		return initialized;
	}
	
	/** Get or create a stream with passed mount point **/
	public ShoutStream getStream(String mountPoint) {
		return factory.getOrCreate(mountPoint);
	}
}
