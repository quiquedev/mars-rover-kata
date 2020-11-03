package info.quiquedev;

import info.quiquedev.CommandProcessor.CommandProcessorException;
import static info.quiquedev.Position.Direction.EAST;
import static info.quiquedev.Position.Direction.NORTH;
import static info.quiquedev.Position.Direction.SOUTH;
import static info.quiquedev.Position.Direction.WEST;
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
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(0, 2, NORTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnUpperLeft() {
        // given
        final String[] args = {"-4", "2", "NORTH", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-4, 4, NORTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnUpperRight() {
        // given
        final String[] args = {"4", "1", "NORTH", "FFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, 4, NORTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnLowerLeft() {
        // given
        final String[] args = {"-7", "-2", "NORTH", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-7, 0, NORTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingNorthOnLowerRight() {
        // given
        final String[] args = {"4", "-2", "NORTH", "FFFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, 2, NORTH), finalPosition);
    }

    ///
    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnCenter() {
        // given
        final String[] args = {"0", "0", "SOUTH", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(0, -2, SOUTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnUpperLeft() {
        // given
        final String[] args = {"-4", "2", "SOUTH", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-4, 0, SOUTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnUpperRight() {
        // given
        final String[] args = {"4", "1", "SOUTH", "FFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, -2, SOUTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnLowerLeft() {
        // given
        final String[] args = {"-7", "-2", "SOUTH", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-7, -4, SOUTH), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingSouthOnLowerRight() {
        // given
        final String[] args = {"4", "-2", "SOUTH", "FFFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(4, -6, SOUTH), finalPosition);
    }


    @Test
    public void shouldProcessCommandFOnVehiclesFacingOnEastCenter() {
        // given
        final String[] args = {"0", "0", "EAST", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(2, 0, EAST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnUpperLeft() {
        // given
        final String[] args = {"-4", "2", "EAST", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-2, 2, EAST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnUpperRight() {
        // given
        final String[] args = {"4", "1", "EAST", "FFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(7, 1, EAST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnLowerLeft() {
        // given
        final String[] args = {"-7", "-2", "EAST", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-5, -2, EAST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingEastOnLowerRight() {
        // given
        final String[] args = {"4", "-2", "EAST", "FFFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(8, -2, EAST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingOnWestCenter() {
        // given
        final String[] args = {"0", "0", "WEST", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-2, 0, WEST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingWestOnUpperLeft() {
        // given
        final String[] args = {"-4", "2", "WEST", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-6, 2, WEST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingWestOnUpperRight() {
        // given
        final String[] args = {"4", "1", "WEST", "FFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(1, 1, WEST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingWestOnLowerLeft() {
        // given
        final String[] args = {"-7", "-2", "WEST", "FF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(-9, -2, WEST), finalPosition);
    }

    @Test
    public void shouldProcessCommandFOnVehiclesFacingWestOnLowerRight() {
        // given
        final String[] args = {"4", "-2", "WEST", "FFFF"};

        // when
        final Position finalPosition = CommandProcessor.processCommandsFromArgs(args);

        // then
        assertEquals(new Position(0, -2, WEST), finalPosition);
    }

}