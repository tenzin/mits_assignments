using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Assignment2.Models
{
    public class Reviews
    {
        public int Id { get; set; }
        public string Date { get; set; }
        public string Name { get; set; }
        public string Heading { get; set; }
        public string Comments { get; set; }
        public int Rating { get; set; }
        public string Restaurant { get; set; }
        public int Agree { get; set; }
        public int Disagree { get; set; }
    }
}
