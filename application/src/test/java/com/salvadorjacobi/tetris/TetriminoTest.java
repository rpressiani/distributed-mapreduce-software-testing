import com.salvadorjacobi.tetris.Tetrimino;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;

import static org.junit.Assert.*;

public class TetriminoTest {

    private Tetrimino tetrimino;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


//    @Test
//    public void rotationCannotBeNegative() throws Exception {
//        Tetrimino tetrimino = new Tetrimino(Tetrimino.Shape.S, new Point(0,0), 0);
//
//        tetrimino.rotate(false);
//
//        assertEquals("Rotation cannot be negative", 3, tetrimino.getRotation());
//    }

    @Test
    public void outOfBoundRotationNotAllowed() throws Exception {
        thrown.expect(IllegalArgumentException.class);

        new Tetrimino(Tetrimino.Shape.T, new Point(0,0), 6);
    }
}
