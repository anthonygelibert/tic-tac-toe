package fr.ag.game.tictactoe;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * View class for the tic-tac-toe.
 *
 * @author Anthony GELIBERT
 * @version 1.0.1
 */
final class TicTacToeView
{
    /** Size of a row. */
    private static final int            ROWS_NUMBER     = 3;
    /** Piece player 1. */
    private static final Icon           CIRCLE_PIECE    = new ImageIcon(getImage("rond.jpg")); /* NON-NLS */
    /** Piece player 2. */
    private static final Icon           CROSS_PIECE     = new ImageIcon(getImage("croix.jpg")); /* NON-NLS */
    /** Empty piece. */
    private static final Icon           EMPTY_PIECE     = new ImageIcon(getImage("vide.jpg")); /* NON-NLS */
    /** Width of the window. */
    private static final int            WINDOW_WIDTH    = 330;
    /** Height of the window. */
    private static final int            WINDOW_HEIGHT   = 360;
    /** i18n. */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("text"); /* NON-NLS */
    /** Tic-Tac-Toe model. */
    private final TicTacToeModel m_model;
    /** Buttons behind the GUI. */
    private final JButton[] m_cases   = new JButton[ROWS_NUMBER * ROWS_NUMBER];
    /** JFrame. */
    private final JFrame    m_jFrame = new JFrame(RESOURCE_BUNDLE.getString("Titre"));
    /** Label on the window. */
    private JLabel m_message;

    /**
     * Create a new view with the model and a delegation link to the controller.
     *
     * @param model      The model.
     * @param controller The controller.
     */
    TicTacToeView(final TicTacToeModel model, final ActionListener controller)
    {
        m_model = model;
        m_jFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        m_jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //jframe.setLocationRelativeTo(getParent());

        final JPanel pan = new JPanel(new GridLayout(ROWS_NUMBER, ROWS_NUMBER));
        m_message = new JLabel(RESOURCE_BUNDLE.getString("Debut"));
        m_jFrame.add(m_message, BorderLayout.NORTH);
        m_jFrame.add(pan, BorderLayout.CENTER);
        for (int i = 0; i < (ROWS_NUMBER * ROWS_NUMBER); i++)
        {
            m_cases[i] = new JButton(EMPTY_PIECE);
            m_cases[i].setName(String.format("%d::%d", i / 3, i % 3)); /* NON-NLS */
            m_cases[i].addActionListener(controller);
            pan.add(m_cases[i]);
        }
        m_jFrame.setVisible(true);
    }

    private static Image getImage(final String pathAndFileName)
    {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
        return Toolkit.getDefaultToolkit().getImage(url);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TicTacToeView"); /* NON-NLS */
        sb.append("{m_model=").append(m_model); /* NON-NLS */
        sb.append(", m_cases=").append(Arrays.asList(m_cases).toString()); /* NON-NLS */
        sb.append(", jframe=").append(m_jFrame); /* NON-NLS */
        sb.append(", m_message=").append(m_message); /* NON-NLS */
        sb.append('}');
        return sb.toString();
    }

    /** Refresh the view with the last modifications. */
    public void refresh()
    {
        final int position = (m_model.getLastX() * ROWS_NUMBER) + m_model.getLastY();
        final int lastPlayer = m_model.getLastPlayer();
        m_cases[position].setIcon((lastPlayer == -1) ? CIRCLE_PIECE : CROSS_PIECE);
        switch (m_model.getCurrentState())
        {
            case 0:
                m_message.setText((lastPlayer == -1) ? RESOURCE_BUNDLE.getString("Joueur2") : RESOURCE_BUNDLE.getString("Joueur1"));
                break;
            case -1:
                m_message.setText(RESOURCE_BUNDLE.getString("Victoire1"));
                break;
            case 1:
                m_message.setText(RESOURCE_BUNDLE.getString("Victoire2"));
                break;
            case 2:
                m_message.setText(RESOURCE_BUNDLE.getString("Egalite"));
                break;
            default:
                /* NOTHING */
                break;
        }
    }

    /** Clear the view. */
    public void clear()
    {
        m_message = new JLabel(RESOURCE_BUNDLE.getString("Debut"));
        for (final JButton jb : m_cases)
        {
            jb.setIcon(EMPTY_PIECE);
        }
    }

    /** Exit. */
    public void exit()
    {
        m_jFrame.dispose();
    }
}
