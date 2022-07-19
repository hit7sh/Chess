
/**
 * Chess game 2nd verestartion
 * by Hitesh Kumar
 */

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class chessV2 extends Frame {
    int xp, yp, click_cnt, fromX, fromY, v, b = 1, restart, cs, checkX, checkY;
    String[] board[] = new String[9][9];
    boolean check;
    public int moves_count = 0, moveX[] = new int[30], moveY[] = new int[30];

    public chessV2() {
        setTitle("---Chess Game---");
        setSize(500, 500);
        setVisible(true);
    }

    public boolean keyDown(Event e, int key) {
        if (key == 'c' || key == 'C' || key == 'x' || key == 'X') {
            int result = JOptionPane.showConfirmDialog(null, "U sure u wna close game?");
            switch (result) {

                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.YES_OPTION:
                    System.exit(0);
                    break;
            }
        }
        if (key == 'r' || key == 'R') {
            int result = JOptionPane.showConfirmDialog(null, "U sure u wna restart?");
            switch (result) {

                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.YES_OPTION:
                    initialize();
                    repaint();
                    b = 1;
                    restart = 1;
                    break;
            }
        }
        return true;
    }

    public boolean mouseDown(Event e, int x, int y) {
        x += 6;
        y -= 40;
        if (click_cnt++ % 2 == 0) {
            fromX = x;
            fromY = y;
            addspot(x - 3, y + 30);
            addmoves(y, x);
        } else {
            int i, f = 0;
            for (i = 0; i < 30; i++) {
                if (moveX[i] == -1)
                    break;
                if (moveY[i] == (int) (y + 65) / 80 && moveX[i] == (int) (x / 80))
                    f = 1;
            }
            if (f == 1) {
                move(x, y);
                b++;
            }
        }
        return true;
    }

    /* function to check if chess piece is moveable */
    boolean isMoveAble(String x, String y) {
        if (y == null)
            return true;
        char ch1 = x.charAt(0), ch2 = y.charAt(0);
        if (ch1 <= (char) 75353 && ch1 >= (char) 75348 && ch2 > (char) 75353 && ch2 <= (char) 75359)
            return true;
        else if (ch1 > (char) 75353 && ch1 <= (char) 75359 && ch2 <= (char) 75353 && ch2 >= (char) 75348)
            return true;
        else
            return false;
    }

    /* function Overload for special cases */
    boolean isMoveAble(String x, String y, int t) {
        if (y == null)
            return false;
        char ch1 = x.charAt(0), ch2 = y.charAt(0);
        int i = 0;
        if (ch1 <= (char) 75353 && ch1 >= (char) 75348 && ch2 > (char) 75353 && ch2 <= (char) 75359)
            return true;
        else if (ch1 > (char) 75353 && ch1 <= (char) 75359 && ch2 <= (char) 75353 && ch2 >= (char) 75348)
            return true;
        else
            return false;
    }

    void addmoves(int x, int y) {
        ++moves_count;
        int a = 0, b = 0;
        x = ((x + 15) / 80) + 1;
        y = ((y + 65) / 80);

        try {
            // for Knight
            if (board[x][y].charAt(0) == (char) 75352 || board[x][y].charAt(0) == (char) 75358) {
                if (x + 2 < 9 && x + 2 > 0 && y + 1 > 0 && y + 1 < 9)
                    if (isMoveAble(board[x][y], board[x + 2][y + 1])) {
                        moveY[a++] = (x + 2);
                        moveX[b++] = (y + 1) - 1;
                    }
                if (x + 2 < 9 && x + 2 > 0 && y - 1 > 0 && y - 1 < 9)
                    if (isMoveAble(board[x][y], board[x + 2][y - 1])) {
                        moveY[a++] = (x + 2);
                        moveX[b++] = (y - 1) - 1;
                    }
                if (x - 2 < 9 && x - 2 > 0 && y + 1 > 0 && y + 1 < 9)
                    if (isMoveAble(board[x][y], board[x - 2][y + 1])) {
                        moveY[a++] = (x - 2);
                        moveX[b++] = (y + 1) - 1;
                    }
                if (x - 2 < 9 && x - 2 > 0 && y - 1 > 0 && y - 1 < 9)
                    if (isMoveAble(board[x][y], board[x - 2][y - 1])) {
                        moveY[a++] = (x - 2);
                        moveX[b++] = (y - 1) - 1;
                    }
                if (x + 1 < 9 && x + 1 > 0 && y + 2 > 0 && y + 2 < 9)
                    if (isMoveAble(board[x][y], board[x + 1][y + 2])) {
                        moveY[a++] = (x + 1);
                        moveX[b++] = (y + 2) - 1;
                    }
                if (x - 1 < 9 && x - 1 > 0 && y + 2 > 0 && y + 2 < 9)
                    if (isMoveAble(board[x][y], board[x - 1][y + 2])) {
                        moveY[a++] = (x - 1);
                        moveX[b++] = (y + 2) - 1;
                    }
                if (x + 1 < 9 && x + 1 > 0 && y - 2 > 0 && y - 2 < 9)
                    if (isMoveAble(board[x][y], board[x + 1][y - 2])) {
                        moveY[a++] = (x + 1);
                        moveX[b++] = (y - 2) - 1;
                    }
                if (x - 1 < 9 && x - 1 > 0 && y - 2 > 0 && y - 2 < 9)
                    if (isMoveAble(board[x][y], board[x - 1][y - 2])) {
                        moveY[a++] = (x - 1);
                        moveX[b++] = (y - 2) - 1;
                    }
            }

            // for pawn(white)
            if (board[x][y].charAt(0) == (char) 75353) {
                if (x > 1 && board[x - 1][y] == null) {
                    moveY[a++] = (x - 1);
                    moveX[b++] = (y) - 1;
                }
                if (x == 7 && board[x - 2][y] == null && board[x - 1][y] == null) {
                    moveY[a++] = (x - 2); // pawn can move 2 moves initially.
                    moveX[b++] = (y) - 1;
                }
                if (x - 1 > 0 && y - 1 > 0)
                    if (isMoveAble(board[x][y], board[x - 1][y - 1], 0)) {
                        moveY[a++] = (x - 1);
                        moveX[b++] = (y - 1) - 1;
                    }
                if (x - 1 > 0 && y + 1 <= 8 && isMoveAble(board[x][y], board[x - 1][y + 1], 0)) {
                    moveY[a++] = (x - 1);
                    moveX[b++] = (y + 1) - 1;
                }
            }

            // for pawn(black)
            if (board[x][y].charAt(0) == (char) 75359) {
                if (y == 8) {
                    if (isMoveAble(board[x][y], board[x + 1][y])) {
                        moveY[a++] = x + 1;
                        moveX[b++] = y - 1;
                    }
                    if (isMoveAble(board[x][y], board[x + 1][y - 1], 0)) {
                        moveY[a++] = x + 1;
                        moveX[b++] = y - 1 - 1;
                    }
                }
                if (x == 7)
                    if (board[x + 1][y] == null) {
                        moveY[a++] = x + 1;
                        moveX[b++] = y - 1;
                    }

                if (x < 7 && board[x + 1][y] == null) {
                    moveY[a++] = (x + 1);
                    moveX[b++] = (y) - 1;
                }
                if (x == 2 && board[x + 2][y] == null && board[x + 1][y] == null) {
                    moveY[a++] = (x + 2);
                    moveX[b++] = (y) - 1;
                }
                if (y < 8 && y > 1 && x > 1 && x < 8)
                    if (isMoveAble(board[x][y], board[x + 1][y - 1], 0)) {
                        moveY[a++] = (x + 1);
                        moveX[b++] = (y - 1) - 1;
                    }
                if (isMoveAble(board[x][y], board[x + 1][y + 1], 0)) {
                    moveY[a++] = (x + 1);
                    moveX[b++] = (y + 1) - 1;
                }
                if (y == 1 && x < 7)
                    if (board[x + 1][y + 1] != null)
                        if (isMoveAble(board[x][y], board[x + 1][y + 1], 0)) {
                            moveY[a++] = (x + 1);
                            moveX[b++] = (y + 1) - 1;
                        }
                if (y == 8 && x < 7)
                    if (board[x + 1][y - 1] != null)
                        if (isMoveAble(board[x][y], board[x + 1][y - 1], 0)) {
                            moveY[a++] = (x + 1);
                            moveX[b++] = (y - 1) - 1;
                        }
            }

            // for Rook && Queen
            if (board[x][y].charAt(0) == (char) 75356 || (board[x][y].charAt(0) == (char) 75350) ||
                    board[x][y].charAt(0) == (char) 75349 || (board[x][y].charAt(0) == (char) 75355)) {
                int i = x - 1, j = y - 1, k = 0;
                for (; i > 0; i--)
                    if (isMoveAble(board[x][y], board[i][y])) {
                        moveY[a++] = i;
                        moveX[b++] = y - 1;
                        k++;
                        if (isMoveAble(board[x][y], board[i][y], 1))
                            break;
                    } else
                        break;

                i = x + 1;
                for (; i < 9; i++)
                    if (isMoveAble(board[x][y], board[i][y])) {
                        moveY[a++] = i;
                        moveX[b++] = y - 1;
                        k++;
                        if (isMoveAble(board[x][y], board[i][y], 1))
                            break;
                    } else
                        break;

                for (; j > 0; j--)
                    if (isMoveAble(board[x][y], board[x][j])) {
                        moveY[a++] = x;
                        moveX[b++] = j - 1;
                        k++;
                        if (isMoveAble(board[x][y], board[x][j], 1))
                            break;
                    } else
                        break;

                j = y + 1;
                for (; j < 9; j++)
                    if (isMoveAble(board[x][y], board[x][j])) {
                        moveY[a++] = x;
                        moveX[b++] = j - 1;
                        k++;
                        if (isMoveAble(board[x][y], board[x][j], 1))
                            break;
                    } else
                        break;
            }

            // for Bishop && Queen
            if (board[x][y].charAt(0) == (char) 75351 || (board[x][y].charAt(0) == (char) 75357) ||
                    board[x][y].charAt(0) == (char) 75349 || (board[x][y].charAt(0) == (char) 75355)) {
                int i = 0, j, k = 0;
                if (x > 1 && y > 1) {
                    i = x - 1;
                    j = y - 1;
                    while (i > 0 && j > 0) {
                        if (isMoveAble(board[x][y], board[i][j])) {
                            moveY[a++] = i;
                            moveX[b++] = j - 1;
                            k++;
                            if (isMoveAble(board[x][y], board[i][j], 1))
                                break;
                        } else
                            break;
                        i--;
                        j--;
                    }
                }
                if (x > 1 && y < 8) {
                    i = x - 1;
                    j = y + 1;
                    while (i > 0 && j <= 8) {
                        if (isMoveAble(board[x][y], board[i][j])) {
                            moveY[a++] = i;
                            moveX[b++] = j - 1;
                            k++;
                            if (isMoveAble(board[x][y], board[i][j], 1))
                                break;
                        } else
                            break;
                        i--;
                        j++;
                    }
                }
                if (x < 8 && y > 1) {
                    i = x + 1;
                    j = y - 1;
                    while (i <= 8 && j > 0) {
                        if (isMoveAble(board[x][y], board[i][j])) {
                            moveY[a++] = i;
                            moveX[b++] = j - 1;
                            k++;
                            if (isMoveAble(board[x][y], board[i][j], 1))
                                break;
                        } else
                            break;
                        i++;
                        j--;
                    }
                }
                if (x < 8 && y < 8) {
                    i = x + 1;
                    j = y + 1;
                    while (i <= 8 && j <= 8) {
                        if (isMoveAble(board[x][y], board[i][j])) {
                            moveY[a++] = i;
                            moveX[b++] = j - 1;
                            k++;
                            if (isMoveAble(board[x][y], board[i][j], 1))
                                break;
                        } else
                            break;
                        i++;
                        j++;
                    }
                }
            }

            // #King
            if (board[x][y].charAt(0) == (char) 75348 || (board[x][y].charAt(0) == (char) 75354)) {
                if (x == 8 && y == 8) {
                    if (isMoveAble(board[8][8], board[8][7])) {
                        moveY[a++] = 8;
                        moveX[b++] = 7 - 1;
                    }
                    if (isMoveAble(board[8][8], board[7][7])) {
                        moveY[a++] = 7;
                        moveX[b++] = 7 - 1;
                    }
                    if (isMoveAble(board[8][8], board[7][8])) {
                        moveY[a++] = 7;
                        moveX[b++] = 8 - 1;
                    }
                }

                if (x == 8 && y == 5) {
                    if (board[x][2] == null && board[x][3] == null && board[x][4] == null && board[x][1].charAt(0) == (char) 75356) {
                        moveY[a++] = 8;
                        moveX[b++] = 3 - 1;
                    }
                    if (board[x][6] == null && board[x][7] == null && board[x][8].charAt(0) == (char) 75356) {
                        moveY[a++] = 8;
                        moveX[b++] = 7 - 1;
                    }
                }
                if (x == 1 && y == 5) {
                    if (board[x][2] == null && board[x][3] == null && board[x][4] == null && board[x][1].charAt(0) == (char) 75350) {
                        moveY[a++] = 1;
                        moveX[b++] = 3 - 1;
                    }
                    if (board[x][6] == null && board[x][7] == null && board[x][1].charAt(0) == (char) 75350) {
                        moveY[a++] = 1;
                        moveX[b++] = 7 - 1;
                    }
                }
                if (x == 8) {
                    if (isMoveAble(board[x][y], board[x][y + 1])) {
                        moveY[a++] = (x);
                        moveX[b++] = (y) + 1 - 1;
                    }
                    if (isMoveAble(board[x][y], board[x - 1][y])) {
                        moveY[a++] = x - 1;
                        moveX[b++] = y - 1;
                    }
                    if (isMoveAble(board[x][y], board[x - 1][y + 1])) {
                        moveY[a++] = x - 1;
                        moveX[b++] = y + 1 - 1;
                    }
                }
                if (y == 1) {
                    if (isMoveAble(board[x][y], board[x + 1][y])) {
                        moveY[a++] = x + 1;
                        moveX[b++] = y - 1;
                    }
                    if (isMoveAble(board[x][y], board[x + 1][y + 1])) {
                        moveY[a++] = x + 1;
                        moveX[b++] = y + 1 - 1;
                    }
                    if (isMoveAble(board[x][y], board[x][y + 1])) {
                        moveY[a++] = x;
                        moveX[b++] = y + 1 - 1;
                    }
                    if (isMoveAble(board[x][y], board[x - 1][y])) {
                        moveY[a++] = x - 1;
                        moveX[b++] = y - 1;
                    }
                    if (isMoveAble(board[x][y], board[x - 1][y + 1])) {
                        moveY[a++] = x - 1;
                        moveX[b++] = y + 1 - 1;
                    }
                } else if (y == 8) {
                    if (isMoveAble(board[x][y], board[x - 1][y])) {
                        moveY[a++] = x - 1;
                        moveX[b++] = y - 1;
                    }
                    if (isMoveAble(board[x][y], board[x - 1][y - 1])) {
                        moveY[a++] = x - 1;
                        moveX[b++] = y - 1 - 1;
                    }
                    if (isMoveAble(board[x][y], board[x][y - 1])) {
                        moveY[a++] = x;
                        moveX[b++] = y - 1 - 1;
                    }
                    if (isMoveAble(board[x][y], board[x + 1][y])) {
                        moveY[a++] = x + 1;
                        moveX[b++] = y - 1;
                    }
                    if (isMoveAble(board[x][y], board[x + 1][y - 1])) {
                        moveY[a++] = x + 1;
                        moveX[b++] = y - 1 - 1;
                    }
                } else {
                    if (x < 8)
                        if (isMoveAble(board[x][y], board[x][y + 1])) {
                            moveY[a++] = x;
                            moveX[b++] = (y + 1) - 1;
                        }

                    if (x < 8 && y < 8) {
                        if (isMoveAble(board[x][y], board[x + 1][y])) {
                            moveY[a++] = (x + 1);
                            moveX[b++] = (y) - 1;
                        }

                        if (isMoveAble(board[x][y], board[x + 1][y + 1])) {
                            moveY[a++] = (x + 1);
                            moveX[b++] = (y + 1) - 1;
                        }
                    }

                    if (x == 1)
                        if (isMoveAble(board[x][y], board[x][y - 1])) {
                            moveY[a++] = (x);
                            moveX[b++] = (y) - 1 - 1;
                        }

                    if (x > 1)
                        if (isMoveAble(board[x][y], board[x][y - 1])) {
                            moveY[a++] = x;
                            moveX[b++] = (y - 1) - 1;
                        }

                    if (x > 1 && y > 1) {
                        if (isMoveAble(board[x][y], board[x - 1][y])) {
                            moveY[a++] = (x - 1);
                            moveX[b++] = (y) - 1;
                        }

                        if (isMoveAble(board[x][y], board[x - 1][y - 1])) {
                            moveY[a++] = (x - 1);
                            moveX[b++] = (y - 1) - 1;
                        }
                    }

                    if (x < 8 && y > 1) {
                        if (isMoveAble(board[x][y], board[x + 1][y])) {
                            moveY[a++] = (x + 1);
                            moveX[b++] = (y) - 1;
                        }

                        if (isMoveAble(board[x][y], board[x + 1][y - 1])) {
                            moveY[a++] = (x + 1);
                            moveX[b++] = (y - 1) - 1;
                        }
                    }

                    if (x > 1 && y < 8) {
                        if (isMoveAble(board[x][y], board[x][y - 1])) {
                            moveY[a++] = x;
                            moveX[b++] = (y - 1) - 1;
                        }

                        if (isMoveAble(board[x][y], board[x - 1][y + 1])) {
                            moveY[a++] = (x - 1);
                            moveX[b++] = (y + 1) - 1;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // do nothing
        }

        moveX[a++] = -1;
        // for check(kings)
        for (int i = 0; i < 30; i++) {
            if (moveX[i] == -1)
                break;
            if (board[moveY[i]][moveX[i] + 1] != null)
                if (board[moveY[i]][moveX[i] + 1].charAt(0) == (char) (75348)
                        || board[moveY[i]][moveX[i] + 1].charAt(0) == (char) (75354)) {
                    check = true;
                    checkX = moveX[i];
                    checkY = moveY[i];
                }
        }

        repaint();
    }

    public void move(int x, int y) {
        try {
            int b = ((x - 15) / 80) + 1;
            int a = ((y + 65 + 16) / 80);
            int m = ((fromX - 15) / 80) + 1, n = (fromY + 65) / 80;
            if (board[a][b] == null) {
                if (a == 1 && board[n][m].charAt(0) == (char) 75353) {
                    pawnChoice(board, n, m, 1);
                    b++;
                    addspot(x - 3, y + 30);
                } else if (a == 8 && board[n][m].charAt(0) == (char) 75359) {
                    pawnChoice(board, n, m, 2);
                    b++;
                    addspot(x - 3, y + 30);
                } else {
                    String t = board[a][b];
                    board[a][b] = board[(n)][m];
                    board[(n)][m] = t;
                }
            } else if ((board[a][b].charAt(0) <= (char) 75353 && board[a][b].charAt(0) >= (char) 75348 &&
                    board[(n)][m].charAt(0) > (char) 75353 &&
                    board[(n)][m].charAt(0) <= (char) 75359)
                    || (board[a][b].charAt(0) > (char) 75353 &&
                            board[a][b].charAt(0) <= (char) 75359 && board[(n)][m].charAt(0) <= (char) 75353 &&
                            board[(n)][m].charAt(0) >= (char) 75348)) {
                String t = board[a][b];
                board[a][b] = board[(n)][m];
                board[(n)][m] = null;
            }
        } catch (Exception e) {
        }
        for (int i = 0; i < 30; i++) {
            moveX[i] = 0;
            moveY[i] = 0;
        }
        addspot(x - 3, y + 30);
    }

    public void addspot(int x, int y) {
        xp = x;
        yp = y;
        repaint();
    }

    // to print board...
    public void initialize() {
        String b[] = { (char) (75356) + "", (char) (75358) + "", (char) (75357) + "",
                (char) (75355) + "", (char) (75354) + "", (char) (75357) + "", (char) (75358) + "",
                (char) (75356) + "" };
        for (int i = 1; i <= 8; i++)
            board[1][i] = b[i - 1];
        String[] w = { (char) (75350) + "", (char) (75352) + "", (char) (75351) + "", (char) (75349) + "",
                (char) (75348) + "", (char) (75351) + "", (char) (75352) + "", (char) (75350) + "" };
        for (int i = 3; i <= 6; i++)
            for (int j = 1; j <= 8; j++)
                board[i][j] = null;
        for (int i = 1; i <= 8; i++)
            board[8][i] = w[i - 1];
        int i = 0, j = 0;
        for (i = 1; i < 9; i++) {
            board[2][i] = (char) (75359) + "";
            board[7][i] = (char) (75353) + "";
        }
    }

    /* function to check for Black King */
    boolean searchBlackKing(String[][] board) {
        for (int i = 1; i < 9; i++)
            for (int j = 1; j < 9; j++)
                if (board[i][j] != null)
                    if (board[i][j].charAt(0) == (char) (75354))
                        return true;
        return false;
    }

    /* function to check for white King */
    boolean searchWhiteKing(String[][] board) {
        for (int i = 1; i < 9; i++)
            for (int j = 1; j < 9; j++)
                if (board[i][j] != null)
                    if (board[i][j].charAt(0) == (char) (75348))
                        return true;
        return false;
    }

    public void paint(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        try {
            int k = 0, x = 0, i = 0, j = 0;
            if (++v == 1)
                initialize();

            setSize(1366, 768);
            g.setColor(Color.orange);
            for (j = 31; j < 640; j += 80) {
                for (i = 8; i < 570; i += 80)
                    if (k++ % 2 == 0) {
                        g.setColor(Color.black);
                        g.draw3DRect(i, j, 80, 80, true);
                        g.drawRect(i, j, 81, 81);
                    } else {
                        g.setColor(Color.cyan.darker());
                        g.fill3DRect(i, j, 80, 80, true);
                        g.setColor(Color.black);
                        g.draw3DRect(i, j, 80, 80, true);
                        g.setColor(Color.cyan.darker());
                    }
                k++;
            }
            g.setColor(Color.black);
            Font f = new Font("TimesRoman", Font.PLAIN, 75);
            g.setFont(f);
            g.setColor(Color.black);
            k = 65;

            for (i = 1; i < 9; i++) {
                x = 8;
                for (j = 1; j < 9; j++) {
                    if (board[i][j] != null)
                        g.drawString(board[i][j], x, k + 31);
                    x += 80;
                }
                k += 80;
            }

            
            if (moves_count > 0) {
                for (i = 0; i < 30; i++) {
                    moves_count = 0;
                    if (moveX[i] == -1)
                        break;
                    g.setColor(Color.green);

                    g.drawRect((80 * moveX[i]) + 8, (80 * moveY[i]) - 65 + 31 + 6 - 21, 79, 79);
                    g.drawRect((80 * moveX[i]) + 8 + 1, (80 * moveY[i]) - 65 + 31 + 6 - 21 + 1, 78 + 1, 79);
                    g.drawRect((80 * moveX[i]) + 8 + 2, (80 * moveY[i]) - 65 + 31 + 6 - 21 + 2, 76 + 2, 76 + 2);
                    g.drawRect((80 * moveX[i]) + 8 + 3, (80 * moveY[i]) - 65 + 31 + 6 - 21 + 3, 75 + 3, 75 + 3);
                    g.setColor(Color.orange);
                    g.drawRect((80 * moveX[i]) + 8 + 4, (80 * moveY[i]) - 65 + 31 + 6 - 21 + 4, 74, 74);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
            if (restart == 1) {
                g.drawString("game restarted", 655 + 8, 350 + 31);
                restart = 0;
            }
            g.setColor(Color.black);
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g.draw3DRect(0, 0 + 31, 641 + 8, 640, true);
            if (b % 2 == 0)
                g.drawString("( BLACK'S TURN )", 670 + 8, 200 + 31);
            else
                g.drawString("( WHITE'S TURN )", 670 + 8, 200 + 31);

            if (check) {
                g.setColor(Color.red);
                g.drawString(">=CHECK=<", 700 + 8, 500);
                g.fill3DRect((80 * checkX) - 1 + 35 + 8, (80 * checkY) - 65 + 31 + 8, 25, 25, true);
                check = false;
            }
            g.setColor(Color.black);
            g.drawString("<press 'c' or 'x' to close game>", 665 + 8, 254 + 31);
            g.drawString("<press 'r' to restart game>", 665 + 8, 294 + 31);
            g.setColor(Color.red);
            for (i = 208; i < 212; i++)
                g.drawLine(660 + 8, i + 31, 890 + 8, i + 31);
            g.setColor(Color.black);
            if (!searchBlackKing(board))
                g.drawString("WHITE KINGDOM WON", 50 + 8, 250 + 31);
            if (!searchWhiteKing(board))
                g.drawString("BLACK KINGDOM WON!!!", 50 + 8, 250 + 31);

            g.setColor(Color.blue);
            g.drawString("CHESS GAME!", 650 + 8, 20 + 36);
            g.draw3DRect(649 + 8, 0 + 34, 369 + 8, 57, true);
            
        } catch (Exception e) {
        }
    }

    public static void main(String args[]) {
        new chessV2();
    }

    JFrame jf = new JFrame("promotion");
    JButton queen = new JButton("queen");
    JButton knight = new JButton("knight");
    JButton rook = new JButton("rook");
    JButton bishop = new JButton("bishop");
    JPanel pane = new JPanel();

    void pawnChoice(String board[][], int x, int y, int z) {
        jf.setSize(400, 300);
        pane.add(queen);
        pane.add(knight);
        pane.add(rook);
        pane.add(bishop);
        jf.add(pane);
        queen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // if choosen queen
                int tmp = 0;
                switch (z) {
                    case 1:
                        tmp = -1;
                        break;
                    case 2:
                        tmp = 1;
                        break;
                }
                board[x + tmp][y] = (char) ((int) (board[x][y].charAt(0)) - 4) + "";
                board[x][y] = null;
                jf.setState(jf.ICONIFIED);
            }
        });
        knight.addActionListener(new ActionListener() { // if chosen knight
            public void actionPerformed(ActionEvent e) {
                int tmp = 0;
                switch (z) {
                    case 1:
                        tmp = -1;
                        break;
                    case 2:
                        tmp = 1;
                        break;
                }
                board[x + tmp][y] = (char) ((int) (board[x][y].charAt(0)) - 1) + "";
                board[x][y] = null;
                jf.setState(jf.ICONIFIED);
            }
        });
        rook.addActionListener(new ActionListener() { // if choosen rook
            public void actionPerformed(ActionEvent e) {
                int tmp = 0;
                switch (z) {
                    case 1:
                        tmp = -1;
                        break;
                    case 2:
                        tmp = 1;
                        break;
                }
                board[x + tmp][y] =((board[x][y].charAt(0)) - 3) + "";
                board[x][y] = null;
                jf.setState(jf.ICONIFIED);
            }
        });
        bishop.addActionListener(new ActionListener() { // if choosen bishop
            public void actionPerformed(ActionEvent e) {
                int tmp = 0;
                switch (z) {
                    case 1:
                        tmp = -1;
                        break;
                    case 2:
                        tmp = 1;
                        break;
                }
                board[x + tmp][y] = (char) ((int) (board[x][y].charAt(0)) - 2) + "";
                board[x][y] = null;
                jf.setState(jf.ICONIFIED);
            }
        });
        jf.setVisible(true);
    }
}
