package com.mytaxi;

/**
 * Created by yogesh on 31/3/21.
 */
public class CarAlreadyInUseException extends Exception
{

    public CarAlreadyInUseException(final String message)
    {
        super(message);
    }

}