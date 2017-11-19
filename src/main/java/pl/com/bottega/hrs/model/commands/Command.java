package pl.com.bottega.hrs.model.commands;

public interface Command {

    default void validate(ValidationErrors validationErrors) {

    }

}
