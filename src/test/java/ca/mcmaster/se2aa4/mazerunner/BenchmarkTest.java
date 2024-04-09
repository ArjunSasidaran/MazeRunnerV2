package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Benchmark;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkTest {

    private Maze maze;
    private Benchmark benchmark;


    @BeforeEach
    public void setUp() throws Exception {
        String filePath = "/home/arjun/a3/a3-maze-runner-take-two-ArjunSasidaran/examples/direct.maz.txt";
        maze = new Maze(filePath);
        benchmark = new Benchmark(maze, "bfs", "righthand");
    }

    @Test
    void testCalculateTime() {
        
        try{
            double time1 = benchmark.calculateTime("righthand");
            double time2 = benchmark.calculateTime("bfs");
            assertTrue(time1 >= 0, "Time should not be negative.");
            assertTrue(time2 >= 0, "Time should not be negative.");
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    @Test
    void testCalculateSpeed() {

        try{
            String speed = benchmark.calculateSpeed();
            assertNotNull(speed, "Speed should not be null.");
        }
        catch(Exception e){
            System.out.println(e);
        }
      
    }
}
