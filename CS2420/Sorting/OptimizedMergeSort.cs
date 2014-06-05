using PEXP;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace Sorting.Sorting
{
    class OptimizedMergeSort : Algorithm
    {
        
        /// <summary>
        /// Sorts recursively using merge sort
        /// </summary>
        /// <param name="unsorted">The unsorted array</param>
        /// <returns></returns>
        public override IList<IComparable> Sort(IList<IComparable> unsorted)
        {
            if (unsorted.Count <= 1)
                return unsorted;
            //Split the array and then recursivley Sort untill single elements achieved
            var split = unsorted.Split();

            IList<IComparable> left = unsorted, right = unsorted;
            Parallel.Invoke(() => Sort(split[0], 2, out right));
            Sort(split[0], 2, out left);
            //Then return a Merge of the split elements.
            return this.Merge(left, right);
        }

        public void Sort(IList<IComparable> unsorted, int level, out IList<IComparable> returnV)
        {
            if (unsorted.Count <= 1)
            {
                returnV = unsorted;
                return;
            }
            //Split the array and then recursivley Sort untill single elements achieved
            var split = unsorted.Split();
            IList<IComparable> left = unsorted, right = unsorted;
            Sort(split[0], level +1, out left);
            Sort(split[1], level + 1, out right);
                
            //Then return a Merge of the split elements.
            returnV = this.Merge(left , right);
        }


        private IList<IComparable> Merge(IList<IComparable> left, IList<IComparable> right)
        {

            if(right.Count == 0)
                return left;
            else if (left.Count == 0)
                return right;

            IList<IComparable> result = new List<IComparable>();
            //cases

            int r = 0;
            int l = 0;
            while(r + l  < left.Count + right.Count){
                if(l == left.Count)
                {
                    result.Add(right[r++]);
                    
                    continue;
                }
                if(r == right.Count)
                {
                    result.Add(left[l++]);
                    
                    continue;
                }
                //Normal case

                if(left[l].CompareTo(right[r]) > 0)
                {
                    result.Add(right[r++]);
                    
                    continue;
                }
                else
                {
                    result.Add(left[l++]);
                    continue;
                }

            }

            return result;


        }

        public override void Overhead(int n)
        {
           //throw new NotImplementedException();
        }
    }
}
