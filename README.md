# Online Train Ticket Reservation System

A simple command-line application built using Java and MySQL to manage train ticket reservations.  Users can book tickets, view ticket information, update details, and cancel tickets.

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)


## Project Overview

This project provides a basic online train ticket reservation system.  It allows users to interact with a database to manage their bookings.  The system is designed for simplicity and ease of understanding, making it suitable for educational purposes or as a starting point for a more complex reservation system.  It addresses the need for a simple, manageable way to handle train ticket reservations without the complexity of a full-fledged web application.

## Table of Contents

* [Prerequisites](#prerequisites)
* [Installation Guide](#installation-guide)
* [Usage Examples](#usage-examples)
* [Project Architecture](#project-architecture)
* [License](#license)


## Prerequisites

* Java Development Kit (JDK) 8 or higher
* MySQL Server
* MySQL Connector/J (version 8.3.0 or compatible)


## Installation Guide

1. **Clone the repository:**
   ```bash
   git clone https://github.com/abhishekdumaniya/online-reservation-system.git
   ```

2. **Set up MySQL Database:**
   - Create a database named `train_ticket_reservation`.
   - Create a table named `reservation_info` with the following schema (adjust data types as needed):
     ```sql
     CREATE TABLE reservation_info (
         uname VARCHAR(255),
         uemail VARCHAR(255),
         umobile VARCHAR(20),
         upnr INT PRIMARY KEY,
         journey_date DATE,
         journey_from VARCHAR(255),
         journey_to VARCHAR(255)
     );
     ```

3. **Import the MySQL Connector/J:** Add the MySQL Connector/J dependency to your project (the `pom.xml` file already includes it).

4. **Compile and Run:** Compile and run the `Main.java` file.  You will be prompted for your MySQL username and password.


## Usage Examples

The application presents a menu-driven interface:

1. **Book Ticket:**  Prompts the user for their name, email, mobile number, journey date, starting location, and destination.  A random PNR (Passenger Name Record) number is generated.

2. **Show information about the ticket:** Prompts the user for their PNR number and displays the booking details.

3. **Update the details:**  Prompts the user for their PNR number and allows them to update their name, email, mobile number, journey date, starting location, and destination.

4. **Delete the ticket:** Prompts the user for their PNR number and deletes the corresponding booking.

5. **Quit:** Exits the application.


**Example Code Snippet (Booking a Ticket):**

The `Main.java` file contains the core logic.  A simplified example of booking a ticket is shown below:

```java
// ... (other code) ...
case 1:
    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
    Random random = new Random();
    int min = 100000;
    int max = 1000000;
    int pnr = random.nextInt(max - min) + min;
    // ... (get user input) ...
    preparedStatement.setString(1, uname);
    // ... (set other parameters) ...
    preparedStatement.execute();
    break;
// ... (other code) ...
```

## Project Architecture

The application follows a simple three-tier architecture:

1. **Presentation Tier:** The Java command-line interface.
2. **Application Tier:** The Java code that handles user input, database interactions, and business logic.
3. **Data Tier:** The MySQL database storing reservation information.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
