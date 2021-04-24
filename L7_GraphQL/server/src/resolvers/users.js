const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const { SECRET_KEY } = require('../config.js');
const User = require('../models/user.js');
const Todo = require('../models/todo.js');
const { validateNewUserInput, validateLogin } = require('../utils/validators')

module.exports = {
    Query: {
        users: async () => getUsers(),
        user: async (_, { id }) => getUserById(id),
    },
    Mutation: {
        register: async (_, { newUser: { login, password, confirmPassword, email, name } }) => registerUser(login, password, confirmPassword, email, name),
        login: async (_, { login, password }) => loginUser(login, password),
        deleteMyAccount: async (_, args, context) => deleteUser(context)
    },
    Todo: {
        user: async (parent) => getUserById(parent.user),
    },
};

const getUsers = async () => {
    try {
        const users = await User.find();
        return users;
    } catch (err) {
        throw new Error(err);
    }
}

const getUserById = async (userId) => {
    try {
        const user = await User.findById(userId);
        return user;
    } catch (err) {
        throw new Error(err);
    }
}

const deleteUser = async (context) => {
    const user_data = auth(context);
    const user = await User.findById(user_data.id);
    if (!user) {
        throw new Error('User not found');
    }

    try {
        await user.delete();
        return 'User deleted successfully';
    } catch(err) {
        throw new Error(err);
    }
}

const loginUser = async (login, password) => {
    const { error, valid } = validateLogin(login, password);

    if (!valid) {
        throw new Error('Error');
    }

    const user = await User.findOne({ login });

    if (!user) {
        throw new Error('User not found');
    }

    const passwordMatch = await bcrypt.compare(password, user.password);
    if (!passwordMatch) {
        throw new Error('Wrong credentials');
    }

    const token = generateToken(user);

    return {
        ...user._doc,
        id: user._id,
        token
    }
}

const registerUser = async (login, password, confirmPassword, email, name) => {
    const { error, valid } = validateNewUserInput(
        login,
        password,
        confirmPassword,
        email);
    if (!valid) {
        throw new Error('Error');
    }

    const user = await User.findOne({ login });
    if (user) {
        throw new Error('Username already exists');
    }

    password = await bcrypt.hash(password, 12);

    const newUser = new User({
        login,
        password,
        email,
        name,
        createdAt: new Date().toISOString()
    });

    const res = await newUser.save();
    const token = generateToken(res);

    return {
        ...res._doc,
        id: res._id,
        token
    }
}

const generateToken = async (user) => {
    return jwt.sign({
        id: user.id,
        email: user.email,
        login: user.login
    }, SECRET_KEY, { expiresIn: '1h' });
}