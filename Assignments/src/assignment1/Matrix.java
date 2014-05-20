package assignment1;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	// Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{
		numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; // d[0] is the first 1D array
		data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		//Check dimensions
		if(this.numRows != m.numRows || this.numColumns != m.numColumns)
			return false;
		
		//Compare data
		for(int r = 0; r < this.numRows; r++)
			for(int c = 0; c < this.numColumns; c++)
				//If at least one of the elements is not equal, the matrices are not equal
				if(this.data[r][c] != m.data[r][c])
					return false;
		
		//Otherwise, no possible differences exist;
		//they are the same.
		return true;
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String rep = "";
		for(int r = 0; r < this.numRows; r++){
			for(int c = 0; c < this.numColumns; c++)
				rep+= data[r][c] + " ";
			rep+= "\n";
		}
				
		return rep;
	}
	
	public Matrix times(Matrix m)
	{
		//Validity test given by c_1 = r_2
		if(this.numColumns != m.numRows){
			System.out.println("Error: matrix arguments are not compatible for multiplication!");
			return null;
		}
		
		//Given that we have assessed correct bounds, we may calculate
		//without issues regarding indices out of bounds.
		int[][] product = new int[this.numRows][m.numColumns];
		
		//Iterate over the product
		for(int r = 0; r < this.numRows; r++)
			for(int c = 0; c < m.numColumns; c++){
				int weightedSum = 0;
				
				//Construct a weighted sum by iterating over
				//the columns of matrix A at row r and
				//the rows of matrix B at column c
				for(int c_A = 0, r_B = 0;
						c_A < this.numColumns && r_B < m.numRows;
						c_A++, r_B++)
						weightedSum += this.data[r][c_A]
								* m.data[r_B][c];
				
				product[r][c] = weightedSum;
			}
		
		//Insert the product into a new matrix and return it
		return new Matrix(product); // placeholder
	}
	
	public Matrix plus(Matrix m)
	{
		//Once again test the boudns of each matrix.
		if(this.numRows != m.numRows || this.numColumns != m.numColumns){
			System.out.println("Error: matrix arguments are not compatible for addition!");
			return null; // placeholder
		}
		
		//Perform normative addition
		int[][] result = new int[this.numRows][this.numColumns];
		for(int r = 0; r < this.numRows; r++)
			for(int c = 0; c < this.numColumns; c++)
			result[r][c] = this.data[r][c] + m.data[r][c];
		
		return new Matrix(result);
	}	
}
