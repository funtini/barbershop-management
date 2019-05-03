import React, { Component } from 'react';
import { Route, Switch } from 'react-router';

// components
import Layout from './template/layout';
import DashBoard from './pages/dashboard';
import Products from './pages/products';

// styles
import './App.css';

// icons
import './shared/assets/icons';

// utils
import PrivateRoute from 'shared/utils/privateRoute';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            users: [],
        }
    }

    render() {
        return (
            <div>
                <Layout>
                    <Switch>
                        <PrivateRoute exact path="/" component={ DashBoard } />
                        <PrivateRoute path="/products" component={ Products } />
                        <PrivateRoute render={() => (<div>NOT FOUND PAGE</div>)} />
                    </Switch>
                </Layout>
            </div>
        );
    }
}

export default App;
