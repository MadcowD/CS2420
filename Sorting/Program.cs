﻿using System;
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
            Console.WriteLine(insAlg.Sort).Render());
            insAlg.Sort(new int[] { 1, 2 }.ToList());
        }

    }
}
