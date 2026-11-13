# CS2114-Project3

### How to Run the Model
1. Open the project in Eclipse.
2. Make sure JUnit 5 is available in the project.
3. Navigate to `src/deliveryservice`.
4. Right-click `OrderQueueTest.java` and select `Run As > JUnit Test`.
5. Repeat for `FoodQueueModelTest.java`.

### How to Run the View
1. Open the project in Eclipse.
2. Navigate to `src/deliveryservice/FoodQueueApp.java`.
3. Right-click the file and select `Run As > Java Application`.
4. Select `1` for the customer menu or `2` for the employee menu.
5. Customers can submit and track orders. Employees can view pending orders and complete orders.

## Reflection:

### Changes from the Specification

- **Order Status:** Our original design included an `OrderStatus` enum, but the current implementation uses a `String` field in `Order` to represent `PENDING`, `IN_PROGRESS`, and `COMPLETED`. This simplified the implementation.

- **Order Lookup:** Instead of separate `getOrder()` and `getOrderStatus()` methods, we implemented `trackOrder()`, which searches both pending and completed orders. The returned `Order` provides its status through `getStatus()`.

- **Input Validation:** We expanded `addOrder()` validation to reject missing food names, missing categories, and unsupported food categories. This prevents invalid order information from entering the queue.

- **Tests:** We implemented JUnit tests for `Node`, `Order`, `OrderQueue`, and `FoodQueueModel`. These tests cover core queue operations, FIFO ordering, order creation, order tracking, status changes. We included empty-queue exception tests to check that the queue handles invalid operations safely.

- **View and Application Structure:** We implemented separate `CustomerView` and `EmployeeView` classes and added `FoodQueueApp` to start the application. This allows customers and employees to access different functions while sharing the same model through the controller.

### Model Independence

Our model works independently from the view and controller. It handles orders, queue operations, and input validation without depending on the view.

We tested the model directly using JUnit tests, and all tests passed. This shows that the model can work on its own.

### Unknowns

**Resolved Unknowns:**
- We figured out how to implement FIFO ordering using a singly linked queue.
- We established how to update order statuses and track both pending and completed orders.
- We determined how to connect the customer and employee interfaces to the same model through the controller.
- We implemented validation for food names, categories, and order-number input.

**Remaining Unknowns:**
- How to detect duplicate orders and ask customers to confirm whether they want to submit another identical order.
- How to implement estimated wait times accurately if orders have different preparation times.

### What doesnot work yet
The current prototype supports the main MVP, including order submission, order tracking, and employee order processing. The application runs without crashing on the normal MVP path, and invalid cases are handled.
However, duplicate orders confirmation has not been implemented yet. 

## Next Steps
### Remaining MVP Work 

**Priority 1: Duplicate Order Confirmation**
- Detect when a customer submits an order with the same food name and category as an existing pending order.
- Ask the customer whether they want to submit the duplicate.
- Allow the customer to confirm or cancel without crashing.
- Owner: Irmmuun (View), with Anna (Model) and Aleysa (Controller).
  
**Priority 2: Improve Order Status Handling**
- Verify that order status changes correctly from PENDING to IN_PROGRESS to COMPLETED.
- Ensure that completed orders remain searchable.
- Improve how completed orders display their queue position and orders ahead.
- Owner: Anna (Model), Irmmuun (View).
  
**Priority 3: Integration and Edge Case Testing**
- Run the complete customer and employee workflow.
- Test invalid input, empty queues, nonexistent order numbers, and duplicate orders.
- Fix any bugs found during integration.
- Owner: Yiqing (Integration and Testing), with all team members.

### Stretch Goals
**Committed Stretch Goal: Estimated Wait Time**

We plan to add an estimated wait time for each pending order. The system will calculate an estimated waiting time using the expected preparation times of the orders ahead in the queue.
We plan to assign estimated preparation times to different food categories. For example, drinks may take less time than dinner orders.
This feature changes the model's calculation logic and will be accessible to customers through the controller and customer view.

**Expected Changes:**
- `FoodQueueModel.java`
  - Add `getEstimatedWaitTime(int orderNumber)`.
  - Calculate estimated waiting time based on the categories of orders ahead.

- `FoodQueueController.java`
  - Add `getEstimatedWaitTime(int orderNumber)` to expose the model calculation.

- `CustomerView.java`
  - Update `trackOrderPrompt()` to display the estimated waiting time.

**Expected Unchanged Classes:**
- `Node.java`
- `OrderQueue.java`
- `EmployeeView.java`
**Owner:** Anna (Model), Aleysa (Controller), Irmmuun (View), Shirley (Integration and Testing).

**Deferred:**
- Delivery Tracking — not planned for Deliverable 4 because it requires additional delivery-related data and workflow.

**Optional if Time Allows:**
- Membership Priority — may be considered only after the MVP and estimated wait time feature are complete. This would require changes to queue ordering and would need additional testing.

### New Tests Needed
**Duplicate Order Confirmation**
- Submit two orders with the same food name and category.
- Confirm the second order and verify both are added.
- Cancel the second order and verify the queue size does not change.

**Estimated Wait Time**
- Test `getEstimatedWaitTime(int orderNumber)` with an empty queue.
- Test a pending order with no orders ahead.
- Test multiple pending orders with different food categories.
- Test a nonexistent order number.
- Test a completed order.
- Verify that estimated waiting time updates after an order is completed.

**Order Status and Integration**
- Verify the correct status after `startNextOrder()`.
- Verify the correct status after `completeNextOrder()`.
- Verify that a completed order remains searchable.
- Verify that invalid input does not crash the application.

**Test Owners:** Anna (Model Tests), Shirley (Integration and Edge-Case Tests), with support from Aleysa and Irmmuun.

## Attribution
| Team Member | Responsibilities |
|---|---|
| Anna | Model implementation, model tests, and model fixes |
| Irmmuun | View implementation and display updates |
| Aleysa | Controller implementation and input validation |
| Shirley | Integration, debugging, and README documentation |
## GenAI Note
We used GenAI to discuss which classes and methods might need to change for strech goals.
We reviewed the suggestions against our project requirements and existing code. The team is responsible for verifying and testing the final implementation.
