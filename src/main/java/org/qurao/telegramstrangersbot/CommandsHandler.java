package org.qurao.telegramstrangersbot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.telegram.telegrambots.meta.api.objects.Message;

public class CommandsHandler {

	private final HashMap<String, CommandExecutor> commands;
	private final HashSet<String> admins;
	
	public CommandsHandler(){
		commands = new HashMap<String, CommandExecutor>();
		admins = new HashSet<String>(Arrays.asList("Qurao"));
	}
	
	public void registerCommand(String commandText, CommandExecutor command) {
		commands.put(commandText, command);
		System.out.println("Command " + commandText + " registered!");
	}
	
	public boolean runCommandIfExist(String commandText, Message message) {
		if(!commands.containsKey(commandText)) {
			return false;
		}
		CommandExecutor command = commands.get(commandText);
		if(command instanceof AdminCommand) {
			if(!admins.contains(message.getFrom().getUserName())){
				return false;
			}
		}
		command.run(message);
		return true;
	}
	
}
