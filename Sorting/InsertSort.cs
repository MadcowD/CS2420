using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sorting
{
    /// <summary>
    /// The insert sort algorithm
    /// </summary>
    class InsertSort : Algorithm
    {
        /// <summary>
        /// Sorts with the insert algorithm
        /// </summary>
        /// <param name="unsorted">The unsorted comparable list.</param>
        /// <returns>The sorted list.</returns>
        public IList<IComparable> Sort(IList<IComparable> unsorted)
        {
            //1. Manipulate original array
            for(int i = 0; i < unsorted.Count; i++)
            {
                IComparable point = unsorted[i];
                unsorted.RemoveAt(i);

                //Check the elements behind the point.
                for (int j = i < unsorted.Count ? i : i-1; j >= 0; j--)
                    if (unsorted[j].CompareTo(point) == -1 || j == 0) 
                    { 
                        unsorted.Insert(j, point);
                        break;
                    }
            }

            return unsorted;
        }
    }
}
