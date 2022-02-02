package org.qurao.telegramstrangersbot;

import org.qurao.telegramstrangersbot.commands.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramStrangersBot {
	
	private static CommandsHandler commandsHandler;
	private static UsersChatHandler usersChatHandler;
	private static LongPollingBot bot;

	public static void main(String[] args) {
		commandsHandler = new CommandsHandler();
		usersChatHandler = new UsersChatHandler();
		bot = new LongPollingBot();
		registerCommands();
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(bot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	private static void registerCommands() {
		commandsHandler.registerCommand("/start", new StartCommand());
		commandsHandler.registerCommand("/next", new NextCommand());
		commandsHandler.registerCommand("/stop", new StopCommand());
	}

	public static CommandsHandler getCommandsHandler() {
		return commandsHandler;
	}

	public static LongPollingBot getBot() {
		return bot;
	}

	public static UsersChatHandler getUsersChatHandler() {
		return usersChatHandler;
	}
	
}
