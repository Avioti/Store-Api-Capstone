# Easyshop Capstone (API)

A compact Spring Boot REST API for a small e-commerce demo.

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
   @GetMapping
    public ShoppingCart getCart(Principal principal)
    {
        try
        {
            int userId = getUserIdFromPrincipal(principal);
            return shoppingCartDao.getByUserId(userId);
        }
        catch(Exception e){
         
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving shopping cart.");
        }
    }
```
