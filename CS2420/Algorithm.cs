using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Sorting
{
    /// <summary>
    /// The base algoirhtm interface from which all sorting algorithms will be derived.
    /// Algoirthms essentially have a sort function and an initial input
    /// </summary>
    public interface Algorithm
    {
        /// <summary>
        /// Sort essentially employing the algorithm.
        /// </summary>
        /// <param name="unsorted"></param>
        /// <returns></returns>
        IList<IComparable> Sort(IList<IComparable> unsorted);
        
    }
}
