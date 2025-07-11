# 🏥 Healthcare Management System

A full-stack **Healthcare Management System** built with **Angular**, **Spring Boot**, and **MySQL**, designed to streamline healthcare operations such as patient data management, appointment scheduling, and secure communication between healthcare providers and patients.

## 🚀 Features

- 👤 **Role-Based Access Control**
  - Admin, Doctor, and Patient roles with dedicated dashboards
- 📋 **Patient Record Management**
  - Create, update, and manage detailed patient profiles
- 📆 **Appointment Scheduling & Tracking**
  - Schedule, modify, and track appointments in real time
- 🔐 **Secure Authentication & Authorization**
  - JWT-based token security for APIs
- 🔄 **Real-time API Integration**
  - Fast and efficient data synchronization between frontend and backend
- 📊 **Dashboard Analytics**
  - View appointments, patient stats, and system usage

---

## 🧰 Tech Stack

### Frontend
- [Angular](https://angular.io/)
- [TypeScript](https://www.typescriptlang.org/)
- [Bootstrap/Tailwind CSS] (your choice)
- [RxJS](https://rxjs.dev/) for reactive programming

### Backend
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL](https://www.mysql.com/) database

---

## ⚙️ System Architecture

```text
Angular Frontend  ⇄  Spring Boot REST API  ⇄  MySQL Database
       ⬇                          ⬆
Role-based UI     ←→     JWT-secured endpoints
