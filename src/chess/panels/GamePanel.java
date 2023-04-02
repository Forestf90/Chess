package chess.panels;

import chess.Menu;
import chess.Position;
import chess.SideColor;
import chess.pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class GamePanel extends JPanel {

    private final BufferedImage boardImg;
    public int SQUARE_SIZE = 64;
    ArrayList<Position> possibleMoves;
    ArrayList<Position> lastMove;
    Position checkPosition;
    Chessman selected;

    boolean endGame = false;
    protected Position focus;
    public Chessman[][] piecesBoard;

    private final BufferedImage[] imgTable;

    public boolean whiteMove;
    public boolean enabled;


    abstract void oponentTurn();

    public GamePanel() {
        boardImg = new BufferedImage(8 * SQUARE_SIZE, 8 * SQUARE_SIZE, BufferedImage.TYPE_INT_ARGB);
        drawBoard();
        this.setPreferredSize(new Dimension(8 * SQUARE_SIZE, 8 * SQUARE_SIZE));

        piecesBoard = new Chessman[8][8];
        imgTable = new BufferedImage[12];
        possibleMoves = new ArrayList<Position>();
        lastMove = new ArrayList<Position>();
        focus = new Position(0, 0);
        whiteMove = true;
        enabled = true;


        generatePieces();
        loadImages();
        MouseListner();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(boardImg, 0, 0, 8 * SQUARE_SIZE, 8 * SQUARE_SIZE, null);

        for (Position lm : lastMove) {
            g.setColor(new Color(0, 102, 102, 128));
            g.fillRect(lm.x * SQUARE_SIZE, lm.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
        if (checkPosition != null) {
            g.setColor(new Color(255, 0, 0, 128));
            g.fillRect(checkPosition.x * SQUARE_SIZE, checkPosition.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }


        g.setColor(new Color(128, 128, 128, 196));
        g.fillRect(focus.x * SQUARE_SIZE, focus.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        for (int i = 0; i < piecesBoard.length; i++) {
            for (int j = 0; j < piecesBoard[i].length; j++) {
                if (piecesBoard[i][j] != null) {
                    g.drawImage(imgTable[piecesBoard[i][j].imgSrc], i * SQUARE_SIZE,
                            j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, null);
                }
            }
        }
        g.setColor(new Color(0, 255, 0, 192));
        if (selected != null) {
            ((Graphics2D) g).setStroke(new BasicStroke(4));
            g.drawRect(selected.pos.x * SQUARE_SIZE + 2, selected.pos.y * SQUARE_SIZE + 2, SQUARE_SIZE - 4, SQUARE_SIZE - 4);
        }

        for (Position ch : possibleMoves) {
            if (piecesBoard[ch.x][ch.y] != null) {
                if (piecesBoard[ch.x][ch.y].color != selected.color) {
                    g.setColor(new Color(255, 0, 0, 192));
                    g.drawRect(ch.x * SQUARE_SIZE, ch.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            } else {
                g.setColor(new Color(0, 255, 0, 192));
                g.fillOval(ch.x * SQUARE_SIZE + SQUARE_SIZE / 4, ch.y * SQUARE_SIZE + SQUARE_SIZE / 4,
                        SQUARE_SIZE / 2, SQUARE_SIZE / 2);

            }

        }
    }

    private void drawBoard() {

        Graphics2D g = (Graphics2D) boardImg.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.decode("#71292a"));
        g.fillRect(0, 0, 8 * SQUARE_SIZE, 8 * SQUARE_SIZE);

        g.setColor(Color.decode("#f1e4c1"));
        for (int i = 0; i < 8; i++) {
            for (int j = i % 2; j < 8; j += 2) {
                g.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

    }

    private void generatePieces() {

        for (int i = 0; i < 8; i++) {
            piecesBoard[i][6] = new Pawn(SideColor.WHITE, i, 6);
            ;
            piecesBoard[i][1] = new Pawn(SideColor.BLACK, i, 1);
        }

        piecesBoard[0][7] = new Rook(SideColor.WHITE, 0, 7);
        piecesBoard[7][7] = new Rook(SideColor.WHITE, 7, 7);
        piecesBoard[0][0] = new Rook(SideColor.BLACK, 0, 0);
        piecesBoard[7][0] = new Rook(SideColor.BLACK, 7, 0);

        piecesBoard[1][7] = new Knight(SideColor.WHITE, 1, 7);
        piecesBoard[6][7] = new Knight(SideColor.WHITE, 6, 7);
        piecesBoard[1][0] = new Knight(SideColor.BLACK, 1, 0);
        piecesBoard[6][0] = new Knight(SideColor.BLACK, 6, 0);

        piecesBoard[2][7] = new Bishop(SideColor.WHITE, 2, 7);
        piecesBoard[5][7] = new Bishop(SideColor.WHITE, 5, 7);
        piecesBoard[2][0] = new Bishop(SideColor.BLACK, 2, 0);
        piecesBoard[5][0] = new Bishop(SideColor.BLACK, 5, 0);

        piecesBoard[3][7] = new Queen(SideColor.WHITE, 3, 7);
        piecesBoard[3][0] = new Queen(SideColor.BLACK, 3, 0);

        piecesBoard[4][7] = new King(SideColor.WHITE, 4, 7);
        piecesBoard[4][0] = new King(SideColor.BLACK, 4, 0);

    }

    public void loadImages() {
        try {
            String source = "/chess/resources/64/";
            imgTable[0] = ImageIO.read(getClass().getResource(source + "KgW.png"));
            imgTable[6] = ImageIO.read(getClass().getResource(source + "KgB.png"));

            imgTable[1] = ImageIO.read(getClass().getResource(source + "QW.png"));
            imgTable[7] = ImageIO.read(getClass().getResource(source + "QB.png"));

            imgTable[2] = ImageIO.read(getClass().getResource(source + "RW.png"));
            imgTable[8] = ImageIO.read(getClass().getResource(source + "RB.png"));

            imgTable[3] = ImageIO.read(getClass().getResource(source + "BW.png"));
            imgTable[9] = ImageIO.read(getClass().getResource(source + "BB.png"));

            imgTable[4] = ImageIO.read(getClass().getResource(source + "KtW.png"));
            imgTable[10] = ImageIO.read(getClass().getResource(source + "KtB.png"));

            imgTable[5] = ImageIO.read(getClass().getResource(source + "PW.png"));
            imgTable[11] = ImageIO.read(getClass().getResource(source + "PB.png"));
        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }

    }


    public void MouseListner() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                int tempX = me.getX() / SQUARE_SIZE;
                int tempY = me.getY() / SQUARE_SIZE;
                if ((piecesBoard[tempX][tempY] == null && selected == null) || !enabled) return;
                else if (selected == null) {
                    selected = piecesBoard[tempX][tempY];
                    if (selected.color == SideColor.WHITE && !whiteMove) {
                        selected = null;
                        return;
                    } else if (selected.color == SideColor.BLACK && whiteMove) {
                        selected = null;
                        return;
                    }
                    possibleMoves = preventCheck(selected.GetMoves(piecesBoard), piecesBoard, selected);
                    repaint();
                } else {
                    if (piecesBoard[tempX][tempY] != null && piecesBoard[tempX][tempY].color == selected.color) {
                        selected = piecesBoard[tempX][tempY];
                        possibleMoves = preventCheck(selected.GetMoves(piecesBoard), piecesBoard, selected);
                        repaint();
                    } else {

                        checkChessmanMove(new Position(tempX, tempY));
                        selected = null;
                        possibleMoves.clear();
                        repaint();
                    }

                }

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent me) {
                focus.x = me.getX() / SQUARE_SIZE;
                focus.y = me.getY() / SQUARE_SIZE;
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent arg0) {
            }

        });

    }

    public void checkChessmanMove(Position newPosition) {
        boolean contains = false;

        for (Position m : possibleMoves) {

            if (m.x == newPosition.x && m.y == newPosition.y) {
                contains = true;
                break;
            }
        }

        if (!contains) return;
        moveChessman(newPosition, selected.pos);
        oponentTurn();


    }

    public static void castling(Position newPosition, Chessman piece, Chessman[][] board) {
        if (newPosition.x == 2) {

            Position rookNewposition = new Position(newPosition.x + 1, newPosition.y);
            Position rookOldposition = new Position(0, newPosition.y);
            if (board[rookOldposition.x][rookOldposition.y].notMoved) {
                if (board[piece.pos.x][piece.pos.y].notMoved) {
                    Chessman rook = board[rookOldposition.x][rookOldposition.y];
                    board[rookNewposition.x][rookNewposition.y] = rook;
                    board[rookOldposition.x][rookOldposition.y] = null;
                    rook.pos = rookNewposition;
                    rook.notMoved = false;
                }
            }
        } else if (newPosition.x == 6) {
            Position rookNewposition = new Position(newPosition.x - 1, newPosition.y);
            Position rookOldposition = new Position(7, newPosition.y);
            if (board[rookOldposition.x][rookOldposition.y].notMoved) {
                if (board[piece.pos.x][piece.pos.y].notMoved) {
                    Chessman rook = board[rookOldposition.x][rookOldposition.y];
                    board[rookNewposition.x][rookNewposition.y] = rook;
                    board[rookOldposition.x][rookOldposition.y] = null;
                    rook.pos = rookNewposition;
                    rook.notMoved = false;
                }
            }
        }
    }

    public void moveChessman(Position newPosition, Position oldPosition) {
        lastMove.clear();
        lastMove.add(oldPosition);
        lastMove.add(newPosition);
        Chessman piece = piecesBoard[oldPosition.x][oldPosition.y];

        if (piece instanceof King && piece.notMoved) {
            castling(newPosition, piece, piecesBoard);
        } else if (piece instanceof Pawn) {
            ((Pawn) piece).startPosition = false;
            if (newPosition.y == 7 || newPosition.y == 0) {
                Position tempP = piece.pos;
                SideColor tempC = piece.color;
                piece = new Queen(tempC, tempP.x, tempP.y);
            }
        }

        piecesBoard[newPosition.x][newPosition.y] = piece;
        piecesBoard[piece.pos.x][piece.pos.y] = null;
        piece.pos = newPosition;
        piece.notMoved = false;
        SideColor c = piece.color.swapColor();


        boolean isCheck = check(piecesBoard, c);
        if (!isCheck) {
            checkPosition = null;
            checkStalemate(piece.color.swapColor(), piecesBoard);
        } else {
            checkPosition = findKing(piecesBoard, c);
            checkmate(piece.color.swapColor(), piecesBoard);
        }
    }


    public static ArrayList<Position> getAllMoves(SideColor col, Chessman[][] board) {
        ArrayList<Position> moves = new ArrayList<Position>();

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].color == col) moves.addAll(board[i][j].GetMoves(board));
                }
            }
        }
        return moves;
    }

    public ArrayList<Position> getAllSafeMoves(SideColor col, Chessman[][] board) {
        ArrayList<Position> moves = new ArrayList<Position>();

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].color == col)
                        moves.addAll(preventCheck(board[i][j].GetMoves(board), board, board[i][j]));
                }
            }
        }
        return moves;
    }

    public static Position findKing(Chessman[][] board, SideColor col) {
        Position kingPosition = new Position(0, 0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j] instanceof King && board[i][j].color == col) {
                        return new Position(i, j);
                    }
                }
            }
        }
        return kingPosition;
    }

    public static boolean check(Chessman[][] board, SideColor col) {

        Position kingPosition = findKing(board, col);
        SideColor c = col.swapColor();

        ArrayList<Position> enemyMoves = getAllMoves(c, board);


        for (Position p : enemyMoves) {
            if (kingPosition.x == p.x && kingPosition.y == p.y) {
                return true;
            }
        }

        return false;
    }

    public static ArrayList<Position> preventCheck(ArrayList<Position> moves, Chessman[][] board, Chessman piece) {

        Iterator<Position> i = moves.iterator();
        while (i.hasNext()) {
            Position p = i.next();

            Chessman[][] tempBoard = Arrays.stream(board).map(r -> r.clone()).toArray(Chessman[][]::new);
            tempBoard[piece.pos.x][piece.pos.y] = null;
            tempBoard[p.x][p.y] = piece;
            boolean isCheck = check(tempBoard, piece.color);
            if (isCheck)
                i.remove();
            else if (piece instanceof King && piece.notMoved) {
                if (preventCheckCastling(p, tempBoard, piece)) {
                    i.remove();
                }
            }

        }
        return moves;
    }

    public static boolean preventCheckCastling(Position p, Chessman[][] tempBoard, Chessman piece) {
        if (p.x == 2) {
            tempBoard[p.x][p.y] = null;
            tempBoard[p.x + 1][p.y] = piece;
            return check(tempBoard, piece.color);
        } else if (p.x == 6) {
            tempBoard[p.x][p.y] = null;
            tempBoard[p.x - 1][p.y] = piece;
            return check(tempBoard, piece.color);
        } else return false;

    }


    public void checkmate(SideColor col, Chessman[][] board) {
        ArrayList<Position> any = getAllSafeMoves(col, board);

        if (any.isEmpty()) {
            endGame = true;
            possibleMoves.clear();
            repaint();
            JOptionPane.showMessageDialog(null, col.getBetterString() + " King is checkmate. " + col.swapColor().getBetterString() +
                            "s wins. ",
                    "Checkmate", JOptionPane.INFORMATION_MESSAGE);
            closeFrame();
        }
    }

    public void checkStalemate(SideColor col, Chessman[][] board) {
        ArrayList<Position> any = getAllSafeMoves(col, board);

        if (any.isEmpty()) {
            possibleMoves.clear();
            repaint();
            JOptionPane.showMessageDialog(null, col.getBetterString() + "s have no more available moves. The game ends with a draw. ",
                    "Stalemate", JOptionPane.INFORMATION_MESSAGE);
            closeFrame();

        }

    }

    public void closeFrame() {
        new Menu();
        SwingUtilities.windowForComponent(this).dispose();
    }

}
