package com.java.audio.DevMp3.commomclass;

public class AudioRequest {
	private String name;
	boolean playlist;

	public boolean isPlaylist() {
		return playlist;
	}

	public void setPlaylist(boolean playlist) {
		this.playlist = playlist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
