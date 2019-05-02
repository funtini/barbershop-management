import * as actionTypes from './actionTypes';
import { setCookie } from 'shared/utils/cookieUtils';
import { post } from 'shared/utils/apiClient';

const initialState = {
    user: '',
    details: {},
    isAuthenticated: false,
    role: {
        isAdmin: false,
        isStoreManager: false,
        isEmployer: false,
    },
    isLoading: false
};

const loginStartLoad = (state) => {
    return {
        ...state,
        isLoading: true,
    }
};

const loginSuccessLoad = (state, action) => {
    console.log(action)
    return {
        ...state,
        isLoading: false,
        isAuthenticated: true,
        user: action.payload,
    }
};

const loginFailLoad = (state, action) => {
    return {
        ...state,
        isLoading: false,
        error: action.error
    }
};

const accountReducer = (state = initialState, action) => {
    switch (action.type) {
        case actionTypes.LOGIN_START:
            return loginStartLoad(state);
        case actionTypes.LOGIN_FAIL:
            return loginFailLoad(state, action);
        case actionTypes.LOGIN_SUCCESS:
            return loginSuccessLoad(state, action);
        default:
            return state
    }
};

export default accountReducer;
