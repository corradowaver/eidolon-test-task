## Eidolon test task

Task: _https://minio.eidoloncorp.com/custom/hr/test-assignment-scala-backend.html_

---
Build & run:

* Spin up docker
```
docker-compose up -d
```
* Do flyway migrations
```
./gradlew -Pflyway.configFiles=migrations/environments/test.conf flywayMigrate
```
* Run application
```
./gradlew bootRun
```

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