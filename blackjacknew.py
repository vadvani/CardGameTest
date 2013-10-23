import math
import array
import random
import string

def main():
    global deck
    global numplayercards
    global numdealercards
    global hand
    global dhand
    global money
    money = 100
    global dmoney
    dmoney = 100
    global busted
    global dbusted
    dbusted = 0
    numplayercards=0
    numdealercards=0
    #handValue = 0
    
    deck = []
    for i in range (0, 13):
        deck.append(4)
    print deck
    z = 1
    while z == 1:
        c1=drawCard()
        if c1 in (1, 11, 12, 13):
            if c1 == 11:
                print "Your first card is a Jack"
            if c1 == 12:
                print "Your first card is a Queen"
            if c1 == 13:
                print "Your first card is a King"
            if c1 == 1:
                print "Your first card is an Ace" 
        else:
            print "Your first card is",c1
        c2=drawCard()
        if c2 in (1, 11, 12, 13):
            if c2 == 11:
                print "Your second card is a Jack"
            if c2 == 12:
                print "Your second card is a Queen"
            if c2 == 13:
                print "Your second card is a King"
            if c2 == 1:
                print "Your second card is an Ace"
        else:
            print "Your second card is",c2
        d1 = drawCard()
        if d1 in (1, 11, 12, 13):
            if d1 == 11:
                print "The dealers face up card is a Jack"
            if d1 == 12:
                print "The dealers face up card is a Queen"
            if d1 == 13:
                print "The dealers face up card is a King"
            if d1 == 1:
                print "The dealers face up card is an Ace"
        else:
            print "The dealers face up card is", d1
        d2 = drawCard()
        dhand = []
        dhand.append(d1)
        dhand.append(d2)
        hand = []
        hand.append(c1)
        hand.append(c2)
        betting()
        handValue()
        hitStand()
        if busted == 0:
            dealerHitStand()
        whoWon()
        giveMoney()
        print "Would you like to deal again?  If so please type y, if not, please type n"
        x = raw_input('-->')
        if len(x) == 1:
            if x[0] == 'n':
                z = 0
            if x[0] == 'y':
                z = 1
        else:
            print "Please type y or n"
    #aceValue()
    
    
def startGame():
    global deck
    global hand
    #global handValue
    global dhand
    global numplayercards
    global numdealercards
    numplayercards=0
    numdealercards=0
    
    deck = []
    for i in range (0, 13):
        deck.append(4)
    hand = []
    c1=drawCard()
    c2=drawCard()
    hand.append(c1)
    hand.append(c2)
    print "Your cards are:"
    print c1
    print c2
    handValue = 0
    if c1 == c2:
        y=(deck.index(2)) + 1
        if y > 1:
            handValue = y * 2
            print "The total value of your hand is", handValue
        else:
            handValue = 0
            print "You have two aces and therefore the value of your cards can be 2, 12, or 22"
    else:
        y = (deck.index(3)) + 1
        deck[y]=4 #making the first instance of 3 turn into 4 so can find next instance of 3
        z = (deck.index(3)) + 1
        if y > 1:
            if z > 1:
                handValue = y + z
                deck[y]=3 #changing first instance of 3 back to 3
                print "The total value of your hand is", handValue
            else:
                if y == 1:
                    handValue = z
                    print "The total value of your hand is", handValue
                    print "Plus your ace which has not yet been assigned a value"
                if z == 1:
                    handValue = y
                    print "The total value of your hand is", handValue
                    print "Plus your ace which has not yet been assigned a value"
    
    
    d1=drawCard()
    d2=drawCard()
    dhand.append(d1)
    dhand.append(d2)
    print "The dealers face up card is", d1

def betting():
    global money
    global yourmoney
    global dmoney
    global bet
    #money = 100
    #dmoney = 100
    z = 0
    print "The amount of money you have is", money
    while z == 0:
        bet = input("Please enter the amount you are willing to bet ")
        #if str.isdigit(bet):
        z = 1
        bet = int(bet)
        if bet == 0:
            z = 0
            if bet > money:
                z = 0
        else:
            money = money - bet
            print "The total amount of money you have is now", money

def giveMoney():
    global money
    global winner
    global dwinner
    global dmoney
    global tie
    if tie == 1:
        money = money + bet
        print "Your balance is now",money
    if winner == 1:
        money = money + bet + bet
        print "Your balance is now",money
    if dwinner == 1:
        dmoney = dmoney + bet
        print "The dealer's balance is now",dmoney
        print "Your balance is now",money
        
    
            
        
    

def hitStand():
    global hand
    global value
    global aces #number of aces
    global busted
    #print "Please indicate whether you would like to hit or stand, type 'h' for hit and 's' for stand"
    #x = raw_input('-->')
    z = 1
    busted = 0
    while z == 1:
        print "Please indicate whether you would like to hit or stand, type 'h' for hit and 's' for stand"
        x = raw_input('-->')
        if len(x) == 1:
            if x[0]=='h':
                c = drawCard()
                hand.append(c)
                print "Your new card is",c
                print "Your complete hand is",hand
                
                #handValue()
                c = handValue()
                #print aces
                t = c + aces
                if t > 21:
                    print "You have busted"
                    z = 0
                    busted = 1
                    #THEN RUN PROGRAM- DEALER HIT STAND RIGHT HERE OR AFTER YOU PRESS STAND- HAVE GLOBAL VARIABLE FOR WHETHER PLAYER HAS BUSTED- USEFUL LATER WHEN PICKING WINNER?
            if x[0]=='s':
                #what do I need the program to do if they pick stand?- dealers turn to hit/stand- while dhandValue<=16- hit, else stand
                z = 0
        else:
            print "Please type either 's' or 'h'"
            x = raw_input('-->')

    if aces > 0:
        if busted == 0:
            b = 1
            for i in range (0, aces):
                while b == 1:
                    print "Please indicate whether this ace will be worth 1 or 11 by typing 1 or 11"
                    a = raw_input('-->')
                    if len(a) == 1:
                        if a[0]=='1':
                            value = value + 1
                            b = 0
                            print "The total value of your cards is now", value
                    if len(a) == 2:
                        if a[0] == '1':
                            if a[1] == '1':
                                value = value + 11
                                #b = 0
                                if value > 21:
                                    print "You cannot assign 11 to this ace, the total of your cards will be too high"
                                    b = 1
                                else:
                                    print "The total value of your cards is now", value
                                    b = 0
            #else:
                #print "Please type either 's' or 'h'"
                #x = raw_input('-->')
        
def aceValue():
    global hand
    global aces
    global value
    
    if aces > 0:
        
        b = 1
        for i in range (0, aces):
            print "aceValue is running"    
            while b == 1:
                print "Please indicate whether this ace will be worth 1 or 11 by typing 1 or 11"
                a = raw_input('-->')
                if len(a) == 1:
                    if a[0]=='1':
                        value = value + 1
                        b = 0
                        print "The total value of your cards is now", value
                if len(a) == 2:
                    if a[0] == '1':
                        if a[1] == '1':
                            value = value + 11
                            #b = 0
                            if value > 21:
                                print "You cannot assign 11 to this ace, the total of your cards will be too high"
                                b = 1
                            else:
                                print "The total value of your cards is now", value
                                b = 0

#def aceValue2():
    

def dealerHitStand():
    global dhand
    global dAces
    global value
    global busted
    global dbusted
    global dvalue
    dvalue = dhandValue()
    z = 1
    dbusted = 0
    while z == 1:
        print dhand
        print dvalue
        if dvalue<=16:
            y = drawCard()
            dhand.append(y)
            dvalue = dhandValue()
            print "The dealer has chosen to hit"
            print "The dealer's new card is", y
        else:
            t = dvalue + dAces
            if dvalue <=21:
                print "The dealer chooses to stand"
                print "The final value of the dealers cards is", dvalue
                z = 0
            else:
                print "The dealer has busted"
                dbusted = 1
                print "The final value of the dealers cards is", dvalue
                z = 0
def whoWon():
    global value
    global dvalue
    global busted
    global dbusted
    global winner
    global tie
    global dwinner
    winner = 0
    dwinner = 0
    tie = 0
    message = 0
    if busted == 1:
        print "The dealer has won"
        message == 1
        dwinner = 1
    else:
        if dbusted == 1:
            print "You have won"
            message = 1
            winner = 1
        if dvalue == value:
            print "You have tied"
            message = 1
            tie = 1
        if message == 0:
            if dvalue > value:
                print "The dealer has won"
                message = 1
                dwinner = 1
            else:
                print "You have won"
                message = 1
                winner = 1

def dhandValue():
    global dhand
    global dAces
    dAces=0
    x = len(dhand)
    value= 0
    y = 0
    z = 0 #whether has printed message or not
    for i in range (0, x):
        if dhand[i]>1:
            if dhand[i]<11:
                value= value + dhand[i]
            else:
                
                value=value + 10
        else:
            dAces = dAces + 1
    for i in range (0, dAces):
        value = value + 11
        if value > 21:
            value = value - 10
    return value
        
    
    
    
            #print "The total value of your hand is", value
            #print "Plus the", y
            #print "ace(s) you have, which do not have a specific value yet"
            #z = 1
    
    #if z == 0:
        #print "The total value of your hand is", value
            
            


def drawCard():
    global deck
    x = 0
    x = random.randint(1,13)
    #print x
    y = deck[(x-1)]
    if y > 0:
        y = y-1
        deck[(x-1)]=y
        #print y
    else:
        while y == 0:
            x = random.randint(1,13)
            y = deck[(x-1)]
        y = y - 1
        #print y
        deck[(x-1)]=y
        #print y
    return x

def handValue(): #a is the previous hand value, be is the card you are adding to your hand value
    global hand
    global aces
    global value
    x = len(hand)
    value= 0
    aces = 0
    for i in range (0, x):
        if hand[i]>1:
            if hand[i]<11:
                value= value + hand[i]
            else:
                
                value=value + 10
        else:
            aces = aces + 1
            
    
    if aces > 0:
        print "The total value of your hand is", value
        print "Plus the", aces
        print "ace(s) you have, which do not have a specific value yet"

    else:
        print "The total value of your hand is", value

    return value
    
    

#startGame()
main()
print deck
