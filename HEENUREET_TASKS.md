# HeenuReet's Tasks - Online Shopping System

## Overview
You are responsible for: Login & Registration Module, Product Search & Browse Module, Ordering Module, Feedback Module, and Final Integration.

---

## **DAY 1 - Login & Registration Module**

### Tasks to Complete:
1. Set up Spring Boot project structure
2. Create User entity with Customer/Admin roles
3. Build UserRepository and UserService
4. Create login/registration controllers
5. Design login/registration HTML pages with Thymeleaf

### Detailed Implementation:

#### **Task 1: Project Setup**
**Prompt to give Claude:**
```
Create a new Spring Boot project for an online shopping system with the following specifications:
- Project name: online-shopping-system
- Dependencies needed: Spring Web, Spring Data JPA, Thymeleaf, MySQL Driver, Spring Boot DevTools
- Create proper package structure: com.shopping.system with subpackages: entity, repository, service, controller
- Set up basic application.properties with MySQL configuration (database name: shopping_system)
```

#### **Task 2: User Entity**
**Prompt to give Claude:**
```
Create a User entity class with the following requirements:
- Fields: id (Long, auto-generated), username (String, unique), email (String, unique), password (String), role (enum: CUSTOMER, ADMIN), createdDate (LocalDateTime)
- Use JPA annotations (@Entity, @Table, @Id, @GeneratedValue, etc.)
- Add constructors, getters, setters, and toString method
- Create UserRole enum with CUSTOMER and ADMIN values
```

#### **Task 3: UserRepository and UserService**
**Prompt to give Claude:**
```
Create UserRepository interface extending JpaRepository and UserService class with the following methods:
- UserRepository: findByUsername, findByEmail, existsByUsername, existsByEmail
- UserService: registerUser, loginUser, findUserByUsername, validateUserCredentials
- Include proper exception handling for duplicate users and invalid login
```

#### **Task 4: Controllers**
**Prompt to give Claude:**
```
Create AuthController with the following endpoints:
- GET /login - show login form
- POST /login - process login
- GET /register - show registration form  
- POST /register - process registration
- GET /logout - logout user
- Use @Controller annotation and return appropriate view names
- Handle form validation and error messages
- Use session to store logged-in user information
```

#### **Task 5: Thymeleaf Templates**
**Prompt to give Claude:**
```
Create Thymeleaf HTML templates in src/main/resources/templates/:
1. login.html - login form with username/password fields
2. register.html - registration form with username, email, password, role selection
3. Include basic CSS styling and form validation
4. Add error message display using Thymeleaf
5. Include navigation links between login and register pages
```

### **Testing Instructions:**
**Where to test:** Use browser at `http://localhost:8080`
**What to test:**
1. Start the application and navigate to `/register`
2. Register a new customer account
3. Register an admin account
4. Try to register with duplicate username/email (should show error)
5. Navigate to `/login` and login with created accounts
6. Verify session is created and user stays logged in
7. Test logout functionality

**How to verify:**
- Check database tables are created
- Verify user data is saved in database
- Check session attributes in browser developer tools
- Ensure proper redirection after login/logout

---

## **DAY 2 - Product Search & Browse Module**

### Tasks to Complete:
1. Build product search functionality (by name, category)
2. Create product listing controllers
3. Design product browse/search pages
4. Implement category-wise filtering
5. Add product detail view page

### Detailed Implementation:

#### **Task 1: Search Functionality**
**Prompt to give Claude:**
```
Create product search functionality with the following requirements:
- Add search methods to ProductRepository: findByNameContainingIgnoreCase, findByCategory, findByNameContainingIgnoreCaseAndCategory
- Update ProductService to include: searchProducts, getProductsByCategory, getAllProducts
- Handle empty search results gracefully
- Implement case-insensitive search
```

#### **Task 2: Product Controllers**
**Prompt to give Claude:**
```
Create ProductController for customer-facing product operations:
- GET /products - show all products with search form
- GET /products/search - handle search requests with query parameters
- GET /products/category/{category} - show products by category
- GET /products/{id} - show product details
- Use @RequestParam for search queries
- Pass search results to Thymeleaf templates
```

#### **Task 3: Product Browse Pages**
**Prompt to give Claude:**
```
Create Thymeleaf templates for product browsing:
1. products.html - main product listing page with search form and category filters
2. product-detail.html - detailed product view with all product information
3. Include search bar, category dropdown, and product cards layout
4. Add "Add to Cart" button on each product (link to cart functionality)
5. Include pagination if more than 20 products
```

### **Testing Instructions:**
**Where to test:** Browser at `http://localhost:8080/products`
**What to test:**
1. Navigate to products page and verify all products display
2. Test search by product name (partial matches)
3. Test category filtering for each category
4. Test combined search (name + category)
5. Click on product to view details
6. Test with empty search results
7. Verify case-insensitive search works

**How to verify:**
- Products display correctly with images and details
- Search returns accurate results
- Category filters work properly
- Product detail page shows complete information
- Navigation works smoothly

---

## **DAY 3 - Ordering Module**

### Tasks to Complete:
1. Create Order and OrderItem entities
2. Build OrderRepository and OrderService
3. Create place-order functionality from cart
4. Design order confirmation page
5. Implement order history for customers

### Detailed Implementation:

#### **Task 1: Order Entities**
**Prompt to give Claude:**
```
Create Order and OrderItem entities with the following specifications:
Order entity: id, userId, orderDate, totalAmount, status (enum: PENDING, CONFIRMED, CANCELLED), shippingAddress
OrderItem entity: id, orderId, productId, quantity, price, subtotal
Include proper JPA relationships (@OneToMany, @ManyToOne)
Create OrderStatus enum with appropriate values
```

#### **Task 2: Order Repository and Service**
**Prompt to give Claude:**
```
Create OrderRepository and OrderService with methods:
- OrderRepository: findByUserId, findByUserIdOrderByOrderDateDesc
- OrderService: createOrderFromCart, cancelOrder, getUserOrders, getOrderDetails
- Include business logic for order processing and total calculation
- Handle inventory updates when order is placed
```

#### **Task 3: Order Controllers**
**Prompt to give Claude:**
```
Create OrderController with endpoints:
- POST /orders/place - place order from current cart
- GET /orders - show user's order history
- GET /orders/{id} - show order details
- POST /orders/{id}/cancel - cancel an order
- Include proper session validation to ensure user can only see their orders
```

#### **Task 4: Order Templates**
**Prompt to give Claude:**
```
Create Thymeleaf templates:
1. order-confirmation.html - show order details after successful placement
2. order-history.html - list all user orders with basic details
3. order-details.html - detailed view of specific order
4. Include order status, items, quantities, and total amounts
5. Add cancel order functionality where applicable
```

### **Testing Instructions:**
**Where to test:** Complete customer flow from login to order
**What to test:**
1. Login as customer and add products to cart (from Aliya's module)
2. Place order from cart
3. Verify order confirmation page displays correctly
4. Check order appears in order history
5. View order details
6. Test order cancellation
7. Verify inventory is updated after order placement

**How to verify:**
- Orders are saved correctly in database
- Order totals calculate accurately
- Order status updates properly
- User can only access their own orders
- Product quantities decrease after order

---

## **DAY 4 - Feedback Module**

### Tasks to Complete:
1. Create Feedback entity
2. Build FeedbackRepository and FeedbackService
3. Create customer feedback submission
4. Design feedback form page
5. Create admin feedback viewing functionality

### Detailed Implementation:

#### **Task 1: Feedback Entity**
**Prompt to give Claude:**
```
Create Feedback entity with fields:
- id, userId, productId (optional), rating (1-5), comment, feedbackDate
- Include JPA annotations and relationships
- Add validation annotations for rating range
- Include getters, setters, constructors
```

#### **Task 2: Feedback Repository and Service**
**Prompt to give Claude:**
```
Create FeedbackRepository and FeedbackService:
- Repository methods: findByUserId, findByProductId, findAll
- Service methods: submitFeedback, getFeedbackByUser, getAllFeedback, getFeedbackByProduct
- Include validation for feedback submission
```

#### **Task 3: Feedback Controllers**
**Prompt to give Claude:**
```
Create FeedbackController:
- GET /feedback - show feedback form for customers
- POST /feedback - process feedback submission
- GET /admin/feedback - show all feedback for admin
- Include proper role-based access control
```

#### **Task 4: Feedback Templates**
**Prompt to give Claude:**
```
Create templates:
1. feedback-form.html - customer feedback submission form
2. admin-feedback.html - admin view of all feedback
3. Include rating system (1-5 stars) and comment section
4. Display product information if feedback is product-specific
```

### **Testing Instructions:**
**Where to test:** Customer and admin sections
**What to test:**
1. Login as customer and submit feedback
2. Submit product-specific feedback
3. Login as admin and view all feedback
4. Test rating validation (1-5 range)
5. Verify feedback displays correctly with user and product info

---

## **DAY 5 - Final Integration**

### Tasks to Complete:
1. Connect all modules together
2. Fix navigation between pages
3. Add proper session management
4. Test customer workflow end-to-end
5. Fix any remaining bugs

### Detailed Implementation:

#### **Task 1: Navigation Integration**
**Prompt to give Claude:**
```
Create a common navigation template (navbar.html) and include it in all pages:
- Customer menu: Home, Products, Cart, Orders, Feedback, Logout
- Admin menu: Dashboard, Products, Orders, Feedback, Reports, Logout
- Use Thymeleaf fragments for reusable navigation
- Add role-based menu visibility
```

#### **Task 2: Session Management**
**Prompt to give Claude:**
```
Implement proper session management:
- Create SessionUtil class for session handling
- Add login required interceptor
- Implement role-based access control
- Add session timeout handling
- Ensure user stays logged in across pages
```

#### **Task 3: Error Handling**
**Prompt to give Claude:**
```
Add global error handling:
- Create GlobalExceptionHandler with @ControllerAdvice
- Add custom error pages (404.html, 500.html)
- Include proper error messages for validation failures
- Add try-catch blocks in critical service methods
```

### **Testing Instructions:**
**Complete End-to-End Testing:**
1. **Customer Flow:**
   - Register → Login → Browse Products → Search → Add to Cart → Place Order → View Order History → Submit Feedback → Logout

2. **Admin Flow:**
   - Login → Manage Products → View Orders → View Feedback → Generate Reports → Logout

3. **Edge Cases:**
   - Invalid login attempts
   - Duplicate registrations
   - Empty cart checkout
   - Order cancellation
   - Out of stock products

**Final Verification:**
- All pages load without errors
- Navigation works smoothly
- Session persists correctly
- Database operations work
- Role-based access functions properly

---

## **Common Debugging Tips:**
1. Check application logs in console
2. Verify database connections
3. Test API endpoints with browser developer tools
4. Use `System.out.println()` for debugging
5. Check Thymeleaf template syntax errors
6. Verify session attributes in browser

## **Handoff Points:**
- **Day 1 End:** Share User entity and auth controllers with team
- **Day 2 End:** Coordinate with Aliya on cart integration
- **Day 3 End:** Test order flow with Aliya's cart module
- **Day 4 End:** Coordinate with Mehwish for admin feedback access
- **Day 5:** Final integration with both team members