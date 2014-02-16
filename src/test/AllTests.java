package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.chess.test_location;
import test.chess.test_piece_factory;
import test.chess.test_piece_reach_table;
import test.chess.test_piece_reachability;
import test.lg.test_board;
import test.lg.test_piece;
import test.lg.test_reachability;
import test.lg.test_reachability_table;

@RunWith( Suite.class )
@SuiteClasses( {  test_location.class, 
                  test_piece_factory.class,
                  test_piece_reachability.class,
                  test_board.class,
                  test_piece.class,
                  test_reachability.class,
                  test_reachability_table.class,
                  test_piece_reach_table.class } )
public class AllTests {

}
