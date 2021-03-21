import java.util.*;

public class mm_Tester {
	public static void main(String[] args) {
		
		Mastermind mm = new Mastermind();
		
		int[] g = new int[4];
		int[] a = new int[4];
		int[] s = new int[2];
		
		a = mm.toArray(6013);
		g = mm.toArray(0611);
		
		System.out.println("a = " + Arrays.toString(a));
		System.out.println("g = " + Arrays.toString(g));
		
		s = mm.computeScore(g, a);
		
		System.out.println(Arrays.toString(s));
	}

}
