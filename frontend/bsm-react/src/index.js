import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux'
import { ConnectedRouter } from 'connected-react-router'
import { Route, Switch } from 'react-router';
import * as serviceWorker from './serviceWorker';
import './i18n';
import buildStore, { history } from './shared/state/buildStore';

// components
import App from './App';
import Login from './pages/account/login';
import LockScreen from './pages/account/lock-screen'

// utils
import PrivateRoute from './shared/utils/privateRoute';

const store = buildStore({});

ReactDOM.render(
    <Provider store={ store }>
        <ConnectedRouter history={ history }>
            <Switch>
                <Route path='/login' component={ Login } />
                <Route path='/lockSession' component={ LockScreen } />
                <PrivateRoute path='/' component={ App } />
            </Switch>
        </ConnectedRouter>
    </Provider>,
    document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
