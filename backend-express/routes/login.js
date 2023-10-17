import express from "express";
import userModel from "../models/user.models.js";
import { jwt_token } from "../libs/jwt.js";
import { comparePassword } from "../utils/auth.js";

const router = express.Router();

router.post("/", async (req, res, next) => {
  try {
    if (!req.body) {
      return res.json({ success: false, error: "body is undefined" });
    }

    const { user, email, password } = req.body;
    const existingUser = await userModel.findOne({ user: user });

    if (!existingUser) {
      return res.json({ success: false, error: "User Not Found" });
    }

    const passwordMatch = await comparePassword(password, existingUser.password);

    if (!passwordMatch) {
      return res.json({ success: false, error: "Password is Incorrect" });
    }

    const token = jwt_token(user, email);
//    console.log(token)

    return res.status(200).json({
      success: true,
      data: {
        userId: user,
        email: email,
        token: token,
      },
    });
  } catch (error) {
    return next(error);
  }
});


router.get('/', (req, res) => {
  res.send("Hello World")
})

export default router;
