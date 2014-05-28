using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PEXP
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
            array[index1] = array[index2];
            array[index2] = temp;
        }

        /// <summary>
        /// Splits a list in half
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="array"></param>
        /// <returns></returns>
        public static IList<T>[] Split<T>(this IList<T> array)
        {
            if (array.Count <= 1)
                return new IList<T>[] {array};
            else
            {
                int halfway = (int)((array.Count - 1) / 2);
                IList<T> left = array.Take(halfway).ToList();
                IList<T> right = array.Skip(halfway).ToList();

                return new IList<T>[] { left, right };
            }
        }

        public static string Render<T>(this IList<T> array)
        {
            return string.Join(",", array);
        }

        public static string Render<K,V>(this IDictionary<K,V> dict)
        {
            return string.Join("\n", dict
                .Select(kvp => kvp.Key + " " + kvp.Value));
        }
    }
}
