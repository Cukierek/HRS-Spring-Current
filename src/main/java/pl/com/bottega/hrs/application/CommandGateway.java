package pl.com.bottega.hrs.application;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.com.bottega.hrs.model.commands.Command;

import java.util.Map;
import java.util.Optional;

@Component
public class CommandGateway {

    private ApplicationContext applicationContext;

    public CommandGateway(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void execute(Command command) {
        Handler handler = handlerFor(command);
        handler.handle(command);
    }

    private Handler handlerFor(Command command) {
        Map<String, Handler> handlers = applicationContext.getBeansOfType(Handler.class);
        Optional<Handler> handlerOptional = handlers.values().stream().
                filter((h) -> h.canHandle(command)).findFirst();
        return handlerOptional.orElseThrow(() ->
                new IllegalArgumentException("No handler found for " + command.getClass()));
    }

}
