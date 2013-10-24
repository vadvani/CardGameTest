import java.awt.Toolkit;
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

void mouseClicked() {//if click piece--> outlined in blue and can't click another piece
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

boolean inSquare() {
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

void highlightPiece() { //highlight piece isn't called unless clicked = true - see main program
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

void movePiece() {
  if (pieceClicked==true) {// if piece is highlighted
  if (clicked == true) {//if another area has been clicked after
  float x1Coor = 0.0;
   float x2Coor = 0.0;
   float y1Coor = 0.0;
   float y2Coor = 0.0;
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
void drawPiece(int xvalue, int yvalue, int colorR, int colorG, int colorB) {//takes in array x and y coordinates, not actual coordinates
  fill (colorR, colorG, colorB);
  rect(floor(pieces[xvalue][yvalue]), (pieces[xvalue][yvalue]%1)*1000, pieceWidth, pieceWidth);
  }
  
    void highlight(int xvalue, int yvalue) { //takes in array x and y coordinates --> uses them to draw a blue outline around a piece (highlights it).
    noFill();
    strokeWeight(6);
    stroke(0,0,255);
    rect(floor(pieces[xvalue][yvalue]), (pieces[xvalue][yvalue]%1)*1000, pieceWidth, pieceWidth);
    
  }
