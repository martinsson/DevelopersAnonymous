using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace GuildedRose.Tests
{
    public static class TestHelper
    {

        //check if the values are the only values in a list
        //extension method for IEnumerable of string   
        public static bool ContainsOnly(this IEnumerable<string> list, IEnumerable<string> values)
        {
            bool result = true;   
            foreach (var val in values)
            {
                result = list.Contains(val);
            }
            return result &&  values.Count() == list.Count(); ;
        }
        
        //check if the values are the only values in a list
        //extension method for IEnumerable of int   
        public static bool ContainsOnly(this IEnumerable<int> list, IEnumerable<int> values)
        {
            bool result = true;
            foreach (var val in values)
            {
                result = list.Contains(val);
            }
            return result && values.Count() == list.Count(); ;
        }
    }
}
