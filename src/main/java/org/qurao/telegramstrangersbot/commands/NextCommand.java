package org.qurao.telegramstrangersbot.commands;

import org.qurao.telegramstrangersbot.LongPollingBot;
import org.qurao.telegramstrangersbot.SimpleCommand;
import org.qurao.telegramstrangersbot.TelegramStrangersBot;
import org.qurao.telegramstrangersbot.UsersChatHandler;
import org.telegram.telegrambots.meta.api.objects.Message;

public class NextCommand extends SimpleCommand{

	@Override
	public boolean run(Message message) {
		UsersChatHandler usersChatHandler = TelegramStrangersBot.getUsersChatHandler();
		Long userID = message.getChatId();
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(usersChatHandler.isUserWaiting(userID)) {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getAlreadySearchMessage());
		}else {
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getStartSearchMessage());
			usersChatHandler.addUserToChat(userID);
		}
		return true;
	}

}
