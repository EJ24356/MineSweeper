
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;
import java.io.File;


/**
 * This class represents a Minesweeper game.
 *
 * @author Jesse Eldell <jte30124@uga.edu>
 */
public class MineSweeper 
{

	private int x; //rows
	private int y; //columns
	private int rounds;//rounds in the game
	private int mines; //mines
	private String [][] grid;// Reads in for the grid size and things
	private boolean[][] MineLocation; //Used to confirm or reject the location of a mine
	private int win;
	private Scanner Key; 
	private int token=0,token2=0;


    /**
     * Constructs an object instance of the {@link MineSweeper} class using the
     * information provided in <code>seedFile</code>. Documentation about the
     * format of seed files can be found in the project's <code>README.md</code>
     * file.
     *
     * @param seedFile the seed file used to construct the game
     * @see            <a href="https://github.com/mepcotterell-cs1302/cs1302-minesweeper-alpha/blob/master/README.md#seed-files">README.md#seed-files</a>
     */
    public MineSweeper(File seedFile)
    
    {
      try
       {
    	  
           File f = new File(seedFile.getAbsolutePath());
           Scanner Seed = new Scanner(f);
         
	   int counta=0;

           if (Seed.hasNextInt())
           {
        	   x=Seed.nextInt();
        	   
		   counta++;

        	 if (Seed.hasNextInt())
        	   {
        		 
        		y=Seed.nextInt();
        		
        		counta++;

        		  if (Seed.hasNextInt()&counta==2)
        		  {
        			  
        			  mines=Seed.nextInt();
        			
        		  }
                }
           }     
           
           if (((x<0) && (x>10) && (y<0) && (y>10)) || (this.mines>(x*y)))
           {
        	   System.out.println("\nCannot create game with " + seedFile + ", because it is not formatted correctly.\n");
     			 System.exit(0); 
           }
                
           grid=new String [x][y];
           MineLocation=new boolean [x][y];
           for(int i = 0; i < x; i++) 
           {
				for(int j = 0; j < y; j++) 
				{
			       	MineLocation[i][j] = false; //It is not the mine until we prove it is (Innocent until proven guilty)

					grid[i][j] = "   "; //This loop is basically the declaration part of the seedFile creation
				}
			}
           
           boolean p=false,q=false;
	   int countloco=0;
			for(int i = 0; i < mines; i++) //Im using this methods to clarify that i want the specified variable to be used 
			{
				int row = 0, col = 0;
				if(Seed.hasNextInt()) 
				{
					row = Seed.nextInt();
					
					if(Seed.hasNextInt()) 
					{
						col = Seed.nextInt();
						
						if((row >= 0&row < x)&(col >= 0 & col < y)) 
						{
							MineLocation[row][col] = true;
							countloco++;
							p=true;
							    if (countloco==mines)
                                                     {
							 q=true;
                                                           }
						}
					}
				} 
			} 
			if ((p==false)|(q==false))
			{
				System.out.println("Cannot create game with " + seedFile + ", because it is not formatted correctly.");
   			System.out.println();
   			System.exit(0);
			}
           
           
           
          }   catch (Exception e)
           {
    	   
    	   System.err.println("ERROR: File not found/invalid file.");
   		     System.exit(0);
    	  
           
           }

    } // Minesweeper

    
    /**
     * Indicates whether or not the square is in the game grid.
     *
     * @param row the row index of the square
     * @param col the column index of the square
     * @return true if the square is in the game grid; false otherwise
     */
    private boolean isInBounds(int row, int col) 
    { 
    	
    	if (((row>=0)&&(row<grid.length))&&((col>=0)&&(col<grid[0].length)))
    	{
    		
    	return true;
    	}
    	else
    	{
    		return false;		
    	}
			
    }
    
    
    /**
     * Returns the number of mines adjacent to the specified
     * square in the grid. 
     *
     * @param row the row index of the square
     * @param col the column index of the square
     * @return the number of adjacent mines
     */
    private int getNumAdjMines(int row, int col) 
    {
    	int Adj=0;
    
    	
    	  if ((row==0)&(col>0&col<y-1))//5
    	  {
    	    	if(MineLocation[row][col-1]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }	
    	     	    	  
    	    	  if(MineLocation[row][col+1]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
    	    	
    	    	
    	    	  if(MineLocation[row+1][col]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
    	    	  if(MineLocation[row+1][col+1]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
    	    	  if(MineLocation[row+1][col-1]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
    	    	 
    	  }
	  else if ((col==0)&(row>0&row<y-1))//5
		   {
		       if (MineLocation[row][col+1]==true)
			   {
			       Adj++;
			   }
		       if (MineLocation[row-1][col+1]==true)
			   {
                              Adj++;
                            
                           }
                       if (MineLocation[row+1][col]==true)
			   {
                           Adj++;
                           }
                       if (MineLocation[row+1][col+1]==true)
			   {
			       Adj++;
			   }
                       if (MineLocation[row-1][col]==true)
			   {
                               Adj++;
                           }
            
		   }
    	  else if(((row==0)&(col==0)))//3
    	  {
    		  if(MineLocation[row][col+1]==true)
 	   	     {
 	   		  Adj++; 
 	   	     }
 	    	
 	    	if(MineLocation[row+1][col]==true)
 	    	  {
 	    		  Adj++; 
 	    	  }
 	    	
 	    	  if(MineLocation[row+1][col+1]==true)
 	    	  {
 	    		  Adj++; 
 	    	  }
 	    	  
    	  }
    	  else if((row==0)&(col==y-1))//3
    	  {
    		 if(MineLocation[row][col-1]==true)
  	   	     {
  	   		  Adj++; 
  	   	     }
  	    	
  	    	 if(MineLocation[row+1][col]==true)
  	    	  {
  	    		  Adj++; 
  	    	  }
  	    	
  	    	 if(MineLocation[row+1][col-1]==true)
  	    	  {
  	    		  Adj++; 
  	    	  }
    	  }
    	  else if ((row==x-1)&(col==0))//3
    	  {
    		  if(MineLocation[row][col+1]==true)
	    	  {
	    		  Adj++; 
	    	  }	
	     	    	  
	    	  if(MineLocation[row-1][col+1]==true)
	    	  {
	    		  Adj++; 
	    	  }
	    	
	    	
	    	  if(MineLocation[row-1][col]==true)
	    	  {
	    		  Adj++; 
	    	  }
		 
    	  }
    	  else if ((row==x-1)&(col>0&col<y-1))//5
    	  {
    		  if(MineLocation[row-1][col]==true)
	    	  {
	    		  Adj++; 
	    	  }	
	     	    	  
	    	  if(MineLocation[row-1][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
	    	  if(MineLocation[row][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
	    	  if(MineLocation[row-1][col+1]==true)
	    	  {
	    		  Adj++; 
	    	  }
	    	  if(MineLocation[row][col+1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    	  }
    	  else if ((row==x-1)&(col==y-1))//3
    	  {
    		  if(MineLocation[row][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }	
	     	    	  
	    	  if(MineLocation[row-1][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
	    	
	    	
	    	  if(MineLocation[row-1][col]==true)
	    	  {
	    		  Adj++; 
	    	  }
    	  }
    	  else if ((row>0)&(row<x-1)&(col==y-1)) //5
    	  {
    		  if(MineLocation[row][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row+1][col]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row-1][col]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row+1][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row-1][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    	  }
    	  else if ((row==x-1)&(col>0)&(col<y-1)) //5
    	  {
    		  if(MineLocation[row-1][col]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row-1][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row-1][col+1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row][col-1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    		  if(MineLocation[row][col+1]==true)
	    	  {
	    		  Adj++; 
	    	  }
    	  }
    	  else if (((row>0)&(row<x-1))&((col>0)&(col<y-1)))//The rest  
    	  {
    		  if(MineLocation[row][col-1]==true)
    	   	     {
    	   		  Adj++; 
    	   	     }
    	    	
    	    	if(MineLocation[row][col+1]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
    	    	
    	    	  if(MineLocation[row+1][col]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
    	    	  
    	    	  if(MineLocation[row-1][col]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
    	    	
    	    	  if(MineLocation[row-1][col-1]==true)
    	    	  {
    	    		  Adj++; 
    	    	   }
    	    	
    	    	  if(MineLocation[row+1][col+1]==true)
    	    	  {
    	    		  Adj++; 
    	    	  }
		  if(MineLocation[row-1][col+1]==true)
		      {
                          Adj++;
		      }
    	    	  if(MineLocation[row+1][col-1]==true)
		      {
                          Adj++;
		      }
    	  }
    	 
      	return Adj;
    }
    
         
   
    private void Round() 

    {
    	System.out.println("\nRounds Completed: " + rounds+"\n");
    
    }
    
    
    private void Grid()

  {
	  
	     for (int i = 0; i<grid.length;i++)
	    {
	    	 System.out.print(" "+i);
	    	   for (int j = 0; j <grid[i].length;j++)
	    	   {
	    	      System.out.print(" |  "); 
	    	     
	    	   }
	    	   System.out.print(" | "); 
	    	   
	    	   if (i==x-1)
	 	      {
	 	    	  for (int k=0;k<grid[i].length;k++)
	 	    	  {
	 	    		  if (k>0)
	 	    		  {
	 	    			  System.out.print("   "+k);
	 	    		  }
	 	    		  else
	 	    		  {
	 	    		  
	 	    		 System.out.print("\n     "+k);
	 	    		  }
	 	    	  }
	 	      }
	    	   System.out.print("\n");
	    	}
  }
    
    
    private void updateGrid()
    {
    	for(int i=0;i<grid.length;i++) 
    	{
    		System.out.print(" "+i+ " |");
    		for(int j=0;j<grid[i].length;j++)
    		{
    		  System.out.print(grid[i][j]); //With this, you can replace the spaces with values F,?,getNumAdjMines
    		  if(j<grid[i].length-1)
    		  {
    			  System.out.print("|"); 
    		  }
    			
    		}
    		System.out.println("| ");
    	}
    	
	    	  for (int k=0;k<grid[0].length;k++) // grid[0] is the generic representation of the amount of spaces in that row
	    	  {
	    		  if (k>0)
	    		  {
	    			  System.out.print("   "+k);
	    		  }
	    		  else
	    		  {
	    		  
	    		 System.out.print("     "+k);
	    		  }
	    	  
	      }
  	   System.out.print("\n");
  	}
    	
    
    
    
    /**
     * Constructs an object instance of the {@link MineSweeper} class using the
     * <code>rows</code> and <code>cols</code> values as the game grid's number
     * of rows and columns respectively. Additionally, One quarter (rounded up)
     * of the squares in the grid will will be assigned mines, randomly.
     *
     * @param rows the number of rows in the game grid
     * @param cols the number of cols in the game grid
     */
    public MineSweeper(int rows, int cols)
    {
    	
        Random xmine = new Random(),ymine=new Random();
        
        
        if(((rows<0)||(rows>10))||((cols<0)||(cols > 10)))
        {
    	    System.out.println("\n?_? says, \"Cannot create a mine field with that many rows and/or columns!\"");
    		System.exit(0);
    	}
    
        x=rows;
        y=cols;
        
        grid=new String [x][y];
        MineLocation=new boolean [x][y];
        for(int i = 0; i < x; i++) 
        {
				for(int j = 0; j < y; j++) 
				{
					MineLocation[i][j] = false; //It is not the mine until we prove it is (Innocent until proven guilty)
					grid[i][j] = "   ";
				}
			}
        
        mines=(int) Math.ceil(rows*cols*.25);
        
        int switchrow=0,switchcol=0;
    	
        for(int i = 0; i < mines; i++) 
        {
    		int row = xmine.nextInt(rows);//Randomize the row
    		int col = ymine.nextInt(cols);//Randomize the column
    		if((i>0)&&(row == switchrow) && (col == switchcol))
    		{
    			   i--;
             }
    		
    		switchrow = row;
    		switchcol = col;
    		MineLocation[row][col] = true;
        }
    	
           

    } // Minesweeper
    
    private boolean R() 
    {
       	boolean row=false,col=false;
    	int RRow;
		int RCol=0;
	
   
		if(token>=0|token<10)
		{
			RRow = token;
			row=true;
		
       
			if((row==true)&(token2<10&token2>=0))
			{
				RCol=token2;
				col=true;
			
				if((row==true)&&(col==true) && (isInBounds(RRow, RCol))&&(MineLocation[RRow][RCol]==false))
				{
					this.rounds++;
					grid[RRow][RCol]=" "+getNumAdjMines(RRow,RCol)+" ";
					
				}
				else if ((row==true)&(col==true)&(isInBounds(RRow, RCol))&(MineLocation[RRow][RCol]==true))
				{
					System.out.println("\n Oh no... You revealed a mine!");
			    	System.out.println("  __ _  __ _ _ __ ___   ___    _____   _____ _ __"+ "\n"+
			    			 " /"+ " _` |/ _` | '_ ` _ "+"\\"+ " / _ "+"\\  / _ \\ \\ / / _ \\"+ " '"+"__|"+"\n"
			    			 +"| (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |"+"\n"+
			    			  " \\"+"__, |"+"\\__,_|_| |_| |_|"+"\\"+"___|  \\___/ \\_/ "+"\\___|_|"+"\n"   
			    			 + " |___/"+"\n"); 
                   System.exit(0);
				}
			}
			else 
			{
				rounds++;
				System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
			}
		
			}
			else 
			{
				rounds++;
				System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
			}
			
		
       return row&&col;
    }
    private boolean M()
    {
    	boolean row=false,col=false;
    	
    	int MRow = 0;
		int MCol = 0;
    	
		if((token>=0|token<10)) 
		{
			MRow=token;
			row=true;
			
		 if ((row==true)&(token2<10&token2>=0))
		 {
				MCol=token2;
				col=true;
			if(MCol<10&&MCol>=0)
			{
				if((row==true)&&(col==true) && isInBounds(MRow, MCol)) 
				{
		
					rounds++;
					grid[MRow][MCol] = " F ";
					
				}
			}
			else 
			{
				this.rounds++;
				System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
			}
					 
		 }
		 else 
		       {
				this.rounds++;
				System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
			}
			
		}
	
		return row &&col;
        	
    }
    
    private boolean G()
    {
    	
    	boolean row= false,col=false;
    	int gRow = 0;
		int gCol = 0;
		if((token<10&token>=0)) 
		{
			gRow = token;
			row=true;
          if(gRow>=0&gRow<10)
          {
			if((row==true)&(token2<10&token2>=0)) 
			{
				gCol = token2;
				col=true;
			if(gCol>=0&gCol<10)
			{
				if((row==true)&&(col==true) && isInBounds(gRow, gCol)) 
				{
					
					rounds++;
					grid[gRow][gCol] = " ? ";
					
				}
			}
			else 
			{
				this.rounds++;
				System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
			}
			
			}
          }
          else 
			{
				this.rounds++;
				System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
			}
			
		}
		else 
		{
			rounds++;
			System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
		}
		return row&&col;
}
    
    
    public void Commandline()
    {
       
    	System.out.print(" \nminesweeper-alpha$ ");
    	Key=new Scanner(System.in);
    	String p="";
    	if (Key.hasNextLine())
    	{
    		p=Key.nextLine().trim();
    	}
    	String keys=p.trim();
    	boolean u=false;
    	 if (keys.isEmpty())
    	 {
    		u=true;
    	 }
    	 else if ((u!=true)&((keys.charAt(0)=='r')|(keys.charAt(0)=='R')|(keys.charAt(0)=='g')|(keys.charAt(0)=='G')|(keys.charAt(0)=='m')|
    			(keys.charAt(0)=='M')))
    	{
    		if (keys.length()==5)
    		{
    	 token=Character.getNumericValue(keys.charAt(2));
    	 token2=Character.getNumericValue(keys.charAt(4));
    		}
    		else if (keys.length()==8)
    		{
    			 token=Character.getNumericValue(keys.charAt(5));
    	    	 token2=Character.getNumericValue(keys.charAt(7));
    		}
    		else if (keys.length()==10)
    		{
    			 token=Character.getNumericValue(keys.charAt(7));
    	    	 token2=Character.getNumericValue(keys.charAt(9));
    		}
    		else if (keys.length()==9)
    		{
    			 token=Character.getNumericValue(keys.charAt(6));
    	    	 token2=Character.getNumericValue(keys.charAt(8));
    		}
    		else 
    		{
    			u=true;
    		}
    	}
    	if (keys.equalsIgnoreCase("help")||keys.equalsIgnoreCase("h"))
    	{
    		rounds++;

        	System.out.println("\nCommands Available...");
        	System.out.println(" - Reveal: r/reveal row col");
        	System.out.println(" -   Mark: m/mark   row col");
        	System.out.println(" -  Guess: g/guess  row col");
        	System.out.println(" -   Help: h/help");
        	System.out.println(" -   Quit: q/quit\n");
    	}
    	else if (u==true)
    	{
    		System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
    		rounds++;
    	}
    	else if ((keys.equalsIgnoreCase("quit")||(keys.equalsIgnoreCase("q"))))
    	{
      		System.out.println("ლ(ಠ_ಠლ)"+"\n"
                              +"Y U NO PLAY MORE?"+"\n"+
				 "Bye!"+"\n");
    		System.exit(0);
    	}
    	else if ((keys.charAt(0)=='g')|(keys.charAt(0)=='G'))
    	{
    		G();
    	}
    	else if ((keys.charAt(0)=='m')|(keys.charAt(0)=='M'))
    	{
    		M();
    	}
    	else if ((keys.charAt(0)=='r')|(keys.charAt(0)=='R'))
    	{
    		R();
    	}
    	else
    	{
    		System.out.print("\nಠ_ಠ says, "+"Command not recognized!\n");
    		rounds++;
    	}
       
    }
     
    private int score() 

    {
    	return (x*y) - mines - rounds;
    }
    
    private int WinCondition()
    {
    	boolean foundmine=false, foundsquare=false;
     
        boolean op=false;
      int foundit=0;
          
    	for(int i=0;i<grid.length;i++)
    	{	
    		if (op)
    		{
    			break;
    		}
    	for (int j=0;j<grid[i].length;j++)
    		{
    			if ((MineLocation[i][j]==true)&(grid[i][j]==" F "))
    			{
    				foundit++;
    				if (foundit==mines)
    				{
    					foundmine=true;
    					
    				}
     			}
    			if (grid[i][j]==" ? ")
    			{
    				{
    					foundsquare=false;
    					op=true;
    				}
    				break;
    		    }
    			else if (!(grid[i][j]==" ? "))
    			{
    				{
    					foundsquare=true;
    				}
    		    }	
    	}
    	
    	if ((foundmine==true)&(foundsquare==true))
		{
			win=1;
        }
    	else
    	{
    		win=0;
    	}
    	
    	}
    	return win;
    }
  
 
    /**
     * Starts the game and execute the game loop.
     * 
     * This is going require MORE helper methods because this method makes the game accessible and make sit function
     * Also, make sure that the run method is filled up with mostly methods because the run method is
     * the compilation of all the methods in the game (other than the Minesweeper classes that take
     * in int and File) that run in sequence. Remember how in the main method, the game call 
     * the run method (game.run) if everything works like it should and you don't receive that default switch
     * statement.
     */ 
        
    public void run() 
    {

    	System.out.println("        _"+"\n"+
    			  "  /"+"\\"+'/'+"\\"+" (_)_ __   ___  _____      _____  ___ _ __   ___ _ __"+"\n"+
    			  " /"+ "    \\"+"| |"+ " '"+"_"+ " \\"+ " /" +" _ "+ "\\"+'/'+ " __"+"\\"+ " \\"+" /"+"\\"+" /"+ " /"+ " _"+
    			  " \\"+'/'+ " _"+ " \\"+ " '"+"_"+ " \\"+ " /" +" _"+ " \\"+ " '"+"__|"+"\n"+
    			 '/'+ " /"+"\\"+'/'+"\\"+ " \\"+ " | | | |  __"+'/'+"\\__"+ " \\"+"\\"+ " V  V /  __/  __/ |_) |  __/ |"+"\n"+
    			 '\\'+'/'+"    \\"+'/'+"_|_| |_|"+'\\'+"___||___"+'/'+ " \\_/"+"\\_/"+ " \\___|"+"\\___| .__/"+ " \\"+"___|_|"+
    			                              "\n                             ALPHA EDITION |_| v2017.f");
    	win=0;
    	Round();
    	Grid();	
    	do
    	{
    		 
    	  do
    	{
    		Commandline();
    		Round();
    		updateGrid();
    	    WinCondition();
    	}while (win==0);
    		
    		if (win==1)
    		{
		    System.out.print("\n");
    System.out.println("░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░"+"\"So Doge\"");
    System.out.println("░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░");
    System.out.println("░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░"+"\"Such Score\"");
    System.out.println("░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░");
    System.out.println("░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░"+"\"Much Minesweeping\"");
    System.out.println("░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░");
    System.out.println("░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░"+"\"Wow\"");
    System.out.println("░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░");
    System.out.println("░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░");
    System.out.println("░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░");
    System.out.println("▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░");
    System.out.println("▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌");
    System.out.println("▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░");
    System.out.println("░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░");
    System.out.println("░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░");
    System.out.println("░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░");
    System.out.println("░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS!");
    System.out.println("░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON!");
    System.out.println("░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: "+score()+"\n");
    			System.exit(0);
    			

    		}
    		
    		
    	}while(win==0);
    	    	

    } // run


    /**
     * The entry point into the program. This main method does implement some
     * logic for handling command line arguments. If two integers are provided
     * as arguments, then a Minesweeper game is created and started with a
     * grid size corresponding to the integers provided and with 10% (rounded
     * up) of the squares containing mines, placed randomly. If a single word
     * string is provided as an argument then it is treated as a seed file and
     * a Minesweeper game is created and started using the information contained
     * in the seed file. If none of the above applies, then a usage statement
     * is displayed and the program exits gracefully.
     *
     * @param args the shell arguments provided to the program
     */
    public static void main(String[] args) 
    {

        /*
          The following switch statement has been designed in such a way that if
          errors occur within the first two cases, the default case still gets
          executed. This was accomplished by special placement of the break
          statements.
        */

		MineSweeper game = new MineSweeper (10,10);

        switch (args.length) 
        {

        // random game
        case 2:

            int rows, cols;

            // try to parse the arguments and create a game
            try {
                rows = Integer.parseInt(args[0]);
                cols = Integer.parseInt(args[1]);
                game = new MineSweeper(rows, cols);
                break;
            } catch (NumberFormatException nfe) {
                // line intentionally left blank
            } // try

        // seed file game
        case 1:

            String filename = args[0];
            File file = new File(filename);

            if (file.isFile())
            {
                game = new MineSweeper(file);
                break;
            } // if

        // display usage statement
        default:

            System.out.println("Usage: java Minesweeper [FILE]");
            System.out.println("Usage: java Minesweeper [ROWS] [COLS]");
           // System.exit(0);

        } // switch

        // if all is good, then run the game
        game.run();

    } // main


} // Minesweeper
