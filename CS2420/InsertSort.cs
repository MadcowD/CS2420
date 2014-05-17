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
            //Convert unsorted into a non fixed size list

            
            //1. Manipulate original array
            for(int i = 1; i < unsorted.Count; i++)
            {
                IComparable point = unsorted[i];
                unsorted.RemoveAt(i);

                //Check the elements behind the point.
                for (int j = i-1; j >= -1; j--)
                    if (j == -1 || point.CompareTo(unsorted[j]) >= 0)
                    {
               
                        unsorted.Insert(j+1, point);
                        break;
                    }

                Console.WriteLine(unsorted.Render());
            }

            return unsorted;
        }
    }
}
