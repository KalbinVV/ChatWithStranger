package org.qurao.telegramstrangersbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class LongPollingBot extends TelegramLongPollingBot{

	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		Long chatID = message.getChatId();
		String messageText = message.getText();
		if(messageText.startsWith("/")) {
			if(!TelegramStrangersBot.getCommandsHandler()
					.runCommandIfExist(messageText, message)) {
				sendTextMessage(chatID, "Команды не существует: " + messageText);
			}
		}else {
			UsersChatHandler usersChatHandler = TelegramStrangersBot.getUsersChatHandler();
			if(usersChatHandler.isUserHasOpponent(chatID)) {
				sendTextMessage(usersChatHandler.getUserOpponent(chatID), update.getMessage().getText());
			}else {
				sendTextMessage(chatID, "У вас нет собеседника!\nВведите /next, чтобы найти его!");
			}
		}
	}

	@Override
	public String getBotUsername() {
		return "Strangers Bot";
	}

	@Override
	public String getBotToken() {
		return "5210419719:AAFVthqiQfidwmPKjFp5Mf_XD-HOmzsAinQ";
	}
	
	public synchronized void sendTextMessage(Long chatID, String message) {
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

}
