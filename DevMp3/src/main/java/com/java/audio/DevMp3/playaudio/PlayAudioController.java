package com.java.audio.DevMp3.playaudio;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.audio.DevMp3.commomclass.AudioRequest;
import com.java.audio.DevMp3.responseconstants.CustomeResponse;
import com.java.audio.DevMp3.responseconstants.ResponseConstants;

@RestController
@RequestMapping("/audio")
public class PlayAudioController {
	private static final Logger logger = Logger.getLogger(PlayAudioController.class);
	@Autowired
	private PlayAudioService playAudioService;
	@RequestMapping(value = "/startaudio", method = RequestMethod.POST, headers = "Accept=application/json")
	public CustomeResponse playAudio(@RequestBody AudioRequest path) {
		CustomeResponse resp = null;
		try {
			resp = playAudioService.playAndStopSongs(path);
		} catch (Exception e) {
			resp.setStatusCode(ResponseConstants.SERVER_ERROR);
			resp.setStatusMessage(ResponseConstants.MSG_SERVER_ERROR);
			logger.error("Error occurred while playing songs", e);

		}
		return resp;
	}

}
