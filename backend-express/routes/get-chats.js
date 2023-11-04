import express from "express";
import userModel from "../models/user.models.js";

const router = express.Router();

router.post("/", async (req, res, next) => {
  try {
    const { username } = req.body;

    if (!username) {
      return res.json({
        response: false,
        error: "Username is missing",
        data: null,
      });
    }

    const data = await userModel.findOne({ user: username }).select("chatList");

    if (data) {
      return res
        .status(200)
        .json({ response: true, error: null, data: data.chatList });
    } else {
      return res.json({
        response: false,
        error: "Username not found",
        data: null,
      });
    }
  } catch (error) {
    next(error);
  }
});

export default router;
