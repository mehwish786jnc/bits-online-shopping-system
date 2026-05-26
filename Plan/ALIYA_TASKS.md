# Aliya's Tasks - Online Shopping System

## Overview
You are responsible for: Product Management Module, Shopping Cart Module, Order Cancellation, Sales Analysis Module, and UI/UX & Validation.

---

## **DAY 1 - Product Management Module**

### Tasks to Complete:
1. Create Product entity with all fields
2. Create Category enum
3. Build ProductRepository and ProductService
4. Create admin product CRUD controllers
5. Design product management HTML pages

### Detailed Implementation:

#### **Task 1: Product Entity**
**Prompt to give Claude:**
```
Create a Product entity class with the following specifications:
- Fields: id (Long, auto-generated), name (String), description (Text), price (BigDecimal), quantityOnHand (Integer), category (enum), createdDate (LocalDateTime), updatedDate (LocalDateTime)
- Use JPA annotations (@Entity, @Table, @Id, @GeneratedValue, @Column)
- Add validation annotations (@NotNull, @NotBlank, @Min for price and quantity)
- Include constructors, getters, setters, and toString method
- Add @PreUpdate annotation method to update modifiedDate
```

#### **Task 2: Category Enum**
**Prompt to give Claude:**
```
Create Category enum with the following values:
- ELECTRONICS, ELECTRICAL, FURNITURE, COSMETICS, TOYS, BOOKS
- Include a displayName field for user-friendly names
- Add constructor and getter method for displayName
- Add static method to get all categories for dropdown
```

#### **Task 3: Product Repository and Service**
**Prompt to give Claude:**
```
Create ProductRepository interface and ProductService class:
- Repository methods: findByCategory, findByNameContainingIgnoreCase, findByNameContainingIgnoreCaseAndCategory
- Service methods: getAllProducts, getProductById, saveProduct, updateProduct, deleteProduct, getProductsByCategory, searchProducts
- Include validation in service methods
- Add method to check if product exists before operations
- Handle business logic for stock management
```

#### **Task 4: Admin Product Controller**
**Prompt to give Claude:**
```
Create AdminProductController with the following endpoints:
- GET /admin/products - list all products for admin
- GET /admin/products/add - show add product form
- POST /admin/products/add - process add product
- GET /admin/products/edit/{id} - show edit product form
- POST /admin/products/edit/{id} - process product update
- POST /admin/products/delete/{id} - delete product
- Use @PreAuthorize or session validation to ensure only admin access
- Include proper error handling and success messages
```

#### **Task 5: Admin Product Templates**
**Prompt to give Claude:**
```
Create Thymeleaf templates in src/main/resources/templates/admin/:
1. products.html - list all products with edit/delete buttons
2. add-product.html - form to add new product
3. edit-product.html - form to edit existing product
4. Include form validation, error messages, and success notifications
5. Add search and filter functionality for admin product list
6. Use proper form binding with th:object and th:field
```

### **Testing Instructions:**
**Where to test:** Browser at `http://localhost:8080/admin/products` (login as admin first)
**What to test:**
1. Login as admin (use HeenuReet's login module)
2. Navigate to admin products page
3. Add new products for each category
4. Edit existing product details
5. Delete a product
6. Test form validations (empty fields, negative prices)
7. Search and filter products in admin panel

**How to verify:**
- Products are saved correctly in database
- Form validations work properly
- Edit functionality updates correctly
- Delete removes product from database
- Admin-only access is enforced
- Success/error messages display properly

---

## **DAY 2 - Shopping Cart Module**

### Tasks to Complete:
1. Create Cart entity and CartItem entity
2. Build CartRepository and CartService
3. Create add-to-cart functionality
4. Design shopping cart view page
5. Implement remove-from-cart functionality

### Detailed Implementation:

#### **Task 1: Cart Entities**
**Prompt to give Claude:**
```
Create Cart and CartItem entities with the following specifications:
Cart entity: id, userId, createdDate, updatedDate
CartItem entity: id, cartId, productId, quantity, price, subtotal
Include proper JPA relationships (@OneToMany for Cart-CartItem, @ManyToOne for CartItem-Product)
Add methods to calculate cart total in Cart entity
Include cascade operations for cart-cartitem relationship
```

#### **Task 2: Cart Repository and Service**
**Prompt to give Claude:**
```
Create CartRepository, CartItemRepository, and CartService:
- CartRepository: findByUserId, findByUserIdAndActiveTrue
- CartItemRepository: findByCartId, findByCartIdAndProductId
- CartService methods: getCartByUserId, addToCart, removeFromCart, updateQuantity, clearCart, getCartTotal
- Include logic to create new cart if user doesn't have one
- Handle quantity updates and stock validation
```

#### **Task 3: Cart Controller**
**Prompt to give Claude:**
```
Create CartController with endpoints:
- GET /cart - show user's shopping cart
- POST /cart/add - add product to cart (AJAX or form post)
- POST /cart/remove/{itemId} - remove item from cart
- POST /cart/update - update item quantities
- POST /cart/clear - clear entire cart
- Include session validation to ensure user can only access their cart
- Return appropriate responses for AJAX calls
```

#### **Task 4: Cart Templates**
**Prompt to give Claude:**
```
Create Thymeleaf templates:
1. cart.html - display cart items with quantities, prices, and totals
2. Include quantity update controls (+/- buttons or input fields)
3. Add remove item buttons for each cart item
4. Show cart total and item count
5. Add "Proceed to Checkout" button (links to order placement)
6. Handle empty cart scenario with appropriate message
```

#### **Task 5: Add to Cart Integration**
**Prompt to give Claude:**
```
Update product listing and detail pages (from HeenuReet's module):
- Add "Add to Cart" buttons on product cards and detail page
- Include product ID and quantity in form data
- Add JavaScript for AJAX add-to-cart functionality (optional)
- Show success messages when items are added
- Update cart icon/counter in navigation
```

### **Testing Instructions:**
**Where to test:** Complete cart flow from product pages to cart view
**What to test:**
1. Login as customer and browse products
2. Add various products to cart from listing and detail pages
3. Navigate to cart page and verify items appear correctly
4. Update quantities using +/- buttons or input fields
5. Remove individual items from cart
6. Clear entire cart
7. Test with out-of-stock products
8. Verify cart totals calculate correctly

**How to verify:**
- Cart items persist in database
- Quantities update correctly
- Totals calculate accurately
- Stock validation works
- User can only access their own cart
- Empty cart displays appropriate message

---

## **DAY 3 - Order Cancellation**

### Tasks to Complete:
1. Add order status field to Order entity (coordinate with HeenuReet)
2. Create cancel/delete order functionality
3. Design order management page for customers
4. Add order status updates
5. Create order details view

### Detailed Implementation:

#### **Task 1: Order Status Management**
**Prompt to give Claude:**
```
Work with HeenuReet to enhance Order entity:
- Ensure OrderStatus enum has values: PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
- Add business rules for which statuses allow cancellation
- Create method in OrderService: canCancelOrder(Order order)
- Only PENDING and CONFIRMED orders should be cancellable
```

#### **Task 2: Order Cancellation Service**
**Prompt to give Claude:**
```
Add methods to OrderService:
- cancelOrder(Long orderId, Long userId) - cancel user's order
- restoreInventory(Order order) - restore product quantities when order cancelled
- validateOrderOwnership(Long orderId, Long userId) - security check
- Include proper validation and business logic
- Update order status and restore product stock
```

#### **Task 3: Order Management Controller**
**Prompt to give Claude:**
```
Create or enhance OrderController (coordinate with HeenuReet):
- GET /orders/{id}/cancel - show order cancellation confirmation
- POST /orders/{id}/cancel - process order cancellation
- GET /orders/manage - customer order management page
- Include proper error handling for invalid cancellation attempts
- Add success/error messages for cancellation actions
```

#### **Task 4: Order Management Templates**
**Prompt to give Claude:**
```
Create enhanced order templates:
1. order-management.html - customer's order dashboard with action buttons
2. order-cancel-confirm.html - cancellation confirmation page
3. Add status badges for different order states
4. Include conditional action buttons based on order status
5. Show cancellation history and reasons
```

### **Testing Instructions:**
**Where to test:** Customer order management section
**What to test:**
1. Place several orders with different products
2. Navigate to order management page
3. Cancel orders in PENDING status
4. Try to cancel orders in different statuses
5. Verify inventory is restored after cancellation
6. Check order history shows cancelled orders correctly
7. Test error handling for invalid cancellation attempts

**How to verify:**
- Order status updates correctly in database
- Product quantities increase after cancellation
- Users can only cancel their own orders
- Cancelled orders display with correct status
- Business rules prevent invalid cancellations

---

## **DAY 4 - Sales Analysis Module**

### Tasks to Complete:
1. Create sales analysis methods in OrderService
2. Build controllers for sales reports
3. Design sales dashboard for admin
4. Implement fast/slow moving product analysis
5. Create charts or simple tables for data visualization

### Detailed Implementation:

#### **Task 1: Sales Analysis Service Methods**
**Prompt to give Claude:**
```
Add sales analysis methods to OrderService or create SalesAnalysisService:
- getWeeklySales() - sales data for current week
- getMonthlySales() - sales data for current month  
- getQuarterlySales() - sales data for current quarter
- getYearlySales() - sales data for current year
- getFastMovingProducts() - products with high sales volume
- getSlowMovingProducts() - products with low sales volume
- getSalesByCategory() - category-wise sales breakdown
- Return data in format suitable for charts/tables
```

#### **Task 2: Sales Analysis Repository Methods**
**Prompt to give Claude:**
```
Add custom query methods to OrderRepository:
- @Query to get sales between date ranges
- @Query to get total sales by product
- @Query to get sales by category
- @Query to get order counts by period
- Use native SQL or JPQL as appropriate
- Include GROUP BY and aggregate functions (SUM, COUNT)
```

#### **Task 3: Sales Dashboard Controller**
**Prompt to give Claude:**
```
Create SalesController for admin:
- GET /admin/sales - main sales dashboard
- GET /admin/sales/weekly - weekly sales data
- GET /admin/sales/monthly - monthly sales data
- GET /admin/sales/quarterly - quarterly sales data
- GET /admin/sales/yearly - yearly sales data
- GET /admin/sales/products - fast/slow moving products analysis
- Return JSON data for AJAX requests or model data for templates
```

#### **Task 4: Sales Dashboard Templates**
**Prompt to give Claude:**
```
Create sales dashboard templates:
1. sales-dashboard.html - main dashboard with summary cards and navigation
2. sales-reports.html - detailed sales reports with tables
3. product-analysis.html - fast/slow moving products analysis
4. Include simple charts using Chart.js or just HTML tables
5. Add date range selectors for custom periods
6. Show key metrics: total sales, order count, average order value
```

#### **Task 5: Simple Data Visualization**
**Prompt to give Claude:**
```
Add basic data visualization:
- Include Chart.js CDN in templates
- Create simple bar charts for sales by period
- Create pie charts for category-wise sales
- Use HTML tables as fallback for complex data
- Add export to CSV functionality (optional)
- Keep visualizations simple and functional
```

### **Testing Instructions:**
**Where to test:** Admin sales dashboard
**What to test:**
1. Login as admin and navigate to sales dashboard
2. View weekly, monthly, quarterly, and yearly sales
3. Check fast-moving and slow-moving products lists
4. Test with different date ranges
5. Verify sales calculations are accurate
6. Test category-wise sales breakdown
7. Ensure charts display correctly (if implemented)

**How to verify:**
- Sales calculations match order data in database
- Charts display correct data
- Fast/slow moving product analysis is logical
- Date range filters work properly
- Admin-only access is enforced

---

## **DAY 5 - UI/UX & Validation**

### Tasks to Complete:
1. Polish all HTML pages styling
2. Add form validations (client & server side)
3. Improve user experience
4. Test admin workflow end-to-end
5. Add success/error messages

### Detailed Implementation:

#### **Task 1: CSS Styling and Layout**
**Prompt to give Claude:**
```
Create comprehensive CSS styling:
- Create main.css file in src/main/resources/static/css/
- Design responsive layout using CSS Grid or Flexbox
- Style navigation menus, forms, buttons, and tables
- Add hover effects and smooth transitions
- Create consistent color scheme and typography
- Use CSS classes for reusable components
- Ensure mobile-friendly responsive design
```

#### **Task 2: Client-Side Form Validation**
**Prompt to give Claude:**
```
Add JavaScript form validation:
- Create validation.js in src/main/resources/static/js/
- Validate email formats, password strength, required fields
- Add real-time validation feedback (show errors as user types)
- Validate numeric inputs (prices, quantities)
- Add confirmation dialogs for delete operations
- Prevent double-click form submissions
- Show loading indicators for AJAX operations
```

#### **Task 3: Server-Side Validation Enhancement**
**Prompt to give Claude:**
```
Enhance server-side validation:
- Add @Valid annotations to controller methods
- Create custom validation annotations if needed
- Enhance error message handling in controllers
- Add business rule validations (stock availability, price limits)
- Create ValidationUtils class for common validations
- Ensure all form inputs are properly validated
```

#### **Task 4: User Experience Improvements**
**Prompt to give Claude:**
```
Improve overall user experience:
- Add breadcrumb navigation
- Include search autocomplete/suggestions
- Add product image placeholders
- Create loading states for slow operations
- Add pagination for long product lists
- Include "Back to Top" buttons on long pages
- Add keyboard shortcuts for common actions
```

#### **Task 5: Message System**
**Prompt to give Claude:**
```
Implement comprehensive messaging:
- Create MessageUtil class for flash messages
- Add success messages for all successful operations
- Include informative error messages for failures
- Add confirmation messages before destructive actions
- Create message display templates (alerts, toasts)
- Ensure messages are user-friendly and actionable
```

#### **Task 6: Comprehensive Testing**
**Prompt to give Claude:**
```
Create testing checklist and perform thorough testing:
- Test all forms with valid and invalid data
- Verify all navigation links work correctly
- Test responsive design on different screen sizes
- Check browser compatibility (Chrome, Firefox, Safari)
- Test performance with large datasets
- Verify security (SQL injection, XSS protection)
```

### **Testing Instructions:**
**Complete Application Testing:**

#### **Customer Workflow Testing:**
1. **Registration & Login:**
   - Register with valid/invalid data
   - Login with correct/incorrect credentials
   - Test password requirements

2. **Product Browsing:**
   - Browse all product categories
   - Search with various keywords
   - View product details

3. **Shopping Cart:**
   - Add multiple products to cart
   - Update quantities
   - Remove items
   - Proceed to checkout

4. **Order Management:**
   - Place orders
   - View order history
   - Cancel orders where allowed
   - Check order details

5. **Feedback System:**
   - Submit product feedback
   - Submit general feedback
   - View submitted feedback

#### **Admin Workflow Testing:**
1. **Product Management:**
   - Add new products
   - Edit existing products
   - Delete products
   - Search admin product list

2. **Sales Analysis:**
   - View sales reports
   - Analyze product performance
   - Check different time periods
   - Verify calculations

3. **Order Management:**
   - View all customer orders
   - Check order details
   - Monitor order statuses

#### **Cross-Browser and Device Testing:**
- Test on Chrome, Firefox, Safari
- Test on mobile devices (responsive design)
- Test on different screen resolutions
- Verify forms work on all browsers

#### **Performance Testing:**
- Add 50+ products and test loading times
- Test with multiple concurrent users
- Check database query performance
- Verify image loading optimization

### **Final Verification Checklist:**
- [ ] All forms validate properly (client & server side)
- [ ] Navigation works smoothly throughout the app
- [ ] Responsive design works on mobile devices
- [ ] Error messages are clear and helpful
- [ ] Success messages confirm user actions
- [ ] Loading states provide feedback for slow operations
- [ ] All buttons and links are functional
- [ ] Database operations complete successfully
- [ ] Security validations prevent unauthorized access
- [ ] Performance is acceptable for demo purposes

---

## **Integration Points with Team Members:**

### **With HeenuReet:**
- **Day 1:** Coordinate on User entity structure for cart-user relationship
- **Day 2:** Integrate cart functionality with product listing pages
- **Day 3:** Coordinate on Order entity enhancements for cancellation
- **Day 5:** Final integration and navigation testing

### **With Mehwish:**
- **Day 1:** Coordinate on database schema for Product and Category tables
- **Day 2:** Test cart functionality with sample data from Mehwish
- **Day 4:** Coordinate on sales analysis data requirements
- **Day 5:** Final database optimization and testing

---

## **Common Debugging Tips:**
1. Use browser developer tools to check for JavaScript errors
2. Verify CSS is loading properly (check Network tab)
3. Test form submissions in Network tab
4. Check database for proper data saving
5. Use Thymeleaf debugging with th:text to verify model data
6. Test AJAX calls with console.log statements
7. Verify CSS selectors are working correctly
8. Check responsive design using device simulation

---

## **Emergency Fallbacks:**
- If Chart.js doesn't work, use simple HTML tables
- If AJAX fails, use traditional form submissions
- If complex CSS fails, use basic Bootstrap classes
- If custom validation fails, rely on browser validation
- If responsive design is complex, focus on desktop version first