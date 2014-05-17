using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sorting
{
    class Program
    {
        static void Main(string[] args)
        {
            InsertSort insAlg = new InsertSort();
            var list = new IComparable[] {1,10,6,13,4,3,2,1,1,6,5,3,7,4,1}.ToList();
            Console.WriteLine(insAlg.Sort(list).Render());
            Console.ReadKey();
        }

    }
}
