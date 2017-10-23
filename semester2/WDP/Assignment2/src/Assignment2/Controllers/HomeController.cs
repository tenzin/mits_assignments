using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Assignment2.Data;
using Assignment2.Models;
using Microsoft.EntityFrameworkCore;

namespace Assignment2.Controllers
{
    public class HomeController : Controller
    {

        private readonly Data.ApplicationDbContext _context;
        public HomeController(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<IActionResult> Agree(int? id)
        {

            var review1 =
            await _context.Reviews.FirstOrDefaultAsync(m => m.Agree == id);

            if (ModelState.IsValid)
            {
                review1.Agree++;
                _context.Update(review1);
                await _context.SaveChangesAsync();
            }
            return RedirectToAction("Reviews");
        }

        public async Task<IActionResult> Disagree(int? id)
        {

            var review2 =
            await _context.Reviews.FirstOrDefaultAsync(m => m.Disagree == id);

            if (ModelState.IsValid)
            {
                review2.Disagree++;
                _context.Update(review2);
                await _context.SaveChangesAsync();
            }
            return RedirectToAction("Reviews");
        }

        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var review = await _context.Reviews.SingleOrDefaultAsync(m => m.Id == id);
            if (review == null)
            {
                return NotFound();
            }

            return View(review);
        }

        public async Task<IActionResult> Reviews()
        {
            return View(await _context.Reviews.ToListAsync());
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Restaurants()
        {

            return View();
        }

        public async Task<IActionResult> Cuisine()
        {
            return View(await _context.Reviews.ToListAsync());
        }


        public IActionResult Dishes()
        {

            return View();
        }

        public IActionResult Prices()
        {

            return View();
        }

        public IActionResult Location()
        {

            return View();
        }

        public IActionResult Error()
        {
            return View();
        }
    }
}
