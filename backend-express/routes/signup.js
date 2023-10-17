import express from "express";
import userModel from "../models/user.models.js";
import { jwt_token } from "../libs/jwt.js";
import cors from 'cors'

const router = express.Router();

router.use(express.json());

router.post("/", async (req, res, next) => {
  const responseJson = {
    success: false,
    data: null,
    error: null,
  };
  const { user, email, password } = req.body;

  let existingUser;
  try {
    existingUser = await userModel.findOne({ user: user });
  } catch {
    const error = new Error("Error! Something went wrong.");
    return next(error);
  }

  if (existingUser) {
    responseJson.error = "username already exists";
    res.json(responseJson);
    return next(new Error(responseJson.error));
  }

  const token = jwt_token(user, email);

  const u = new userModel({
    user: user,
    email: email,
    password: password
  })


  try {
    u.save()
    responseJson.success = true;
    responseJson.data = {
      userId: user,
      email: email,
      token: token,
    };
  
  }
  catch (err) {
    console.log(err)
  }

  res.json(responseJson);
});

export default router;
