const { GraphQLServer } = require('graphql-yoga');
const axios = require('axios');

async function todoById(parent, args, context, info) {
    try {
        const todos = (await axios.get(`https://jsonplaceholder.typicode.com/todos/${args.id}`)).data;
        return {
            id: id,
            title: title,
            completed: completed,
            user_id: userId,
        } = todos;
    } catch (error) {
        throw error;
    }
}

async function userById(parent, args, context, info) {
    try {
        const users = (await axios.get(`https://jsonplaceholder.typicode.com/users/${args.id}`)).data;
        return {
            id: id,
            name: name,
            email: email,
            login: username,
        } = users;
    } catch (error) {
        throw error;
    }
}

async function getRestUsersList() {
    try {
        const users = await axios.get("https://jsonplaceholder.typicode.com/users");
        return users.data.map(({ id, name, email, username }) => ({
            id: id,
            name: name,
            email: email,
            login: username,
        }))
    } catch (error) {
        throw error;
    }
}

async function getRestTodoList() {
    try {
        const todos = await axios.get("https://jsonplaceholder.typicode.com/todos");
        return todos.data.map(({ userId, id, title, completed }) => ({
            id: id,
            title: title,
            completed: completed,
            user_id: userId,
        }))
    } catch (error) {
        throw error;
    }
}


const resolvers = {
    Query: {
        demo: () => 'Witaj, GraphQL działa!',
        users: async () => getRestUsersList(),
        todos: async () => getRestTodoList(),
        todo: async (parent, args, context, info) => todoById(parent, args, context, info),
        user: async (parent, args, context, info) => userById(parent, args, context, info),
    },
    User: {
        todos: async (parent, args, context, info) => {
            const todos = await getRestTodoList();
            return todos.filter(t => t.user_id == parent.id);
        }
    },
    ToDoItem: {
        user: async (parent, args, context, info) => {
            return user = await userById(parent, { id: parent.userId }, context, info);
        }
    },
}


const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
});

server.start(() => console.log('Server is running on http://localhost:4000'));
