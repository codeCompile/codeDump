using System;
using System.ComponentModel.DataAnnotations;

namespace MvcDemo.Models
{
    public class Company
    {
        public int Id { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public string City { get; set; }
        [Required]
        public string Business { get; set; }
        [Required]
        public int TurnOver {get;set;}
    }

     public class CompanyRevenue
    {
        public int Id { get; set; }
        [Required]
        public double Sales { get; set; }
        [Required]
        public double ProfitLoss { get; set; }
        
    }
}