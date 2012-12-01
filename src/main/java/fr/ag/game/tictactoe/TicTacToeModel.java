/******************************************************************************
 * Copyright (c) 2011, Anthony GELIBERT                                       *
 * All rights reserved.                                                       *
 *                                                                            *
 * Redistribution and use in source and binary forms, with or without         *
 * modification, are permitted provided that the following conditions are met:*
 *                                                                            *
 *     * Redistributions of source code must retain the above copyright       *
 * notice, this list of conditions and the following disclaimer.              *
 *                                                                            *
 *     * Redistributions in binary form must reproduce the above copyright    *
 * notice, this list of conditions and the following disclaimer in the        *
 * documentation and/or other materials provided with the distribution.       *
 *                                                                            *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS        *
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT          *
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A    *
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER   *
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,   *
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,        *
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR         *
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY        *
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT               *
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE      *
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.       *
 ******************************************************************************/

package fr.ag.game.tictactoe;

import java.util.Arrays;

/**
 * Model class for the tic-tac-toe.
 *
 * @author Anthony GELIBERT
 * @version 1.0.0
 */
final class TicTacToeModel
{
    /** Height of the board. */
    private static final int BOARD_HEIGHT = 4; // Real size is 3
    /** Weight of the board. */
    private static final int BOARD_WEIGHT = 5; // Real size is 3
    /** Board. */
    private int[][] m_board;
    /** Turn. */
    private int     m_turn;
    /** Winner. */
    private int     m_winner;
    /** Last play : X. */
    private int     m_lastX;
    /** Last play : Y. */
    private int     m_lastY;
    /** Last play : player. */
    private int     m_lastPlayer;

    /** Create an empty board. */
    TicTacToeModel()
    {
        clear();
    }

    /** Clear the board. */
    public void clear()
    {
        m_board = new int[BOARD_WEIGHT][BOARD_HEIGHT];
        m_winner = 0;
        m_turn = 0;
    }

    /**
     * <i>player</i> put a piece on <i>(x,y)</i>.
     *
     * @param x      [0 <= x <= 2]
     * @param y      [0 <= y <= 2]
     * @param player [1 | -1]
     */
    public void play(final int x, final int y, final int player) throws AlreadyPlayedException
    {
        assert (x >= 0) && (x <= 2) : "(" + x + ") -> 0 <= x <= 2";
        assert (y >= 0) && (y <= 2) : "(" + y + ") -> 0 <= y <= 2";
        assert (player == -1) || (player == 1) : "(" + player + ") player = [-1 | 1]";

        /* Don't to two times the same play. */
        if (m_board[x + 1][y + 1] != 0)
        {
            throw new AlreadyPlayedException(player + " already made this move..."); /* NON-NLS */
        }

        /* Keep info */
        m_lastX = x;
        m_lastY = y;
        m_lastPlayer = player;

        /* Mark a new turn. */
        m_turn++;

        /* Put the piece. */
        m_board[x + 1][y + 1] = player;
        /* Update column. */
        m_board[x + 1][0] += player;
        /* Update line */
        m_board[4][y + 1] += player;
        /* Update diags */
        if (x == y)
        {
            m_board[4][0] += player;
        }
        if ((x + y) == 2)
        {
            m_board[0][0] += player;
        }
        /* Check win */
        if ((m_board[0][0] == (3 * player)) ||
            (m_board[4][0] == (3 * player)) ||
            (m_board[4][y + 1] == (3 * player)) ||
            (m_board[x + 1][0] == (3 * player)))
        {
            m_winner = player;
        }
    }

    /**
     * Check the current state :
     * <ul>
     * <li>-1 and 1 => winner</li>
     * <li>0        => normal</li>
     * <li>2        => draw</li>
     * </ul>
     *
     * @return [-1 | 0 | 1 | 2]
     */
    public int getCurrentState()
    {
        if (m_winner != 0)
        {
            return m_winner;
        }
        if (m_turn == 9)
        {
            return 2;
        }
        return 0;
    }

    public int getLastX()
    {
        return m_lastX;
    }

    public int getLastY()
    {
        return m_lastY;
    }

    public int getLastPlayer()
    {
        return m_lastPlayer;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TicTacToeModel"); /* NON-NLS */
        sb.append("{m_board=").append((m_board == null) ? "null" : Arrays.asList(m_board).toString()); /* NON-NLS */
        sb.append(", m_turn=").append(m_turn); /* NON-NLS */
        sb.append(", m_winner=").append(m_winner); /* NON-NLS */
        sb.append(", m_lastX=").append(m_lastX); /* NON-NLS */
        sb.append(", m_lastY=").append(m_lastY); /* NON-NLS */
        sb.append(", m_lastPlayer=").append(m_lastPlayer); /* NON-NLS */
        sb.append('}');
        return sb.toString();
    }
}
