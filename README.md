# storages4j

## ⚠️ **WARNING: NOT READY FOR PRODUCTION YET**
![Status](https://img.shields.io/badge/status-under%20development-orange)

## Made by: **Douglas Luciano**

![LinkedIn](https://img.shields.io/badge/LinkedIn-Douglas_Luciano-0077B5?style=for-the-badge&logo=linkedin)


![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Maven](https://img.shields.io/badge/Maven-4.0.0-critical.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

**storages4j** is a lightweight Java library that provides a unified API for interacting with object storage systems.

The goal of this project is to offer a simple and framework-agnostic adapter for S3-compatible object storage providers.

Supported (planned) providers:

- MinIO
- AWS S3
- Local filesystem (for development and testing)

This library is designed to work with **pure Java**, without requiring any specific framework such as Spring, Quarkus or Micronaut.

---

# Motivation

Working directly with object storage SDKs (like S3 or MinIO) often introduces:

- Vendor lock-in
- Verbose APIs
- Tight coupling between application code and storage provider

**storages4j** provides a clean abstraction that allows applications to interact with object storage through a single, consistent API.

---

# Features

Planned core features:

- Upload objects
- Download objects
- Delete objects
- Check object existence
- Retrieve object metadata
- Support multiple storage providers
- Framework-agnostic design

---

# Installation

Until the library is published to Maven Central, you can build it locally:

```bash
mvn clean install
````

Then include it in your project:

````xml
<dependency>
    <groupId>io.dougluciano</groupId>
    <artifactId>storages4j</artifactId>
    <version>0.1.0</version>
</dependency>
````