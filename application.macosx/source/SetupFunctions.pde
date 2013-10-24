int columns = 9;
int rows = 9;
float[][] pieces = new float[columns][rows];
int pieceWidth = 48;
boolean click = false;


void initialSetup() {
 startScreen();
if (play==true){
 drawBoard();
  setupArrayBoard();
  drawPieces();
} 
}

void startScreen() {
 String Title = "Peg Solitaire";
 String Howto = "How to Play:";
 String Instructions1 = "In this game, you have 44 red pieces in a cross shaped configuration.";
 String Instructions2 = "For each move, you are permitted to make one red piece 'jump' over another red piece and into an empty square.";
 String Instructions3 = "After you make a move, the red piece you just jumped over will disappear.";
 String Instructions4 = "The goal of this game is to keep jumping over red pieces until their is just one piece left.";
 String Instructions5 = "But be careful, red pieces can only move by jumping over another red piece.  If there are no pieces nearby, it will be unable to move."; 
 String Instructions6 = "In order to move a piece, you must click on it with your mouse.";
 String Instructions7 = "If the piece has potential moves it can make, it will become outlined in blue.";
 String Instructions8 = "Once the piece is outlined in blue, you must then click in the black empty space where you would like to move your piece.";
 String Instructions9 = "Pieces can only jump over one other piece at a time.";
 fill(0,0,255);
 rect(260,500,80,30);
 fill(0);
 text (Title, 280, 20);
 text (Howto, 280, 35);
 text (Instructions1, 20, 50);
 text (Instructions2, 20, 65);
 text (Instructions3, 20, 80);
 text (Instructions4, 20, 95);
 text (Instructions5, 20, 110);
 text (Instructions6, 20, 125);
 text (Instructions7, 20, 140);
 text (Instructions8, 20, 155);
 text (Instructions9, 20, 170);
 if ((mouseX>260)&&(mouseX<340)&&(mouseY>500)&&(mouseY<530)) {
  fill(0,255,255);
  rect(260,500,80,30);
  if(mousePressed==true){
  background(255);
  drawBoard();
  setupArrayBoard();
  drawPieces(); 
  setUpLocation();
  
  //for (int c=0; c<9; c++) {
   //for (int d=0; d<9; d++) {
    //print (location[d][c]); 
    //print (",");
   //} 
  //}
  font = loadFont("ComicSansMS-Bold-24.vlw");
  textFont(font, 24);
  String piecesLeft = "Pieces Left="+pieceNumber; //initializes pieces left counter at 44
  text(piecesLeft, 10, 590); //printing in corner of screen- your moves
  play=true;
  }
 }

}
void drawBoard() { //draws black cross shaped board
  int number = width/3;
  fill(0);
  beginShape();
  vertex(number, 0);
  vertex(2*number, 0);
  vertex(2*number, number);
  vertex(width, number);
  vertex(width, 2*number);
  vertex(2*number, 2*number);
  vertex(2*number, height);
  vertex(number, height);
  vertex(number, 2*number);
  vertex(0, 2*number);
  vertex(0, number);
  vertex(number, number);
  endShape(CLOSE);
}

void setupArrayBoard() {

  float yvalue = 28.0;
  float xvalue = 28.0;
  int counter1 = 0;
  int counter2 = 0;
  for (int i=0; i<columns; i++) {
    yvalue = 28.0;  //resets the y value for the next column.
    for (int j=0; j<rows; j++) {
      pieces[i][j] = xvalue + (yvalue/1000);
      yvalue = yvalue + 62.0;
    }
    xvalue = xvalue + 62.0;
  }
  //reset top left corner
  pieces[0][0] = 0.0;
  pieces[1][0] = 0.0;
  pieces[2][0] = 0.0;
  pieces[0][1] = 0.0;
  pieces[1][1] = 0.0;
  pieces[2][1] = 0.0;
  pieces[0][2] = 0.0;
  pieces[1][2] = 0.0;
  pieces[2][2] = 0.0;
  //reset top right corner
  pieces[7][0] = 0.0;
  pieces[8][0] = 0.0;
  pieces[6][0] = 0.0;
  pieces[7][1] = 0.0;
  pieces[8][1] = 0.0;
  pieces[6][1] = 0.0;
  pieces[7][2] = 0.0;
  pieces[8][2] = 0.0;
  pieces[6][2] = 0.0;
  //reset bottom left corner
  pieces[0][7] = 0.0;
  pieces[1][7] = 0.0;
  pieces[2][7] = 0.0;
  pieces[0][8] = 0.0;
  pieces[1][8] = 0.0;
  pieces[2][8] = 0.0;
  pieces[0][6] = 0.0;
  pieces[1][6] = 0.0;
  pieces[2][6] = 0.0;
  //reset bottom right corner
  pieces[7][7] = 0.0;
  pieces[8][7] = 0.0;
  pieces[6][7] = 0.0;
  pieces[7][8] = 0.0;
  pieces[8][8] = 0.0;
  pieces[6][8] = 0.0;
  pieces[7][6] = 0.0;
  pieces[8][6] = 0.0;
  pieces[6][6] = 0.0;
  //make center empty
  //pieces[4][4] = 0.0;
}

void drawPieces(){
  for (int a = 0; a<columns; a++) {
    for (int b=0; b<rows; b++) {
      if (pieces[a][b]>0.0) {
       drawPiece(a, b, 255, 0, 0);
        
       
      //rect(floor(pieces[a][b]), (pieces[a][b]%1)*1000, pieceWidth, pieceWidth);
      }
    }
  }
  drawPiece(4, 4, 0, 0, 0);
}

