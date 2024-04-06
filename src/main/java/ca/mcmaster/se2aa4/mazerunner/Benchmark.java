package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Benchmark {

    private static final Logger logger = LogManager.getLogger();

    Maze maze;
    String method;
    String baseline;

    public Benchmark(Maze maze, String method, String baseline){
        this.maze = maze;
        this.method = method;
        this.baseline = baseline;

    }

    private Path solveMaze(String solvingMethod) throws Exception {
        logger.debug(solvingMethod + " algorithm chosen.");
        MazeSolver solver;
        switch (solvingMethod) {
            case "righthand":
                solver = new RightHandSolver();
                break;
            case "tremaux":
                solver = new TremauxSolver();
                break;
            case "bfs":
                solver = new BFSSolver();
                break;
            default:
                throw new Exception("Maze solving method '" + solvingMethod + "' not supported.");
        }
        return solver.solve(maze);
    }



    public double calculateTime(String method1) throws Exception{
        long startTime = System.nanoTime();
        Path path = solveMaze(method1);
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        double milliseconds = elapsedTime / 1000000.0;
        double roundedTime = Math.round(milliseconds * 100.0) / 100.0;

        return roundedTime;

    }

    public String calculateSpeed() throws Exception{

        Path methodPath = solveMaze(method);
        Path baselinePath = solveMaze(baseline);

        double speed = (double) baselinePath.getCanonicalForm().length()/methodPath.getCanonicalForm().length();
        String formattedSpeed = String.format("%.2f", speed);

        return formattedSpeed;
    }


    
}
