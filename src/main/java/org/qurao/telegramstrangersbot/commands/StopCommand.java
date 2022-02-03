package org.qurao.telegramstrangersbot.commands;

import org.qurao.telegramstrangersbot.LongPollingBot;
import org.qurao.telegramstrangersbot.SimpleCommand;
import org.qurao.telegramstrangersbot.TelegramStrangersBot;
import org.qurao.telegramstrangersbot.UsersChatHandler;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StopCommand extends SimpleCommand{

	@Override
	public boolean run(Message message) {
		Long userID = message.getChatId();
		UsersChatHandler usersChatHandler = TelegramStrangersBot.getUsersChatHandler();
		LongPollingBot bot = TelegramStrangersBot.getBot();
		if(usersChatHandler.isUserWaiting(userID)) {
			usersChatHandler.removeUserFromWaiting(userID);
			bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getCancelSearchMessage());
		}else {
			if(usersChatHandler.isUserHasOpponent(userID)) {
				usersChatHandler.removeUserFromChat(userID);
				bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getCancelChatMessage());
			}else {
				bot.sendTextMessage(userID, TelegramStrangersBot.getMessageTexts().getDontHaveOpponentMessage());
			}
		}
		return true;
	}

}
