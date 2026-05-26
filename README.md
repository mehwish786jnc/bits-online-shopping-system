# e-Kiosk — Online Shopping System

A full-stack e-commerce web application built with Spring Boot, Spring Data JPA, Thymeleaf, and MySQL for a BITS college project.

---

## Team

| Member | Role |
|--------|------|
| HeenuReet | Login & Registration · Product Search & Browse · Ordering · Feedback · Final Integration |
| Aliya | Product Management (Admin) · Shopping Cart · Order Cancellation · Sales Analysis · UI/UX & Validation |
| Mehwish Sultana | Database & Config · Customer Dashboard · Admin Dashboard · Reports · Testing & Deployment |

---

## Tech Stack

- **Backend:** Java 17, Spring Boot 3.2.0, Spring Data JPA (Hibernate)
- **Frontend:** Thymeleaf, Bootstrap 5, custom CSS + JS
- **Database:** MySQL 8/9
- **Security:** BCrypt password hashing via `spring-security-crypto`
- **Build:** Maven

---

## Features

### Customer
- Register and log in
- Browse and search products by name and category
- Add to cart, update quantities, remove items
- Place orders and view order history
- Cancel pending/confirmed orders
- View order details
- Submit feedback with star rating

### Admin
- Manage products (add, edit, delete, search by category)
- View all orders
- Dashboard with total products, orders, customers, today's sales, and low-stock alerts
- Reports: sales (date range), inventory (stock status), customer activity
- View all customer feedback with colour-coded ratings

---

## Quick Start

```bash
./start.sh
```

The script will:
1. Check for Java 17+
2. Install Homebrew if missing (macOS)
3. Install and start MySQL if missing
4. Set up the root password (`Shopping@123`) on first run
5. Create the `shopping_system` database
6. Launch the application

Then open: **http://localhost:8080**

---

## Default Accounts

| Username | Password | Role |
|----------|----------|------|
| `admin` | `password123` | Admin |
| `heenureet` | `password123` | Customer |
| `aliya` | `password123` | Customer |
| `mehwish` | `password123` | Customer |

---

## Project Structure

```
src/main/java/com/shopping/system/
├── OnlineShoppingSystemApplication.java   # Main class + BCryptPasswordEncoder bean
├── DataInitializer.java                   # Seeds users and products on startup
├── entity/                                # JPA entities (User, Product, Cart, Order, Feedback)
├── repository/                            # Spring Data JPA repositories
├── service/                               # Business logic layer
└── controller/                            # MVC controllers + SessionInterceptor

src/main/resources/
├── application.properties                 # DB, JPA, Thymeleaf config
├── templates/                             # Thymeleaf HTML templates
└── static/                                # CSS, JS, favicon
```

---

## Known Setup Issues

See [ISSUES.md](ISSUES.md) for documented errors and resolutions encountered during development.
