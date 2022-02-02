package org.qurao.telegramstrangersbot.commands;

import org.qurao.telegramstrangersbot.SimpleCommand;
import org.qurao.telegramstrangersbot.TelegramStrangersBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartCommand extends SimpleCommand{

	
	
	@Override
	public boolean run(Message message) {
		TelegramStrangersBot.getBot().sendTextMessage(message.getChatId(), 
				"Добро пожаловать!\n/next - найти нового собеседника.\n/stop - прекратить чат.\nСоздатель: @Qurao");
		return true;
	}

}
