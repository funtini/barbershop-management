import { createStore, applyMiddleware, compose, combineReducers } from 'redux';
import { connectRouter, routerMiddleware } from 'connected-react-router';
import { createBrowserHistory } from 'history';
import { responsiveStateReducer, responsiveStoreEnhancer, createResponsiveStateReducer } from 'redux-responsive';

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
        router: connectRouter(history),
        viewport: createResponsiveStateReducer({
            xs: 500,
            sm: 768,
            md: 1024,
            lg: 1280,
            xl: 1440,
        }),
    });

    // If Redux DevTools Extension is installed use it, otherwise use Redux compose.
    const composeEnhancers =
        typeof window === 'object' && process.env.NODE_ENV === 'development' && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ ?
            window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ :
            compose;

    return createStore(
        rootReducer(history),
        initialState,
        composeEnhancers(responsiveStoreEnhancer, applyMiddleware(...middlewares))
    );
}

