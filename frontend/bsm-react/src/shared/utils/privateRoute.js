import React from 'react'
import { connect } from 'react-redux';
import { compose } from 'redux';
import { get } from 'lodash';
import {
    Redirect,
    Route
} from 'react-router'

// state
import { loadUserProfile, getUserDetails } from 'shared/state/account';

// utils
import { ACCESS_TOKEN } from './apiClient';
import { getCookie } from 'shared/utils/cookieUtils';

const privateRoute = ({ component: Component, ...rest }) => (
    <Route { ...rest } render={ (props) => {
        if(!localStorage.getItem(ACCESS_TOKEN)){
            return (
                <Redirect to={{
                pathname: '/login',
                state: { from: props.location }
            }} />
            )
        }
        rest.loadUser();
        const username = get(rest,'details.email');

        return(
        username && getCookie(username) ?
            <Component {...props} /> :
            <Redirect to={{
                pathname: '/lockSession',
                state: { from: props.location }
            }} />
    )}} />
);

const mapDispatchToProps = (dispatch) => ({
    loadUser: () => dispatch(loadUserProfile())
});

const mapStateToProps = (state) => ({
    userDetails: getUserDetails(state),
});

export default connect(mapStateToProps,mapDispatchToProps)(privateRoute);
