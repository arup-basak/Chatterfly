import express from "express";

const router = express.Router();

router.use(express.json());

router.post("/", async (req, res, next) => {
    const responseJson = {
        chats: [],
        error: null, 
        status: false
    }
    try {
        if(!req.body) {
            responseJson.error = "Body Not Found"
            return res.json(responseJson)
        }
        const { chatId } = req.body;

        if(!chatId) {
            responseJson.error = "chatId Not Found"
            console.log(req.body)
            return res.json(responseJson)
        }

        
        responseJson.status = true
        return res.json(responseJson)
    } catch (error) {
        return next(error);
    }
});

export default router;
