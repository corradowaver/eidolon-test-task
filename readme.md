## Eidolon test task

Preconditions:
* Installed jdk-11
* Installed docker
---
Build & run:

* Build jar
```
./gradlew bootJar
```

* Run using docker-compose
```
docker-compose up -d
```

* Application is listening at port 8081

---

REST Endpoints: 

---
#### Add new binding
- `POST /add`
    - Request example:
      ```
      {
        "binding": "Ctrl + Shift + K",
        "description": "Push current branch to remote repository",
        "action": "git.push"
      }
      ```
#### Get all bindings by category
- `GET /category/{category_name}`
    - Response example:
      ```
      [
        {"actionName": "push", "binding": "Ctrl + Shift + K" },
        {"actionName": "fetch", "binding": "Ctrl + T" }
      ]
      ```
