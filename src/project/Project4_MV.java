package project;

import java.util.ArrayList;

import chess.ChessPieceFactory;
import lg.data_objects.Color;
import lg.data_objects.location.Location;
import lg.data_objects.piece.Piece;
import lg.data_objects.translations.Transition;

public class Project4_MV {
  public static ArrayList<Transition> retiTransitions(){
    ChessPieceFactory chessPieceFactory = new ChessPieceFactory();
    ArrayList<Transition> arrayList = new ArrayList<Transition>();
    
    Piece whiteKing = chessPieceFactory.createKing( Color.WHITE );
    Piece whitePawn = chessPieceFactory.createPawn( Color.WHITE );
    Piece whiteTarget = chessPieceFactory.createTarget( Color.WHITE );
    Piece blackKing = chessPieceFactory.createKing( Color.BLACK );
    Piece blackPawn = chessPieceFactory.createPawn( Color.BLACK );
    Piece blackTarget = chessPieceFactory.createTarget( Color.BLACK );
    
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(2,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(0,2,0), 
            new Location(1,1,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,1,0), 
            new Location(2,0,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,1,0), 
            new Location(2,0,0) ) );
    // -1
    // back 2
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(7,0,0), 
            new Location(6,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,1,0), 
            new Location(2,0,0) ) );
    // -1
    // back 4
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(7,0,0), 
            new Location(6,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(0,2,0), 
            new Location(1,2,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(2,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(2,1,0) ) );
    // -1
    // back 2
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(6,1,0), 
            new Location(5,2,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(2,2,0) ) );
    // 0
    // back 1
    arrayList.add( 
        new Transition( 
            blackPawn, 
            new Location(7,3,0), 
            new Location(7,4,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(2,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(2,1,0) ) );
    // -1
    // back 2
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(5,2,0), 
            new Location(4,3,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(2,2,0) ) );
    // 0 
    // back 1
    arrayList.add( 
        new Transition( 
            blackPawn, 
            new Location(7,4,0), 
            new Location(7,5,0) ) );
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(4,3,0), 
            new Location(3,2,0) ) );
    // 0
    // back 4
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(1,1,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(1,1,0) ) );

    // 1
    // back 4
    arrayList.add( 
        new Transition( 
            blackPawn, 
            new Location(7,3,0), 
            new Location(7,4,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(2,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(0,2,0), 
            new Location(1,1,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,1,0), 
            new Location(2,0,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,1,0), 
            new Location(2,0,0) ) );
    // -1
    // back 2
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(7,0,0), 
            new Location(6,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,1,0), 
            new Location(2,0,0) ) );
    // -1
    // back 4
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(6,1,0), 
            new Location(5,2,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(0,2,0), 
            new Location(1,1,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(1,1,0) ) );
    // 1
    // back 2
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(0,2,0), 
            new Location(1,2,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(2,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(2,1,0) ) );
    // -1
    // back 2
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(5,2,0), 
            new Location(4,3,0) ) );
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(2,2,0) ) );
    // 0
    // back 1
    arrayList.add( 
        new Transition( 
            blackPawn, 
            new Location(7,4,0), 
            new Location(7,5,0) ) );
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(4,3,0), 
            new Location(3,2,0) ) );
    // 0
    // back 2
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(1,2,0), 
            new Location(1,1,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(1,1,0) ) );
    // 1
    // back 4
    arrayList.add( 
        new Transition( 
            blackPawn, 
            new Location(7,4,0), 
            new Location(7,5,0) ) );
    arrayList.add( 
        new Transition( 
            whiteKing, 
            new Location(5,2,0), 
            new Location(4,1,0) ) );
    // 0
    // back 4
    arrayList.add( 
        new Transition( 
            blackKing, 
            new Location(0,2,0), 
            new Location(1,3,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,2,0), 
            new Location(2,1,0) ) );
    arrayList.add( 
        new Transition( 
            blackPawn, 
            new Location(7,3,0), 
            new Location(7,4,0) ) );
    arrayList.add( 
        new Transition( 
            whitePawn, 
            new Location(2,1,0), 
            new Location(2,0,0) ) );
    // 0
    // CUT
    
    
    
    return arrayList;
  }
}
