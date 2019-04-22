import axios from 'axios';
import { setCookie } from './cookieUtils';
// import { buildUrlFromPattern } from './patternUtils';

export const API_BASE_URL = "http://" + window.location.hostname + ":8080";
export const ACCESS_TOKEN = 'accessToken';

axios.defaults.baseURL = API_BASE_URL;
axios.defaults.headers.post['Content-Type'] = 'application/json';
//
// export const apiRequest = (options) => {
//     const headers = new Headers({
//         "Content-Type": "application/json"
//     });
//
//     if (localStorage.getItem(ACCESS_TOKEN)) {
//         headers.append("Authorization", "Bearer " + localStorage.getItem(ACCESS_TOKEN));
//     }
//
//     const defaults = { headers: headers };
//     options = Object.assign({}, defaults, options);
//     console.log('IM requesting ON', API_BASE_URL);
//     return fetch(options.url, options).then(response =>
//         response.json().then(json => {
//             if (response.ok) {
//                 return json;
//             }
//             return Promise.reject(json);
//         })
//     );
// };
//
// export function listUsers() {
//     return apiRequest({
//         url: API_BASE_URL + "/api/users",
//         method: "GET"
//     });
// }
//
// export function login(loginRequest) {
//     return apiRequest({
//         url: API_BASE_URL + "/api/auth/signin",
//         method: "POST",
//         body: JSON.stringify(loginRequest)
//     });
// }

/**
 * Returns a http request.
 *
 * @param {Object} options              - Api request options.
 * @param {Object} [options.method]     - Request method (GET, POST, PUT or DELETE).
 * @param {Object} [options.url]        - Request url.
 * @param {Object} [options.data]       - Optional request payload.
 * @param {Object} [options.query]      - Optional request querystring values.
 * @param {Object} [options.headers]    - Optional request headers.
 *
 * @returns {Promise}                    Request promise.
 */
export function apiRequest({ method, url, data, query, headers }) {
    if (localStorage.getItem(ACCESS_TOKEN)) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`;
    }
    return axios({
        method,
        url,
        data,
        params: query,
        cache: false,
        headers: {
            ...headers,
        },
    });
}

export function listUsers() {
    return get('/api/users')
}

export function login(loginRequest) {
    setCookie('username', loginRequest.usernameOrEmail);
    return post(
        '/api/auth/signin',
        JSON.stringify(loginRequest)
    );
}

/**
 * Returns the response for an api GET request.
 *
 * @param {String} url       - Request url to be called.
 * @param {Object} data       - Optional Body of the request.
 * @param {Object} options     - Optional Get options.
 * @returns {Promise}                    Request promise.
 */
export function get(url, data = {}, options = {}) {
    return apiRequest({
        method: 'get',
        url,
        data,
        ...options });
}

/**
 * Returns the response for an api POST request.
 *
 * @param {String} url       - Request url to be called.
 * @param {Object} data       - Optional Body of the request.
 * @param {Object} options     - Optional Get options.
 * @returns {Promise}                    Request promise.
 */
export function post(url, data = {}, options = {}) {
    return apiRequest({
        method: 'post',
        url,
        data,
        ...options });
}

/**
 * Returns the response for an api PUT request.
 *
 * @param {String} url       - Request url to be called.
 * @param {Object} data       - Optional Body of the request.
 * @param {Object} options     - Optional Get options.
 * @returns {Promise}                    Request promise.
 */
export function put(url, data = {}, options = {}) {
    return apiRequest({
        method: 'put',
        url,
        data,
        ...options });
}

/**
 * Returns the response for an api DELETE request.
 *
 * @param {String} url       - Request url to be called.
 * @param {Object} data       - Optional Body of the request.
 * @param {Object} options     - Optional Get options.
 * @returns {Promise}                    Request promise.
 */
export function del(url, data = {}, options = {}) {
    return apiRequest({
        method: 'delete',
        url,
        data,
        ...options });
}
