package net.de1mos.jbox.webclient.controllers;

import net.de1mos.jbox.webclient.viewmodels.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {

	@RequestMapping("/admin")
	public Admin hello() {
		return new Admin();
	}
}
