using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PEXP.Sorting;
using Sorting;

namespace PEXP
{
    class Program
    {
        static void Main(string[] args)
        {
            var list = new IComparable[] { 1, 10, 6, 13, 4, 3, 2, 1, 1, 6, 5, 3, 7, 4, 1 }.ToList();

            InsertSort insAlg = new InsertSort();
            Console.WriteLine(insAlg.Sort(list).Render());
            Console.ReadKey();

            list = new IComparable[] { 1, 15, 56, 0, -2, 4, 2, 7, 4, 7, 34, 1, 23, 2, 6, 4, 1, 12 }.ToList();

            SelectionSort selAlg = new SelectionSort();
            Console.WriteLine(selAlg.Sort(list).Render());;
            Console.ReadKey();

            System.IO.File.WriteAllText
             (@"C:\temp\growth.dat", insAlg.Growth(100, 2000).Render());
            Console.ReadKey();


        }

    }
}
