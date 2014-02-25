package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.chess.TestPieceFactory;
import test.chess.TestPieceReachTable;
import test.chess.TestPieceReachability;
import test.lg.abstract_board_game.TestBoard;
import test.lg.data_objects.TestLocation;
import test.lg.data_objects.TestPiece;
import test.lg.reachability.TestReachability;
import test.lg.reachability.TestReachabilityTable;

@RunWith( Suite.class )
@SuiteClasses( {  TestLocation.class, 
                  TestPieceFactory.class,
                  TestPieceReachability.class,
                  TestBoard.class,
                  TestPiece.class,
                  TestReachability.class,
                  TestReachabilityTable.class,
                  TestPieceReachTable.class } )
public class AllTests {

}
