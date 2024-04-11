package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Benchmark;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkTest {

    private Maze maze;
    private Benchmark benchmark;
    private String filePath;


    @BeforeEach
    public void setUp() throws Exception {
        filePath = "./examples/direct.maz.txt";
        maze = new Maze(filePath);
        benchmark = new Benchmark(maze);
    }

    @Test
    void testMazeLoadTime() throws Exception{
        double time = benchmark.calculateMazeLoadTime(filePath);
        assertTrue(time >= 0, "Time should not be negative.");
        assertNotNull(time, "Time should not be null.");
    }


    @Test
    void testCalculateTimeRightHand() throws Exception{
        double time1 = benchmark.calculateTime("righthand");
        assertNotNull(time1, "Time should not be null.");
        assertTrue(time1 >= 0, "Time should not be negative.");     
    }

    @Test
    void testCalculateTimeBFS() throws Exception{
        double time1 = benchmark.calculateTime("bfs");
        assertNotNull(time1, "Time should not be null.");
        assertTrue(time1 >= 0, "Time should not be negative.");     
    }

    @Test
    void testCalculateTimeTremaux() throws Exception{
        double time1 = benchmark.calculateTime("tremaux");
        assertNotNull(time1, "Time should not be null.");
        assertTrue(time1 >= 0, "Time should not be negative.");     
    }

    @Test
    public void testCalculateSpeed1() throws Exception{
        String speed = benchmark.calculateSpeed("bfs","righthand");
        assertTrue(Double.valueOf(speed) >= 0, "speed should be non negative");
        assertNotNull(speed, "Speed should not be null.");
    }

    @Test
    public void testCalculateSpeed2() throws Exception{
        String speed = benchmark.calculateSpeed("bfs","tremaux");
        assertTrue(Double.valueOf(speed) >= 0, "speed should be non negative");
        assertNotNull(speed, "Speed should not be null.");
    }

    @Test
    public void testCalculateSpeed3() throws Exception{
        String speed = benchmark.calculateSpeed("tremaux","righthand");
        assertTrue(Double.valueOf(speed) >= 0, "speed should be non negative");
        assertNotNull(speed, "Speed should not be null.");
    }
}
