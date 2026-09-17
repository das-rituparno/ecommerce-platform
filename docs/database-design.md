## Product

| Field | Type |
|---|---|
| id | Long |
| name | String |
| description | String |
| price | BigDecimal |
| categoryId | Long |
| brand | String |
| quantity | Integer |
| imageUrl | String |
| createdAt | LocalDateTime |
| updatedAt | LocalDateTime |

## Category

| Field | Type |
|---|---|
| id | Long |
| name | String |
| description | String |

## User

| Field | Type |
|---|---|
| id | Long |
| name | String |
| email | String |
| password | String |
| role | String |
| createdAt | LocalDateTime |

## Cart

| Field | Type |
|---|---|
| id | Long |
| userId | Long |

## CartItem

| Field | Type |
|---|---|
| id | Long |
| cartId | Long |
| productId | Long |
| quantity | Integer |
| price | BigDecimal |

## Order

| Field | Type |
|---|---|
| id | Long |
| userId | Long |
| totalAmount | BigDecimal |
| status | String |
| paymentStatus | String |
| createdAt | LocalDateTime |

## OrderItem

| Field | Type |
|---|---|
| id | Long |
| orderId | Long |
| productId | Long |
| quantity | Integer |
| price | BigDecimal |

## Entity Relationships

### Category → Product

One category can contain multiple products.

Category 1 -------- * Product

### Cart → CartItem

One cart can contain multiple cart items.

Cart 1 -------- * CartItem

### Order → OrderItem

One order can contain multiple order items.

Order 1 -------- * OrderItem