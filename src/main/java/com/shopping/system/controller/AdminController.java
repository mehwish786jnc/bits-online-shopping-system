// Owner: Mehwish | Admin Dashboard | Admin dashboard controller — reads stats via DashboardService
package com.shopping.system.controller;

import com.shopping.system.entity.User;
import com.shopping.system.entity.UserRole;
import com.shopping.system.service.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DashboardService dashboardService;

    public AdminController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.ADMIN) {
            return "redirect:/login";
        }

        // All data fetched via DashboardService — no writes, no @Transactional needed
        model.addAttribute("totalProducts", dashboardService.getTotalProducts());
        model.addAttribute("totalOrders",   dashboardService.getTotalOrders());
        model.addAttribute("totalCustomers", dashboardService.getTotalCustomers());
        model.addAttribute("todaySales",    dashboardService.getTodaySales());
        model.addAttribute("lowStockProducts", dashboardService.getLowStockProducts());
        model.addAttribute("loggedInUser",  user);

        return "admin/dashboard";
    }
}
