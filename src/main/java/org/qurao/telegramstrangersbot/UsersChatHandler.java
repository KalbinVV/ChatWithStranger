package org.qurao.telegramstrangersbot;

import java.util.ArrayList;
import java.util.HashMap;

import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;

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
			Long opponentID = waitingUsers.get(0);
			opponents.put(userID, opponentID);
			opponents.put(opponentID, userID);
			waitingUsers.remove(0);
			LongPollingBot bot = TelegramStrangersBot.getBot();
			String opponentFoundMessage = TelegramStrangersBot.getMessageTexts().getOpponentFoundMessage();
			bot.sendTextMessage(userID, opponentFoundMessage);
			bot.sendTextMessage(opponentID, opponentFoundMessage);
		}
	}
	
	public void removeUserFromChat(Long userID) {
		Long opponentID = opponents.get(userID);
		opponents.remove(userID);
		opponents.remove(opponentID);
		TelegramStrangersBot.getBot().sendTextMessage(opponentID, 
				TelegramStrangersBot.getMessageTexts().getOpponentCancelChatMessage());
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
	
	public void sendTextMessageToChat(Long userID, String messageText) {
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(isUserHasOpponent(userID)) {
			bot.sendTextMessage(getUserOpponent(userID), messageText);
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
		}
	}
	
	public void sendStickerToChat(Long userID, Sticker sticker) {
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(isUserHasOpponent(userID)) {
			bot.sendStickerMessage(getUserOpponent(userID), sticker);
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
		}
	}
	
	public void sendPhotoToChat(Long userID, ArrayList<PhotoSize> photos) {
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(isUserHasOpponent(userID)) {
			bot.sendPhotoMessage(getUserOpponent(userID), photos);
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
		}
	}
	
	public void sendAudioToChat(Long userID, Audio audio) {
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(isUserHasOpponent(userID)) {
			bot.sendAudioMessage(getUserOpponent(userID), audio);
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
		}
	}
	
	public void sendVoiceToChat(Long userID, Voice voice) {
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(isUserHasOpponent(userID)) {
			bot.sendVoiceMessage(getUserOpponent(userID), voice);
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
		}
	}
	
	public void sendVideoToChat(Long userID, Video video) {
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(isUserHasOpponent(userID)) {
			bot.sendVideoMessage(getUserOpponent(userID), video);
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
		}
	}
	
	public void sendAnimationToChat(Long userID, Animation animation) {
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(isUserHasOpponent(userID)) {
			bot.sendAnimationMessage(getUserOpponent(userID), animation);
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
		}
	}
	
	
}
