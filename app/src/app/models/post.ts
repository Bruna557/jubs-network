import { User } from './user';

export class Post {
    constructor(
        postId: number = null,
        postDate: string,
        postText: string,
        postVotes: number,
        user: User
    ) {}
}