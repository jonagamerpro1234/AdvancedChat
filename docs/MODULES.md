# AdvancedChat Modules

> **Architecture Document**
>
> **Status:** Draft
>
> **Version:** 2.0.0-alpha.1
>
> **Last Updated:** 01-07-2026

## Overview

This document defines the purpose and responsibility of every module in AdvancedChat.

Each module has a single responsibility.

Modules should remain independent whenever possible and communicate through well-defined interfaces.

---

# Module Overview

| Module                | Responsibility                           |
|-----------------------|------------------------------------------|
| advancedchat-api      | Public API exposed to developers         |
| advancedchat-common   | Shared utilities and common components   |
| advancedchat-core     | Business logic and domain implementation |
| advancedchat-storage  | Data persistence implementations         |
| advancedchat-compat   | Compatibility with external plugins      |
| advancedchat-platform | Platform-specific implementations        |

---

# Module Details

## advancedchat-api

### Responsibility

Provides the public API of AdvancedChat.

### Contains

- Public interfaces
- Public events
- Public models
- Public exceptions

### Depends On

None

### Must Never

- Depend on Paper.
- Contain implementation logic.
- Access storage.

---

## advancedchat-common

### Responsibility

Provides reusable components shared across the project.

### Contains

- Utilities
- Validation
- Logging
- Cache
- Shared abstractions

### Depends On

- advancedchat-api

### Must Never

- Contain business logic.
- Depend on Paper.
- Access storage.

---

## advancedchat-core

### Responsibility

Implements the business logic of AdvancedChat.

### Contains

- Chat
- Channels
- Messages
- Formats
- Services
- Internal events

### Depends On

- advancedchat-api
- advancedchat-common

### Must Never

- Import platform-specific classes.
- Access Bukkit/Paper APIs directly.
- Depend on external plugins.

---

## advancedchat-storage

### Responsibility

Provides persistence implementations.

### Contains

- YAML
- JSON
- SQLite
- MySQL
- Future storage implementations

### Depends On

- advancedchat-api
- advancedchat-common
- advancedchat-core

### Must Never

- Contain business logic.
- Register commands.
- Access platform APIs directly.

---

## advancedchat-compat

### Responsibility

Provides compatibility with external plugins.

### Contains

- PlaceholderAPI
- Vault
- LuckPerms
- DiscordSRV
- Future integrations

### Depends On

- advancedchat-api
- advancedchat-common
- advancedchat-core

### Must Never

- Contain business logic.
- Replace services from the Core.
- Store plugin data.

---

## advancedchat-platform

### Responsibility

Provides platform-specific implementations.

### Contains

- Bootstrap
- Plugin entry point
- Commands
- Listeners
- Scheduler adapters
- Platform adapters

### Depends On

- All project modules

### Must Never

- Implement business logic.
- Duplicate Core functionality.
- Bypass the API.

---

# Module Dependency Rules

The dependency direction should always be respected.

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

Higher-level modules may depend on lower-level modules.

Lower-level modules must never depend on higher-level modules.

---

# Design Principles

- One responsibility per module.
- Prefer composition over duplication.
- Platform code belongs only in Platform.
- Business rules belong only in Core.
- Public contracts belong only in API.
- Shared code belongs only in Common.

---

# Notes

This document evolves together with the project architecture.

Whenever a new module is introduced, this document must be updated first.