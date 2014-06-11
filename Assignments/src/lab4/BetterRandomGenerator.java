package lab4;

public class BetterRandomGenerator implements RandomNumberGenerator  {
	long seed;
	long x_n;
	long n = (long) Math.pow(2, 31)-1;
	long const1 = 16807;
	long const2 = 3;

	public int next_int(int max) 
	{
		long time = System.nanoTime();
		x_n = const1* x_n + const2 %System.nanoTime(); 
		const2+= System.nanoTime()*2 - time;
		return (int) Math.abs((x_n) % max) ;
	}

	public void set_seed(long _seed) 
	{
		this.seed = _seed;
		this.x_n = seed;
	}

	public void set_constants(long _const1, long _const2) 
	{
		this.const1 = _const1;
		this.const2 = _const2;
	}
}
