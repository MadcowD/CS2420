using PEXP;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sorting.Sorting
{
    class ShellSort : Algorithm
    {
        /// <summary>
        /// Start with gap size = N/2
        /// Keep going with smaller gap sizes
        /// Compare items that are N/44 apart
        /// Each step removing up to N/4 inversions
        /// </summary>
        /// <param name="unsorted"></param>
        /// <returns></returns>
        public override IList<IComparable> Sort(IList<IComparable> unsorted)
        {
            for (int i = 0; i < unsorted.Count; i ++)
                //Comparing best cases
            {

            }
            return new List<IComparable>();
        }

        public override void Overhead(int n)
        {
            throw new NotImplementedException();
        }
    }
}
