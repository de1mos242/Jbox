package net.de1mos.jbox.api.client.vk;

import com.fasterxml.jackson.databind.JsonNode;

public class VKMusicTrack {
	
	private String title;
	
	private String artist;
	
	private String linkUrl;
	
	private Integer duration;

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public Integer getDuration() {
		return duration;
	}

	public VKMusicTrack(JsonNode jsonNode) {
		super();
		this.title = jsonNode.get("title").asText();
		this.artist = jsonNode.get("artist").asText();
		this.linkUrl = jsonNode.get("url").asText();
		this.duration = jsonNode.get("duration").asInt();
	}
}