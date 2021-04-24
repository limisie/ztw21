const jwt = require('jsonwebtoken');

const { SECRET_KEY } = require('../config')

module.exports = auth = (context) => {
    const authHeader = context.req.request.headers.authorization;
    if (authHeader) {
        const token = authHeader.split('Bearer: ')[1];
        if (token) {
            try {
                const user = jwt.verify(token, SECRET_KEY);
                return user;
            } catch(err) {
                throw new Error('Invalid token');
            }
        }
        throw new Error('Invalid token format');
    }
    throw new Error('Auth Header must be provided');
}