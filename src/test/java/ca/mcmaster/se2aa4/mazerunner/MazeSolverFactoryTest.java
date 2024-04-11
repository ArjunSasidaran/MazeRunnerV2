package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class MazeSolverFactoryTest {

    private MazeSolverFactory solveFactory;

    @BeforeEach
    public void setUp(){
        solveFactory = new MazeSolverFactory();
    }

    @Test
    public void testCreateRightHandSolver() {
        try {
            MazeSolver solver = solveFactory.createSolver("righthand");
            assertNotNull(solver);
            assertEquals(RightHandSolver.class, solver.getClass());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateTremauxSolver() {
        try {
            MazeSolver solver = solveFactory.createSolver("tremaux");
            assertNotNull(solver);
            assertEquals(TremauxSolver.class, solver.getClass());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateBFSSolver() {
        try {
            MazeSolver solver = solveFactory.createSolver("bfs");
            assertNotNull(solver);
            assertEquals(BFSSolver.class, solver.getClass());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCreateUnsupportedSolver() {
        try {
            MazeSolver solver = solveFactory.createSolver("dfs"); 
            fail("Expected an exception for unsupported type ");
        } catch (Exception e) {
            assertEquals("Maze solving method dfs not supported.", e.getMessage());
        }
    }
}
