export class User {
    constructor(
        public userId: number,
        public userName: string,
        public userDescription: string,
        public userPicture: string,
        public userEmail: string,
        public userPassword: string
    ) {}
}