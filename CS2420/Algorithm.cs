using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using System.Threading.Tasks;
using System.Timers;

namespace PEXP
{
    /// <summary>
    /// The base algoirhtm interface from which all sorting algorithms will be derived.
    /// Algoirthms essentially have a sort function and an initial input
    /// </summary>
    public abstract class Algorithm
    {
        /// <summary>
        /// Sort essentially employing the algorithm.
        /// </summary>
        /// <param name="unsorted"></param>
        /// <returns></returns>]
        public abstract IList<IComparable> Sort(IList<IComparable> unsorted);


        /// <summary>
        /// Represent the language overhead of the algorithm
        /// </summary>
        public abstract void Overhead(int n);

        /// <summary>
        /// Creates a dictionary of points representing growth rates of the algorithm
        /// </summary>
        /// <param name="range">The range ( n to dispaly the growth rate)</param>
        /// <returns></returns>
        public Dictionary<int, double> Growth(int range,  Func<int,IList<int>> listGenerator, int averageCount = 10)
        {
            Random rnd = new Random();
            Dictionary<int, double> NTable = new Dictionary<int,double>();
            Stopwatch sw = new Stopwatch();

            sw.Start();
            //Go through the range
            for (int n = 1000; n < range; n+= 500)
            {
                Console.WriteLine("N: [{0}]", n);
                //Take the average algorithm time at range r
                long algorithmTime = 0;
                for (int a = 0; a < averageCount; a++)
                {
                    //Timing
                    List<IComparable> unsorted = new List<IComparable>();
                    for (int i = 0; i < n; i++)
                        unsorted.Add(rnd.Next());

                    int start = 0;
                    while (start < 10000)
                    { start++; /*Let the thread warm up.*/ }

                    long warmUptime = sw.ElapsedTicks;

                    //Run the algorithm
                    this.Sort(unsorted);
                    long sortTime = sw.ElapsedTicks;
                    this.Overhead(n);
                    long overHead = sw.ElapsedTicks;

                    //Calculate time
                    algorithmTime += (sortTime - warmUptime) - (overHead - sortTime);
                    
                }

                NTable.Add(n ,algorithmTime/averageCount);
            }

            sw.Stop();
            return NTable;
        }

        public Dictionary<int, double> Growth(int range, int averageCount = 10)
        {
            return this.Growth(range, Helper.PermutatedList, averageCount);
        }
    }
}
