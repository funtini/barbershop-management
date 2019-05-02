import * as actionTypes from './actionTypes';
import { setCookie } from 'shared/utils/cookieUtils';
import { ACCESS_TOKEN, post, get} from 'shared/utils/apiClient';


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
        error: error,
    };
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
    setCookie('username', values.usernameOrEmail);
    post('/api/auth/signin', JSON.stringify(values))
        .then(resp => {
            console.log(resp)
            console.log(values)
            localStorage.setItem(ACCESS_TOKEN, resp.data.accessToken)
            dispatch(loginSuccess(values.usernameOrEmail))
        })
        .catch(err => {
            dispatch(loginFail(err))
        });
};

// export const userProfile = () => {
//     get('/api/users/me')
//         .then(resp => resp.data)
//         .catch(err => err)
// }
