# 5-Day Development Plan for Online Shopping System

## Team Members:
- **HeenuReet** (21BCS1055)
- **Aliya** (93072)  
- **Mehwish Sultana** (93065)

## Technology Stack:
- **Backend**: Spring Boot, Spring MVC, Spring Data JPA, Hibernate
- **Frontend**: Thymeleaf (simpler than React for 5 days)
- **Database**: MySQL

---

## **DAY 1 - Project Setup & Core Foundation**

### HeenuReet - Login & Registration Module:
- Set up Spring Boot project structure
- Create User entity (Customer/Admin roles)
- Build UserRepository and UserService
- Create login/registration controllers
- Design login/registration HTML pages with Thymeleaf

### Aliya - Product Management Module:
- Create Product entity with all fields (ID, name, description, price, QOH)
- Create Category enum (Electronics, Electrical, Furniture, Cosmetics, Toys, Books)
- Build ProductRepository and ProductService
- Create admin product CRUD controllers
- Design product management HTML pages

### Mehwish Sultana - Database & Initial Setup:
- Install and configure MySQL database
- Create database schema and tables
- Configure application.properties
- Add sample data for testing
- Set up basic CSS styling for all pages

---

## **DAY 2 - Customer Features**

### HeenuReet - Product Search & Browse Module:
- Build product search functionality (by name, category)
- Create product listing controllers
- Design product browse/search pages
- Implement category-wise filtering
- Add product detail view page

### Aliya - Shopping Cart Module:
- Create Cart entity and CartItem entity
- Build CartRepository and CartService
- Create add-to-cart functionality
- Design shopping cart view page
- Implement remove-from-cart functionality

### Mehwish Sultana - Customer Dashboard:
- Create customer dashboard after login
- Design customer navigation menu
- Integrate HeenuReet's search with cart functionality
- Test and fix any database connectivity issues
- Add more sample products to database

---

## **DAY 3 - Order Management**

### HeenuReet - Ordering Module:
- Create Order and OrderItem entities
- Build OrderRepository and OrderService
- Create place-order functionality from cart
- Design order confirmation page
- Implement order history for customers

### Aliya - Order Cancellation:
- Add order status field to Order entity
- Create cancel/delete order functionality
- Design order management page for customers
- Add order status updates
- Create order details view

### Mehwish Sultana - Admin Dashboard:
- Create admin dashboard after login
- Design admin navigation menu
- Connect product management from Day 1
- Test order flow end-to-end
- Add validation and error handling

---

## **DAY 4 - Feedback & Sales Analysis**

### HeenuReet - Feedback Module:
- Create Feedback entity
- Build FeedbackRepository and FeedbackService
- Create customer feedback submission
- Design feedback form page
- Create admin feedback viewing functionality

### Aliya - Sales Analysis Module:
- Create sales analysis methods in OrderService
- Build controllers for sales reports (weekly/monthly/quarterly/yearly)
- Design sales dashboard for admin
- Implement fast/slow moving product analysis
- Create charts or simple tables for data visualization

### Mehwish Sultana - Reports Module:
- Create report generation functionality
- Design report pages for admin
- Add product sales reports
- Add customer feedback reports
- Test all admin functionalities

---

## **DAY 5 - Integration & Testing**

### HeenuReet - Final Integration:
- Connect all modules together
- Fix navigation between pages
- Add proper session management
- Test customer workflow end-to-end
- Fix any remaining bugs

### Aliya - UI/UX & Validation:
- Polish all HTML pages styling
- Add form validations (client & server side)
- Improve user experience
- Test admin workflow end-to-end
- Add success/error messages

### Mehwish Sultana - Testing & Deployment:
- Test entire application thoroughly
- Add more test data
- Create application documentation
- Package the application
- Prepare demo data and presentation

---

## **Daily Schedule:**
- **Morning (2 hours)**: Individual module work
- **Afternoon (2 hours)**: Integration and testing together
- **Evening (1 hour)**: Code review and planning next day

## **Key Deliverables by End of Day 5:**
1. Working customer registration/login
2. Product browse and search by category
3. Shopping cart with add/remove functionality
4. Order placement and cancellation
5. Customer feedback system
6. Admin product management
7. Admin sales analysis and reports
8. Complete database with sample data

## **Tips for Success:**
- Keep code simple and functional
- Focus on core requirements only
- Test frequently during development
- Help each other when stuck
- Document any setup instructions

This plan ensures everyone gets full-stack experience while staying focused on the essential requirements!