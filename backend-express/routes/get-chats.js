import express from 'express'
import userModel from '../models/user.models.js';
import cors from 'cors'

const router = express.Router();
router.use(cors())

router.post('/', async (req, res, next) => {
    try {
        if (!req.body)
            return next("Not Username Found");

        const { username } = req.body;

        const data = await userModel.findOne({user: username}).select("chatList");
        res.json(data.chatList)
    }
    catch (error) {
        next(error);
    }
})

export default router;