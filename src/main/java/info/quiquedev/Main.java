package info.quiquedev;

import info.quiquedev.CommandProcessor.CommandProcessorException;

public class Main {
    public static void main(String[] args) {
        try {
            final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);
            System.out.printf("final position: %s%n", finalPosition);
        } catch (final CommandProcessorException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.printf("Please contact IT department asap. Unknown error: %s%n", e.getMessage());
        }
    }
}

