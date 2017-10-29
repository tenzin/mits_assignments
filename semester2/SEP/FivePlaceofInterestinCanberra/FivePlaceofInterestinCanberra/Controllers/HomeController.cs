using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using FivePlaceofInterestinCanberra.Models;

namespace FivePlaceofInterestinCanberra.Controllers
{
	public class HomeController : Controller
	{
		public IActionResult Index()
		{
			return View();
		}
		public IActionResult WarMemorial()
		{
			return View();
		}
		public IActionResult NationalArboretum()
		{
			return View();
		}
		public IActionResult CanberraZoo()
		{
			return View();
		}
		public IActionResult ParliamentHouse()
		{
			return View();
		}
		public IActionResult TelstraTower()
		{
			return View();
		}

		public IActionResult About()
		{
			ViewData["Message"] = "Your application description page.";

			return View();
		}

		public IActionResult Contact()
		{
			ViewData["Message"] = "Your contact page.";

			return View();
		}

		public IActionResult Error()
		{
			return View();
		}
	}
}
