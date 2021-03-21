import java.util.*;

class Mastermind {

	//define the properties of the class.
	int[] currentGuess = new int[4];
	int[] ans = new int[4];
	int numberOfValidSolutions = 10000;
	int[][] possibleSolutions = new int[numberOfValidSolutions][4];
	private int numberOfTries = 0;
	private final int SENTINAL = -1000;

	Mastermind(){
		//constructor

		//where the program finds all possible solutions
		int index;
		for(int i = 0; i <= 9; i++) {
			for(int j = 0; j <= 9; j++) {
				for(int k = 0; k <= 9; k++) {
					for(int m = 0; m <= 9; m++) {
						index = (i * 1000) + (j * 100) + (k * 10) + m;
						possibleSolutions[index][0]=i; //thousands digit
						possibleSolutions[index][1]=j; //hundreds digit
						possibleSolutions[index][2]=k; //tens digit
						possibleSolutions[index][3]=m; //ones digit
					}//m
				}//k
			}//j
		}//i
	}//constructor
	
	public int getNumberOfValidSolutions() {
		return this.numberOfValidSolutions;
	}
	
	public int getNumberOfTries() {
		return this.numberOfTries;
	}
	
	public int getGuess() {
		//generate random number that is going to be between 0 and numberOfValidSolutions - 1
		//return possible solution given by random number  
		//and update currentGuess array
		Random rand = new Random();
		int randomRow;
		if(numberOfValidSolutions > 1) {
			randomRow = rand.nextInt(numberOfValidSolutions);
		} else {
			randomRow = 0;
		}//else
		//currentGuess is an array that stores the code the mm object just guessed 
		//currentGuess is an single dimension array and it has 4 elements
		//the element in the 0 index place is the thousands digit of the guess
		//the element in the 1 index place is the hundreds digit of the guess
		//the element in the 2 index place is the tens digit of the guess
		//the element in the 3 index place is the ones digit of the guess
		currentGuess[0] = possibleSolutions[randomRow][0]; 
		currentGuess[1] = possibleSolutions[randomRow][1]; 
		currentGuess[2] = possibleSolutions[randomRow][2]; 
		currentGuess[3] = possibleSolutions[randomRow][3]; 
		return (currentGuess[0] * 1000) + (currentGuess[1] * 100) + (currentGuess[2] * 10) + (currentGuess[3] * 1);
	}
	
	public String getInstructions(){
		String instructions;
		instructions = "When scoring a whole number means there is the right digiht in the right place and a decimal number means there is a right number in the wrong place.";
		return instructions;
	}
	
	public void evaluatePossibleSolutions(double score) {
		int correctPlace = (int) score;
		int wrongPlace = (int) (score * 10) % 10;
		int[] scoreForRow = new int[1];
		for(int row = 0; row <= numberOfValidSolutions - 1; row++){
			//scoreForRow element 0 will be the number of numbers in the code in the right spot
			//scoreForRow element 0 will be the number of numbers in the code that are right but in the wrong spot
			scoreForRow = computeScore(currentGuess, possibleSolutions[row]);
			if((correctPlace != scoreForRow[0]) || (wrongPlace != scoreForRow[1])){
				//we now know that the solutions in possibleSolutions[row] is invalid
				//indicate this row's solution is invalid by setting the thousands digit in the code to the value SENTINAL
				possibleSolutions[row][0] = SENTINAL;
			}//if
		}//for
		compactArray(possibleSolutions);
		this.numberOfTries++;
	}
	
	public int[] computeScore(int[] g, int[] a){
		boolean debug = false;

		//g[] is current guess
		//a[] is possible answer
		boolean[] used = {false, false, false, false};
		//count element 0 is the number of numbers in the code in the right spot
		//count element 1 is the number of numbers in the code that are right but in the wrong spot
		int[] count = { 0, 0 };

		for(int i = 0; i <= 3; i++) { 
			if (a[i] == g[i]) {
				used[i] = true; 
				count[0]++;// the zero index is the numbers
			
			}//if a[i] == g[i]  
		}//for a
		
		for(int i = 0; i <= 3; i++) {
			//i iterates over g[]
			if(a[i] != g[i]) {
				for (int j = 0; j <= 3; j++) {
					//j iterates over a[]
					if(used[j] == true) {
						
						continue; 	//takes us to the the next j values
					}//if used[j] true
					if(g[i] == a[j]) {
						used[j] = true;
						break;  //breaks j loop, goes to next i
					} 
				}//for j
			}//if
		}//for   i
		return count;
	} // computeScore

	public void compactArray(int[][] a) {
		//write code here to compact the array
		//you know that it contains 26 elements, but a.length() tells you that

		int num1 = -1;

		for(int row = 0; row <= this.numberOfValidSolutions - 1; row++) {

			if(a[row][0] != SENTINAL) {

				num1++;
				a[num1][0] = a[row][0];
				a[num1][1] = a[row][1];
				a[num1][2] = a[row][2];
				a[num1][3] = a[row][3];
			}//if
		}//for
		this.numberOfValidSolutions = num1;
		System.out.println("Row of answer " + rowOfAnswer());
	}//compactArray
	
	public void printPossibleSolutions() {
		for(int row = 0; row <= this.numberOfValidSolutions - 1; row++) {
			System.out.println(row + ": " + possibleSolutions[row][0] + possibleSolutions[row][1] + possibleSolutions[row][2] + possibleSolutions[row][3]);
		}//for
		System.out.println("There are " + this.numberOfValidSolutions + " valid solutions.");
	}//printPossibleSolutions
	
	public void setAnswer(int answer) {
		this.ans[3] = answer % 10;
		this.ans[2] = (answer / 10) % 10;
		this.ans[1] = (answer / 100) % 10;
		this.ans[0] = (answer / 1000) % 10;
		System.out.println("Answer = " + ans[0] + ans[1] + ans[2] + ans[3]);
	}//setAnswer
	
	public int rowOfAnswer() {
		for(int row = 0; row <= this.numberOfValidSolutions - 1; row++) {
			if(possibleSolutions[row][0] == this.ans[0] &
			   possibleSolutions[row][1] == this.ans[1] &
			   possibleSolutions[row][2] == this.ans[2] &
			   possibleSolutions[row][3] == this.ans[3] ){
				return row;
			}//if
		}//for
		return SENTINAL;
	}//rowOfAnswer
	
	public int[] toArray(int number) {
		int[] j = new int[4];

		for (int i = 0; i<=3; i++) {
			j[i] = (int) ( number / Math.pow(10, 3-i) ) % 10;
		}//for
		return j;
	}//toArray
	
}//Class Mastermind