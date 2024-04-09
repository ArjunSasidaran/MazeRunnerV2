package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.*;

class BFSSolverTest {

    Maze maze;

    @BeforeEach
    public void setUp() throws Exception {
        String filePath = "/home/arjun/a3/a3-maze-runner-take-two-ArjunSasidaran/examples/direct.maz.txt";
        maze = new Maze(filePath);
    }

    @Test
    public void testSolve(){

        MazeSolver solver = new BFSSolver();

        Path path = solver.solve(maze);

        assertNotNull(path);
        assertEquals("F R 2F L 4F R 2F L 2F", path.getFactorizedForm()); 
    }
}