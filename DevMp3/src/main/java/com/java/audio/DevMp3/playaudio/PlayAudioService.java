package com.java.audio.DevMp3.playaudio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.audio.DevMp3.commomclass.AudioRequest;
import com.java.audio.DevMp3.responseconstants.CustomeResponse;
import com.java.audio.DevMp3.responseconstants.ResponseConstants;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

@Service("playAudioService")
public class PlayAudioService {
	@Autowired
	private IPlayAudio iPlayAudio;
	private static final Logger logger = Logger.getLogger(PlayAudioController.class);

	public CustomeResponse playAndStopSongs(AudioRequest audioresp) {
		CustomeResponse response = null;
		if (audioresp.isPlaylist()) {
			response = new CustomeResponse();
			playSong(audioresp, response);
		} else {
			response = new CustomeResponse();
			response.setStatusCode(ResponseConstants.FAILED);
			response.setStatusMessage(ResponseConstants.FAIL_MESSAGE);
		}
		return response;

	}

	public void playSong(AudioRequest audioresp, CustomeResponse response) {
		try {
			String path = audioresp.getName();
			if (path == null) {
				path = "";
			}
			File[] listOfFiles = getFilePath(path);
			Set<Integer> ref = new HashSet<>();
			int counter = 0;
			boolean isSongAvaible = false;
			while (listOfFiles != null && listOfFiles.length > 0 && counter < listOfFiles.length) {
				isSongAvaible = true;
				int random = getRandomInteger(listOfFiles.length, 0);
				if (listOfFiles[random].isFile() && ref.add(random)) {
					counter++;
					FileInputStream fileInputStream = new FileInputStream(path + "\\" + listOfFiles[random].getName());
					Player player = new Player(fileInputStream);
					logger.debug(listOfFiles[random].getName() + " is playing...");
					player.play();
					response.setStatusCode(ResponseConstants.SUCCESS_CREATED);
					response.setStatusMessage(ResponseConstants.SUCESS_MESSAGE);

				}

			}
			if (!isSongAvaible) {
				response.setStatusCode(ResponseConstants.STATUS403);
				response.setStatusMessage(ResponseConstants.NO_SONG_MESSAGE);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

	public static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	public static File[] getFilePath(String path) {

		File[] listOfFiles = null;
		try {
			File folder = new File(path);
			listOfFiles = folder.listFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfFiles;
	}

}
