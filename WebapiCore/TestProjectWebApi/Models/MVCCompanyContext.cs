using Microsoft.EntityFrameworkCore;

namespace MvcDemo.Models
{
    public class MvcCompanyContext : DbContext
    {
        public MvcCompanyContext (DbContextOptions<MvcCompanyContext> options)
            : base(options)
        {
        }

public MvcCompanyContext(){}
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite("Data Source=CompanyData.db");
        }

        public virtual DbSet<MvcDemo.Models.Company> Companies { get; set; }
        public virtual DbSet<MvcDemo.Models.CompanyRevenue> CompanyRevenue { get; set; }
    }
}

