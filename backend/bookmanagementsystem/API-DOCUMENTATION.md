# Book Management System - API Documentation

## Overview
This document describes all available REST API endpoints for the Book Management System.

Base URL: `http://localhost:8080`

---

## 📚 Books API

### 1. Display a list of all books in the inventory with their details
**GET** `/api/books`

**Description:** Returns all books with details (title, author, genre, quantity)

**Response:**
```json
[
  {
    "bookId": 1,
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "genre": "Fiction",
    "quantity": 5
  },
  {
    "bookId": 2,
    "title": "1984",
    "author": "George Orwell",
    "genre": "Dystopian",
    "quantity": 3
  }
]
```

---

### 2. Get a single book by ID
**GET** `/api/books/{id}`

**Example:** `GET /api/books/1`

**Response:**
```json
{
  "bookId": 1,
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Fiction",
  "quantity": 5
}
```

---

### 3. Add a new book to the inventory
**POST** `/api/books`

**Request Body:**
```json
{
  "title": "To Kill a Mockingbird",
  "author": "Harper Lee",
  "genre": "Fiction",
  "quantity": 10
}
```

**Response:** (HTTP 201 Created)
```json
{
  "bookId": 3,
  "title": "To Kill a Mockingbird",
  "author": "Harper Lee",
  "genre": "Fiction",
  "quantity": 10
}
```

---

### 4. Update book details
**PUT** `/api/books/{id}`

**Example:** `PUT /api/books/1`

**Request Body:**
```json
{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Classic Fiction",
  "quantity": 8
}
```

**Response:**
```json
{
  "bookId": 1,
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Classic Fiction",
  "quantity": 8
}
```

---

### 5. Delete a book
**DELETE** `/api/books/{id}`

**Example:** `DELETE /api/books/1`

**Response:** HTTP 204 No Content

---

## 📋 Borrowing Records API

### 1. Display all borrowing details
**GET** `/api/borrowings`

**Description:** Returns all borrowing records (both current and returned)

**Response:**
```json
[
  {
    "borrowingId": 1,
    "book": {
      "bookId": 1,
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "genre": "Fiction",
      "quantity": 4
    },
    "user": {
      "userId": 1,
      "name": "John Doe",
      "address": "123 Main St",
      "contactNumber": "0771234567"
    },
    "borrowedDate": "2026-01-09",
    "returningDate": "2026-01-23",
    "returned": false
  },
  {
    "borrowingId": 2,
    "book": {
      "bookId": 2,
      "title": "1984",
      "author": "George Orwell",
      "genre": "Dystopian",
      "quantity": 3
    },
    "user": {
      "userId": 2,
      "name": "Jane Smith",
      "address": "456 Oak Ave",
      "contactNumber": "0777654321"
    },
    "borrowedDate": "2026-01-05",
    "returningDate": "2026-01-15",
    "returned": true
  }
]
```

---

### 2. Get a single borrowing record by ID
**GET** `/api/borrowings/{id}`

**Example:** `GET /api/borrowings/1`

---

### 3. Get all currently borrowed books (not returned)
**GET** `/api/borrowings/current`

**Description:** Shows only books that haven't been returned yet

---

### 4. Get all returned books
**GET** `/api/borrowings/returned`

**Description:** Shows only books that have been returned

---

### 5. Get borrowing history for a specific user
**GET** `/api/borrowings/user/{userId}`

**Example:** `GET /api/borrowings/user/1`

**Description:** Shows all books borrowed by a specific user

---

### 6. Get borrowing history for a specific book
**GET** `/api/borrowings/book/{bookId}`

**Example:** `GET /api/borrowings/book/1`

**Description:** Shows all borrowing records for a specific book

---

### 7. Borrow a book
**POST** `/api/borrowings/borrow`

**Query Parameters:**
- `userId` - ID of the borrower
- `bookId` - ID of the book to borrow
- `returningDate` - Expected return date (format: YYYY-MM-DD)

**Example:** 
```
POST /api/borrowings/borrow?userId=1&bookId=1&returningDate=2026-01-23
```

**Response:** (HTTP 201 Created)
```json
{
  "borrowingId": 3,
  "book": {
    "bookId": 1,
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "genre": "Fiction",
    "quantity": 4
  },
  "user": {
    "userId": 1,
    "name": "John Doe",
    "address": "123 Main St",
    "contactNumber": "0771234567"
  },
  "borrowedDate": "2026-01-09",
  "returningDate": "2026-01-23",
  "returned": false
}
```

**Note:** Book quantity is automatically decreased by 1

---

### 8. Return a borrowed book
**PUT** `/api/borrowings/{id}/return`

**Example:** `PUT /api/borrowings/1/return`

**Response:**
```json
{
  "borrowingId": 1,
  "book": {
    "bookId": 1,
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "genre": "Fiction",
    "quantity": 5
  },
  "user": {
    "userId": 1,
    "name": "John Doe",
    "address": "123 Main St",
    "contactNumber": "0771234567"
  },
  "borrowedDate": "2026-01-09",
  "returningDate": "2026-01-23",
  "returned": true
}
```

**Note:** Book quantity is automatically increased by 1

---

## 👥 Users API

### 1. Get all users
**GET** `/api/users`

**Response:**
```json
[
  {
    "userId": 1,
    "name": "John Doe",
    "address": "123 Main St",
    "contactNumber": "0771234567"
  },
  {
    "userId": 2,
    "name": "Jane Smith",
    "address": "456 Oak Ave",
    "contactNumber": "0777654321"
  }
]
```

---

### 2. Get a single user by ID
**GET** `/api/users/{id}`

**Example:** `GET /api/users/1`

---

### 3. Add a new user/borrower
**POST** `/api/users`

**Request Body:**
```json
{
  "name": "Alice Johnson",
  "address": "789 Pine Rd",
  "contactNumber": "0779876543"
}
```

**Response:** (HTTP 201 Created)
```json
{
  "userId": 3,
  "name": "Alice Johnson",
  "address": "789 Pine Rd",
  "contactNumber": "0779876543"
}
```

---

### 4. Update user details
**PUT** `/api/users/{id}`

**Example:** `PUT /api/users/1`

**Request Body:**
```json
{
  "name": "John Doe",
  "address": "123 Main Street, Colombo",
  "contactNumber": "0771234567"
}
```

---

### 5. Delete a user
**DELETE** `/api/users/{id}`

**Example:** `DELETE /api/users/1`

**Response:** HTTP 204 No Content

---

## Testing the API

### Using cURL (PowerShell)

#### Get all books:
```powershell
curl http://localhost:8080/api/books
```

#### Get all borrowing details:
```powershell
curl http://localhost:8080/api/borrowings
```

#### Add a new book:
```powershell
curl -X POST http://localhost:8080/api/books `
  -H "Content-Type: application/json" `
  -d '{\"title\":\"Harry Potter\",\"author\":\"J.K. Rowling\",\"genre\":\"Fantasy\",\"quantity\":5}'
```

#### Borrow a book:
```powershell
curl -X POST "http://localhost:8080/api/borrowings/borrow?userId=1&bookId=1&returningDate=2026-01-23"
```

---

### Using Postman

1. **Get all books:**
   - Method: GET
   - URL: `http://localhost:8080/api/books`

2. **Get all borrowing details:**
   - Method: GET
   - URL: `http://localhost:8080/api/borrowings`

3. **Add a new book:**
   - Method: POST
   - URL: `http://localhost:8080/api/books`
   - Headers: `Content-Type: application/json`
   - Body (raw JSON):
     ```json
     {
       "title": "Harry Potter",
       "author": "J.K. Rowling",
       "genre": "Fantasy",
       "quantity": 5
     }
     ```

4. **Borrow a book:**
   - Method: POST
   - URL: `http://localhost:8080/api/borrowings/borrow?userId=1&bookId=1&returningDate=2026-01-23`

---

## Error Responses

All endpoints may return the following errors:

- **404 Not Found** - Resource not found
  ```json
  "User not found with id: 1"
  ```

- **400 Bad Request** - Invalid request data
  ```json
  "Book not available"
  ```

- **500 Internal Server Error** - Server error

