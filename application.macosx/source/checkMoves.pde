float[][] location = new float[columns][rows];
boolean noMoves;
boolean lost = false;
int sum = 0;
int zeroCounter = 0;

void setUpLocation () {
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
  location[4][4]=0.5;
  location[0][0] = 0.0;
  location[1][0] = 0.0;
  location[2][0] = 0.0;
  location[0][1] = 0.0;
  location[1][1] = 0.0;
  location[2][1] = 0.0;
  location[0][2] = 0.0;
  location[1][2] = 0.0;
  location[2][2] = 0.0;
  //reset top right corner
  location[7][0] = 0.0;
  location[8][0] = 0.0;
  location[6][0] = 0.0;
  location[7][1] = 0.0;
  location[8][1] = 0.0;
  location[6][1] = 0.0;
  location[7][2] = 0.0;
  location[8][2] = 0.0;
  location[6][2] = 0.0;
  //reset bottom left corner
  location[0][7] = 0.0;
  location[1][7] = 0.0;
  location[2][7] = 0.0;
  location[0][8] = 0.0;
  location[1][8] = 0.0;
  location[2][8] = 0.0;
  location[0][6] = 0.0;
  location[1][6] = 0.0;
  location[2][6] = 0.0;
  //reset bottom right corner
  location[7][7] = 0.0;
  location[8][7] = 0.0;
  location[6][7] = 0.0;
  location[7][8] = 0.0;
  location[8][8] = 0.0;
  location[6][8] = 0.0;
  location[7][6] = 0.0;
  location[8][6] = 0.0;
  location[6][6] = 0.0; 
}

boolean checkSquare(int startX, int startY) {//for squares (0,3) and (6,3)
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
  
  
boolean checkSquareTwo(int startX, int startY) {//for squares at coordinates (2,3) and (5,3)
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

boolean checkSquareThree(int startX, int startY) {// for square coordinates (3,2) and (3,5)
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

boolean checkSquareFour(int startX, int startY) {//for squares (3,0) and (3,6)
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


boolean checkSquareFive(int startX, int startY) {//for center square - that way three pieces either vertically or horizontally --> still moves
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

  
boolean endGameCheck () {
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

