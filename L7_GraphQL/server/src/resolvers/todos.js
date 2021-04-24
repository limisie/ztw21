const Todo = require('../models/todo.js');
const auth = require('../utils/auth');

module.exports = {
    Query: {
        demo: () => 'hello',
        todos: async () => getTodos(),
        todo: async (_, { id }) => getTodo(id),
    },
    Mutation: {
        createTodo: async (_, { title }, context) => createNewTodo(title, context),
        deleteTodo: async (_, { id }, context) => deleteTodo(id, context),
        completeTodo: async (_, { id }, context) => completeTodo(id, context),
    },
    User: {
        todos: async ({ id }) => getTodosByUser(id)
    }
};

const completeTodo = async (id, context) => {
    const user = auth(context);
    try {
        const todo = await getTodo(id);
        todo.completed = true;

        const completed = await todo.save();
        return completed;
    } catch (err) {
        throw new Error(err);
    }
}

const getTodosByUser = async (id) => {
    const todos = await getTodos();
    return todos.filter(u => u.user == id);
}

const createNewTodo = async (title, context) => {
    const user = auth(context);

    const newTodo = new Todo({
        title,
        completed: false,
        user: user.id,
        createdAt: new Date().toISOString(),
    });

    const todo = await newTodo.save();

    return todo;
}

const deleteTodo = async (id, context) => {
    const user = auth(context);

    try {
        const todo = await Todo.findById(id);
        if (user.id == todo.user) {
            await todo.delete();
            return 'Todo deleted successfully';
        } else {
            throw new Error('Todo not found');
        }
    } catch (err) {
        throw new Error(err);
    }

}

const getTodos = async () => {
    try {
        const todos = await Todo.find();
        return todos;
    } catch (err) {
        throw new Error(err);
    }
}

const getTodo = async (id) => {
    try {
        const todo = await Todo.findById(id);
        if (todo) {
            return todo;
        } else {
            throw new Error('Todo not found')
        }
    } catch (err) {
        throw new Error(err);
    }
}