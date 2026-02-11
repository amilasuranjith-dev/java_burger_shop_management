
---

### **java-burger-shop-management/README.md**
```markdown
# Burger Shop Management System (Procedural)

A complete burger shop management system implemented using procedural programming in Java.

## Features
- **Order Management**:
  - Place new orders with auto-generated IDs
  - Search orders by ID
  - View orders by status (Preparing/Delivered/Cancelled)
  - Update order details (quantity/status)

- **Customer Management**:
  - Customer registration
  - Search customer details
  - View customer order history
  - Best customer identification

- **Advanced Features**:
  - Phone number validation (Sri Lankan networks)
  - Order status tracking (0=Preparing, 1=Delivered, 2=Cancelled)
  - Dynamic array expansion
  - Console screen clearing

## Data Structure
```java
// Parallel arrays for data management
String[] orderIdArray = {"B0001", "B0002"...};
String[] custIdArray = {"0770000001", "0770000002"...};
String[] custNameArray = {"Sanath", "Malan"...};
int[] quantityArray = {5, 10...};
int[] orderStatusArray = {1, 1...};
