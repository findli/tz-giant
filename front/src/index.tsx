import * as React from "react";
import Layout from "./layout/Layout";

import {ApolloProvider} from "react-apollo";

import ApolloClient from 'apollo-client';
import {createHttpLink} from 'apollo-link-http';
import {ApolloLink, split} from 'apollo-link';
import {InMemoryCache} from 'apollo-cache-inmemory';

const httpLink = createHttpLink({
    uri: 'http://localhost:9000/graphql'
    , credentials: 'same-origin'
});
const cache = new InMemoryCache({
    dataIdFromObject: o => o.id
});
import {WebSocketLink} from 'apollo-link-ws';

const wsLink = new WebSocketLink({
    uri: `ws://localhost:9000/subscriptions`,
    options: {
        reconnect: true
    }
});

const middlewareLink = new ApolloLink((operation, forward) => {
    return forward(operation)
});

import {getMainDefinition} from 'apollo-utilities';

let split1 = split(
    ({query}: any) => {
        const {kind, operation}: any = getMainDefinition(query);
        return kind === 'OperationDefinition' && operation === 'subscription'
    },
    wsLink,
    httpLink,
)
const link = middlewareLink.concat(split1)

const client = new ApolloClient({
    link,
    cache,
})

import ReactDOM = require("react-dom")
import {BrowserRouter} from "react-router-dom"

ReactDOM.render(
// @ts-ignore
    <ApolloProvider client={client}><BrowserRouter><Layout/></BrowserRouter></ApolloProvider>,
    document.querySelector('#app-container')
)