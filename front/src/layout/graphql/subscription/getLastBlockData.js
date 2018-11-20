import gql from 'graphql-tag';

export default gql`
    subscription {
        getLastBlockData {
            num
            difficulty
        }
    }
`;
