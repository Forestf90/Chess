package chess.pieces;

import chess.Position;
import chess.SideColor;

import java.util.ArrayList;

public class Bishop extends Chessman {

    public Bishop(SideColor col, int x, int y) {
        color = col;
        pos = new Position(x, y);
        value = 350;
        loadImage();
    }

    @Override
    public Chessman copy(Chessman other) {
        return new Bishop(color, pos.x, pos.y);
    }

    @Override
    protected void loadImage() {
        if (this.color == SideColor.WHITE) {
            imgSrc = 3;
        } else imgSrc = 9;
    }

    @Override
    public ArrayList<Position> getMoves(Chessman[][] board) {

        ArrayList<Position> moves = new ArrayList<Position>();

        //NE
        int i = 1;

        while (this.pos.x + i <= 7 && this.pos.y - i >= 0) {

            if (board[pos.x + i][pos.y - i] == null) {
                moves.add(new Position(this.pos.x + i, this.pos.y - i));
            }

            if (board[pos.x + i][pos.y - i] != null) {
                if (board[this.pos.x + i][this.pos.y - i].color != this.color) {
                    moves.add(new Position(this.pos.x + i, this.pos.y - i));
                    break;
                } else
                    break;
            }
            i++;
        }

        //NW
        i = 1;
        while (this.pos.x - i >= 0 && this.pos.y - i >= 0) {

            if (board[pos.x - i][pos.y - i] == null) {
                moves.add(new Position(this.pos.x - i, this.pos.y - i));
            }

            if (board[pos.x - i][pos.y - i] != null) {
                if (board[this.pos.x - i][this.pos.y - i].color != this.color) {
                    moves.add(new Position(this.pos.x - i, this.pos.y - i));
                    break;
                } else
                    break;
            }
            i++;
        }

        //SE
        i = 1;
        while (this.pos.x + i <= 7 && this.pos.y + i <= 7) {

            if (board[pos.x + i][pos.y + i] == null) {
                moves.add(new Position(this.pos.x + i, this.pos.y + i));
            }

            if (board[pos.x + i][pos.y + i] != null) {
                if (board[this.pos.x + i][this.pos.y + i].color != this.color) {
                    moves.add(new Position(this.pos.x + i, this.pos.y + i));
                    break;
                } else
                    break;
            }
            i++;
        }

        //SW
        i = 1;

        while (this.pos.x - i >= 0 && this.pos.y + i <= 7) {

            if (board[pos.x - i][pos.y + i] == null) {
                moves.add(new Position(this.pos.x - i, this.pos.y + i));

            }

            if (board[pos.x - i][pos.y + i] != null) {
                if (board[this.pos.x - i][this.pos.y + i].color != this.color) {
                    moves.add(new Position(this.pos.x - i, this.pos.y + i));
                    break;
                } else
                    break;
            }
            i++;
        }


        return moves;

    }
}
