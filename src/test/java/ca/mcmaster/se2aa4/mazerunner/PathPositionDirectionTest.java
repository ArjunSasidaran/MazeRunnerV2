package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.PathPositionDirection;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class PathPositionDirectionTest {

    private Path path;
    private Position position;
    private Direction direction;
    private PathPositionDirection pathPositionDirection;

    @BeforeEach
    public void setUp() throws Exception {
        
        path = new Path("FRLFFRFFRR");
        position = new Position(0, 0);
        direction = Direction.UP;
        pathPositionDirection = new PathPositionDirection(path, position, direction);
        
    }

    @Test
    public void testGetPath() {
        assertEquals(path, pathPositionDirection.getPath());
    }

    @Test
    public void testGetPosition() {
        assertEquals(position, pathPositionDirection.getPosition());
    }

    @Test
    public void testGetDirection() {
        assertEquals(direction, pathPositionDirection.getDirection());
    }
}
