##Mobile Communication Systems Project
#Project Overview
This project simulates a mobile communication system by designing four key classes: Phone, Antenna, SimCard, and Network. Each class encapsulates specific attributes and methods that model the conditions for a phone to communicate within a network, such as battery life, network coverage, credit balance, and antenna capacity.

#System Requirements
A Phone can make calls only if it has sufficient battery, an active SIM card with enough credit, and is within network coverage.

The Phone connects to the nearest antenna with available capacity.

Calls are disconnected immediately if the Phone leaves coverage.

For receiving calls, the Phone must be charged and within any antenna’s range; caller and receiver can be connected to different antennas.

The Network manages antennas ensuring they overlap coverage to form a connected system.

#Class Descriptions
Phone: Tracks battery, location, SIM card, and manages call making/receiving with conditions for stationary and moving calls.

SimCard: Manages phone number, credit balance, and communication activation.

Antenna: Defines location, coverage radius, communication capacity, tracks active calls, and checks call acceptance.

Network: Stores antennas, ensures coverage overlap, finds nearest antenna, and manages connections.

#Objectives
Practice object-oriented design by defining attributes and methods aligned with real-world communication systems.

Develop critical thinking for modeling class relationships, functionality, and system state management.

Simulate call scenarios with constraints like mobility, battery life, and antenna capacity.

#How to Use
Explore each class implementation in the code.

Run test scenarios that simulate calls under varying conditions to observe system behavior.

#Technologies Used
Developed in java.

Utilizes OOP principles to create modular and testable components.
