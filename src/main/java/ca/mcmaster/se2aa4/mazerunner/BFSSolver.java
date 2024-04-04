package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSSolver implements MazeSolver {
    private static final Logger logger = LogManager.getLogger();

    private boolean isValidMove(Position pos, boolean[][] visited, Maze maze) {
        int x = pos.x();
        int y = pos.y();
        return x >= 0 && x < maze.getSizeX() && y >= 0 && y < maze.getSizeY()
                && !maze.isWall(pos) && !visited[x][y];
    }

    @Override
    public Path solve(Maze maze) {

        Path path = new Path();
        
        Queue<PathPositionDirection> queue = new LinkedList();
        boolean[][] visited = new boolean[maze.getSizeX()][maze.getSizeY()]; 

        Position position = maze.getStart();

        visited[maze.getStart().x()][maze.getStart().y()] = true;

        Direction dir = Direction.RIGHT;

        PathPositionDirection pathAndPosition = new PathPositionDirection(path, position, dir);

        queue.add(pathAndPosition);

        while(!queue.isEmpty()) {
            PathPositionDirection currentSquare = queue.poll();
            Path currentPath = currentSquare.getPath();
            Position currentPos = currentSquare.getPosition();
            Direction direction = currentSquare.getDirection();
            //logger.info(currentPath.getFactorizedForm());
            //logger.info(currentPos.x());
            //logger.info(currentPos.y());
            //logger.info(direction);
            
            if(currentPos.x() == maze.getEnd().x() && currentPos.y() == maze.getEnd().y()){
                long endTime = System.currentTimeMillis();
                System.out.println("ElpasedTime: " + (endTime - startTime));
                return currentPath;
            }
            
            for (int i = 0; i < 4; i++) {
                Direction nextDir = direction.turnRight();
                Position nextMove = currentPos.move(nextDir);
                Path newPath = currentPath.copyPath();

                if (isValidMove(nextMove, visited, maze)) {
                    if(i == 3){
                        newPath.addStep('F');
                        queue.add(new PathPositionDirection(newPath, nextMove, nextDir));
                        visited[nextMove.x()][nextMove.y()] = true;
                    }
                    else if(i == 2){
                        newPath.addStep('L');
                        newPath.addStep('F');
                        queue.add(new PathPositionDirection(newPath, nextMove, nextDir));
                        visited[nextMove.x()][nextMove.y()] = true;
                    }
                    else if(i == 1){
                        newPath.addStep('R');
                        newPath.addStep('R');
                        newPath.addStep('F');
                        queue.add(new PathPositionDirection(newPath, nextMove, nextDir));
                        visited[nextMove.x()][nextMove.y()] = true;
                    }
                    else{
                        newPath.addStep('R');
                        newPath.addStep('F');
                        queue.add(new PathPositionDirection(newPath, nextMove, nextDir));
                        visited[nextMove.x()][nextMove.y()] = true;
                    }
                   
                }

                direction = nextDir;
            }


            /* 
            Position nextMove = currentPos.move(dir);
            
            if(visited[nextMove.x()][nextMove.y()] == false && !maze.isWall(nextMove)){
                currentPath = currentPath.addStep('F');
                currentPos = currentPos.move(dir);
                visited[currentPos.x()][currentPos.y()] = true;
                PathPosition temp = new PathPosition(currentPath,currentPos);
                queue.add(temp);
            }
            */

           
           
        }
        logger.debug("Current Position: " + position.toString() + "\n Current Path: " + path.getCanonicalForm());
        return null;
    }
}
