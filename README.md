# Drug Store Management System (DSMS) – Assignment #4

## Project overview
This is a small Java version of the Drug Store Management System for CSCI 5801.
It focuses on medicine details, stock and expiry checking, low-stock checking,
and sales. The code is kept simple so a beginner Java student can follow it.

The program is a small working part of the proposed stand-alone DSMS. It is not
the complete desktop application yet. This version uses in-memory records for
the tests, so records are reset when the program finishes. It does not use a
permanent database or real store information.

## Requirements
- Java JDK 17 or newer
- Apache Maven 3.8 or newer
- Internet access the first time Maven downloads JUnit 5 and its test plugins

Check your installations:
```bash
java -version
javac -version
mvn -version
```

## Folder structure
- `src/main/java/dsms/model` – medicine, stock batch, and sale classes
- `src/main/java/dsms/validation` – login and medicine input checks
- `src/main/java/dsms/service` – inventory and sales calculations
- `src/main/java/dsms/repository` – temporary in-memory data storage
- `src/main/java/dsms/component` – features that connect multiple classes
- `src/test/java/dsms/validation` – login and medicine validation unit tests
- `src/test/java/dsms/service` – inventory and sales calculation unit tests
- `src/test/java/dsms/component` – component tests
- `pom.xml` – Maven settings and JUnit 5 dependency

## Install and run tests
1. Download and unzip the project.
2. Open a terminal in the `DSMS_Java_Assignment4` folder.
3. Run all 18 tests:
```bash
mvn test
```

Maven compiles the source code, downloads JUnit 5 if needed, runs the tests,
and prints the test summary. A successful run should show 6 test classes,
18 tests, 0 failures, and 0 errors.

## Run one group of tests
Run the four unit test classes:
```bash
mvn -Dtest=LoginValidatorTest,MedicineValidatorTest,InventoryServiceTest,SalesCalculatorTest test
```

Run the two component test classes:
```bash
mvn -Dtest=MedicineInventoryComponentTest,SalesInventoryComponentTest test
```

## Selected units
1. `LoginValidator` – checks that username and password fields are filled.
2. `MedicineValidator` – checks required medicine details, price, and reorder level.
3. `InventoryService` – counts usable stock and reduces stock without using expired batches.
4. `SalesCalculator` – works out line totals and the full sale total.

Each unit has three JUnit 5 tests: a normal case and two invalid, edge, or failure cases.

## Selected components
1. `MedicineInventoryComponent` – connects medicine validation, medicine records,
   stock records, and low-stock checking.
2. `SalesInventoryComponent` – connects medicine records, inventory checks,
   sales calculations, and sale records.

Each component has three JUnit 5 tests. They check how the classes work together,
including successful and rejected operations.

## Connection to Assignment #3
The project keeps the planned stand-alone DSMS idea and the two main focus areas:
medicine inventory/expiry management and sales transactions. The classes use a
simple layered style: model classes hold data, validation/service classes do
the work, repositories hold records, and components connect the parts.

## Testing notes and limitations
The login unit checks the username and password fields only. It does not yet
check a real user account or password hash. The tests use sample data and fixed
dates. They do not connect to a live store database or change production data.
A permanent database, desktop user interface, password encryption, receipt
printing, supplier screens, and other full application features are not part
of this assignment's small tested version.

## GitHub upload steps
Create a new public GitHub repository named `DSMS-Java-Assignment4`. From this
project folder, run:
```bash
git init
git add .
git commit -m "Add DSMS Java project and JUnit tests"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/DSMS-Java-Assignment4.git
git push -u origin main
```
Replace `YOUR-USERNAME` with your GitHub username. Copy the real repository page
URL into the testing report. Do not submit the example URL before replacing it.
