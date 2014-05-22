using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PEXP.Sorting
{
    class SelectionSort : IAlgorithm
    {
        public IList<IComparable> Sort(IList<IComparable> unsorted)
        {
            //Iterate over the collection
            for(int i = 0; i < unsorted.Count; i++)
            {
                KeyValuePair<int, IComparable> lowestLessThan
                    = new KeyValuePair<int,IComparable>(i, unsorted[i]);

                //Find the lowest value ahead of unsorted[i]
                for (int j = i; j < unsorted.Count; j++)
                    //Perform comparation
                    if (unsorted[j].CompareTo(lowestLessThan.Value) < 0)
                        lowestLessThan
                            = new KeyValuePair<int, IComparable>(j, unsorted[j]);
                
                //Swap unsorted[i] with lowestLessThan
                unsorted.Swap(i, lowestLessThan.Key);

#if DEBUG
                Console.WriteLine(unsorted.Render());
#endif
            }
            //Return the sorted array
            return unsorted;
        }
    }
}
