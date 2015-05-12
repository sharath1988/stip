package edu.ucsd.som.stip.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.seam.annotations.Name;
import java.lang.StringBuffer;

@Name("stipStrings")
public class StipStrings {
	
	public static String toProperCase(String s) {
		if (null != s){
			Pattern p = Pattern.compile("(^|\\W)([a-z])");
			Matcher m = p.matcher(s.toLowerCase());
			StringBuffer sb = new StringBuffer(s.length());
			while(m.find()) {
				m.appendReplacement(sb, m.group(1) + m.group(2).toUpperCase() );
			}
			m.appendTail(sb);
			return sb.toString();
		} else {
			return null;
		}
	}	
}
