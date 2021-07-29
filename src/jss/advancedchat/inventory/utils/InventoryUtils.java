package jss.advancedchat.inventory.utils;

import java.util.List;

public class InventoryUtils {

	public static List<String> coloredList(List<String> list){
		for(int i  = 0; i < list.size(); i++) {
			String addcolor = list.get(i).toString();
			list.add(addcolor);
		}
		return list;
	}

}
