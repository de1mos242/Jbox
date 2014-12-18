package net.de1mos.jbox.webclient.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.vkapi.VkApi;
import com.googlecode.vkapi.domain.OAuthToken;
import com.googlecode.vkapi.exceptions.VkException;
import com.googlecode.vkapi.exceptions.VkTokenExpiredException;

@Controller
public class VkController {
	private final VkApi vkApi;

	@Autowired
	public VkController(VkApi vkApi) {
		this.vkApi = vkApi;
	}

	@RequestMapping(value = "/vk/auth", method = RequestMethod.GET)
	public String auth() {
		return "redirect:" + vkApi.getAuthUri();
	}

	@RequestMapping(value = "/vk/process", method = RequestMethod.GET)
	public String process(
			@RequestParam(value = "code", required = false) String code)
			throws VkException {
		OAuthToken token = null;

		if (code != null) {
			token = vkApi.authUser(code);
		}

		return token.toString();
	}

	@ExceptionHandler(VkTokenExpiredException.class)
	public ModelAndView handleVkTokenExpiredException(Principal principal,
			VkTokenExpiredException ex) {
		return null;
	}
}