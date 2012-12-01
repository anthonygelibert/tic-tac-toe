/******************************************************************************
 * Copyright (c) 2012, Nocosium.                                              *
 * All rights reserved.                                                       *
 ******************************************************************************/

package fr.ag.game.tictactoe;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for the TicTacToeModel class.
 *
 * @author Anthony GELIBERT
 * @version 1.0.0
 */
public final class ModelTest
{
    /** Test an empty model. */
    @Test
    public void testEmptyModel()
    {
        final TicTacToeModel model = new TicTacToeModel();
        Assert.assertEquals("Empty model", 0, model.getCurrentState());
    }

    /**
     * Test the winner detection for all the lines.
     *
     * @throws AlreadyPlayedException Normally not thrown
     */
    @Test
    public void testCheckAllTheLines() throws AlreadyPlayedException
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
     *
     * @throws AlreadyPlayedException Normally not thrown
     */
    @Test
    public void testCheckAllTheColumns() throws AlreadyPlayedException
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
     *
     * @throws AlreadyPlayedException Normally not thrown
     */
    @Test
    public void testCheckAllTheDiag() throws AlreadyPlayedException
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

