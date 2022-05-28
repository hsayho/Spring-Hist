package hello.core.order;

public class Order {
    private Long memberId;
    private String iteName;
    private int itemPrice;
    private int discountPrice;

    public int calculatePrice(){
        return itemPrice - discountPrice;
    }
    public Order(Long memberId, String iteName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.iteName = iteName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getIteName() {
        return iteName;
    }

    public void setIteName(String iteName) {
        this.iteName = iteName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", iteName='" + iteName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
