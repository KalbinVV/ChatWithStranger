package org.qurao.telegramstrangersbot;

import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class SimpleCommand implements CommandExecutor{

	@Override
	public abstract boolean run(Message message);

}
