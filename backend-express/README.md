# JWT Authentication for JavaScript REST API

This README provides information on how to use the "jwt_auth" package, a JavaScript REST API for implementing JWT (JSON Web Token) authentication in your Node.js application. This package allows you to secure your endpoints by verifying JWT tokens and managing user authentication.

## Installation

Before running the code, make sure you have Node.js and MongoDB installed on your system.

1. Clone this repository to your local machine:

        git clone https://github.com/arup-basak/jwt_authentication.git

2. Change directory to the project folder:

        cd jwt_auth

3. Install the required dependencies:

        npm install

4. Create a .env file in the project root and add the following environment variables:

        MONGODB_URI=<your-mongodb-connection-string>
        JWT_SECRET=<your-secret-key>

    PORT: The port on which the server will run.
    MONGODB_URI: Your MongoDB connection string.
    JWT_SECRET: Your secret key for JWT token generation.

5. Start the application:

    npm start

This will start the server using Nodemon, which will automatically restart the server whenever you make changes to the code.

## Usage

This application provides the following endpoints for JWT authentication:

- `POST /api/auth/signup`: Register a new user.
- `POST /api/auth/login`: Login and generate a JWT token.

To use these endpoints, you can use tools like Postman or curl.

## Dependencies

- `bcrypt`: For hashing passwords.
- `dotenv`: For managing environment variables.
- `express`: Web application framework.
- `jsonwebtoken`: For JWT token generation and validation.
mongoose: MongoDB object modeling tool.

## License

This project is licensed under the MIT License. See the LICENSE file for details.