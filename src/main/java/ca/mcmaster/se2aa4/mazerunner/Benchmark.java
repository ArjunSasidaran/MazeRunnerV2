package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Benchmark {

    private static final Logger logger = LogManager.getLogger();

    private Maze maze;
    private MazeSolverFactory solverFactory;

    public Benchmark(Maze maze){
        this.maze = maze;
        this.solverFactory = new MazeSolverFactory();
    }

    public double calculateMazeLoadTime(String filepath) throws Exception{
        long startTime = System.nanoTime();
        Maze maze = new Maze(filepath);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        double milliseconds = elapsedTime / 1000000.0;
        double roundedTime = Math.round(milliseconds * 100.0) / 100.0;

        return roundedTime;

    }

    public double calculateTime(String method) throws Exception{
        long startTime = System.nanoTime();
        MazeSolver mazeSolver = solverFactory.createSolver(method);
        Path path = mazeSolver.solve(maze);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        // convert nano to milli
        double milliseconds = elapsedTime / 1000000.0;
        // rounds to two decimal places
        double roundedTime = Math.round(milliseconds * 100.0) / 100.0;

        return roundedTime;

    }

    public double calculateSpeed(String method, String baseline) throws Exception{

        MazeSolver methodSolver = solverFactory.createSolver(method);
        MazeSolver baselineSolver = solverFactory.createSolver(baseline);

        Path methodPath = methodSolver.solve(maze);
        Path baselinePath = baselineSolver.solve(maze);

        String methodString = methodPath.getCanonicalForm();
        String baselineString = baselinePath.getCanonicalForm();

        double speed = (double) methodString.length() / baselineString.length();
        speed = Math.round(speed * 100.0) / 100.0;

        return speed;
    }


    
}
