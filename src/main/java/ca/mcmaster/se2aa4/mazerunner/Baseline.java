package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Baseline {

    private static final Logger logger = LogManager.getLogger();

    Maze maze;
    String method1;
    String baseline;
    long startTime;
    long endTime;
    Path path;

    public Baseline(Maze maze, String method1, String baseline){
        this.maze = maze;
        this.method1 = method1;
        this.baseline = baseline;

    }

    public long calculateTime(String method) throws Exception{
        startTime = System.currentTimeMillis();

        MazeSolver solve = null;

        switch (method) {
            case "righthand" -> {
                logger.debug("RightHand algorithm chosen.");
                solve = new RightHandSolver();
                path = solve.solve(maze);
                endTime = System.currentTimeMillis();
                break;
            }
            case "tremaux" -> {
                logger.debug("Tremaux algorithm chosen.");
                solve = new TremauxSolver();
                path = solve.solve(maze);
                endTime = System.currentTimeMillis();
                break;
            }
            case "bfs" ->{
                logger.debug("BFS algorithm chosen.");
                solve = new BFSSolver();
                path = solve.solve(maze);
                endTime = System.currentTimeMillis();
                break;
            }
            default -> {
                throw new Exception("Maze solving method '" + method + "' not supported.");
            }
        }

        long elapsedTime = endTime - startTime;
        return elapsedTime;

    }

    public int calculateSpeed(String method, String baseline) throws Exception{

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
        

        int speed =  baselinePath.getCanonicalForm().length()/methodPath.getCanonicalForm().length();
        return speed;
    }


    
}
