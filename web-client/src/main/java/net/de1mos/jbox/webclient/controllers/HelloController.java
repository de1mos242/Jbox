package net.de1mos.jbox.webclient.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioPermission;
import javax.sound.sampled.AudioSystem;

import net.de1mos.jbox.webclient.viewmodels.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.googlecode.vkapi.HttpVkApi;


@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@RequestMapping("/song") 
	@ResponseBody
	public Song getSimpleSong() {
		return new Song("song title", "song artist");
	}
	
	@RequestMapping("/play")
	public OutputStream play() throws Exception {
		FileInputStream stream = new FileInputStream("/home/denis/song.mp3");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(stream);
		return null;
		
	}
}
