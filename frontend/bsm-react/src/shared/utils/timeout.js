/**
 * Helper useful to timeout async methods.
 *
 * @param {Number} timeoutTime Timeout time, in milliseconds.
 * @return {Promise} Timeout promise.
 */
export default function timeout(timeoutTime) {
    return new Promise((resolve) => setTimeout(resolve, timeoutTime));
}
