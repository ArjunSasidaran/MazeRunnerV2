package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Benchmark;
import ca.mcmaster.se2aa4.mazerunner.Maze;
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
            System.err.println("Configuration failed.  Reason: " + e.getMessage());
            logger.error("Configuration failed.  Reason: " + e.getMessage());
        }
    }

    private void processCmdLine(CommandLine cmd) throws Exception {

        String filePath = cmd.getOptionValue("i");
        Maze maze = new Maze(filePath);
        
        if (cmd.hasOption("p")) {
            Path path = new Path(cmd.getOptionValue("p"));
            if (maze.validatePath(path)) {
                System.out.println("correct path");
            } else {
                System.out.println("incorrect path");
            }
        } 
        else if (cmd.hasOption("method")) {
            processMazeSolve(cmd, maze);
            if(cmd.hasOption("baseline")){
                processBaseline(cmd, maze,filePath);
            }
        } 
        else if(cmd.hasOption("baseline")){
            processBaseline(cmd, maze, filePath);
        }
        
        else{
            logger.error("Invalid Argument");
        }
    }

    private void processMazeSolve(CommandLine cmd, Maze maze) throws Exception{
        String method = cmd.getOptionValue("method");
        MazeSolver mazeSolver = solverFactory.createSolver(method);
        Path path = mazeSolver.solve(maze);
        System.out.println(path.getFactorizedForm());
    }

    private void processBaseline(CommandLine cmd, Maze maze, String filePath) throws Exception{

        String method;
        if(cmd.getOptionValue("method") != null){
            method = cmd.getOptionValue("method");
        }
        else{
            method = "righthand";
        }
        
        String baseline = cmd.getOptionValue("baseline");
        Benchmark benchmark = new Benchmark(maze);
        System.out.println("Speed: " + benchmark.calculateSpeed(method,baseline));
        System.out.println(method + " Time: " + benchmark.calculateTime(method));
        System.out.println(baseline + " Time: " + benchmark.calculateTime(baseline));
        System.out.println("Maze Load Time: " + benchmark.calculateMazeLoadTime(filePath));
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
}
