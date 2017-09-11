import java.util.*;
import java.lang.*;

public class PlayerGrowth {
	public static void main	(String[] args) {
      Random rand = new Random();
      
		final	int weeksPerYear = 52;
		final	int totalMatches = 40;
		final	int yearsToMature	= 7;
		
		//	Minimum experience gained per week.
		final	int highExp	= 380;
		final	int regExp = 180;
		final	int lowExp = 30;
		
		// Minimum experience gained per match played.
		final	int matchHigh = 475;
		final	int matchReg =	275;
		final	int matchLow =	50;
		
      // Ranges.
      final int weeklyRange = 40;
      final int matchRange = 50;
      
		//	Match	multiplier based off	match	result.
		final	double winMult	= 1.5;
		final	double drawMult =	1;
		final	double lossMult =	0.75;
		
		//	Experience per	level	function	constants.
		final	double functionMultYouth = 0.001;
      final double functionMultOlder = 0.01;
		final	int exponentYouth = 4;
      final int exponentOlder = 4;
		
		//	Initial values. Row 1 is fast, 2 is reg, 3 is slow. Col 1 is wins, 2 is draw, 3 is loss.
		int[][] overalls = new int[3][3];
      int[][] experience = new int[3][3];
      
      for (int x = 0; x < 3; x++) {
         for (int y = 0; y < 3; y++) {
            overalls[x][y] = 70;
            experience[x][y] = 0;
         }
      }
		
		for (int	x = 0; x	< yearsToMature; x++) {
			for (int y = 0; y < weeksPerYear; y++) {
            experience[0][0] += (rand.nextInt(weeklyRange) + highExp);
            experience[1][0] += (rand.nextInt(weeklyRange) + highExp);
            experience[2][0] += (rand.nextInt(weeklyRange) + highExp);
            experience[0][1] += (rand.nextInt(weeklyRange) + regExp);
            experience[1][1] += (rand.nextInt(weeklyRange) + regExp);
            experience[2][1] += (rand.nextInt(weeklyRange) + regExp);
            experience[0][2] += (rand.nextInt(weeklyRange) + lowExp);
            experience[1][2] += (rand.nextInt(weeklyRange) + lowExp);
            experience[2][2] += (rand.nextInt(weeklyRange) + lowExp); 
         }
         
         for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
               int expNeeded;
               
               if (overalls[r][c] < 80) {
                  expNeeded = (int) (functionMultYouth * (Math.pow(overalls[r][c], exponentYouth)));
               }
               else {
                  expNeeded = (int) (functionMultOlder * (Math.pow(overalls[r][c], exponentOlder)));
               }
               
               while (overalls[r][c] < 99 && experience[r][c] >= expNeeded) {
                  experience[r][c] -= expNeeded;
                  overalls[r][c]++;
                  
                  if (overalls[r][c] < 80)
                     expNeeded = (int) (functionMultYouth * (Math.pow(overalls[r][c], exponentYouth)));
                  else
                     expNeeded = (int) (functionMultYouth * (Math.pow(overalls[r][c], exponentOlder)));
               }
            }
         }
         
         for (int y = 0; y < totalMatches; y++) {
            experience[0][0] += (int) ((rand.nextInt(matchRange) + matchHigh) * winMult);
            experience[1][0] += (int) ((rand.nextInt(matchRange) + matchHigh) * drawMult);
            experience[2][0] += (int) ((rand.nextInt(matchRange) + matchHigh) * lossMult);
            experience[0][1] += (int) ((rand.nextInt(matchRange) + matchReg) * winMult);
            experience[1][1] += (int) ((rand.nextInt(matchRange) + matchReg) * drawMult);
            experience[2][1] += (int) ((rand.nextInt(matchRange) + matchReg) * lossMult);
            experience[0][2] += (int) ((rand.nextInt(matchRange) + matchLow) * winMult);
            experience[1][2] += (int) ((rand.nextInt(matchRange) + matchLow) * drawMult);
            experience[2][2] += (int) ((rand.nextInt(matchRange) + matchLow) * lossMult); 
         }
         
         for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
               int expNeeded;
               
               if (overalls[r][c] < 80) {
                  expNeeded = (int) (functionMultYouth * (Math.pow(overalls[r][c], exponentYouth)));
               }
               else {
                  expNeeded = (int) (functionMultOlder * (Math.pow(overalls[r][c], exponentOlder)));
               }
               
               while (overalls[r][c] < 99 && experience[r][c] >= expNeeded) {
                  experience[r][c] -= expNeeded;
                  overalls[r][c]++;
                  
                  if (overalls[r][c] < 80)
                     expNeeded = (int) (functionMultYouth * (Math.pow(overalls[r][c], exponentYouth)));
                  else
                     expNeeded = (int) (functionMultYouth * (Math.pow(overalls[r][c], exponentOlder)));
               }
            }
         }
         
         System.out.println("Year " + (x + 1) + " results");
         for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
               System.out.println("Overall:    " + overalls[r][c]);
               System.out.println("Experience: " + experience[r][c]);
               System.out.println();
            }
         }
		}
	}
}