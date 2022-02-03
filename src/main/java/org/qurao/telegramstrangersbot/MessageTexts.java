package org.qurao.telegramstrangersbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MessageTexts {

	private final String startMessage;
	private final String startSearchMessage;
	private final String alreadySearchMessage;
	private final String cancelSearchMessage;
	private final String opponentFoundMessage;
	private final String opponentCancelChatMessage;
	private final String dontHaveOpponentMessage;
	private final String cancelChatMessage;
	private final String incorrectMessageFormatMessage;
	private final String commandNotFoundMessage;
	
	public MessageTexts(){
		startMessage = loadMessageOrDefault(new File("messages" + File.separator
				+ "startMessage.txt"), "Welcome to chat!\n/next - new chat");
		startSearchMessage = loadMessageOrDefault(new File("messages" 
				+ File.separator
				+ "startSearchMessage.txt"), "Search started!");
		alreadySearchMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "alreadySearchMessage.txt"), "You arleady search chat!");
		cancelSearchMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "cancelSearchMessage.txt"), "You cancel search!");
		opponentFoundMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "opponentFoundMessage.txt"), "Opponent found!");
		opponentCancelChatMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "opponentCancelChatMessage.txt"), 
				"Opponent cancel chat!");
		dontHaveOpponentMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "dontHaveOpponentMessage.txt"), 
				"You don't have chat!\n/next - new chat");
		cancelChatMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "cancelChatMessage.txt"), 
				"You cancelled chat!");
		incorrectMessageFormatMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "incorrectMessageFormatMessage.txt"), 
				"Incorrect format of message!");
		commandNotFoundMessage = loadMessageOrDefault(new File("messages"
				+ File.separator
				+ "commandNotFoundMessage.txt"), 
				"Command not found: ");
	}
	
	private String loadMessageOrDefault(File file, String defaultMessage) {
		String result = new String();
		if(file.exists()) {
			result = readFromFile(file);
		}else {
			FileWriter writer = null;
			try {
				file.createNewFile();
				writer = new FileWriter(file);
				writer.write(defaultMessage);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			result = defaultMessage;
		}
		return result;
	}
	
	
	private String readFromFile(File file) {
		String result = new String();
		BufferedReader reader = null;
		try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            StringBuilder builder = new StringBuilder();
            while (line != null) {
                builder.append(line).append("\n");
                line = reader.readLine();
            }
            result = builder.toString();
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
		return result;
	}


	public String getStartMessage() {
		return startMessage;
	}

	public String getStartSearchMessage() {
		return startSearchMessage;
	}

	public String getAlreadySearchMessage() {
		return alreadySearchMessage;
	}

	public String getCancelSearchMessage() {
		return cancelSearchMessage;
	}

	public String getOpponentFoundMessage() {
		return opponentFoundMessage;
	}

	public String getOpponentCancelChatMessage() {
		return opponentCancelChatMessage;
	}

	public String getDontHaveOpponentMessage() {
		return dontHaveOpponentMessage;
	}

	public String getCancelChatMessage() {
		return cancelChatMessage;
	}

	public String getIncorrectMessageFormatMessage() {
		return incorrectMessageFormatMessage;
	}

	public String getCommandNotFoundMessage() {
		return commandNotFoundMessage;
	}
	
}
