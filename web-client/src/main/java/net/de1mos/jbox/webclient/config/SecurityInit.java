package net.de1mos.jbox.webclient.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInit extends AbstractSecurityWebApplicationInitializer {

	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class }; // We dont need any special
												// servlet config yet.
	}
	
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
