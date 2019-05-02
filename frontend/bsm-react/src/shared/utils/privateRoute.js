import React from 'react'
import {
    Redirect,
    Route
} from 'react-router'

import { ACCESS_TOKEN } from './apiClient';

const privateRoute = ({ component: Component, ...rest }) => (
    <Route { ...rest } render={ (props) => (
        localStorage.getItem(ACCESS_TOKEN) ?
            <Component {...props} /> :
            <Redirect to={{
                pathname: '/login',
                state: { from: props.location }
            }} />
    )} />
);

export default privateRoute;