export interface APIResponse<T> {
    success: boolean;
    message: string;
    data: T;
}

export interface Session {
    sessionId: number;
    userId: number;
    expireDate: string;
}

export interface User {
    id: number
    email: string;
    firstName: string;
    lastName: string;
    roleType: string;
}