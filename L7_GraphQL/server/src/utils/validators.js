module.exports.validateNewUserInput = (
    login,
    password,
    confirmPassword,
    email,
) => {
    const error = {};
    if (login.trim() === '') {
        error.login = 'Login must not be empty';
    }

    if (email.trim() === '') {
        error.email = 'Login must not be empty';
    } else {
        const regEx = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        if (!email.match(regEx)) {
            error.email = 'Email must be valid';
        }
    }

    if (password === '') {
        error.password = 'Password must not be empty';
    } else if (password !== confirmPassword) {
        error.confirmPassword = 'Passwords must match';
    }

    return {
        error,
        valid: Object.keys(error).length < 1
    }
};

module.exports.validateLogin = (login, password) => {
    const error = {};
    if (login.trim() === '') {
        error.login = 'Login must not be empty';
    }

    if (password === '') {
        error.password = 'Password must not be empty';
    }

    return {
        error,
        valid: Object.keys(error).length < 1
    }
};