package jss.advancedchat.json.handlers;

import jss.advancedchat.utils.Utils;

public class JsonHoverEvent {
	
	private HoverAction action;
	private String arg0;
	private static final int MAX_LINES;
	private static final int MAX_LENGTH;
	
	static {
		MAX_LINES = 20;
		MAX_LENGTH = 50;
	}
	
	public JsonHoverEvent(HoverAction action, String arg0) {
		this.action = action;
		this.arg0 = arg0;
	}
	
	public JsonHoverEvent(HoverAction action, String[] arg0) {
		this.action = action;
		int l = 0;
		String c = "";
		
		for(int i = 0; i < arg0.length && l < MAX_LINES; ++i) {
			String[] s = arg0[i].split(" ");
			int ll  = 0;
			String[] v = s;
			int d = s.length;
			
			for(int e = 0; e < d; ++e) {
				String ss = v[e];
				ll += ss.length();
				if(ll > MAX_LENGTH) {
					ll = 0;
					c = c + "\n" + Utils.hexcolor(c);
					++l;
					c = c + ss + " ";
				}else {
					c = c + ss + " ";
				}
			}
			
			if(i != arg0.length - 1) {
				c = c + "\n";
			}
		}
		if(l >= MAX_LINES) {
			c = c + "\n...";
		}
		this.arg0 = c;
	}

	public HoverAction getAction() {
		return action;
	}

	public String getValue() {
		return arg0;
	}	
}
