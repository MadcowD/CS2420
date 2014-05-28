using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PEXP.Sorting
{
    class SelectionSort : Algorithm
    {
        public override  IList<IComparable> Sort(IList<IComparable> unsorted)
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
            }
            //Return the sorted array
            return unsorted;
        }

        
        public override void Overhead(int n )
        {
            for (int i = 0; i < n; i++)
                for (int j = i; j < n; j++) ;


        }
    }
}
