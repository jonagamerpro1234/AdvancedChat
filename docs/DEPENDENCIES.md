# AdvancedChat Module Dependencies

> **Architecture Document**
>
> **Status:** Draft
>
> **Version:** 2.0.0-alpha.1
>
> **Last Updated:** YYYY-MM-DD

## Overview

This document defines the dependency rules between the different modules of AdvancedChat.

The goal is to maintain a clean architecture, avoid circular dependencies, and ensure each module has a well-defined responsibility.

---

# Architecture Layers

The project is divided into three architectural layers.

```text
Foundation
-----------
API
Common

Domain
-------
Core

Infrastructure
--------------
Storage
Compat
Platform
```

Each layer may depend only on lower layers.

---

# Dependency Diagram

```text
                     Platform
                    /        \
              Compat      Storage
                    \        /
                     Core
                       |
                    Common
                       |
                      API
```

---

# Dependency Rules

## API

Depends on:

- None

Can be used by:

- Common
- Core
- Storage
- Compat
- Platform
- External plugins

---

## Common

Depends on:

- API

Can be used by:

- Core
- Storage
- Compat
- Platform

---

## Core

Depends on:

- API
- Common

Can be used by:

- Storage
- Compat
- Platform

---

## Storage

Depends on:

- API
- Common
- Core

Can be used by:

- Platform

---

## Compat

Depends on:

- API
- Common
- Core

Can be used by:

- Platform

---

## Platform

Depends on:

- API
- Common
- Core
- Storage
- Compat

Can be used by:

None

Platform is the final application layer.

---

# Forbidden Dependencies

The following dependencies are not allowed.

API
← Common

API
← Core

Core
← Platform

Core
← Storage

Core
← Compat

Storage
← Platform

Compat
← Platform

Any circular dependency.

---

# General Rules

- Dependencies always point toward lower architectural layers.
- Business logic belongs exclusively to Core.
- Platform implementations belong exclusively to Platform.
- Shared code belongs exclusively to Common.
- Public contracts belong exclusively to API.
- Infrastructure modules must never contain business rules.

---

# Future Modules

Future modules must follow the same dependency model.

Examples:

- Platform Velocity
- Platform Folia
- Storage Redis
- Storage PostgreSQL
- Compat Geyser
- and more.... T-T

---

# Notes

Whenever a dependency is added or modified, this document must be reviewed.

Maintaining the dependency architecture is essential for the long-term maintainability of the project.