import * as actionTypes from './actionTypes';
import { setCookie } from 'shared/utils/cookieUtils';
import { ACCESS_TOKEN, post, get } from 'shared/utils/apiClient';


/*
####  ACTION CREATORS #####
 */
const loginStart = () => {
    return {
        type: actionTypes.LOGIN_START
    };
};

const loginSuccess = (data) => {
    return {
        type: actionTypes.LOGIN_SUCCESS,
        payload: data,
    };
};

const loginFail = (error) => {
    return {
        type: actionTypes.LOGIN_FAIL,
        error:{
            api: error.response,
            custom: 'LOGIN FAILED',
        }
    };
};

const loadDetailsStart = () => {
    return {
        type: actionTypes.LOAD_DETAILS_START,
    }
};

const loadDetailsSuccess = (data) => {
    return {
        type: actionTypes.LOAD_DETAILS_SUCCESS,
        payload: data,
    }
};

const loadDetailsFail = (error) => {
    return {
        type: actionTypes.LOAD_DETAILS_SUCCESS,
        error:{
            api: error,
            custom: 'LOAD USER FAILED',
        }
    }
};

/**
 * Login action
 * Values object must have a structure similar to
 * {
 *     usernameOrEmail: 'username@mail.com',
 *     password: 'pa$$w0rd123!',
 * }
 *
 * @param {Object} values request payload
 * @returns {function} redux action
 */
export const login = (values) => async (dispatch) => {
    dispatch(loginStart());
    post('/api/auth/signin', JSON.stringify(values))
        .then(resp => {
            setCookie('username', values.usernameOrEmail,0.002);
            localStorage.setItem(ACCESS_TOKEN, resp.data.accessToken);
            dispatch(loginSuccess(values.usernameOrEmail))
        })
        .catch(err => {
            dispatch(loginFail(err))
        });
};

export const loadUserProfile = () => async (dispatch) => {
    dispatch(loadDetailsStart());
    await get('/api/users/me')
        .then(resp => {
            dispatch(loadDetailsSuccess(resp.data))
        })
        .catch(err => {
            dispatch(loadDetailsFail(err))
        })
};
