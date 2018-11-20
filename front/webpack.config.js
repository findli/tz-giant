const webpack = require("webpack");
const path = require("path");

let entryPoint = "./src/index.tsx";
let config = {
    entry: [
        path.resolve(__dirname, entryPoint)
    ],
    output: {
        path: path.join(__dirname, "dist"),
        filename: "bundle.js"
    },
    devtool: "inline-source-map",
    resolve: {
        alias: {
            "@src": path.resolve(__dirname, 'src'),
        },
        extensions: ['.ts', '.tsx', '.js', '.jsx', ".json"],
        mainFields: ['browser', 'main']
    },

    plugins: [
        new webpack.NamedModulesPlugin(),
        new webpack.HotModuleReplacementPlugin(),
    ],
    module: {
        rules: [
            {
                test: /\.(graphql|gql)$/,
                exclude: /node_modules/,
                loader: ['graphql-tag/loader'],
            },
            {
                test: /\.[tj]sx?$/,
                use: [
                    {
                        loader: 'awesome-typescript-loader',
                    }
                ],
                exclude: path.resolve(__dirname, 'node_modules'),
                include: path.resolve(__dirname, "src"),
            },

            {
                enforce: "pre",
                test: /\.js$/,
                loader: "source-map-loader"
            },
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            }
        ]
    },
    target: 'web',
    devServer: {
        stats: {
            all: false,
            timings: true,
            warnings: false,
            errors: true
        },
        historyApiFallback: true,
        noInfo: false,
        port: 8080,
        compress:
            false,
        inline: true,
        hot:
            true,
        contentBase: path.join(__dirname, "dist"),
        overlay: true,
        host: "localhost",
    }
}
module.exports = (env, argv) => {
    return config
}