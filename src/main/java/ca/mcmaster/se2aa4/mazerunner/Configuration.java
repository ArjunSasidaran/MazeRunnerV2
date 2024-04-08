package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Benchmark;
import ca.mcmaster.se2aa4.mazerunner.Path;

public class Configuration {

    private static final Logger logger = LogManager.getLogger();

    private CommandLineParser parser;
    private Options options;
    private MazeSolverFactory solverFactory;

    public Configuration(){
        this.parser = new DefaultParser();
        this.options = getParserOptions();
        this.solverFactory = new MazeSolverFactory();
    }

    public void configure(String [] args) throws Exception{
        try {
            CommandLine cmd = parser.parse(options, args);
            processCmdLine(cmd);
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
        }
    }

    private void processCmdLine(CommandLine cmd) throws Exception {
        Maze maze = loadMaze(cmd.getOptionValue("i"));
        
        if (cmd.hasOption("p")) {
            Path path = new Path(cmd.getOptionValue("p"));
            if (maze.validatePath(path)) {
                System.out.println("correct path");
            } else {
                System.out.println("incorrect path");
            }
        } else if (cmd.hasOption("method")) {
            processMazeSolve(cmd, maze);
            if(cmd.hasOption("baseline")){
                processBaseline(cmd, maze);
            }
        }
        else{
            System.out.println("Invalid Argument");
        }
    }

    private void processMazeSolve(CommandLine cmd, Maze maze) throws Exception{
        String method = cmd.getOptionValue("method");
        Path path = this.solverFactory.createSolver(method).solve(maze);
        System.out.println(path.getFactorizedForm());
    }

    private void processBaseline(CommandLine cmd, Maze maze) throws Exception{
        String method = cmd.getOptionValue("method", "righthand");
        String baseline = cmd.getOptionValue("baseline");
        Benchmark benchmark = new Benchmark(maze, method, baseline);
        System.out.println("Speed: " + benchmark.calculateSpeed());
        System.out.println(method + " Time: " + benchmark.calculateTime(method));
        System.out.println(baseline + " Time: " + benchmark.calculateTime(baseline));
    }

    private Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));
        options.addOption(new Option("baseline", true, "Specify which path computation algorithm will be tested"));

        return options;
    }

    private Maze loadMaze(String filePath) throws Exception{
        return new Maze(filePath);
    }
    
}
