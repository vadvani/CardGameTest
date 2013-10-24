import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.Toolkit; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class veenapegsolitaire extends PApplet {

//int[] startPiece = new int[2];
boolean play = false;
boolean finish = false;
PFont font;

public void setup() {
  background(255);
  size(600, 600);
  //startScreen();
  //initialSetup();
  //drawBoard();
  //setupArrayBoard();
  //drawPieces();
  }

public void draw() {
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



boolean pieceClicked = false;
boolean clicked = false;
//int[] startPiece = new int[2];
int[] endPiece = new int[2];
int startPieceArrayX;
int startPieceArrayY;
//int endPieceArrayX;
//int endPieceArrayY;
int moves = 0;
int pieceNumber = 44;
boolean highlight = false;

public void mouseClicked() {//if click piece--> outlined in blue and can't click another piece
if (play==false) {
clicked = false;
}
if (finish == true) {
 clicked = false; 
}
if (play==true) {
  if (pieceClicked == true) {
   if (inSquare()==true) {
    clicked= true; 
   }
    else {
       strokeWeight(7);
       stroke(0,0,0);
       drawPiece(startPieceArrayX, startPieceArrayY, 0, 0, 0);//draws black box over the red and blue outline
       noStroke();
       drawPiece(startPieceArrayX, startPieceArrayY, 255, 0, 0);//draws red over the black
       clicked=false;
       pieceClicked=false;
       Toolkit.getDefaultToolkit().beep(); 
     }
  }
 else {
  clicked = true;
 } 
}
}

public boolean inSquare() {
  float x1Coor;
   float x2Coor;
   float y1Coor;
   float y2Coor;
  for (int e = 0; e<columns; e++) {
    for (int f=0; f<rows; f++) { 
      x1Coor = floor(pieces[e][f]);
      x2Coor = floor(pieces[e][f]) + pieceWidth;
      y1Coor = (pieces[e][f]%1)*1000;
      y2Coor = ((pieces[e][f]%1)*1000) + pieceWidth;
     if ((mouseX>=x1Coor) && (mouseX<=x2Coor) && (mouseY>=y1Coor) && (mouseY<=y2Coor) && (get(mouseX,mouseY) == -16777216)) {
      return true; 
   }
    }
     }
     return false;
  
}

public void highlightPiece() { //highlight piece isn't called unless clicked = true - see main program
   float x1Coor;
   float x2Coor;
   float y1Coor;
   float y2Coor;
   if (pieceClicked==false){//if a moving piece has not been selected
 for (int c = 0; c<columns; c++) {
    for (int d=0; d<rows; d++) { //for every value in board array- calculate the actual coordinates--> see if the mouse is within one of those pieces
      x1Coor = floor(pieces[c][d]);
      x2Coor = floor(pieces[c][d]) + pieceWidth;
      y1Coor = (pieces[c][d]%1)*1000;
      y2Coor = ((pieces[c][d]%1)*1000) + pieceWidth;
     if (((mouseX>=x1Coor) && (mouseX<=x2Coor) && (mouseY>=y1Coor) && (mouseY<=y2Coor) && (get(mouseX, mouseY) ==-65536))
     && (((get((mouseX-124),mouseY) ==-16777216)&&(get((mouseX-62),mouseY) ==-65536)) || 
     ((get((mouseX+124), mouseY)==-16777216)&&(get((mouseX+62),mouseY)==-65536)) || 
     ((get(mouseX, (mouseY-124))==-16777216)&&(get(mouseX, (mouseY-62))==-65536)) || 
     ((get(mouseX, (mouseY+124))==-16777216)&&(get(mouseX, (mouseY+62))==-65536)))) {
       strokeWeight(6);//pickes width of outline in pixels
       stroke(0,0,255);//picks color of outline (RGB)
       highlight(c,d);
       //drawPiece(c, d, 255, 0, 0);//need to enter array coordinates into drawPiece, not actual coordinates you want to draw
       highlight = true;
      startPieceArrayX =c;
      startPieceArrayY=d;
       pieceClicked = true;//the piece to move has been selected
       clicked = false;//has the area to move the piece to been clicked- turned it false because earlier- the clicked variable measuring for first click- piece to move selected, now false again--> if clicked, second piece has been clicked
     } 
     else{
      clicked = false; //if inside a square isn't clicked- still set this to false, otherwise, the minute the mouse finally hovers over a real square, it'll be highlighted  
      
 }
    }
 }
if (pieceClicked == false) {
 Toolkit.getDefaultToolkit().beep(); 
} 
}
}

public void movePiece() {
  if (pieceClicked==true) {// if piece is highlighted
  if (clicked == true) {//if another area has been clicked after
  float x1Coor = 0.0f;
   float x2Coor = 0.0f;
   float y1Coor = 0.0f;
   float y2Coor = 0.0f;
 for (int e = 0; e<columns; e++) {
    for (int f=0; f<rows; f++) { 
      x1Coor = floor(pieces[e][f]);
      x2Coor = floor(pieces[e][f]) + pieceWidth;
      y1Coor = (pieces[e][f]%1)*1000;
      y2Coor = ((pieces[e][f]%1)*1000) + pieceWidth;
     if ((mouseX>=x1Coor) && (mouseX<=x2Coor) && (mouseY>=y1Coor) && (mouseY<=y2Coor) && (get(mouseX,mouseY) == -16777216)) {//if mouse was in a square and that square was black
       int endPieceArrayX=e;
       int endPieceArrayY=f; //Array coordinates for coordinate values of the end destination for the moving piece
       if ((((startPieceArrayX-2==endPieceArrayX) || (startPieceArrayX+2==endPieceArrayX))&&(startPieceArrayY==endPieceArrayY)) || (((startPieceArrayY-2==endPieceArrayY) || (startPieceArrayY+2==endPieceArrayY)) && (startPieceArrayX==endPieceArrayX)))
       
       /*if (((abs(endPieceArrayX-startPieceArrayX) == 2) || (abs(endPieceArrayY-startPieceArrayY)==2)) && (abs(endPieceArrayX-startPieceArrayX) != abs(endPieceArrayY-startPieceArrayY)))*/ {//if the spot clicked to move the piece to is the right distance away, they can't both ==2 --> a diagonal, can't jump a diagonal.
        strokeWeight(7); 
        stroke(0,0,0);
        drawPiece(startPieceArrayX, startPieceArrayY, 0, 0, 0);//drawing black where square used to be
        location[startPieceArrayX][startPieceArrayY] = (location[startPieceArrayX][startPieceArrayY])/10;//new location array value
        //print ("New value of location array where piece used to be");
        //print (location[startPieceArrayX][startPieceArrayY]);
        noStroke();
        drawPiece(endPieceArrayX, endPieceArrayY, 255, 0, 0);//drawing red square to new location
        location[endPieceArrayX][endPieceArrayY] = (location[endPieceArrayX][endPieceArrayY]) * 10; //new location array value
        //print ("New value of location array where piece now has been moved to");
        //print (location[endPieceArrayX][endPieceArrayY]);
        if (abs(endPieceArrayX-startPieceArrayX) == 2) {//if moving horizontally
         drawPiece(endPieceArrayX-((endPieceArrayX-startPieceArrayX)/2), endPieceArrayY, 0, 0, 0); //drawing black in square in between
         location[endPieceArrayX-((endPieceArrayX-startPieceArrayX)/2)][endPieceArrayY] = (location[endPieceArrayX-((endPieceArrayX-startPieceArrayX)/2)][endPieceArrayY])/10;//new location array value
         //print ("new value of location array where jumped over piece was");
         //print (location[endPieceArrayX-((endPieceArrayX-startPieceArrayX)/2)][endPieceArrayY]);  
      }
        if (abs(endPieceArrayY-startPieceArrayY)==2) {//if moving vertically
          drawPiece(endPieceArrayX, endPieceArrayY-((endPieceArrayY-startPieceArrayY)/2), 0, 0, 0); //drawing black in square inbetween
          location[endPieceArrayX][endPieceArrayY-((endPieceArrayY-startPieceArrayY)/2)] = (location[endPieceArrayX][endPieceArrayY-((endPieceArrayY-startPieceArrayY)/2)])/10; //new location array value
          //print ("new value of location array where jumped over piece was");
          //print (location[endPieceArrayX][endPieceArrayY-((endPieceArrayY-startPieceArrayY)/2)]);  
      }
        pieceClicked=false;//not highlighted anymore- just the boolean variable, changing this doesn't actually unhighlight anything
        clicked=false;
        moves = moves + 1;
        pieceNumber = pieceNumber - 1;
        noMoves = endGameCheck();
        //print (checkSquare(3,3));
        
       }
       else {//the area clicked is not where a piece can move- already a piece there
       strokeWeight(7);
       stroke(0,0,0);
       drawPiece(startPieceArrayX, startPieceArrayY, 0, 0, 0);//draws black box over the red and blue outline
       noStroke();
       drawPiece(startPieceArrayX, startPieceArrayY, 255, 0, 0);//draws red over the black
       clicked=false;
       pieceClicked=false;
       Toolkit.getDefaultToolkit().beep();  
       }
       
     }

    
    }
 }
  }
  }
}
public void drawPiece(int xvalue, int yvalue, int colorR, int colorG, int colorB) {//takes in array x and y coordinates, not actual coordinates
  fill (colorR, colorG, colorB);
  rect(floor(pieces[xvalue][yvalue]), (pieces[xvalue][yvalue]%1)*1000, pieceWidth, pieceWidth);
  }
  
    public void highlight(int xvalue, int yvalue) { //takes in array x and y coordinates --> uses them to draw a blue outline around a piece (highlights it).
    noFill();
    strokeWeight(6);
    stroke(0,0,255);
    rect(floor(pieces[xvalue][yvalue]), (pieces[xvalue][yvalue]%1)*1000, pieceWidth, pieceWidth);
    
  }
int columns = 9;
int rows = 9;
float[][] pieces = new float[columns][rows];
int pieceWidth = 48;
boolean click = false;


public void initialSetup() {
 startScreen();
if (play==true){
 drawBoard();
  setupArrayBoard();
  drawPieces();
} 
}

public void startScreen() {
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
public void drawBoard() { //draws black cross shaped board
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

public void setupArrayBoard() {

  float yvalue = 28.0f;
  float xvalue = 28.0f;
  int counter1 = 0;
  int counter2 = 0;
  for (int i=0; i<columns; i++) {
    yvalue = 28.0f;  //resets the y value for the next column.
    for (int j=0; j<rows; j++) {
      pieces[i][j] = xvalue + (yvalue/1000);
      yvalue = yvalue + 62.0f;
    }
    xvalue = xvalue + 62.0f;
  }
  //reset top left corner
  pieces[0][0] = 0.0f;
  pieces[1][0] = 0.0f;
  pieces[2][0] = 0.0f;
  pieces[0][1] = 0.0f;
  pieces[1][1] = 0.0f;
  pieces[2][1] = 0.0f;
  pieces[0][2] = 0.0f;
  pieces[1][2] = 0.0f;
  pieces[2][2] = 0.0f;
  //reset top right corner
  pieces[7][0] = 0.0f;
  pieces[8][0] = 0.0f;
  pieces[6][0] = 0.0f;
  pieces[7][1] = 0.0f;
  pieces[8][1] = 0.0f;
  pieces[6][1] = 0.0f;
  pieces[7][2] = 0.0f;
  pieces[8][2] = 0.0f;
  pieces[6][2] = 0.0f;
  //reset bottom left corner
  pieces[0][7] = 0.0f;
  pieces[1][7] = 0.0f;
  pieces[2][7] = 0.0f;
  pieces[0][8] = 0.0f;
  pieces[1][8] = 0.0f;
  pieces[2][8] = 0.0f;
  pieces[0][6] = 0.0f;
  pieces[1][6] = 0.0f;
  pieces[2][6] = 0.0f;
  //reset bottom right corner
  pieces[7][7] = 0.0f;
  pieces[8][7] = 0.0f;
  pieces[6][7] = 0.0f;
  pieces[7][8] = 0.0f;
  pieces[8][8] = 0.0f;
  pieces[6][8] = 0.0f;
  pieces[7][6] = 0.0f;
  pieces[8][6] = 0.0f;
  pieces[6][6] = 0.0f;
  //make center empty
  //pieces[4][4] = 0.0;
}

public void drawPieces(){
  for (int a = 0; a<columns; a++) {
    for (int b=0; b<rows; b++) {
      if (pieces[a][b]>0.0f) {
       drawPiece(a, b, 255, 0, 0);
        
       
      //rect(floor(pieces[a][b]), (pieces[a][b]%1)*1000, pieceWidth, pieceWidth);
      }
    }
  }
  drawPiece(4, 4, 0, 0, 0);
}

float[][] location = new float[columns][rows];
boolean noMoves;
boolean lost = false;
int sum = 0;
int zeroCounter = 0;

public void setUpLocation () {
  int number = 1;
  for (int j=0; j<9; j++) { //for every row
    for (int i=0; i<3; i++) { //every third column set value = to number
      location[i][j]= number; 
      location[i+3][j] = number;
      location[i+6][j]=number;
      if (number<9) {
       number = number + 1; 
      }
      else {
       number = 1; 
      } 
}
  }
  location[4][4]=0.5f;
  location[0][0] = 0.0f;
  location[1][0] = 0.0f;
  location[2][0] = 0.0f;
  location[0][1] = 0.0f;
  location[1][1] = 0.0f;
  location[2][1] = 0.0f;
  location[0][2] = 0.0f;
  location[1][2] = 0.0f;
  location[2][2] = 0.0f;
  //reset top right corner
  location[7][0] = 0.0f;
  location[8][0] = 0.0f;
  location[6][0] = 0.0f;
  location[7][1] = 0.0f;
  location[8][1] = 0.0f;
  location[6][1] = 0.0f;
  location[7][2] = 0.0f;
  location[8][2] = 0.0f;
  location[6][2] = 0.0f;
  //reset bottom left corner
  location[0][7] = 0.0f;
  location[1][7] = 0.0f;
  location[2][7] = 0.0f;
  location[0][8] = 0.0f;
  location[1][8] = 0.0f;
  location[2][8] = 0.0f;
  location[0][6] = 0.0f;
  location[1][6] = 0.0f;
  location[2][6] = 0.0f;
  //reset bottom right corner
  location[7][7] = 0.0f;
  location[8][7] = 0.0f;
  location[6][7] = 0.0f;
  location[7][8] = 0.0f;
  location[8][8] = 0.0f;
  location[6][8] = 0.0f;
  location[7][6] = 0.0f;
  location[8][6] = 0.0f;
  location[6][6] = 0.0f; 
}

public boolean checkSquare(int startX, int startY) {//for squares (0,3) and (6,3)
  sum = 0;
  zeroCounter =0;
  for (int yValue = startY; yValue < startY+3; yValue++) {
    for (int xValue=startX; xValue < startX+3; xValue++) {
     sum = sum + floor(location[xValue][yValue]);
     //print (sum);
     if (floor(location[xValue][yValue]) == 0) {
      zeroCounter = zeroCounter + 1; 
     }
    }
    if (zeroCounter==1) {// if zero counter = 0 --> whole row is filled, no moves within row, if zero counter is 3 --> no moves, if zero counter is 2 --> one piece --> no moves
     if ((sum % 2) == 1) {
      return false; // the sum is an odd number --> there are moves
     } 
    }
    if (zeroCounter==0) {//no zeros horizontally --> 3 pieces horizontally --> left and right wings --> there is a move.
    return false;//there are moves
    }
    zeroCounter = 0;
    sum = 0;
  } //done checking whether there is a horizontal move
  
  for (int x2Value=startX; x2Value < startX+3; x2Value++) {
   for (int y2Value = startY; y2Value < startY+3; y2Value++) {
    sum = sum + floor(location[x2Value][y2Value]);
    if (floor(location[x2Value][y2Value]) == 0) {
     zeroCounter = zeroCounter + 1; 
    }
   }
    if (zeroCounter == 1) {
      if((sum % 2) == 1) {
        return false;
      }
    }
    zeroCounter = 0;
    sum = 0;
  }//done checking for vertical moves
  
  return true;// if still hasnt' return false, it means it hasn't found moves --> therefore return true - there aren't any moves in that square
  
  }
  
  
public boolean checkSquareTwo(int startX, int startY) {//for squares at coordinates (2,3) and (5,3)
  sum = 0;
  zeroCounter =0;
  for (int yValue = startY; yValue < startY+3; yValue++) {
    for (int xValue=startX; xValue < startX+3; xValue++) {
     sum = sum + floor(location[xValue][yValue]);
     //print (sum);
     if (floor(location[xValue][yValue]) == 0) {
      zeroCounter = zeroCounter + 1; 
     }
    }
    if (zeroCounter==1) {// if zero counter = 0 --> whole row is filled, no moves within row, if zero counter is 3 --> no moves, if zero counter is 2 --> one piece --> no moves
     if (((sum % 4) == 0)||((sum % 10)==0)||((sum % 3)==0)) {
      return false; // the sum is an odd number --> there are moves
     } 
    }
    zeroCounter = 0;
    sum = 0;
  } //done checking whether there is a horizontal move
  
  for (int x2Value=startX; x2Value < startX+3; x2Value++) {
   for (int y2Value = startY; y2Value < startY+3; y2Value++) {
    sum = sum + floor(location[x2Value][y2Value]);
    if (floor(location[x2Value][y2Value]) == 0) {
     zeroCounter = zeroCounter + 1; 
    }
   }
    if (zeroCounter == 1) {
      if((sum % 2) == 1) {
        return false;
      }
    }
    zeroCounter = 0;
    sum = 0;
  }//done checking for vertical moves
  
  return true;// if still hasnt' return false, it means it hasn't found moves --> therefore return true - there aren't any moves in that square
  
  }

public boolean checkSquareThree(int startX, int startY) {// for square coordinates (3,2) and (3,5)
  sum = 0;
  zeroCounter =0;
  for (int yValue = startY; yValue < startY+3; yValue++) {
    for (int xValue=startX; xValue < startX+3; xValue++) {
     sum = sum + floor(location[xValue][yValue]);
     //print (sum);
     if (floor(location[xValue][yValue]) == 0) {
      zeroCounter = zeroCounter + 1; 
     }
    }
    if (zeroCounter==1) {// if zero counter = 0 --> whole row is filled, no moves within row, if zero counter is 3 --> no moves, if zero counter is 2 --> one piece --> no moves
     if ((sum % 2) == 1) {
      return false; // the sum is an odd number --> there are moves
     } 
    }
    zeroCounter = 0;
    sum = 0;
  } //done checking whether there is a horizontal move
  
  for (int x2Value=startX; x2Value < startX+3; x2Value++) {
   for (int y2Value = startY; y2Value < startY+3; y2Value++) {
    sum = sum + floor(location[x2Value][y2Value]);
    if (floor(location[x2Value][y2Value]) == 0) {
     zeroCounter = zeroCounter + 1; 
    }
   }
    if (zeroCounter == 1) {
      if(((sum % 2) == 0)||(sum==5)||(sum==7)||(sum==9)) {
        return false;
      }
    }
    zeroCounter = 0;
    sum = 0;
  }//done checking for vertical moves
  
  return true;// if still hasnt' return false, it means it hasn't found moves --> therefore return true - there aren't any moves in that square
  
  }

public boolean checkSquareFour(int startX, int startY) {//for squares (3,0) and (3,6)
  sum = 0;
  zeroCounter =0;
  for (int yValue = startY; yValue < startY+3; yValue++) {
    for (int xValue=startX; xValue < startX+3; xValue++) {
     sum = sum + floor(location[xValue][yValue]);
     //print (sum);
     if (floor(location[xValue][yValue]) == 0) {
      zeroCounter = zeroCounter + 1; 
     }
    }
    if (zeroCounter==1) {// if zero counter = 0 --> whole row is filled, no moves within row, if zero counter is 3 --> no moves, if zero counter is 2 --> one piece --> no moves
     if ((sum % 2) == 1) {
      return false; // the sum is an odd number --> there are moves
     } 
    }
    zeroCounter = 0;
    sum = 0;
  } //done checking whether there is a horizontal move
  
  for (int x2Value=startX; x2Value < startX+3; x2Value++) {
   for (int y2Value = startY; y2Value < startY+3; y2Value++) {
    sum = sum + floor(location[x2Value][y2Value]);
    if (floor(location[x2Value][y2Value]) == 0) {
     zeroCounter = zeroCounter + 1; 
    }
   }
    if (zeroCounter == 1) {
      if((sum % 2) == 1) {
        return false;
      }
    }
    if (zeroCounter==0) {//there are three pieces vertically --> there are moves in squares 1 and 3
    return false;
    }
    zeroCounter = 0;
    sum = 0;
  }//done checking for vertical moves
  
  return true;// if still hasnt' return false, it means it hasn't found moves --> therefore return true - there aren't any moves in that square
  
  }


public boolean checkSquareFive(int startX, int startY) {//for center square - that way three pieces either vertically or horizontally --> still moves
  sum = 0;
  zeroCounter =0;
  for (int yValue = startY; yValue < startY+3; yValue++) {
    for (int xValue=startX; xValue < startX+3; xValue++) {
     sum = sum + floor(location[xValue][yValue]);
     //print (sum);
     if (floor(location[xValue][yValue]) == 0) {
      zeroCounter = zeroCounter + 1; 
     }
    }
    if (zeroCounter==1) {// if zero counter = 0 --> whole row is filled, no moves within row, if zero counter is 3 --> no moves, if zero counter is 2 --> one piece --> no moves
     if ((sum % 2) == 1) {
      return false; // the sum is an odd number --> there are moves
     } 
    }
    if (zeroCounter==0) {
      return false;
    }
    zeroCounter = 0;
    sum = 0;
  } //done checking whether there is a horizontal move
  
  for (int x2Value=startX; x2Value < startX+3; x2Value++) {
   for (int y2Value = startY; y2Value < startY+3; y2Value++) {
    sum = sum + floor(location[x2Value][y2Value]);
    if (floor(location[x2Value][y2Value]) == 0) {
     zeroCounter = zeroCounter + 1; 
    }
   }
    if (zeroCounter == 1) {
      if((sum % 2) == 1) {
        return false;
      }
    }
    if (zeroCounter==0) {//there are three pieces vertically --> there are moves in squares 1 and 3
    return false;
    }
    zeroCounter = 0;
    sum = 0;
  }//done checking for vertical moves
  
  return true;// if still hasnt' return false, it means it hasn't found moves --> therefore return true - there aren't any moves in that square
  
  }

  
public boolean endGameCheck () {
  if (checkSquareFour(3,0) == false) {//top square
   return false; 
  }
  if (checkSquare(0,3) == false) {//left square
    return false;
  }
  if (checkSquare(3,3) == false) {//middle square
   return false; 
  }
  if (checkSquare(6,3) == false) {//right square
   return false; 
  }
  if (checkSquareFour(3,6) == false) {//bottom square
   return false; 
  }
  if (checkSquareTwo(2,3) == false) {
   return false; 
  }
  if (checkSquareTwo(5,3) == false) {
   return false; 
  }
  if (checkSquareThree(3,2) == false) {
   return false; 
  }
  if (checkSquareThree(3,5) == false) {
   return false; 
  }
  return true;
}  

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "veenapegsolitaire" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
