package chess.pieces;

import chess.Position;
import chess.SideColor;

import java.util.ArrayList;

public class Knight extends Chessman {

    public Knight(SideColor col, int x, int y) {
        color = col;
        pos = new Position(x, y);
        value = 350;
        loadImage();

    }

    @Override
    public Chessman copy(Chessman other) {
        return new Knight(color, pos.x, pos.y);
    }

    @Override
    protected void loadImage() {
        if (this.color == SideColor.WHITE) {
            imgSrc = 4;
        } else imgSrc = 10;
    }

    @Override
    public ArrayList<Position> getMoves(Chessman[][] board) {

        ArrayList<Position> moves = new ArrayList<Position>();

        if (pos.x + 1 <= 7 && pos.y + 2 <= 7) {

            if (board[pos.x + 1][pos.y + 2] == null) {
                moves.add(new Position(this.pos.x + 1, this.pos.y + 2));
            }
            if (board[pos.x + 1][pos.y + 2] != null) {
                if (board[pos.x + 1][pos.y + 2].color != this.color) {

                    moves.add(new Position(this.pos.x + 1, this.pos.y + 2));
                }
            }

        }
        if (pos.x + 2 <= 7 && pos.y + 1 <= 7) {
            if (board[pos.x + 2][pos.y + 1] == null) {
                moves.add(new Position(this.pos.x + 2, this.pos.y + 1));
            }
            if (board[pos.x + 2][pos.y + 1] != null) {
                if (board[pos.x + 2][pos.y + 1].color != this.color) {

                    moves.add(new Position(this.pos.x + 2, this.pos.y + 1));
                }
            }
        }
        if (pos.x + 1 <= 7 && pos.y - 2 >= 0) {
            if (board[pos.x + 1][pos.y - 2] == null) {
                moves.add(new Position(this.pos.x + 1, this.pos.y - 2));
            }
            if (board[pos.x + 1][pos.y - 2] != null) {
                if (board[pos.x + 1][pos.y - 2].color != this.color) {

                    moves.add(new Position(this.pos.x + 1, this.pos.y - 2));
                }
            }
        }

        if (pos.x + 2 <= 7 && pos.y - 1 >= 0) {

            if (board[pos.x + 2][pos.y - 1] == null) {
                moves.add(new Position(this.pos.x + 2, this.pos.y - 1));
            }
            if (board[pos.x + 2][pos.y - 1] != null) {
                if (board[pos.x + 2][pos.y - 1].color != this.color) {

                    moves.add(new Position(this.pos.x + 2, this.pos.y - 1));
                }
            }
        }


        if (pos.x - 1 >= 0 && pos.y + 2 <= 7) {

            if (board[pos.x - 1][pos.y + 2] == null) {
                moves.add(new Position(this.pos.x - 1, this.pos.y + 2));
            }
            if (board[pos.x - 1][pos.y + 2] != null) {
                if (board[pos.x - 1][pos.y + 2].color != this.color) {

                    moves.add(new Position(this.pos.x - 1, this.pos.y + 2));
                }
            }
        }


        if (pos.x - 2 >= 0 && pos.y + 1 <= 7) {

            if (board[pos.x - 2][pos.y + 1] == null) {
                moves.add(new Position(this.pos.x - 2, this.pos.y + 1));
            }
            if (board[pos.x - 2][pos.y + 1] != null) {
                if (board[pos.x - 2][pos.y + 1].color != this.color) {

                    moves.add(new Position(this.pos.x - 2, this.pos.y + 1));
                }
            }
        }

        if (pos.x - 1 >= 0 && pos.y - 2 >= 0) {
            if (board[pos.x - 1][pos.y - 2] == null) {
                moves.add(new Position(this.pos.x - 1, this.pos.y - 2));
            }
            if (board[pos.x - 1][pos.y - 2] != null) {
                if (board[pos.x - 1][pos.y - 2].color != this.color) {

                    moves.add(new Position(this.pos.x - 1, this.pos.y - 2));
                }
            }
        }

        if (pos.x - 2 >= 0 && pos.y - 1 >= 0) {

            if (board[pos.x - 2][pos.y - 1] == null) {
                moves.add(new Position(this.pos.x - 2, this.pos.y - 1));
            }
            if (board[pos.x - 2][pos.y - 1] != null) {
                if (board[pos.x - 2][pos.y - 1].color != this.color) {

                    moves.add(new Position(this.pos.x - 2, this.pos.y - 1));
                }
            }
        }

        return moves;

    }
}
