using PEXP;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sorting.Sorting
{
    class MergeSort : IAlgorithm
    {
        /// <summary>
        /// Sorts recursively using merge sort
        /// </summary>
        /// <param name="unsorted">The unsorted array</param>
        /// <returns></returns>
        public IList<IComparable> Sort(IList<IComparable> unsorted)
        {
            //Split the array and then recursivley Sort untill single elements achieved
            var split = unsorted.Split();
            //Then return a Merge of the split elements.
            return this.Merge(Sort(split[0]), Sort(split[1]));
        }


        private IList<IComparable> Merge(IList<IComparable> left, IList<IComparable> right)
        {
            //Check the size bounds on each array
            if (left.Count == 0)
                return right;
            else if (right.Count == 0)
                return left;
            else
            {
                //420 BLAZE List
                IList<IComparable> result = new List<IComparable>();

                //quntfag420 blaze swaq
                for (int l = 0, r = 0; ; )
                {
                    if (l < left.Count && r < right.Count)
                    {
                        if (left[l].CompareTo(right[r]) < 0)
                        {
                            l++;
                            result.Add(left[l]);
                        }
                    }
                    else
                    {
                        if(l < left.Count)
                            
                    }
                }
            }
        }
    }
}
