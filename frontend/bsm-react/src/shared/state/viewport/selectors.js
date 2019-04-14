import get from 'lodash/get';

export function getCurrentBreakpoint(state) {
    return get(state, 'viewport.mediaType');
}

export function getCurrentOrientation(state) {
    return get(state, 'viewport.orientation');
}

export function istBreakpointLessThan(state, breakpoint) {
    return get(state, `viewport.lessThan.${ breakpoint }`);
}

export function isBreakpointGreaterThan(state, breakpoint) {
    return get(state, `viewport.greaterThan.${ breakpoint }`);
}

export function isBreakpoint(state, breakpoint){
    return get(state, `viewport.is.${ breakpoint }`);
}
