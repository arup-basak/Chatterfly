import { Schema, model } from "mongoose";
import { groupSchema as Groups } from "./group.models";

const chatsSchema = new Schema({
    groups: [Groups]
}, {timestamps: true});

userSchema.pre('save', async function(next) {
    next();
});

const userModel = model('Chats', chatsSchema);

export default userModel;