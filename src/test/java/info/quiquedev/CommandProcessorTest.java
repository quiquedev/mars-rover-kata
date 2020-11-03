package info.quiquedev;

import info.quiquedev.CommandProcessor.CommandProcessorException;
import info.quiquedev.Position.Direction;
import static info.quiquedev.Position.Direction.EAST;
import static info.quiquedev.Position.Direction.NORTH;
import static info.quiquedev.Position.Direction.SOUTH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CommandProcessorTest {
    @Test
    public void shouldFailIfInitialXIsNotValid() {
        // given
        final String[] args = {"A", "2", "EAST", "F"};

        try {
            // when
            CommandProcessor.processCommandsFromArgs(args);
            fail("exception should be thrown");
        } catch (final Exception e) {
            // then
            assertTrue(e instanceof CommandProcessorException);
            assertEquals("Coordinate value A is not a valid integer.", e.getMessage());
        }
    }

    @Test
    public void shouldFailIfInitialYIsNotValid() {
        // given
        final String[] args = {"1", "L", "EAST", "F"};

        try {
            // when
            CommandProcessor.processCommandsFromArgs(args);
            fail("exception should be thrown");
        } catch (final Exception e) {
            // then
            assertTrue(e instanceof CommandProcessorException);
            assertEquals("Coordinate value L is not a valid integer.", e.getMessage());
        }
    }

    @Test
    public void shouldFailIfInitialDirectionIsNotValid() {
        // given
        final String[] args = {"5", "2", "AST", "F"};

        try {
            // when
            CommandProcessor.processCommandsFromArgs(args);
            fail("exception should be thrown");
        } catch (final Exception e) {
            // then
            assertTrue(e instanceof CommandProcessorException);
            assertEquals("Direction must be one of: [NORTH, SOUTH, EAST, WEST].", e.getMessage());
        }
    }

    @Test
    public void shouldFailIfAnyOfTheCommandsIsNotValid() {
        // given
        final String[] args = {"5", "2", "EAST", "FRF"};

        try {
            // when
            CommandProcessor.processCommandsFromArgs(args);
            fail("exception should be thrown");
        } catch (final Exception e) {
            // then
            assertTrue(e instanceof CommandProcessorException);
            assertEquals("Invalid command list FRF. Commands must be one of: [F].", e.getMessage());
        }
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnCenter() {
        // given
        final String[] args = {"0", "0", "NORTH", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(0, 2, NORTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnUpperLeft() {
        // given
        final String[] args = {"-4", "2", "NORTH", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-4, 4, NORTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnUpperRight() {
        // given
        final String[] args = {"4", "1", "NORTH", "FFF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, 4, NORTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnLowerLeft() {
        // given
        final String[] args = {"-7", "-2", "NORTH", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-7, 0, NORTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnLowerRight() {
        // given
        final String[] args = {"4", "-2", "NORTH", "FFFF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, 2, NORTH), endPosition);
    }

    ///
    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnCenter() {
        // given
        final String[] args = {"0", "0", "SOUTH", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(0, -2, SOUTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnUpperLeft() {
        // given
        final String[] args = {"-4", "2", "SOUTH", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-4, 0, SOUTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnUpperRight() {
        // given
        final String[] args = {"4", "1", "SOUTH", "FFF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, -2, SOUTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnLowerLeft() {
        // given
        final String[] args = {"-7", "-2", "SOUTH", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-7, -4, SOUTH), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnLowerRight() {
        // given
        final String[] args = {"4", "-2", "SOUTH", "FFFF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, -6, SOUTH), endPosition);
    }


    @Test
    public void shouldProcessCommandFOnVehiclesFacingOnEastCenter() {
        // given
        final String[] args = {"0", "0", "EAST", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(2, 0,EAST), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnUpperLeft() {
        // given
        final String[] args = {"-4", "2", "EAST", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-2, 2, EAST), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnUpperRight() {
        // given
        final String[] args = {"4", "1", "EAST", "FFF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(7, 1, EAST), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnLowerLeft() {
        // given
        final String[] args = {"-7", "-2", "EAST", "FF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-5, -2, EAST), endPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnLowerRight() {
        // given
        final String[] args = {"4", "-2", "EAST", "FFFF"};

        // when
        final Position endPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(8, -2, EAST), endPosition);
    }
}