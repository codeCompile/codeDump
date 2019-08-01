using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Moq;
using MvcDemo.Controllers;
using MvcDemo.Models;
using Microsoft.EntityFrameworkCore;

using Xunit;

namespace MvcDemo.Tests.UnitTests
{
    public class CompanyControllerTests
    {

        [Fact]
        public void GetAllCompanies_WithCompaniesInRepo_ReturnsOk()
        {
            var contextMock = new Mock<MvcCompanyContext>();
            var comp = new Mock<DbSet<MvcDemo.Models.Company>>();

            contextMock.Setup(x => x.Companies).Returns(comp.Object);

            // arrange
            var controller = new CompanyController(contextMock.Object, null,null);

            // act
            var result = controller.All();
            var okResult = result;

            // assert
            Assert.NotNull(okResult);
        }
    }
}