package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    ShoppingCart getAllItemsInCart(int userId);
    void addItemToCart(int userId, int productId, int quantity);
    void removeItemFromCart(int userId, int productId);
    void clearCart(int userId);
    // add additional method signatures here
}
