// Owner: Mehwish | Admin Dashboard | Aggregates stats for the admin dashboard view
package com.shopping.system.service;

import com.shopping.system.entity.Product;
import com.shopping.system.repository.OrderRepository;
import com.shopping.system.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {

    private static final int LOW_STOCK_THRESHOLD = 5;

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public DashboardService(ProductRepository productRepository,
                            OrderRepository orderRepository,
                            UserService userService) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public long getTotalProducts() {
        return productRepository.count();
    }

    public long getTotalOrders() {
        return orderRepository.count();
    }

    public long getTotalCustomers() {
        return userService.getTotalCustomers();
    }

    // Today's revenue: SUM of totalAmount for orders placed since midnight today
    public BigDecimal getTodaySales() {
        LocalDateTime startOfDay = LocalDateTime.now()
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = LocalDateTime.now();
        return orderRepository.sumTotalAmountByOrderDateBetween(startOfDay, endOfDay);
    }

    // Products with quantityOnHand < 5 — triggers alert badge on admin dashboard
    public List<Product> getLowStockProducts() {
        return productRepository.findByQuantityOnHandLessThan(LOW_STOCK_THRESHOLD);
    }
}
