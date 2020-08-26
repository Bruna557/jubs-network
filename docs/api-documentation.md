# API

## Resources

### User Controller
| Resource                      | Verb   | Description                |
| ----------------------------- | ------ | -------------------------- |
| /user                         | GET    | Get user id given username |
| /user/{id}                    | GET    | Get username given user id |
| /user/{id}/change-password    | PUT    | Change password            |
| /user/{id}/change-picture     | PUT    | Change profile picture     |
| /user/{id}/change-description | PUT    | Change user description    |
| /user/{id}                    | DELETE | Delete user account        |

### Account Controller
|      Resource      |  Verb  |              Description              |
| ------------------ | ------ | ------------------------------------- |
| /register          |  POST  | Create user account                   |
| /login             |  POST  | Validate password and generates token |
| /logout            |  POST  | Logout and invalidate token           |

### Post Controller
| Resource            | Verb   | Description    |
| ------------------- | ------ | -------------- |
| /user/{userId}/post | POST   | Create post    |
| /user/{userId}/post | PUT    | Edit post text |
| /user/{userId}/post | DELETE | Delete post    |

### Feed Controller
| Resource            | Verb | Description         |
| ------------------- | ---- | ------------------- |
| /user/{userId}/feed | GET  | Get posts from self |
| /user/{userId}/feed | PUT  | Like post           |

## Authentication

When a user logs in, a token is generated. The frontend application stores the token in `localStorage`:

```typescript
localStorage.setItem('token', response);
```

The `getToken()` method in `user.service.ts` returns the user token, or null, when token is undefined.

Every request to `/user/**` must contain a valid token in its header.

In Angular, this is done by

```typescript
let headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Authorization', this.userService.getToken());
```

Account controller methods are unprotected:

```
/register
/login
/logout
```

Additional authentication is required in some methods:

- Only the post owner can update a post;
- Only the post owner can delete a post;
- A user can only update password, description and picture on his own account;
- A user can only delete his own account;

This is accomplished with the `@PreAuthorize` annotation:

```java
@PreAuthorize("#userId == authentication.principal.userId")
```



