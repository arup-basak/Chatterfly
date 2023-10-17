import './libs/mongo.js'
import express from 'express';
import { Server } from 'socket.io';
import loginRouter from './routes/login.js'
import signupRouter from './routes/signup.js'
import chatsRouter from './routes/chats.js'
import cors from 'cors'

const app = express();
const PORT = process.env.NODE_ENV === "PRODUCTION" ? 80 : 3001

app.use(cors())

const server = app.listen(PORT, () => {
    console.log(`Express server is running on port ${PORT}`);
});

const io = new Server(server, {
    cors: {
        origin: "http://localhost:3000"
    }
});
app.get('/', (req, res) => {
    res.send("Hello WOrld")
})

app.use("/signup", signupRouter)
app.use("/login", loginRouter)
app.use("/chats", chatsRouter)

io.on('connection', (socket) => {
    console.log('a user connected');

    socket.onAny((ag) => {
        console.log(ag)
    })
    socket.on('disconnect', () => {
        console.log('user disconnected');
    });
});


