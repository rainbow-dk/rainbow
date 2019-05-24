package com.java.common;

import java.util.Collection;
import java.util.Map;

public final class CollectionUtils {

	public static boolean isEmpty(Collection<?> coll)
	{
		if (coll == null || coll.size() == 0) 
			return true;
		
		return false;
	}
	
	public static boolean isEmpty(Map<?,?> coll)
	{
		if (coll == null || coll.size() == 0) 
			return true;
		
		return false;
	}
	
	public static boolean isEmpty(Object[] arr)
	{
		if (arr == null || arr.length == 0) 
			return true;
		
		return false;
	}
	
}
