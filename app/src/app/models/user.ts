export class User {
    constructor(
        public username: string,
        public userDescription: string,
        public userPicture: string,
        public userEmail: string,
        public password: string = null
    ) {}
    
}