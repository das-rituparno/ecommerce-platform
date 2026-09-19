# Product Service API

## Create Product
POST /api/products

## Get Products
GET /api/products

## Get Product
GET /api/products/{id}

## Update Product
PUT /api/products/{id}

## Delete Product
DELETE /api/products/{id}

## Search and Filtering
### Search by name
GET /api/products?name=iphone

### Filter by category
GET /api/products?category=mobile

### Filter by price
GET /api/products?minPrice=10000&maxPrice=50000

### Pagination
GET /api/products?page=0&size=10

## HTTP Status Codes
### 200 OK
Successful GET/PUT request.

### 201 CREATED
Successfully created resource.

### 204 NO CONTENT
Successfully deleted resource.

### 400 BAD REQUEST
Invalid request.

### 401 UNAUTHORIZED
Authentication required or authentication failed.

### 403 FORBIDDEN
User does not have permission.

### 404 NOT FOUND
Requested resource does not exist.

### 409 CONFLICT
Resource conflict.

### 500 INTERNAL SERVER ERROR
Unexpected server-side error.

## Product Search
GET /api/products/search

### Query Parameters
| Parameter | Required | Description |
|---|---|---|
| name | No | Search by product name |
| categoryId | No | Filter by category |
| minPrice | No | Minimum product price |
| maxPrice | No | Maximum product price |
| page | No | Page number, default 0 |
| size | No | Number of records per page, default 10 |

### Example
GET /api/products/search?name=iphone&categoryId=1&minPrice=50000&maxPrice=100000&page=0&size=10

## Error Response
All API errors follow a common structure.

```json
{
  "timestamp": "2026-09-19T20:00:00",
  "status": 404,
  "error": "Product Not Found",
  "message": "Product with id 999 not found",
  "path": "/api/products/999"
}