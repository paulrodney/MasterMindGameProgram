import java.util.Scanner;
public class Mastermind_Game {
	public static void main(String[] args) {
		
		int[] s = new int[2];
		Mastermind mm = new Mastermind();
		//Where i show instructions.
		System.out.println(mm.getInstructions());
		
		//Wait for user to think of code.
		Scanner input = new Scanner (System.in);
		System.out.println("Think of a 4 dight code. Enter code and press ENTER when you are ready.");
		int ready = input.nextInt();
		mm.setAnswer(ready);
		double userScore = 0.0;
		while(userScore != 4.0){
			
			//The mm object  has to guess random code from possible codes
			System.out.println("My guess is " + mm.getGuess());
			s = mm.computeScore(mm.currentGuess, mm.ans);
			System.out.println(" (" +  s[0] + "." + s[1] + ")");
			System.out.println("There are " + mm.getNumberOfValidSolutions()+ " solutions");
			
			//This is where the user scores the guess.
			System.out.println("Score my guess you!");
			userScore = input.nextDouble();
			if(userScore == -1) {
				//command to print out possibleSolutions
				mm.printPossibleSolutions();
			} else if(userScore == -2) {
				System.out.println("Your answer is in row " + mm.rowOfAnswer());
			} else {
				//This is where the mm object checks every possible solution to see if solution
				//could result in this userScore.
				//Then removes invalid solutions. 
				mm.evaluatePossibleSolutions(userScore);
			}//if
		}//while
		System.out.println("HaHa i guessed your code and it only took me " + mm.getNumberOfTries() + " tries!");
	}//main
}//class