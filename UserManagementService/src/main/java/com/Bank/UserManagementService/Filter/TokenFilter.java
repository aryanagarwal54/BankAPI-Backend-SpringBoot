package com.Bank.UserManagementService.Filter;

import java.io.IOException;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenFilter extends GenericFilter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest httpRequest= (HttpServletRequest)request;		
		HttpServletResponse httpResponse= (HttpServletResponse)response;
		
		String token= httpRequest.getHeader("Authorization");
		
		if(token == null || !token.startsWith("Bearer"))
			throw new ServletException();
		else
		{
			String jwttoken= token.substring(7);
			String pass= Jwts.parser().setSigningKey("team4").parseClaimsJws(jwttoken).getBody().getSubject();
			chain.doFilter(httpRequest, httpResponse);
		}				
	}
}
