package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Benchmark {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private String method;
    private String baseline;
    private MazeSolverFactory solverFactory = new MazeSolverFactory();

    public Benchmark(Maze maze, String method, String baseline){
        this.maze = maze;
        this.method = method;
        this.baseline = baseline;
    }


    public double calculateTime(String method1) throws Exception{
        long startTime = System.nanoTime();
        Path path = solverFactory.createSolver(method1).solve(maze);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        double milliseconds = elapsedTime / 1000000.0;
        double roundedTime = Math.round(milliseconds * 100.0) / 100.0;

        return roundedTime;

    }

    public String calculateSpeed() throws Exception{

        Path methodPath = solverFactory.createSolver(method).solve(maze);
        Path baselinePath = solverFactory.createSolver(baseline).solve(maze);

        double speed = (double) baselinePath.getCanonicalForm().length()/methodPath.getCanonicalForm().length();
        String formattedSpeed = String.format("%.2f", speed);

        return formattedSpeed;
    }


    
}
