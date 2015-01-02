package net.de1mos.jbox.webclient.controllers;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import net.de1mos.jbox.api.client.vk.VkApiClient;
import net.de1mos.jbox.api.client.vk.core.VKAuthToken;
import net.de1mos.jbox.api.client.vk.core.VKUser;
import net.de1mos.jbox.webclient.viewmodels.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
	
	@Autowired
	private VkApiClient apiClient;
	
	private final static String[] vkApiScope = {"audio"};
	
	@RequestMapping("/hello")
	public String hello(HttpServletRequest request,
			ModelMap model) {
		
		HttpSession session = request.getSession();
		
		
		VKUser vk = (VKUser) session.getAttribute("VK_USER");
		
		model.addAttribute("VKUser", vk);
		
		System.out.println(vk);
		
		return "hello";
	}
	
	@RequestMapping("/vk/sign") 
	public String vksign(HttpServletRequest request) {
		
		String code = request.getParameter("code");
		
		System.out.println("AUTH CODE "+code);
		
		if (code!=null)
		{
			VKAuthToken token = apiClient.getOAuthToken(code);
			
			VKUser user = apiClient.getCurrentVKUserInfo(token);
			
			request.getSession().setAttribute("VK_USER", user);
			
			return "redirect:/hello";
		}
		else
		{
			return "redirect:"+apiClient.getAuthURL(vkApiScope);
		}
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
