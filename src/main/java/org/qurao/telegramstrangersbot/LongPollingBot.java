package org.qurao.telegramstrangersbot;

import java.util.ArrayList;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.send.SendVoice;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class LongPollingBot extends TelegramLongPollingBot{

	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		Long chatID = message.getChatId();
		UsersChatHandler usersChatHandler = TelegramStrangersBot.getUsersChatHandler();
		if(message.hasSticker()) {
			usersChatHandler.sendStickerToChat(chatID, message.getSticker());
		}else if(message.hasPhoto()) {
			usersChatHandler.sendPhotoToChat(chatID, new ArrayList<PhotoSize>(
					message.getPhoto()));
		}else if(message.hasAnimation()) {
			usersChatHandler.sendAnimationToChat(chatID, message.getAnimation());
		}else if(message.hasAudio()){
			usersChatHandler.sendAudioToChat(chatID, message.getAudio());
		}else if(message.hasVoice()){
			usersChatHandler.sendVoiceToChat(chatID, message.getVoice());
		}else if(message.hasVideo()) {
			usersChatHandler.sendVideoToChat(chatID, message.getVideo());
		}else if(message.hasText()){
			String messageText = message.getText();
			if(messageText.startsWith("/")) {
				if(!TelegramStrangersBot.getCommandsHandler()
						.runCommandIfExist(messageText, message)) {
					sendTextMessage(chatID, TelegramStrangersBot.getMessageTexts().getCommandNotFoundMessage() + messageText);
				}
			}else {
				usersChatHandler.sendTextMessageToChat(chatID, message.getText());
			}
		}else {
			sendTextMessage(chatID, TelegramStrangersBot.getMessageTexts().getIncorrectMessageFormatMessage());
		}
	}
	

	@Override
	public String getBotUsername() {
		return "Strangers Bot";
	}

	@Override
	public String getBotToken() {
		return TelegramStrangersBot.getBotToken();
	}
	
	public void sendTextMessage(Long chatID, String message) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(chatID.toString());
		sendMessage.enableMarkdown(true);
		sendMessage.setText(message);
		try {
			execute(sendMessage);
		} catch(TelegramApiException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendStickerMessage(Long chatID, Sticker sticker) {
		SendSticker sendSticker = new SendSticker();
		sendSticker.setSticker(new InputFile(sticker.getFileId()));
		sendSticker.setChatId(chatID.toString());
		try {
			execute(sendSticker);
		} catch(TelegramApiException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendPhotoMessage(Long chatID, ArrayList<PhotoSize> photos) {
		SendPhoto sendPhoto = new SendPhoto();
		sendPhoto.setPhoto(new InputFile(photos.get(photos.size() - 1).getFileId()));
		sendPhoto.setChatId(chatID.toString());
		try {
			execute(sendPhoto);
		} catch(TelegramApiException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendAudioMessage(Long chatID, Audio audio) {
		SendAudio sendAudio = new SendAudio();
		sendAudio.setAudio(new InputFile(audio.getFileId()));
		sendAudio.setChatId(chatID.toString());
		try {
			execute(sendAudio);
		} catch(TelegramApiException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendVoiceMessage(Long chatID, Voice voice) {
		SendVoice sendVoice = new SendVoice();
		sendVoice.setVoice(new InputFile(voice.getFileId()));
		sendVoice.setChatId(chatID.toString());
		try {
			execute(sendVoice);
		} catch(TelegramApiException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void sendVideoMessage(Long chatID, Video video) {
		SendVideo sendVideo = new SendVideo();
		sendVideo.setVideo(new InputFile(video.getFileId()));
		sendVideo.setChatId(chatID.toString());
		try {
			execute(sendVideo);
		} catch(TelegramApiException ex) {
			ex.printStackTrace();
		}
	}
	
	public void sendAnimationMessage(Long chatID, Animation animation) {
		SendAnimation sendAnimation = new SendAnimation();
		sendAnimation.setAnimation(new InputFile(animation.getFileId()));
		sendAnimation.setChatId(chatID.toString());
		try {
			execute(sendAnimation);
		} catch(TelegramApiException ex) {
			ex.printStackTrace();
		}
	}
	
	

}
