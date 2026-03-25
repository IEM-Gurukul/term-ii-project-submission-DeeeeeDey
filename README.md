[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# PCCCS495 – Term II Project


## PROJECT TITLE
Smart Study Planner using Strategy and Observer Pattern

## PROBLEM STATEMENT
Students frequently experience issues in managing school work due to improper prioritization, shifting deadlines, and the absence of a structured planning tool. Conventional paper-based planners are static and do not respond to shifting priorities based on the urgency, complexity, or time available for a given task. The project aims to create a Smart Study Planner using Java that automatically organizes academic tasks based on user-defined criteria such as deadlines, difficulty level, and time required for a task. The application also includes features such as multiple scheduling methods, the ability to change methods as required, reminders for approaching deadlines, and notifications for overdue tasks. The project seeks to create a modular, object-oriented solution that reflects the principles of abstraction, extensibility, separation of concerns, and the use of design patterns in a simple and maintainable fashion.

## TARGET USER
College students who want a structured and flexible way to organize academic tasks and manage deadlines.

## CORE FEATURES
- Add, update, and delete academic tasks  
- Task categorization (assignment, exam preparation, reading)  
- Dynamic scheduling using selectable strategies  
- Runtime switching between scheduling strategies  
- Deadline reminders and overdue alerts  
- File-based persistence for saving and loading tasks  
- Input validation and exception handling  

## OOP CONCEPTS TO BE USED

**ABSTRACTION :** Will represent shared attributes such as title, deadline, difficulty, and estimated effort while allowing flexibility for specialized task types.  

**INHERITANCE :** The design will include specialized task categories such as assignments, examinations, and reading activities, each extending a generalized task structure to support code reuse and logical hierarchy.  

**POLYMORPHISM :** Different scheduling behaviors dynamically according to user request.  

**EXCEPTION HANDLING :** Custom exceptions for invalid input, missing data, file errors.  

## COLLECTIONS
Collections (ArrayList, PriorityQueue) for task management. Optional thread for reminders.

## PROPOSED ARCHITECTURE DESCRIPTION
The system will be designed using a layered architecture, where user interaction, business logic, and data storage are separated. The design will include task management, multiple scheduling models, and deadline notifications. Modularity, extensibility, and separation of concerns are also included.
## How to Run

---

## Git Discipline Notes
Minimum 10 meaningful commits required.
