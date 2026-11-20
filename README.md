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

### Changes from D3 Predictions

- **Estimated Waiting Time:** We updated `FoodQueueModel.java`, `FoodQueueController.java`, and `CustomerView.java` to calculate and display estimated waiting times based on the preparation times of orders ahead in the queue.

- **Duplicate Order Confirmation:** We implemented duplicate detection in the model, passed the confirmation parameter through the controller, and added a confirmation prompt in the customer view.

- **Order Status Handling:** We added a separate Start Next Order option to the employee view and controller so orders must move from `PENDING` to `IN_PROGRESS` before being marked `COMPLETED`.

- **Unchanged Classes:** The estimated waiting time feature did not require changes to `Node.java` or `OrderQueue.java`. Although we originally expected `EmployeeView.java` to remain unchanged, we updated it to support the order status workflow.

### Model Independence

Our model works independently from the view and controller. It handles orders, queue operations, and input validation without depending on the view.

We tested the model directly using JUnit tests, and all tests passed. This shows that the model can work on its own.

### Challenges and Remaining Limitations

**Resolved Challenges:**

- **Duplicate Order Confirmation:** We implemented duplicate detection and added the confirmation prompt so customers can confirm or cancel duplicate orders.

- **Order Status Workflow:** During integration testing, we found that employees could not complete orders without starting them first. We added a separate Start Next Order option to fix this issue.

- **Estimated Waiting Time:** We implemented category-based waiting time calculations and corrected the calculation to count orders ahead of the customer's order.

- **Integration Testing:** We tested the complete customer and employee workflow and confirmed that invalid inputs do not crash the application.

**Remaining Limitations:**

- **Estimated Waiting Time Accuracy:** Our estimates use fixed preparation times for each food category rather than actual preparation times.

- **Order Processing:** The current system processes one order at a time using FIFO ordering. It does not support preparing multiple orders simultaneously.

### What Still Needs Work
- **Final Presentation:** Prepare and rehearse a three-minute demo showing order submission, duplicate confirmation, estimated waiting time, and employee order processing.

- **Deferred Features:** Delivery Tracking and Membership Priority are not implemented in the current version.

## Next Steps
### Remaining MVP Work 

**Priority 1: Duplicate Order Confirmation**
- Detect when a customer submits an order with the same food name and category as an existing pending order.
- Ask the customer whether they want to submit the duplicate.
- Allow the customer to confirm or cancel without crashing.
- Owner: Irmuun (View), with Anna (Model) and Aleysa (Controller).
  
**Priority 2: Improve Order Status Handling**
- Verify that order status changes correctly from PENDING to IN_PROGRESS to COMPLETED.
- Ensure that completed orders remain searchable.
- Improve how completed orders display their queue position and orders ahead.
- Owner: Anna (Model), Irmuun (View).
  
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
**Owner:** Anna (Model), Aleysa (Controller), Irmuun (View), Yiqing (Integration and Testing).

**Deferred:**
- Delivery Tracking not planned for Deliverable 4 because it requires additional delivery-related data and workflow.

**Optional if Time Allows:**
- Membership Priority may be considered only after the MVP and estimated wait time feature are complete. This would require changes to queue ordering and would need additional testing.

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

**Test Owners:** Anna (Model Tests), Yiqing (Integration and Edge-Case Tests), with support from Aleysa and Irmuun.

## Attribution
| Team Member | Responsibilities |
|---|---|
| Anna | Model implementation, model tests, and model fixes |
| Irmuun | View implementation and display updates |
| Aleysa | Controller implementation and input validation |
| Yiqing | Integration, debugging, and README documentation |
## GenAI Note
We used GenAI to discuss which classes and methods might need to change for strech goals.
We reviewed the suggestions against our project requirements and existing code. The team is responsible for verifying and testing the final implementation.
