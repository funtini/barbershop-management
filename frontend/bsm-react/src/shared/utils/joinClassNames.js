/**
 * Join multiple classes when values are truthy
 *
 * @param {...string} classNames - Classes to join
 * @returns {string} classes joined
 */
export default function joinClassNames(...classNames) {
    return classNames
        .filter((value) => Boolean(value))
        .join(' ')
        .trim();
}

