package ca.mcmaster.se2aa4.mazerunner;

public class PathPositionDirection{

    private Path path;
    private Position position;
    private Direction direction;

    public PathPositionDirection(Path path, Position position, Direction direction){
        this.path = path;
        this.position = position;
        this.direction = direction;
    }

    public Path getPath(){
        return this.path;
    }

    public Position getPosition(){
        return this.position;
    }
    public Direction getDirection(){
        return this.direction;
    }


}