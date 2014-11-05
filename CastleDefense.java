
/**This is my own work: Ben Mabie
 * September 09, 2014
 * this program will scan a castle wall, print the scan and then 
 * determine the weakest 2X2 grid.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CastleDefense
{
    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File("data.in");                                       //first, create a scanner object, file system and 
        Scanner scan = new Scanner(file);                                       //a new ArrayList to store the data
        ArrayList<Double> myArray = new ArrayList<>();
       
     while(scan.hasNextDouble()){                                                //while loop to scan over file
                                                                                 //and set to the ArrayList      
     myArray.add(scan.nextDouble());
     }
     scan.close();                                                               //close the scanner
     
     int arrayLength = myArray.size();                                           //Calculate the number of rows and columns for the wall
     int squareRoot = (int) Math.sqrt(arrayLength);             
     int numRows = 0;
     int numCols = 0;
     
     for(int i = squareRoot; i > 0; i --)
     {
         if(((arrayLength % i)) == 0){
             numCols = i;
             numRows = arrayLength/i;
             break;
         }
     }
     
     double passer[] = new double[myArray.size()];                              //Create new double array and fill with values from arrayList to pass along to constructor
     for(int i = 0; i < myArray.size(); i++){
     passer[i] = myArray.get(i);
     }
         
    CastleDefense koopaCastle = new CastleDefense(numRows,numCols,passer);      //create new castle wall object

    System.out.println(koopaCastle);
  
    koopaCastle.findWeakest();
    }
    
    private double [][] grid;                                                   //Grid declaration
    
    public CastleDefense(int numRows, int numCols, double[] scan){              //Create constructor; store properly the grid in correct order
                                                        
    grid = new double[numCols][numRows]; 
    int counter = 0;  
        
    for(int i =0; i < numCols; i++){
        if (((i % 2)) == 1){
            for(int j = grid[i].length-1;j >= 0; j--){
               grid[i][j] = scan[counter];
               counter++;     
            }
        }
        else
        for(int j =0; j < numRows; j++){

           grid[i][j] = scan[counter];
           counter++;           
        }
    
    }
    
    }

    public double getAverage(int startRow, int endRow, int startCol, int endCol){         //create getAverage method  
          
        double average = 0.0;
        int counter = 0;
        average += grid[startRow][startCol];
        average += grid[startRow][endCol]; 
        average += grid[endRow][startCol];
        average += grid[endRow][endCol];
        
        average = average / 4.0;
        return average;
        }
            
    public void findWeakest(){                                                  //create findWeakest method
        int weakestX = 0;
        int weakestY = 0;
        double sum = 0.0;
        
        for(int i = 0; i < grid.length-1; i++){
            for(int j = 0; j < grid[i].length-1;j++){
                double average = getAverage(i,i+1,j,j+1);
                if(sum == 0){
                    sum = average;
                    weakestX = i;
                    weakestY = j;
                }
                else if(sum > average){
                    sum = average;
                    weakestX = i;
                    weakestY = j;
                }
            }
        }
        System.out.println("\n");   
        System.out.print("The weakest 2 X 2 on the castle begins at with "); 
        System.out.print("(" + weakestX + "," + weakestY + ")");        
        System.out.print(" with the average strength of " + sum); 
    }  
    
    public String toString(){                                                       //toString method
      String str = "";
      
       for(int i = 0; i < grid.length;i++){ 
                  str += "\n";
              for(int j = 0; j < grid[i].length;j ++){
                  str += grid[i][j] + "\t";
              }
      }
      
      return str;
    }
     
}
    
    
