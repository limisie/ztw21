const { model, Schema } = require('mongoose');

const todoSchema = new Schema({
    title: String,
    completed: Boolean,
    user: {
        type: Schema.Types.ObjectId,
        ref: 'users'
    },
    createdAt: String
});

module.exports = model('Todo', todoSchema);