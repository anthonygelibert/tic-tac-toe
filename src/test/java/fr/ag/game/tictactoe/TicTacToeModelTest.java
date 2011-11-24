package fr.ag.game.tictactoe;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for the TicTacToeModel class.
 *
 * @author Anthony GELIBERT
 * @version 1.0.0
 */
public final class TicTacToeModelTest
{
    /**
     * Test an empty model.
     */
    @Test
    public void testEmptyModel()
    {
        final TicTacToeModel model = new TicTacToeModel();
        Assert.assertEquals("Empty model", 0, model.getCurrentState());
    }

    /**
     * Test the winner detection for all the lines.
     */
    @Test
    public void testCheckAllTheLines()
    {
        final TicTacToeModel model = new TicTacToeModel();
        model.play(0, 0, 1);
        model.play(1, 0, 1);
        model.play(2, 0, 1);
        Assert.assertEquals("Check line 0", 1, model.getCurrentState());
        model.clear();
        model.play(0, 1, 1);
        model.play(1, 1, 1);
        model.play(2, 1, 1);
        Assert.assertEquals("Check line 1", 1, model.getCurrentState());
        model.clear();
        model.play(0, 2, 1);
        model.play(1, 2, 1);
        model.play(2, 2, 1);
        Assert.assertEquals("Check line 2", 1, model.getCurrentState());
    }

    /**
     * Test the winner detection for all the columns.
     */
    @Test
    public void testCheckAllTheColumns() throws Exception
    {
        final TicTacToeModel model = new TicTacToeModel();
        model.play(0, 0, 1);
        model.play(0, 1, 1);
        model.play(0, 2, 1);
        Assert.assertEquals("Check column 0", 1, model.getCurrentState());
        model.clear();
        model.play(1, 0, 1);
        model.play(1, 1, 1);
        model.play(1, 2, 1);
        Assert.assertEquals("Check column 1", 1, model.getCurrentState());
        model.clear();
        model.play(2, 0, 1);
        model.play(2, 1, 1);
        model.play(2, 2, 1);
        Assert.assertEquals("Check column 2", 1, model.getCurrentState());
    }

    /**
     * Test the winner detection for all the diags.
     */
    @Test
    public void testCheckAllTheDiag() throws Exception
    {
        final TicTacToeModel model = new TicTacToeModel();
        model.play(0, 0, 1);
        model.play(1, 1, 1);
        model.play(2, 2, 1);
        Assert.assertEquals("Check diag 1", 1, model.getCurrentState());
        model.clear();
        model.play(0, 2, 1);
        model.play(1, 1, 1);
        model.play(2, 0, 1);
        Assert.assertEquals("Check diag 2", 1, model.getCurrentState());
    }
}

