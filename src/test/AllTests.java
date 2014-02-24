package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.chess.TestLocation;
import test.chess.TestPieceFactory;
import test.chess.TestPieceReachTable;
import test.chess.TestPieceReachability;
import test.lg.TestBoard;
import test.lg.TestPiece;
import test.lg.TestReachability;
import test.lg.TestReachabilityTable;

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
