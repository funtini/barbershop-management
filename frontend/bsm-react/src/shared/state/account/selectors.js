import get from 'lodash/get';

export function getErrors(state) {
    return get(state, 'account.error');
}

export function getUser(state) {
    return get(state, 'account.user');
}

export function getUserDetails(state) {
    return get(state, 'account.details');
}

export function isUserAuthenticated(state) {
    return get(state, 'account.isAuthenticated');
}

export function isLoading(state) {
    return get(state, 'account.isLoading');
}