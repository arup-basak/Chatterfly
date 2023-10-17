import { io } from 'socket.io-client';

// const baseUrl = process.env.NEXT_JS_PUBLIC_BACKEND_URL as string;
const baseUrl = 'http://localhost:3001'

export default io(baseUrl);