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