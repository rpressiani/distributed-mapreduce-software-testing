package com.salvadorjacobi.tetris;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class GameModelTest {

    private GameModel model;

    @Before
    public void setUp() throws Exception {
        model = new GameModel(10, 20, 32);
    }

    @Test
    public void swappedTetriminoWillComeBack() throws Exception {
        Tetrimino fallingTetrimino = model.getFallingTetrimino();

        model.swap();
        model.hardDrop();
        model.swap();

        assertEquals("Previously swapped Tetrimino has to come back in the next swap",
                fallingTetrimino, model.getFallingTetrimino());
    }

    @Test
    public void fallingTetriminoCanBeSwappedOnlyOnce() throws Exception {
        model.swap();
        Tetrimino newFallingTetrimino = model.getFallingTetrimino();
        model.swap();

        assertEquals(newFallingTetrimino, model.getFallingTetrimino());
    }

    @Test
    public void shiftTrueMoveTetriminoLeft() throws Exception {
        int startingPos = model.getFallingTetrimino().getPosition().x;
        model.shift(true);

        assertEquals(model.getFallingTetrimino().getPosition().x + 1, startingPos);
    }

    @Test
    public void shiftFalseMoveTetriminoRight() throws Exception {
        int startingPos = model.getFallingTetrimino().getPosition().x;
        model.shift(false);

        assertEquals(model.getFallingTetrimino().getPosition().x - 1, startingPos);
    }

    @Test
    public void tetriminoCannotMoveOutsideBounds() throws Exception {
        model.getFallingTetrimino().setPosition(new Point(0, 5));
        int startingPos = model.getFallingTetrimino().getPosition().x;
        model.shift(true);

        assertEquals(model.getFallingTetrimino().getPosition().x, startingPos);
    }
}