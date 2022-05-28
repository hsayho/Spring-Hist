package hello.core.order;

public interface Orderservice {
    Order createOrder(Long member, String itemName, int itemPrice);
}
