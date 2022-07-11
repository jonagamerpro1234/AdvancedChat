package jss.advancedchat.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jss.advancedchat.utils.Logger;

public class ChatLogManager {
	
	private List<String> blacklist = Arrays.asList("/login","/log","/register","/reg");
	private List<String> ignore = new ArrayList<String>();
	private List<String> check = new ArrayList<String>();
	
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
