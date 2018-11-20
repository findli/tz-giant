import * as React from 'react';
import {Route, Switch} from "react-router-dom";
import {graphql} from "react-apollo";
import query from "@src/layout/graphql/subscription/getLastBlockData";
import IndexPage from "@src/page/IndexPage";

class Layout extends React.Component {

    render() {
        return <Switch>
            // @ts-ignore
            <Route path="/" component={IndexPage}/>
            // @ts-ignore
            <Route component={Code404}/>
        </Switch>
    }
}

class Code404 extends React.Component {
    render() {
        return <div><h1>404: page not found. <a href="/">Home page.</a></h1>
            <br/>
            <p>The page you are looking for does not exist.</p>
        </div>
    }
}

export default graphql(query)(Layout)