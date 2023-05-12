package com.abaimov.jwtshop.payload.request;

public class RemoveFromCartRequest {
    private Long productId;

    public RemoveFromCartRequest() {
    }

    public RemoveFromCartRequest(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
