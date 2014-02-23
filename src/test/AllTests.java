package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.chess.test_location;
import test.chess.test_piece_factory;
import test.chess.test_piece_reach_table;
import test.chess.test_piece_reachability;
import test.lg.TestBoard;
import test.lg.TestPiece;
import test.lg.TestReachability;
import test.lg.TestReachabilityTable;

@RunWith( Suite.class )
@SuiteClasses( {  test_location.class, 
                  test_piece_factory.class,
                  test_piece_reachability.class,
                  TestBoard.class,
                  TestPiece.class,
                  TestReachability.class,
                  TestReachabilityTable.class,
                  test_piece_reach_table.class } )
public class AllTests {

}
