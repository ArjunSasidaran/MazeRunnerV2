package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Baseline {

    private static final Logger logger = LogManager.getLogger();

    Maze maze;
    String method;
    String baseline;

    public Baseline(Maze maze, String method, String baseline){
        this.maze = maze;
        this.method = method;
        this.baseline = baseline;

    }

    public double calculateTime(String method1) throws Exception{
        long startTime = System.nanoTime();

        MazeSolver solve = null;
        Path path;
        long endTime;

        switch (method1) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solve = new RightHandSolver();
                path = solve.solve(maze);
                endTime = System.nanoTime();
                break;
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solve = new TremauxSolver();
                path = solve.solve(maze);
                endTime = System.nanoTime();
                break;
            }
            case "bfs" ->{
                logger.debug("BFS algorithm chosen.");
                solve = new BFSSolver();
                path = solve.solve(maze);
                endTime = System.nanoTime();
                break;
            }
            default -> {
                throw new Exception("Maze solving method '" + method1 + "' not supported.");
            }
        }

        long elapsedTime = endTime - startTime;
        double milliseconds = elapsedTime / 1000000.0;
        double roundedTime = Math.round(milliseconds * 100.0) / 100.0;

        return roundedTime;

    }

    public String calculateSpeed() throws Exception{

        MazeSolver solve = null;
        Path methodPath;
        Path baselinePath;

        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solve = new RightHandSolver();
                methodPath = solve.solve(maze);
                break;
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solve = new TremauxSolver();
                methodPath = solve.solve(maze);
                break;
            }
            case "bfs" ->{
                logger.debug("BFS algorithm chosen.");
                solve = new BFSSolver();
                methodPath = solve.solve(maze);
                break;
            }
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported.");
            }
        }

        switch (baseline) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solve = new RightHandSolver();
                baselinePath = solve.solve(maze);
                break;
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solve = new TremauxSolver();
                baselinePath = solve.solve(maze);
                break;
            }
            case "bfs" ->{
                logger.debug("BFS algorithm chosen.");
                solve = new BFSSolver();
                baselinePath = solve.solve(maze);
                break;
            }
            default -> {
                throw new Exception("Maze solving method '" + baseline + "' not supported.");
            }
        }

        double speed = (double) baselinePath.getCanonicalForm().length()/methodPath.getCanonicalForm().length();
        String formattedSpeed = String.format("%.2f", speed);

        return formattedSpeed;
    }


    
}
