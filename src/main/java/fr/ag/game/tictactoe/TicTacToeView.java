package fr.ag.game.tictactoe;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * View class for the tic-tac-toe.
 *
 * @author Anthony GELIBERT
 * @version 1.0.0
 */
final class TicTacToeView extends JFrame
{
    /** Size of a row. */
    private static final int ROWS_NUMBER = 3;

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(80);
        sb.append("TicTacToeView");
        sb.append("{super=").append(super.toString());
        sb.append(", m_model=").append(m_model);
        sb.append(", m_case=").append(m_case == null ? "null" : Arrays.asList(m_case).toString());
        sb.append(", m_message=").append(m_message);
        sb.append('}');
        return sb.toString();
    }

    /** Piece player 1. */
    private static final Icon CIRCLE_PIECE  = new ImageIcon("rond.jpg");
    /** Piece player 2. */
    private static final Icon CROSS_PIECE   = new ImageIcon("croix.jpg");
    /** Empty piece. */
    private static final Icon EMPTY_PIECE   = new ImageIcon("vide.jpg");
    /** Width of the window. */
    private static final int  WINDOW_WIDTH  = 330;
    /** Height of the window. */
    private static final int  WINDOW_HEIGHT = 350;

    /** Tic-Tac-Toe model. */
    private final TicTacToeModel m_model;
    /** Buttons behind the GUI. */
    private final JButton[] m_case    = new JButton[ROWS_NUMBER * ROWS_NUMBER];
    /** Label on the window. */
    private final JLabel    m_message = new JLabel("Joueur 1 commence !");

    /**
     * Create a new view with the model and a delegation link to the controller.
     *
     * @param model      The model.
     * @param controller The controller.
     */
    TicTacToeView(final TicTacToeModel model, final ActionListener controller)
    {
        m_model = model;

        setTitle("Morpion:");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(getParent());

        final JPanel pan = new JPanel(new GridLayout(ROWS_NUMBER, ROWS_NUMBER));
        add(m_message, BorderLayout.NORTH);
        add(pan, BorderLayout.CENTER);
        for (int i = 0; i < ROWS_NUMBER * ROWS_NUMBER; i++)
        {
            m_case[i] = new JButton(EMPTY_PIECE);
            m_case[i].setName((i / 3) + "::" + (i % 3));
            m_case[i].addActionListener(controller);
            pan.add(m_case[i]);
        }
        setVisible(true);
    }

    /**
     * Refresh the view with the last modifications.
     */
    public void refresh()
    {
        m_case[m_model.getLastX() * ROWS_NUMBER + m_model.getLastY()].setIcon(m_model.getLastPlayer() == -1 ? CIRCLE_PIECE : CROSS_PIECE);
        switch (m_model.getCurrentState())
        {
            case -1:
                m_message.setText("Joueur 1 a gagné !!!");
                break;
            case 1:
                m_message.setText("Joueur 2 a gagné !!!");
                break;
            case 2:
                m_message.setText("Egalité !!!");
                break;
            default:
                /* NOTHING */
                break;
        }
    }

    /**
     * Clear the view.
     */
    public void clear()
    {
        for (final JButton jb : m_case)
        {
            jb.setIcon(EMPTY_PIECE);
        }
    }
}
