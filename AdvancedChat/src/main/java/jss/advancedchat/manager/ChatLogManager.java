package jss.advancedchat.manager;

import jss.advancedchat.utils.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatLogManager {
	
	private final List<String> blacklist = Arrays.asList("/login","/log","/register","/reg");
	private final List<String> ignore = new ArrayList<String>();
	private final List<String> check = new ArrayList<String>();
	
	public List<String> getBlacklist() {
		return blacklist;
	}
	
	public void addBlacklist(String blacklist) {	
		for(String i : this.blacklist) {
			if(!i.contains(blacklist)) {
				this.blacklist.add(blacklist);
			}else {
				Logger.debug("&cIt is already in the list &7[&e" + blacklist + "&7]");
			}
		}
	}
	
	public List<String> getIgnore() {
		return ignore;
	}
	
	public void addIgnore(String ignore) {
		for(String i : this.ignore) {
			if(!i.contains(ignore)) {
				this.ignore.add(ignore);
			}else {
				Logger.debug("&cIt is already in the list &7[&e" + ignore + "&7]");
			}
		}
	}
	public List<String> getCheck() {
		return check;
	}
	public void setCheck(String check) {
		for(String i : this.check) {
			if(!i.contains(check)) {
				this.check.add(check);
			}else {
				Logger.debug("&cIt is already in the list &7[&e" + check + "&7]");
			}
		}
	}
	
}
