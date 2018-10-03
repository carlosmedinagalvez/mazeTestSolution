Maze Test
---------


We recognize that you may have questions about these requirements. For the purpose of this test, please make an assumption about the answer and note your assumptions in a readme.txt submitted with your solution. You should be ready to explain any assumptions and technical choices you have made during later interview stages.

There is no stated time limit. The important thing is to arrive at a solution that you are satisfied with and comfortable discussing with others.

The code must be representative of what you would produce “on the job”, by that we mean it must be clear, maintainable bug free and efficient.

The project is for intellij and uses maven; you should feel free to use any other tools you are more comfortable with.  

The test is based on exploring any arbitrary maze (one is provided).

User Story 1
------------
As a world famous explorer of Mazes I would like a maze to exist
So that I can explore it

Acceptance Criteria

* A Maze (as defined in maze.txt consists of walls 'X', Empty spaces ' ', one and only one Start point 'S' and one and only one exit 'F'
* After a maze has been created the number of walls and empty spaces should be available to me
* After a maze has been created I should be able to put in a co ordinate and know what exists at that point

User Story 2
------------

As a world famous explorer of Mazes I would like to exist in a maze and be able to navigate it
So that I can explore it

Acceptance Criteria

* Given a maze the explorer should be able to drop in to the Start point
* An explorer on a maze must be able to:
    Move forward
    Turn left and right
    Understand what is in front of them
    Understand all movement options from their given location
    Have a record of where they have been
* Once an explorer has exited a maze they must be able to state the route they took in an understandable fashion


Considerations
------------
* Assume the explorer robot can only move 1 step in one of 4 directions. Valid moves are: a) Up: (x,y) -> (x,y-1) b) Right East: (x,y) -> (x+1,y) c) Down: (x,y) -> (x,y+1) d) Left: (x,y) -> (x-1,y) note: positions are noted in zero-based coordinates

* Assume robot can only move through spaces " " within the maze

* The explorer should search for a path from the starting position "S" to the "F" position until it finds one or until it exhausts all possibilities. In addition, it should mark the path it finds (if any) in the maze.

* Assume the maze map is sealed completed in a way that no empty spaces are allowed in each edge of the maze map.

* Assume the explorer will randomly select an open route to proceed if there were more than one adjacent empty spaces to current square/cell.