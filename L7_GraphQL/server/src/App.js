const { GraphQLServer } = require('graphql-yoga');
const mongoose = require('mongoose');

const { MONGODB } = require('./config.js');
const typeDefs = './src/schema.graphql';
const resolvers = require('./resolvers');

const server = new GraphQLServer({
    typeDefs,
    resolvers,
    context: req => ({ req }),
});

const options = {
    port: 8000,
};

mongoose.connect(MONGODB, { useUnifiedTopology: true, useNewUrlParser: true })
    .then(() => {
        return server.start(options, ({ port }) =>
            console.log(
                `Server started, listening on port ${port} for incoming requests.`,
            ),
        )
    });



