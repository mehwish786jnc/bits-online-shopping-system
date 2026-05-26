// Owner: HeenuReet | Ordering | JpaRepository for retrieving items belonging to an order
package com.shopping.system.repository;

import com.shopping.system.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long orderId);
}
