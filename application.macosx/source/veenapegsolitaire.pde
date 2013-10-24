//int[] startPiece = new int[2];
boolean play = false;
boolean finish = false;
PFont font;

void setup() {
  background(255);
  size(600, 600);
  //startScreen();
  //initialSetup();
  //drawBoard();
  //setupArrayBoard();
  //drawPieces();
  }

void draw() {
  if (play==false){
    startScreen();
  }
  if (play==true){
  if (clicked==true) {
    highlightPiece();
  }
  if (clicked==true) {
    movePiece();
    fill(255);
    rect(10,570,175,30);
    fill(0);
    String piecesLeft = "Pieces Left="+pieceNumber;
    text(piecesLeft, 10, 590); //printing in corner of screen- your moves
  }
  }
  if (noMoves == true) {
   finish = true; 
  }
  if (finish == true) {
  //if (pieceNumber == 1) {
    //fill (255);
    //font = loadFont("ComicSansMS-Bold-48.vlw");
    //textFont(font, 48);
    //String Win = "Congratulations, you have won!  :)"; 
    //text(Win, 100, 300);
  //}
  //else {
  String sorry = "Sorry, you have lost";
  String noMoreMoves = "You have no more legal moves left.";
  String endScore = "You had " + pieceNumber + " pieces left on the board";
  String playAgain = "Click the button below to play again";
  fill (255);
  font = loadFont("ComicSansMS-Bold-24.vlw");
  textFont(font, 24);
  text (sorry, 75, 220);
  text (noMoreMoves, 75, 260);
  text (endScore, 75, 300);
  text(playAgain, 75, 340);
  fill(0,0,255);
  rect(260,360,80,30);
  fill (0);
  if ((mouseX>260)&&(mouseX<340)&&(mouseY>360)&&(mouseY<390)) {
  fill(0,255,255);
  rect(260,360,80,30);
  if(mousePressed==true){
  background(255);
  fill(255);
  rect(260,360,80,30);
  fill(0);
  play=false;
  finish=false;
  pieceNumber=44;
  noMoves = false;
  }
  }
  //}  
}
  }


