import { formatPattern, getParamNames } from 'react-router/lib/PatternUtils';

/**
 * Helper that builds an url from an url pattern for a given parameters object.
 * When the parameters object is not defined, the result will be calculated with
 * the parameters parsed from the currentPathname.
 *
 * @param {String} path - Example: /:subfolder/shopping/:productId.
 * @param {Object} pathParams - Object with the parameters to fill in the url.
 * @param {String} currentPathname - Current pathname.
 * @returns {String} The resulting url.
 */
export function buildUrlFromPattern(path, pathParams = {}, currentPathname) {
    const requiredParamNames = getParamNames(path);
    const haveAllRequiredParams = requiredParamNames.reduce((result, paramName) =>
        result && pathParams[paramName] !== undefined, true);

    if (haveAllRequiredParams) {
        return formatPattern(path, pathParams);
    }

    const currentParams = matchUrlParams(path, currentPathname);

    return formatPattern(path, { ...currentParams, ...pathParams });
}

/**
 * Parse the url parameters from a pathname, for a given an url pattern, only for the
 * substring of the given pathname that is matchable with the pattern.
 *
 * Example:
 *  Pattern = '/:lang/products/:productId'
 *  pathname = '/us/shopping/woman'
 *  return = { lang: 'us' }.
 *
 * @param {String} pattern - Example: /:subfolder/shopping/:productId.
 * @param {String} pathname - Pathname from which the parameters will be extracted.
 * @returns {Object} The resulting path parameters.
 */
export function matchUrlParams(pattern, pathname) {
    const matcher = /:([a-zA-Z_$][a-zA-Z0-9_$]*)|\*\*|\*|\(|\)/g;
    const params = {};

    let match = matcher.exec(pattern);
    let lastIndex = 0;

    while (match) {
        if (match.index !== lastIndex && match[1] &&
            (pattern.slice(lastIndex, match.index) !==
                pathname.slice(lastIndex, match.index))) {
            break;
        }

        params[match[1]] = pathname.slice(1).match(/([^/]+)/)[0];
        lastIndex = matcher.lastIndex;
        match = matcher.exec(pattern);
    }

    return params;
}

export { formatPattern };
