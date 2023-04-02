package chess.pieces;


import chess.Position;
import chess.SideColor;

import java.util.ArrayList;


public abstract class Chessman {

    public SideColor color;
    public boolean notMoved;
    public Position pos;
    public int Value;

    public short imgSrc;

    abstract void loadImage();


    public abstract ArrayList<Position> GetMoves(Chessman[][] board);
}
