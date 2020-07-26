INSERT INTO users(user_id, user_name, user_description, user_picture, user_email, user_password)
VALUES (1, 'bruna', 'Software developer at Instituto de Pesquisas Eldorado.', 'profile_picture', 'schroder.bruna@hotmail.com', 'a1s2d3f4');

INSERT INTO users(user_id, user_name, user_description, user_picture, user_email, user_password)
VALUES (2, 'sandy', 'Software developer at South System.', 'profile_picture', 'cartoonsandymaniac@gmail.com', 'hinnata23');


INSERT INTO posts(post_id, user_id, post_date, post_text, post_votes)
VALUES (1, 1, '2020-07-24 18:58:25-07', 'This is the first jubs post!', 0);


INSERT INTO followings(follower_id, followed_id)
VALUES (1, 2);