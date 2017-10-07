package com.salvadorjacobi.tetris;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameModelTest {

    private GameModel model;

    @Before
    public void setUp() throws Exception {
        model = new GameModel(10, 20, 32);
    }

//    @Test
//    public void swappedTetriminoWillComeBack() throws Exception {
//        Tetrimino fallingTetrimino = model.getFallingTetrimino();
//
//        model.swap();
//        model.hardDrop();
//        model.swap();
//
//        assertEquals("Previously swapped Tetrimino has to come back in the next swap",
//                fallingTetrimino, model.getFallingTetrimino());
//    }

//    @Test
//    public void fallingTetriminoCanBeSwappedOnlyOnce() throws Exception {
//        model.swap();
//        Tetrimino newFallingTetrimino = model.getFallingTetrimino();
//        model.swap();
//
//        assertEquals(newFallingTetrimino, model.getFallingTetrimino());
//    }
}