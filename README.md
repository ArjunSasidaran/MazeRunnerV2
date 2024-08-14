# Maze Runner, Take Two

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory.
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by
  the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’.
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
- The program can take a path as input and verify if it's a legit one.

## How to run this software?

To build the program, simply package it with Maven:

```
mosser@azrael A1-Template % mvn -q clean package 
```

### Delivered version

#### Command line arguments

The program should use the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-method {tremaux, righthand, bfs}`: specifies which path computation method to use. (default is right hand)
- `-baseline {tremaux, righthand, bfs}`: specifies which path computation method as a comparision baseline. When run in benchmark mode, the code will:
    - Print on stdout the time spent loading the maze from the file

    -   Print on stdout the time spent exploring the maze using the provided `-method`

    - Print on stdout the time spent exploring the maze using the provided `-baseline`

    - Print on stdout the improvement on the path as a speedup: Speedup = |path| baseline / |path| method

#### Examples

When no logs are activated, the programs only print the computed path on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt
4F
mosser@azrael A1-Template %
```

If a given path is correct, the program prints the message `correct path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
correct path
mosser@azrael A1-Template %
```

If a given path is incorrect, the program prints the message `incorrect path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
inccorrect path
mosser@azrael A1-Template %
```
If a `method` and `baseline` is passed in, the program will print out the `path` of the method, the `speed`, and the `stdout time` for `baseline`, `method` and `maze load time`.

```
java -jar target/mazerunner.jar -i ./examples/direct.maz.txt -method bfs -baseline righthand
F R 2F L 4F R 2F L 2F
Speed: 1.26
bfs Time: 0.3
righthand Time: 1.06
Maze Load Time: 0.26
```

