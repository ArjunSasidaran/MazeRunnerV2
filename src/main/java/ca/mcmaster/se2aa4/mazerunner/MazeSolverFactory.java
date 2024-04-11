package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolverFactory {

    public MazeSolver createSolver(String method) throws Exception{
        switch (method) {
            case "righthand":
                return new RightHandSolver();
            case "tremaux":
                return new TremauxSolver();
            case "bfs":
                return new BFSSolver();
            default:
                throw new Exception("Maze solving method " + method + " not supported.");
        }

    }
    
}
