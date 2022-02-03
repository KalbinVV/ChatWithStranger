package org.qurao.telegramstrangersbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.qurao.telegramstrangersbot.commands.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramStrangersBot {
	
	private static CommandsHandler commandsHandler;
	private static UsersChatHandler usersChatHandler;
	private static LongPollingBot bot;
	private static MessageTexts messageTexts;
	private static String botToken;

	public static void main(String[] args) {
		commandsHandler = new CommandsHandler();
		usersChatHandler = new UsersChatHandler();
		messageTexts = new MessageTexts();
		if(loadTokenFromFile()) {
			bot = new LongPollingBot();
			registerCommands();
			try {
				TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
				botsApi.registerBot(bot);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Write you bot token to token.txt file!");
		}
	}
	
	private static boolean loadTokenFromFile() {
		File file = new File("token.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		BufferedReader reader = null;
		try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            if(line == null) {
            	return false;
            }else {
            	botToken = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return true;
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

	public static MessageTexts getMessageTexts() {
		return messageTexts;
	}

	public static String getBotToken() {
		return botToken;
	}
	
}
