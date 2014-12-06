package net.de1mos.jbox.media;

import org.gstreamer.Element;
import org.gstreamer.Pipeline;

class GstreamerProperties {

	public String mountPoint;
	
	public Element fileSrc;
	
	private Pipeline pipeline;
	
	private Element tagInject;

	public GstreamerProperties(String mountPoint, Pipeline pipeline, Element fileSrc, Element tagInject) {
		this.mountPoint = mountPoint;
		this.pipeline = pipeline;
		this.fileSrc = fileSrc;
		this.tagInject = tagInject;
	}

	public String getMountPoint() {
		return mountPoint;
	}

	public Element getFileSrc() {
		return fileSrc;
	}

	public Pipeline getPipeline() {
		return pipeline;
	}

	public Element getTagInject() {
		return tagInject;
	}

}
