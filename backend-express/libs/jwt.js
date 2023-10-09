import jwt from "jsonwebtoken";

const tokenKey = process.env.JWT_SECRET;

export const jwt_token = (user, email) => {
    let token;
    try { 
        token = jwt.sign(
            { user: user, email: email },
            tokenKey,
            { expiresIn: "1h" }
        );
    } catch(err) {
        console.log(err);
    }

    return token;
};