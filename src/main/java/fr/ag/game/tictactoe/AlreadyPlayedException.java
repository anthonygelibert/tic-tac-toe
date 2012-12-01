/******************************************************************************
 * Copyright (c) 2012, Nocosium.                                              *
 * All rights reserved.                                                       *
 ******************************************************************************/

package fr.ag.game.tictactoe;

/**
 * Exception for "already played" move.
 *
 * @author Anthony GELIBERT
 * @version 1.0.0
 */
class AlreadyPlayedException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Create a new Exception with the given message.
     *
     * @param message Message
     */
    AlreadyPlayedException(final String message)
    {
        super(message);
    }
}
