## Eidolon test task

Task: _https://minio.eidoloncorp.com/custom/hr/test-assignment-scala-backend.html_
 
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