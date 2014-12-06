package net.de1mos.jbox.media;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.gstreamer.Element;
import org.gstreamer.ElementFactory;
import org.gstreamer.Pipeline;

class ShoutStreamFactory {
	
	GstreamerConfig config;
	
	Map<String, ShoutStream> streams = new HashMap<>();
	
	public ShoutStreamFactory(GstreamerConfig config) {
		this.config = config;
	}

	public ShoutStream getOrCreate(String mountPoint) {
		if (!streams.containsKey(mountPoint)) {
			streams.put(mountPoint, makeStream(mountPoint));
		}
		return streams.get(mountPoint);
	}

	private ShoutStream makeStream(String mountPoint) {
		Pipeline pipeline = new Pipeline();
		Element filesrc = ElementFactory.make("filesrc", "filesrc");
		Element decoder = ElementFactory.make("mad", "mad");
		Element tee = ElementFactory.make("tee", "tee");
		Element shoutcaseQueue = ElementFactory.make("queue", "queue");
		Element converter = ElementFactory.make("audioconvert", "audioconvert");
		Element lame = ElementFactory.make("lame", "lame");
		lame.set("bitrate", 192);
		Element tagInject = ElementFactory.make("taginject", "taginject");
		Element shoutcast = ElementFactory.make("shout2send", "shout2send");
		
		shoutcast.set("ip", config.ip);
		shoutcast.set("port", config.port);
		shoutcast.set("password", config.password);
		shoutcast.set("mount", mountPoint);
		
		pipeline.addMany(filesrc, decoder, tee, shoutcaseQueue, converter, lame, tagInject, shoutcast);
		
		filesrc.link(decoder);
		decoder.link(tee);
		tee.link(shoutcaseQueue);
		shoutcaseQueue.link(converter);
		converter.link(lame);
		lame.link(tagInject);
		tagInject.link(shoutcast);
		
		return new ShoutStream(new GstreamerProperties(mountPoint, pipeline, filesrc, tagInject));
	}

	public void dispose() {
		for (Entry<String, ShoutStream> stream : streams.entrySet()) {
			stream.getValue().dispose();
		}
	}
	
	
	
}
