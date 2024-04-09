package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.*;

class BFSSolverTest {

    Maze maze;

    @Test
    public void testDirectMaze() throws Exception{

        String filePath = "./examples/direct.maz.txt";
        maze = new Maze(filePath);

        MazeSolver solver = new BFSSolver();

        Path path = solver.solve(maze);

        assertNotNull(path);
        assertEquals("F R 2F L 4F R 2F L 2F", path.getFactorizedForm()); 
    }

    @Test
    public void testRectangleMaze() throws Exception{

        String filePath = "./examples/rectangle.maz.txt";
        maze = new Maze(filePath);

        MazeSolver solver = new BFSSolver();

        Path path = solver.solve(maze);
        assertNotNull(path);
        assertEquals("F L 11F R 2F R 4F L 8F L 2F R 4F L 2F R 10F R 4F L 6F R 2F L 4F R 4F L 10F L 2F R 4F R F L F", path.getFactorizedForm()); 
    }

    @Test
    public void testGiantMaze() throws Exception{

        String filePath = "./examples/giant.maz.txt";
        maze = new Maze(filePath);

        MazeSolver solver = new BFSSolver();

        Path path = solver.solve(maze);

        assertNotNull(path);
        assertEquals("F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F", path.getFactorizedForm()); 
    }
}