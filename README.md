# Easyshop Capstone (API Starter)

A compact Spring Boot REST API starter for a small e-commerce demo used in the YearUp capstone.

What this repo is
- A backend API for categories, products, authentication (JWT), user profiles and a persisted shopping cart.

Screenshots

Below are the current screenshots included in this repo (stored under `docs/images/`):



![Login screenshot](docs/images/Screenshot%202025-12-18%20153704.png)



![App image](docs/images/Screenshot%202025-12-18%20153722.png)


![Products screenshot](docs/images/Screenshot%202025-12-18%20153734.png)

One interesting piece of code
- Example: retrieving the current user's shopping cart by resolving the user id from the Principal and calling the DAO.

Example (excerpt):

```java
// ShoppingCartController.getCart (simplified)
@GetMapping
public ShoppingCart getCart(Principal principal) {
    int userId = getUserIdFromPrincipal(principal);
    return shoppingCartDao.getByUserId(userId);
}

private int getUserIdFromPrincipal(Principal principal) {
    String userName = principal.getName();
    User user = userDao.getByUserName(userName);
    return user.getId();
}
```
