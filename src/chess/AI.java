package chess;

import chess.panels.GamePanel;
import chess.pieces.Chessman;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AI {
    static int currentDepth;
    static int maxDepth = 4;

    public static Move minMax(Chessman[][] board, SideColor aiCol, int alpha, int beta) {
        int bestScore = alpha;
        int bestID = 0;
        int move, score;
        int i = 0;
        List<Move> allMoves = getAIMoves(aiCol, board);
        Collections.shuffle(allMoves);
        Chessman[][] tempBoard = Arrays.stream(board).map(arr -> Arrays.stream(arr).map(chess -> chess == null ? null : chess.copy(chess))
                .toArray(Chessman[]::new)).toArray(Chessman[][]::new);

        for (Move p : allMoves) {
            score = getScore(aiCol, tempBoard);
            moveChessmanAI(p.end, p.start, tempBoard);
            move = min(currentDepth + 1, aiCol, aiCol.swapColor(), tempBoard, alpha, beta);
            score += move;
            moveChessmanAI(p.start, p.end, tempBoard);
            if (score > bestScore) {
                bestID = i;
                bestScore = score;
            }
            i++;
        }
        return allMoves.get(bestID);
    }

    public static int min(int depth, SideColor aiCol, SideColor turnCol, Chessman[][] board, int alpha, int beta) {
        ArrayList<Move> allMoves = getAIMoves(turnCol, board);
        if (depth == maxDepth || allMoves.size() == 0) {
            return getScore(aiCol, board);
        }
        int move;
        //int score;
        int bestScore = beta;
        Chessman[][] tempBoard = Arrays.stream(board).map(Chessman[]::clone).toArray(Chessman[][]::new);
        for (Move p : allMoves) {
            //score = -getScore(aiCol, tempBoard);
            moveChessmanAI(p.end, p.start, tempBoard);
            move = max(depth + 1, aiCol, turnCol.swapColor(), tempBoard, alpha, beta);
            //score += move;
            moveChessmanAI(p.start, p.end, tempBoard);
            if (move < bestScore) {
                bestScore = move;
            }
            if (bestScore < alpha) {
                return bestScore;
            }
            if (bestScore < beta) {
                beta = bestScore;
            }
        }
        return bestScore;
    }

    public static int max(int depth, SideColor aiCol, SideColor turnCol, Chessman[][] board, int alpha, int beta) {
        ArrayList<Move> allMoves = getAIMoves(turnCol, board);
        if (depth == maxDepth || allMoves.size() == 0) {
            return getScore(aiCol, board);
        }
        int move;
        int score;
        int bestScore = alpha;
        Chessman[][] tempBoard = Arrays.stream(board).map(Chessman[]::clone).toArray(Chessman[][]::new);
        for (Move p : allMoves) {
            score = getScore(turnCol, tempBoard);
            moveChessmanAI(p.end, p.start, tempBoard);
            move = min(depth + 1, aiCol, turnCol.swapColor(), tempBoard, alpha, beta);
            score += move;
            moveChessmanAI(p.start, p.end, tempBoard);
            if (score > bestScore) {
                bestScore = score;
            }
            if (bestScore > beta) {
                return bestScore;
            }
            if (bestScore > alpha) {
                alpha = bestScore;
            }
        }
        return bestScore;
    }

    public static int getScore(SideColor col, Chessman[][] board) {
        int result = 0;
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].color == col) {
                            result += board[i][j].value;
                        }
                    }
                }
            }
        return result;
    }


    public static void moveChessmanAI(Position newPosition, Position oldPosition, Chessman[][] board) {
        Chessman piece = board[oldPosition.x][oldPosition.y];
        if (piece instanceof King && piece.notMoved) {
            GamePanel.castling(newPosition, piece, board);
        } else if (piece instanceof Pawn) {
            ((Pawn) piece).startPosition = false;
            if (newPosition.y == 7 || newPosition.y == 0) {
                Position tempP = piece.pos;
                SideColor tempC = piece.color;
                piece = new Queen(tempC, tempP.x, tempP.y);
            }
        }
        board[newPosition.x][newPosition.y] = piece;
        board[piece.pos.x][piece.pos.y] = null;
        piece.pos = newPosition;
        piece.notMoved = false;
    }

    public static ArrayList<Move> getAIMoves(SideColor col, Chessman[][] board) {
        ArrayList<Move> moves = new ArrayList<Move>();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].color == col) {
                        ArrayList<Position> allMoves = new ArrayList<>(GamePanel.preventCheck(board[i][j].getMoves(board), board, board[i][j]));
                        for (Position p : allMoves) {
                            moves.add(new Move(board[i][j].pos, p));
                        }
                    }
                }
            }
        }
        return moves;
    }

}

