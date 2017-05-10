import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CBCDesign {

	public static void main(String[] args) {
		
		//Implement Scanner
		Scanner scan = new Scanner(System.in);
		
		//Creating the 2D array
		//Decide how many attributes 
		System.out.print("Enter how many attributes (4): ");
		int numAtt = Integer.parseInt(scan.nextLine());
		
		//Decide how many levels
		System.out.print("Enter how many levels (3-5): ");
		int numLvl = Integer.parseInt(scan.nextLine());
		
		//Create a 2D string array to store the attributes and their respective levels
		//Attributes are stored as rows and levels are stored as columns after the 1st column index (which stores the attribute name)
		String attributes[][] = new String[numAtt][numLvl+1];
		//Store names of user-defined attributes up to number of attributes choice by user
		for (int i = 0; i < numAtt; i++) {
			System.out.print("Enter the name of attribute " + (i+1) + " : ");
            attributes[i][0] = scan.nextLine();
            System.out.println("Attribute: " + attributes[i][0]);
            
    		//Store names of respective levels of attributes up to number of levels choice by user
            for (int j = 1; j <= numLvl; j++) {
            	System.out.print("Enter the name of level " + (j) + " : ");
            		attributes[i][j] = scan.nextLine();
            		System.out.println("Level " + j + ": " + attributes[i][j]);
            }
        }
		System.out.println("\nYour attributes and their respective levels are defined as follows:");
		System.out.println(""+Arrays.deepToString(attributes));												
		
		
		//Generate series of tasks:
		System.out.println("\nYou will now be asked to choose an option for each task:");
		
		//Question combinations:
		String[] concept = new String[200]; // Array for storing concept combinations
		String[] choice = new String[200];  // Array for storing users' answers

		int cmbnum = 0;
		
		for (int j = 0; j < 92; j++) {
			for (int i = 0; i < 4; i += 4) {
				concept[cmbnum] = "\t" + attributes[i][rng()] + "\t" + attributes[i + 1][rng()] + "\t"
						+ attributes[i + 2][rng()] + "\t" + attributes[i + 3][rng()];
				cmbnum++;
			}
		}
		//Generate set of tasks:
		int T = 1;
		for (int numT = 0; numT < 5; numT++) {	// Default is set to 5
			int Tcount = numT + 1;
			System.out.println("\nTask " + Tcount);
			for (int i = T; i < T + 4; i++) {
				System.out.println("Option " + i + ": " + concept[i]);
			}
			T += 4;
			System.out.print("\nWhich option would you rather choose? ");

			int input = scan.nextInt();
			choice[numT] = concept[input];		//Store users' choices in the array
		}

		//Perform aggregation of selected options:
		
		StringBuilder sb = new StringBuilder(); 	// create empty stringBuilder
		for (int i = 0; i < choice.length; i++) {
			sb.append(choice[i]); 					// append element
			if (i == (choice.length - 1)) {
				sb.append(" "); 					// append space
			} else {
				sb.append(", "); 					// append comma
			}
		}
		System.out.println("\nItem frequency list: ");
		String result = sb.toString();
		int max = 0;
		for (int row = 0; row < numAtt; row++) {
			for (int col = 1; col <= numLvl; col++) {
				String str = result;
				String findStr = attributes[row][col];
				int lastIndex = 0;
				int count = 0;
				while (lastIndex != -1) {

					lastIndex = str.indexOf(findStr, lastIndex);

					if (lastIndex != -1) {
						count++;
						lastIndex += findStr.length();
					}

				}
				if (count > max){
					max = count;
				}
				
				System.out.println(findStr + ":" +  "\t" + count);
			}
		}
		System.out.println("Frequency of most valued item(s): " + max);
		
	}
	
	//Random generator 
	public static int rng(){
		Random r = new Random();
		int low = 1;
		int high = 4;
		int Result = r.nextInt(high - low) + low;
		return Result;
	}
}
