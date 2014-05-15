using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sorting
{
    public static class Helper
    {
        /// <summary>
        /// Swaps an element of the IList
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="array"></param>
        /// <param name="index1"></param>
        /// <param name="index2"></param>
        public static void Swap<T>(this IList<T> array, int index1, int index2)
        {
            if (index1 > array.Count || index2 > array.Count
                || index1 < 0 || index2 < 0)
                throw new IndexOutOfRangeException();


            //Standard XOR swap
            T temp = array[index1];
            array[index2] = array[index1];
            array[index1] = temp;
        }

        public static string Render<T>(this IList<T> array)
        {
            return string.Join(",", array);
        }
    }
}
