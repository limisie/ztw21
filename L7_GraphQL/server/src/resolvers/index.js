const todosResolvers = require('./todos.js');
const usersResolvers = require('./users.js');

module.exports = {
    Query: {
        ...todosResolvers.Query,
        ...usersResolvers.Query
    },
    Mutation: {
        ...usersResolvers.Mutation,
        ...todosResolvers.Mutation,
    },
    Todo: {
        ...usersResolvers.Todo
    },
    User: {
        ...todosResolvers.User
    },
}