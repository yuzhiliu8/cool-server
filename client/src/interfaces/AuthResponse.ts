export interface AuthResponse{
    success: boolean;
    message: string;
    session: Session;
}

export interface Session {
    sessionId: number;
    userId: number;
}