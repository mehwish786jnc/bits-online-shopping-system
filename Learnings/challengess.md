# Setup Issues & Resolutions

**Project:** Online Shopping System (e-Kiosk)  
**Stack:** Spring Boot 3.2 + Spring Data JPA + Thymeleaf + MySQL 9  
**Date:** May 2026

---

## Issue Log

| # | Error | Root Cause | Resolution |
|---|-------|-----------|------------|
| 1 | `package org.springframework.security.crypto.bcrypt does not exist` | `BCryptPasswordEncoder` is part of `spring-security-crypto` which was not in `pom.xml`. The project used BCrypt for password hashing without declaring the dependency. | Added `spring-security-crypto` as a standalone Maven dependency — no full Spring Security needed. |
| 2 | `mysql: command not found` — `CREATE DATABASE` typed directly in terminal | MySQL was not installed. User tried to run SQL directly in the shell instead of inside the MySQL CLI. | Added auto-install logic to `start.sh` using `brew install mysql`. |
| 3 | `ERROR: MySQL did not start in time` | `brew services start mysql` was called and immediately followed by a fixed `sleep 3`. MySQL 9 takes longer to initialise on first run, so the app launched before MySQL was ready. | Replaced fixed sleep with a polling loop — `mysqladmin ping` retried every 2 seconds for up to 30 seconds. |
| 4 | `Access denied for user 'root'@'localhost' (using password: NO)` | Fresh Homebrew MySQL 9 install had a root password set (not blank as in older versions). `application.properties` had `password=root` which didn't match. | Used `mysqld --init-file` to reset the root password to `Shopping@123`. Updated `application.properties` to match. `start.sh` now auto-detects this case and resets on first run. |
| 5 | `Communications link failure — Connection refused on port 3306` | Spring Boot launched immediately after `brew services start mysql` was called in `start.sh`. MySQL service was registered but not yet accepting TCP connections on 3306. | Added `mysqladmin ping` readiness poll before Spring Boot starts. |
| 6 | Login with `admin / password123` showing "Invalid username or password" | `data.sql` used a hardcoded BCrypt hash that did not correspond to `password123`. The `users` table was empty — `INSERT IGNORE` in `data.sql` silently did nothing because the hash was wrong and Spring Boot's `spring.sql.init.mode` timing caused the inserts to run before Hibernate created the tables. | Removed `data.sql` seeding. Created `DataInitializer.java` (`CommandLineRunner`) which uses the app's own `BCryptPasswordEncoder` to hash and insert users correctly on startup, only if they don't already exist. |
| 7 | `favicon.ico` returning HTTP 500 | No favicon file existed. Spring Boot tried to serve `favicon.ico` from static resources, found nothing, and threw a 500 instead of 404. | Created `favicon.svg` in `static/` and added `<link rel="icon">` to all 24 HTML templates. |
| 8 | Browser warning: `Input elements should have autocomplete attributes (suggested: "current-password")` | Password `<input>` fields in `login.html`, `register.html`, and `profile.html` were missing the `autocomplete` attribute, which browsers and accessibility tools require. | Added `autocomplete="current-password"` to the login password field and `autocomplete="new-password"` to register and profile password fields. |
| 9 | Hardcoded DB password and default credentials in committed source files (`application.properties`, `README.md`) | Passwords committed to a public/shared repository are a security risk — anyone with repo access can read them. Spring Boot allows property placeholders that resolve from the environment at runtime, which means secrets never need to live in the codebase. | Replaced hardcoded values in `application.properties` with `${ENV_VAR}` placeholders. Created a gitignored `.env` file that holds the real values locally. Added `.env.example` (committed) so teammates know which variables to set. `start.sh` sources `.env` before launching Maven. No extra dependencies needed. |

---

## Files Changed Per Issue

| # | Files Modified |
|---|---------------|
| 1 | `pom.xml` |
| 2 | `start.sh` |
| 3 | `start.sh` |
| 4 | `start.sh`, `src/main/resources/application.properties` |
| 5 | `start.sh` |
| 6 | `src/main/resources/application.properties`, `src/main/resources/data.sql` (disabled), `src/main/java/com/shopping/system/DataInitializer.java` (new) |
| 7 | `src/main/resources/static/favicon.svg` (new), all 24 HTML templates |
| 8 | `templates/login.html`, `templates/register.html`, `templates/customer/profile.html` |
| 9 | `src/main/resources/application.properties`, `start.sh`, `.env` (new, gitignored), `.env.example` (new), `.gitignore` |

---

## Issue #9 Detail — Removing Hardcoded Credentials

**Problem:** `application.properties` contained the database password in plain text and `README.md` listed default account passwords. Both files were committed to the repository.

**Solution — Environment Variables (Option 1):**

Spring Boot supports `${VARIABLE_NAME}` placeholders in `application.properties`. The actual values are supplied at runtime from the shell environment, so they never need to be in the codebase.

```properties
# application.properties — safe to commit
spring.datasource.password=${DB_PASSWORD}
```

```bash
# .env — gitignored, stays local only
export DB_PASSWORD=Shopping@123
export DB_USERNAME=root
```

`start.sh` sources this file before launching Maven:

```bash
if [ -f .env ]; then
  source .env
fi
mvn spring-boot:run
```

A `.env.example` file (committed, no real values) tells teammates what variables to create:

```bash
# .env.example
export DB_PASSWORD=your_mysql_password
export DB_USERNAME=root
```

**Why this works:**
- No secrets in git history
- Simple — no extra Maven dependencies
- Each developer can have a different local password
- `start.sh` handles sourcing automatically so the workflow is unchanged

---

## Final Working Credentials

| Role | Username | Password |
|------|----------|----------|
| Admin | `admin` | `password123` |
| Customer | `heenureet` | `password123` |
| Customer | `aliya` | `password123` |
| Customer | `mehwish` | `password123` |

---

## How to Run

```bash
./start.sh
```

Then open: [http://localhost:8080](http://localhost:8080)

> `start.sh` handles everything on first run: installs MySQL via Homebrew, starts the service, waits for readiness, resets the root password if needed, creates the database, and launches the Spring Boot app.
