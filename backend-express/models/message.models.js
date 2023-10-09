import { Schema } from "mongoose";

const messageSchema = new mongoose.Schema({
    message: {
        type: String,
        required: true
    },
    user_id: {
        type: String,
        required: true
    },
    group_id: {
        type: String,
        required: true
    },
    created_at: {
        type: Date,
        required: true
    }
});

export { messageSchema };