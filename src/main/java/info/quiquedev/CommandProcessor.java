package info.quiquedev;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandProcessor {
    private static final int INITIAL_X_ARG = 0;
    private static final int INITIAL_Y_ARG = 1;
    private static final int INITIAL_DIRECTION_ARG = 2;
    private static final int COMMANDS_ARG = 3;

    static class CommandProcessorException extends RuntimeException {
        CommandProcessorException(final String msg) {
            super(msg);
        }
    }

    enum Command {
        F;

        static final Map<Command, Function<Position, Position>> ACTIONS = Map.of(F, p -> switch (p.direction()) {
            case NORTH -> new Position(p.x(), p.y() + 1, p.direction());
            case SOUTH -> new Position(p.x(), p.y() - 1, p.direction());
            case EAST -> new Position(p.x() + 1, p.y(), p.direction());
            case WEST -> new Position(p.x() - 1, p.y(), p.direction());
        });
    }

    public static Position processCommandsFromArgs(final String[] args) throws CommandProcessorException {
        verifyNumberOfArgs(args);

        final int initialX = parseCoordinate(args[INITIAL_X_ARG]);
        final int initialY = parseCoordinate(args[INITIAL_Y_ARG]);
        final Position.Direction initialDirection = parseDirection(args[INITIAL_DIRECTION_ARG]);
        final List<Command> commands = parseCommands(args[COMMANDS_ARG]);

        final Position position = new Position(initialX, initialY, initialDirection);

        return applyCommands(position, commands);
    }

    private static void verifyNumberOfArgs(String[] args) {
        if (args.length != 4) {
            throw new CommandProcessorException("Args must be initialX initialY initialDirection command");
        }
    }

    private static Position applyCommands(final Position position, final List<CommandProcessor.Command> commands) {
        final Function<Position, Position> combinedActions = commands.stream().map(c ->
                Optional.of(CommandProcessor.Command.ACTIONS.getOrDefault(c, null))
                        .orElseThrow(() -> new CommandProcessorException("No action found for command %s"
                                .formatted(c))))
                .reduce(Function.identity(), Function::andThen);


        return combinedActions.apply(position);
    }

    private static int parseCoordinate(final String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            throw new CommandProcessorException(MessageFormat.format("Coordinate value {0} is not a valid integer.",
                    value));
        }
    }

    private static Position.Direction parseDirection(final String value) {
        try {
            return Position.Direction.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new CommandProcessorException(MessageFormat.format("Direction must be one of: {0}.",
                    Arrays.toString(Position.Direction.values())));
        }
    }

    private static List<CommandProcessor.Command> parseCommands(final String value) {
        try {
            return value.chars()
                    .mapToObj(e -> String.valueOf((char) e))
                    .map(CommandProcessor.Command::valueOf)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CommandProcessorException("Invalid command list %s. Commands must be one of: %s."
                    .formatted(value, Arrays.toString(CommandProcessor.Command.values())));
        }

    }
}
