import './libs/mongo.js'
import express from 'express';
import { Server } from 'socket.io';
import loginRouter from './routes/login.js'
import signupRouter from './routes/signup.js'
import getChatsRouter from './routes/get-chats.js'
import cors from 'cors'
import userModel from './models/user.models.js';

const app = express();
const PORT = process.env.NODE_ENV === "PRODUCTION" ? 80 : 3001

app.use(cors())
app.use(express.json())

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
app.use("/get-chats", getChatsRouter)

io.on('connection', (socket) => {
    console.log('a user connected');

    socket.on("add-chat", (value) => {
        const query = { user: value.userId };
        const newChat = {
            chatId: value.chatId,
            users: []
        };
        
        const updateUserChatList = async () => {
            try {
                console.log("updated");
                const result = await userModel.updateOne(query, { $push: { chatList: newChat } });
                console.log(result);
            } catch (err) {
                console.error(err);
            }
        };
        
        updateUserChatList();        
    })

    socket.on('disconnect', () => {
        console.log('user disconnected');
    });
});


