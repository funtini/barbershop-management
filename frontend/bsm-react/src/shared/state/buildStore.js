import { createStore, applyMiddleware, compose, combineReducers } from 'redux';
import { connectRouter, routerMiddleware } from 'connected-react-router';
import { createBrowserHistory } from 'history';
import { responsiveStateReducer, responsiveStoreEnhancer, createResponsiveStateReducer } from 'redux-responsive';
import { reducer as formReducer } from 'redux-form'

// middleware
import thunk from 'redux-thunk';

// reducers
import counterReducer from './counter';
import layoutReducer from './layout';
import accountReducer from './account';
import viewportReducer from './viewport';

// Create an enhanced history that syncs navigation events with the store.
export const history = createBrowserHistory();

export default function buildStore(initialState) {
    const middlewares = [
        routerMiddleware(history),
        thunk,
    ];

    const rootReducer = (history) => combineReducers({
        count: counterReducer,
        form: formReducer,
        account: accountReducer,
        layout: layoutReducer,
        router: connectRouter(history),
        viewport: viewportReducer,
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

