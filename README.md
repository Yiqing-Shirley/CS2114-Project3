# CS2114-Project3

## Reflection:
### Changes from the Specification
We made several changes while implementing the model:
- **Order Status:** Our original design included an `OrderStatus` enum, but the current implementation uses a `String` field in `Order` to represent `PENDING`, `IN_PROGRESS`, and `COMPLETED`. This simplified the implementation.
- **Order Lookup:** Instead of separate `getOrder()` and `getOrderStatus()` methods, we implemented `trackOrder()`, which searches both pending and completed orders. The returned `Order` provides its status through `getStatus()`.
- **Input Validation:** We expanded `addOrder()` validation to reject missing food names, missing categories, and unsupported food categories. This prevents invalid order information from entering the queue.
- **Tests:** We implemented JUnit tests for `Node`, `Order`, `OrderQueue`, and `FoodQueueModel`. These tests cover core queue operations, FIFO ordering, order creation, order tracking, status changes. We included empty-queue exception tests to check that the queue handles invalid operations safely.

**Classes and Methods:**
**Validation:**
**Tests:**

### Model Independence
### Unknowns
**Resolved Unknowns:**
**Remaining Unknowns:**

### What doesnot work yet
**Working MVP Features:** 
**Incomplete MVP Features:**
**Bad-Input Cases:**
**Missing or Failing Tests:**

## Next Steps
### Remaining MVP Features

## Attribution
| Team Member | Responsibilities |
|---|---|
| Anna | Model implementation, model tests, and model fixes |
| Irmmuun | View implementation and display updates |
| Aleysa | Controller implementation and input validation |
| Shirley | Integration, debugging, and README documentation |
## GenAI Note

