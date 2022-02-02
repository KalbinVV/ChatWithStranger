package org.qurao.telegramstrangersbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class UsersChatHandler {

	public HashMap<Long, Long> opponents;
	public ArrayList<Long> waitingUsers;
	
	UsersChatHandler(){
		opponents = new HashMap<Long, Long>();
		waitingUsers = new ArrayList<Long>();
	}
	
	public void addUserToChat(Long userID) {
		if(waitingUsers.isEmpty()) {
			waitingUsers.add(userID);
		}else {
			int randomIndex = new Random().nextInt(waitingUsers.size());
			Long opponentID = waitingUsers.get(randomIndex);
			opponents.put(userID, opponentID);
			opponents.put(opponentID, userID);
			waitingUsers.remove(randomIndex);
			LongPollingBot bot = TelegramStrangersBot.getBot();
			bot.sendTextMessage(userID, "Собеседник найден!\n/stop - остановить чат.");
			bot.sendTextMessage(opponentID, "Собеседник найден!\n/stop - остановить чат.");
		}
	}
	
	public void removeUserFromChat(Long userID) {
		Long opponentID = opponents.get(userID);
		opponents.remove(userID);
		opponents.remove(opponentID);
		TelegramStrangersBot.getBot().sendTextMessage(opponentID, "Собеседник прекратил чат!");
	}
	
	public void removeUserFromWaiting(Long userID) {
		waitingUsers.remove(userID);
	}
	
	public boolean isUserWaiting(Long userID) {
		return waitingUsers.contains(userID);
	}
	
	public boolean isUserHasOpponent(Long userID) {
		return opponents.containsKey(userID);
	}
	
	public Long getUserOpponent(Long userID) {
		return opponents.get(userID);
	}
	
}
