package org.qurao.telegramstrangersbot;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandExecutor {
	
	public boolean run(Message message);

}
