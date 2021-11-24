import java.io.*;
import java.util.Random;

class Card {
	
    public final String face; // 1번 instance variable; instance field
    public final String rank; // instance variable; instance field
	
    // faceNum은 0~3 안에 있는 정수 이어야 함
    private static String intToFace(int faceNum) {
        String face;
		
        switch( faceNum ) {
        case 0: face = "H";
            break;
        case 1: face = "C";
            break;
        case 2: face = "S";
            break;
        default: face = "D";
        }
		
        return face;
    }
	
    // rankNum은 1~13에 해당하는 정수이어야 함
    private static String intToRank(int rankNum) {
		
        String rank;
		
        if(rankNum >= 2 && rankNum <= 10) { // 숫자를 그대로 가져다 씀
            rank = Integer.toString(rankNum);
        } else {
            switch( rankNum ) {
            case 1: rank = "A";
                break;
            case 11: rank = "J";
                break;
            case 12: rank = "Q";
                break;
            default: rank = "K";
            }
        }
		
        return rank;
    }
	
    public int getValue() {
        switch( rank ) {
        case "A":
            return 11;
        case "J":
        case "Q":
        case "K":
            return 10;
        default: // 정수를 rank로 가지고 있음
            return Integer.parseInt(rank);
        }
    }
	
    private static Random rand = new Random(); // rand는 임의의 숫자를 생성하는 Random 객체

    // 왜 다른 생성자를 호출할 때 문장의 맨 앞에 와야 하는가?
    // 왜 다른 생성자를 호출할 때 호출 전에 다른 instance 함수를 호출하면 안되는가?
    public Card() {
        this(rand.nextInt(4), rand.nextInt(13) + 1);
    }
	
    public Card(int faceNum, int rankNum) {
        this(intToFace(faceNum), intToRank(rankNum));
    }
				 
    public Card(String face, String rank)
    {
        this.face = face;
        this.rank = rank;
    }
}
				 
public class Main {
    public static void main(String[] args) throws Exception {
		
        int accValue = 0;
        int numAs = 0;
		
        while( accValue < 17 ) {
            Card c = new Card();
			
            System.out.printf("딜러가 카드를 받았습니다: %s %s\n", c.face, c.rank);
            if( c.rank.equals("A") ) {
                numAs ++;
            }
			
            accValue += c.getValue();
			
            if(accValue > 21 && numAs > 0) {
                numAs --;
                accValue -= 10;
            }
			
            System.out.printf("누적가치: %d\n", accValue);
        }

        if(accValue <= 21) {
            System.out.println("딜을 마칩니다.");
        } else {
            System.out.println("파산하였습니다.");
        }
    }
}
