CREATE TABLE users(
    user_id INT PRIMARY KEY,
    user_name VARCHAR(50) UNIQUE NOT NULL,
    user_description VARCHAR(255) NOT NULL,
    user_picture VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    user_password CHAR(60) NOT null,
    user_token CHAR(36)
);

CREATE TABLE posts(
    post_id INT PRIMARY KEY,
    user_id INT NOT NULL,
    post_date TIMESTAMP,
    post_text VARCHAR(255) NOT NULL,
    post_votes INT,
    FOREIGN KEY (user_id) 
        REFERENCES users (user_id)
);

CREATE TABLE followings(
    follower_id INT NOT NULL,
    followed_id INT NOT NULL,
    PRIMARY KEY (follower_id, followed_id),
    FOREIGN KEY (follower_id) 
        REFERENCES users (user_id),
    FOREIGN KEY (followed_id) 
        REFERENCES users (user_id)
);
