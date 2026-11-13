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
Our model does not depend on the view or controller. `FoodQueueModel` manages orders through `OrderQueue`, `Order`, and `Node<T>`, and none of these classes reference the view.

We wrote JUnit tests that directly create and interact with model objects without using the view or controller. This allows us to test the model independently.

The model-view boundary remains consistent with our original design. The model handles order data, queue operations, and order validation, while user interaction is handled separately.
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
### Stretch Goals
**Committed Stretch Goal: Estimated Wait Time**
We plan to add an estimated wait time function in Deliverable 4. This feature will give customers an approximate waiting time based on the number of orders ahead of them.
For the initial implementation, we will assume that each order takes approximately 5 minutes to process.
- `FoodQueueModel`: Add `getEstimatedWaitTime(int orderNumber)` using `getOrdersAhead()`.
- `FoodQueueController`: Add a method to retrieve the estimated wait time from the model.
- `FoodQueueView`: Display the estimated wait time when customers track their orders.
We will test the estimated wait time for the first order, orders with multiple orders ahead, and invalid or completed order numbers.

**Potential Stretch Goal: Membership Priority**
We are also considering adding a membership priority feature if we successfully complete the remaining MVP features and estimated wait time feature.
This feature would allow member orders to receive higher priority while maintaining FIFO ordering among orders with the same priority.
- `Order`: Add a membership field and a getter method.
- `FoodQueueModel`: Modify `addOrder()` and `completeNextOrder()` to support membership priority.
- `OrderQueue`: Modify queue operations to support priority-based processing.
- `FoodQueueController`: Update `submitOrder()` to handle membership information.
- `FoodQueueView`: Add an option for customers to indicate their membership status.
**Dropped Stretch Goal: Delivery Tracking**
We decided to defer delivery tracking so we can focus on completing the core order management features and testing the estimated wait time feature.

## Attribution
| Team Member | Responsibilities |
|---|---|
| Anna | Model implementation, model tests, and model fixes |
| Irmmuun | View implementation and display updates |
| Aleysa | Controller implementation and input validation |
| Shirley | Integration, debugging, and README documentation |
## GenAI Note
We used GenAI to discuss which classes and methods might need to change for a possible priority-based order processing feature.
We reviewed the suggestions against our project requirements and existing code. The team is responsible for verifying and testing the final implementation.
