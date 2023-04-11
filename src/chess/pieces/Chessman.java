package chess.pieces;


import chess.Position;
import chess.SideColor;

import java.util.ArrayList;


public abstract class Chessman {

    public SideColor color;
    public boolean notMoved;
    public Position pos;
    public int value;
    public short imgSrc;

    public abstract Chessman copy(Chessman other);

    protected abstract void loadImage();

    public abstract ArrayList<Position> getMoves(Chessman[][] board);
}
