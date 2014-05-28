package assignment1;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

import junit.framework.TestCase;

public class Test extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testEqualsObject() {
		String a = "asd";
		Matrix m = new Matrix(new int[][]{{10,10},{5,12}});
		super.assertNotSame(m, a);
		
		//Bounds test
		Matrix o = new Matrix(new int[][]{{10, 10}});
		assertNotSame(m, o);
		
		//Element wise comparison
		o = new Matrix(new int[][]{{10, 10}, {5, 12}});
		assertEquals(m,o);
		
	}



	public void testTimes() {
		//Bounds test
		Matrix a = new Matrix(new int[][]{{10}});
		Matrix b = new Matrix(new int[][]{{10,10},{10,10},{15,15}});
		
		super.assertNull(a.times(b));
		
		//multiplicationn test
		Matrix M1 = new Matrix(new int[][]
                {{1, 2, 3},
				  {2, 5, 6}});

		Matrix M2 = new Matrix(new int[][]
        	{{4, 5},
			  {3, 2},
			  {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		Matrix M1_M2 = new Matrix(new int[][]
			{{13, 12},
			{29, 26}});
		
		assertEquals(M1.times(M2), M1_M2);
	}

	public void testPlus() {
		//Bounds test
				Matrix a = new Matrix(new int[][]{{10}});
				Matrix b = new Matrix(new int[][]{{10,10},{10,10},{15,15}});
				
				assertNull(a.plus(b));
				//Addition test
				Matrix M1 = new Matrix(new int[][]
						{{1,2,3},
						{4,5,6}});
				Matrix M2 = new Matrix(new int[][]
						{{7,8,9},
						{10,11,12}});
				
				Matrix M1_M2 = new Matrix(new int[][]
						{{8,10,12},
						{14,16,18}});
				
				assertEquals(M1.plus(M2), M1_M2);

	}
	
	public void testExhaustive(){
		
		Random r= new Random();
		
		int trials = 1000;
		for(int i = 0; i < trials; i++)
		{
			int c1 = r.nextInt(10), r1= r.nextInt(10);
			int c2 = r.nextInt(10), r2 = r.nextInt(10);
			
			Matrix m1 = new Matrix(r1,c1);
			Matrix m2 = new Matrix(r2,c2);
			
			//Test multiplication bounds
			Object result = m1.times(m2);
			if(m1.numColumns == m2.numRows)
				assertNotNull(result);
			if(m1.numColumns != m2.numRows)
				assertNull(result);
			
			//Test addition
			
			result = m1.plus(m2);
			if( m1.numColumns == m2.numColumns && m1.numRows == m2.numRows) 
				assertNotNull(result);
			if((m1.numColumns != m2.numColumns || m1.numRows != m1.numRows))
				assertNull(result);
		}
	}

}
