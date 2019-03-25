import { createStore, applyMiddleware, compose, combineReducers } from 'redux'
import { connectRouter, routerMiddleware } from 'connected-react-router'
import { createBrowserHistory } from 'history'

// middleware
import thunk from 'redux-thunk';

// reducers
import counterReducer from './counter';
import layoutReducer from './layout';

// Create an enhanced history that syncs navigation events with the store.
export const history = createBrowserHistory();

export default function buildStore(initialState) {
    const middlewares = [
        routerMiddleware(history),
        thunk,
    ];

    const rootReducer = (history) => combineReducers({
        count: counterReducer,
        layout: layoutReducer,
        router: connectRouter(history)
    });

    // If Redux DevTools Extension is installed use it, otherwise use Redux compose.
    const composeEnhancers =
        process.env.NODE_ENV === 'development' ?
            window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ : null || compose;

    const store = createStore(
        rootReducer(history),
        initialState,
        composeEnhancers(applyMiddleware(...middlewares))
    );

    return store;
}

