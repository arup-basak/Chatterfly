import { Schema, model } from "mongoose";
import { messageSchema as Message } from "./message.models";

const groupSchema = new Schema({
    group_id: {
        type: String,
        required: true
    },
    total_messages: {
        type: [Message],
        required: true
    }
});

export { groupSchema };