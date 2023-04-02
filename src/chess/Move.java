package chess;


public class Move {

    public Position start;
    public Position end;

    public Move(Position pos1, Position pos2) {
        start = pos1;
        end = pos2;
    }

}
