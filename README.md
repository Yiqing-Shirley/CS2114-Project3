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

### Remaining Work 

**Priority 1: Final Verification**
- Run the complete test suite and verify the MVP and Estimated Waiting Time feature.
- Owner: All team members.

**Priority 2: Lightning Demo and Slides**
- Prepare a live demo showing the MVP, Estimated Waiting Time, and bad-input handling.
- Create slides covering the system diagram, design changes, MVC reflection.
- Owner: All team members.

**Priority 3: Final Submission**
- Each team member writes an individual reflection on MVC.
- Submit the final source code, tests, README, and individual writeups.
- Owner: Final Submission Yiqing, Individual writesups all members.


### Stretch Goal Status

**Completed: Estimated Waiting Time**

- Implemented `getEstimatedWaitTime(int orderNumber)` in `FoodQueueModel`.
- Estimated waiting time is calculated using the preparation times of orders ahead in the queue.
- Connected the feature to `FoodQueueController` and `CustomerView`.
- Added JUnit tests for normal cases, invalid order numbers, and waiting time updates.
- Owner: Anna (Model), Aleysa (Controller), Irmuun (View), Yiqing (Testing).

**Deferred: Delivery Tracking**

- Not implemented because it requires additional delivery information and workflow changes.

**Dropped: Membership Priority**

- Not implemented because it would require changing FIFO queue behavior and additional testing.


### Contributions
- **Anna:** Implemented duplicate order handling, improved order status logic, and established the framework for estimated waiting time.
- **Irmuun:** Completed the estimated waiting time calculation in the model and updated the customer view to display it.
- **Aleysa:** Added JUnit tests for estimated waiting time and newly implemented methods.
- **Yiqing:** Integrated the new features, updated the controller, added additional JUnit tests, and updated the README documentation.
- 
## GenAI Note
We used GenAI to discuss which classes and methods might need to change for strech goals.
We reviewed the suggestions against our project requirements and existing code. The team is responsible for verifying and testing the final implementation.
