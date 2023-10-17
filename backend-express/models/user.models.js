import { Schema, model } from "mongoose";
import bcrypt from 'bcrypt';

const chatListSchema = new Schema({
    chatId: {
        type: String,
        required: true
    },
    users: {
        type: [String],
        required: true
    }
})

const userSchema = new Schema({
    user: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true
    },
    name: {
        type: String,
        required: false
    },
    lastSeen: {
        type: Date,
        required: true,
        default: Date.now
    },
    chatList: {
        type: [chatListSchema],
        required: false,
        default: []
    }
}, { timestamps: true });

userSchema.pre('save', async function (next) {
    try {
        const salt = await bcrypt.genSalt();
        this.password = await bcrypt.hash(this.password, salt);
        next();
    } catch (error) {
        next(error); // Pass any error to the next middleware
    }
});

const userModel = model('User', userSchema);

export { userSchema };
export default userModel;