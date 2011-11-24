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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Simple class permitting to play parties.
 *
 * @author Anthony GELIBERT
 * @version 1.0.0
 */
final class TicTacToeController implements ActionListener
{
    /** Model. */
    private final TicTacToeModel m_model;
    /** View. */
    private final TicTacToeView m_view;
    /** Current player. */
    private int m_current = -1;

    /**
     * Create a new party with the player names.
     */
    TicTacToeController()
    {
        m_model = new TicTacToeModel();
        m_view = new TicTacToeView(m_model, this);
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
        /* Translate JButton to (x,y) */
        final JButton source = (JButton) e.getSource();
        final String name = source.getName();
        final int i = Character.getNumericValue(name.charAt(0));
        final int j = Character.getNumericValue(name.charAt(3));

        if (m_model.play(i, j, m_current))
        {
            m_current = -m_current;
            m_view.refresh();
            if (m_model.getCurrentState() != 0)
            {
                final int answer = JOptionPane.showConfirmDialog(null, "Voulez vous rejouer ?", "Encore une partie ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION)
                {
                    m_view.clear();
                    m_model.clear();
                    m_current = -1;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(50);
        sb.append("TicTacToeController");
        sb.append("{m_model=").append(m_model);
        sb.append(", m_view=").append(m_view);
        sb.append(", m_current=").append(m_current);
        sb.append('}');
        return sb.toString();
    }
}
