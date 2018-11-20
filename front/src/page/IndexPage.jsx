import * as React from 'react';
import {graphql, Subscription} from "react-apollo";
import subscription from "@src/layout/graphql/subscription/getLastBlockData";

class IndexPage extends React.Component {

    render() {
        return <div><Subscription
            subscription={subscription}
        >{({data, loading}) => {
            if (loading) return (<div/>)
            return (<div>
                <h4>last block data: </h4>
                num: {data.getLastBlockData.num}
                <br/>
                difficulty: {data.getLastBlockData.difficulty}
            </div>)
        }}
        </Subscription></div>
    }
}

export default graphql(subscription)(IndexPage)