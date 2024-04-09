package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();

    private boolean isValidMove(Position pos, boolean[][] visited, Maze maze) {
        int x = pos.x();
        int y = pos.y();
        if(x >= 0 && x < maze.getSizeX() && y >= 0 && y < maze.getSizeY() && !maze.isWall(pos) && !visited[x][y])
            return true;
        else
            return false;
    }


    @Override
    public Path solve(Maze maze) {

        boolean[][] visited = new boolean[maze.getSizeX()][maze.getSizeY()]; 
        visited[maze.getStart().x()][maze.getStart().y()] = true;
        
        Path path = new Path();
        Position position = maze.getStart();
        Direction dir = Direction.RIGHT;

        PathPositionDirection pathAndPosition = new PathPositionDirection(path, position, dir);

        Queue<PathPositionDirection> queue = new LinkedList();

        queue.add(pathAndPosition);

        while(!queue.isEmpty()) {
            PathPositionDirection currentSquare = queue.poll();
            Path currentPath = currentSquare.getPath();
            Position currentPos = currentSquare.getPosition();
            Direction direction = currentSquare.getDirection();
            
            if(currentPos.x() == maze.getEnd().x() && currentPos.y() == maze.getEnd().y()){
                return currentPath;
            }
            
            // check all directions
            for (int i = 0; i < 4; i++) {
                // get the direction
                Direction nextDir = direction;
                Path newPath = currentPath.copyPath();
            
                switch (i) {
                    case 0:
                        newPath.addStep('F');
                        break;
                    case 1:
                        nextDir = direction.turnRight();
                        newPath.addStep('R');
                        newPath.addStep('F');
                        break;
                    case 2:
                        nextDir = direction.turnLeft();
                        newPath.addStep('L');
                        newPath.addStep('F');
                        break;
                    case 3:
                        nextDir = direction.turnRight().turnRight();
                        newPath.addStep('R');
                        newPath.addStep('R');
                        newPath.addStep('F');
                        break;
                }
            
                Position nextMove = currentPos.move(nextDir);
                
                if (isValidMove(nextMove, visited, maze)) {
                    queue.add(new PathPositionDirection(newPath, nextMove, nextDir));
                    visited[nextMove.x()][nextMove.y()] = true;
                }
            }
        }
            
        logger.debug("Current Position: " + position.toString() + "\n Current Path: " + path.getCanonicalForm());
        return null;

    }
}
