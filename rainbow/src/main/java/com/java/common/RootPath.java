package com.java.common;

import java.io.File;
import java.net.URISyntaxException;

public class RootPath {
	private static String path="";
	public String getRootPath()
	{
		
		try 
		{
			if(path.equals(""))
			{
				path=new File(getClass().getClassLoader().getResource("").toURI()).getPath();
				path = path.replace("WEB-INF" + File.separator + "classes", "");
			}
		}
		catch(URISyntaxException ex) {}
		return path;
	}
	
}
