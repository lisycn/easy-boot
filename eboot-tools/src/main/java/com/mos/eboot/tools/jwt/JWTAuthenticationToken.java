package com.mos.eboot.tools.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken{
	
	private static final long serialVersionUID = 1L;
	
	public JWTAuthenticationToken(Object principal) {
		super(principal,null);
	}

	@Override
	public Object getCredentials() {
		return super.getCredentials();
	}

	@Override
	public Object getPrincipal() {
		return super.getPrincipal();
	}

}
