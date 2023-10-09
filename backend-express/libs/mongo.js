import dotenv from "dotenv";
import mongoose from "mongoose";

dotenv.config();
const URI = process.env.MONGO_URI;

const main = async () => {
  if (URI) {
    await mongoose.connect(URI);
    console.log("MONGODB CONNECTED");
} else {
    console.log("URI FAILED");
  }
};

main().catch((err) => console.log(err));

export default main;
