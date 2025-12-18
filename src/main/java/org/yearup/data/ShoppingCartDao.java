package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    boolean itemExistsInCart(int userId, int productId);
    void addItemToCart(int userId, int productId);
    void updateItemQuantity(int userId, int productId, int quantity);
    void removeItemFromCart(int userId, int productId);
    void clearCart(int userId);
}
